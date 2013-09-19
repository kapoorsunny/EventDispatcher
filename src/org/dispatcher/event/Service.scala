package org.dispatcher.event

import scala.actors.Actor

import java.util.concurrent.atomic.AtomicBoolean

abstract class Service(name: String, stateModel: ServiceStateModelT) extends ServiceOperations with Actor{

  var terminationNotification = new AtomicBoolean(false)
  var conf: Configuration = new Configuration
  
  var startTime:Long=System.currentTimeMillis()	
  
  
  val stateChangeLock: Object = new Object
  
  def init(config: Configuration) {
    if (isInState(STATE.INITED)) return
    stateChangeLock.synchronized {
      if (enterState(STATE.INITED) != STATE.INITED) {
        setConfig(conf);
        try {
          serviceInit(config);
          if (isInState(STATE.INITED)) {
            //if the service ended up here during init,
            //notify the listeners
            notifyListeners(this);
          }
        } catch {
          case ex: Exception => stopQuietly(this)
        }
      }
    }
  }

  def getConfig(): Configuration = conf

   def start() {
    if (isInState(STATE.INITED)) return
    stateChangeLock.synchronized {
      if (stateModel.enterState(STATE.STARTED) != STATE.STARTED)
        try {
          startTime = System.currentTimeMillis();
          serviceStart();
          if (isInState(STATE.STARTED)) {

            println("Service " + getName() + " is started");

            notifyListeners(this);
          }
        } catch {
          case ex: Exception => stopQuietly(this)

        }
    }
  }

  def stop() {
    if (isInState(STATE.STOPPED)) return
    stateChangeLock.synchronized {
      if (enterState(STATE.STOPPED) != STATE.STOPPED) {
        try {
          serviceStop();
        } catch {
          case ex: Exception => throw ex

        } finally {
          //report that the service has terminated
          terminationNotification.set(true);
          terminationNotification.synchronized {
            terminationNotification.notifyAll();
          }
          //notify anything listening for events
          notifyListeners(this);
        }
      }
    }

  }

  def getServiceState(): STATE = {
    stateModel.getState()
  }
  def getStartTime(): Long = startTime

  def waitForServiceToStop(timeout: Long): Boolean = {
    var completed = terminationNotification.get();

    while (!completed) {
      try {
        terminationNotification.synchronized {
          terminationNotification.wait(timeout);
        }
        // here there has been a timeout, the object has terminated,
        // or there has been a spurious wakeup (which we ignore)
        completed = true;
      } catch {
        // interrupted; have another look at the flag
        case ex: InterruptedException => completed = terminationNotification.get();

      }
    }
    terminationNotification.get();
  }

  def enterState(newState: STATE): STATE = {
    //     assert stateModel != null : "null state in " + name + " " + this.getClass();
    var oldState: STATE = stateModel.enterState(newState);
    if (oldState != newState) {
      println("Service: " + getName() + " entered state " + getServiceState());
    }
    oldState;
  }

  def getName(): String = {
    name
  }

  def isInState(expected: STATE): Boolean = stateModel.isInState(expected)

  @throws(classOf[Exception])
  protected def serviceInit(conf: Configuration)
  @throws(classOf[Exception])
  protected def serviceStart()

  @throws(classOf[Exception])
  protected def serviceStop()

  protected def setConfig(conf: Configuration) {
    this.conf = conf;
  }

}