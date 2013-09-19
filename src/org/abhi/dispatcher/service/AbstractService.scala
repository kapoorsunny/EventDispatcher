package org.abhi.dispatcher.service

import java.util.concurrent.atomic.AtomicBoolean
import org.abhi.dispatcher.config.Configuration

trait AbstractService extends Service with StateModel{
	
	var config = new Configuration
	var startTime:Long=System.currentTimeMillis()	
	var terminationNotification = new AtomicBoolean(false)
	
    val stateChangeLock: Object = new Object
    
//    def name = "AbstractService"  
      
   def init(conf: Configuration) {
    if (isInState(STATE.INITED)) return
    stateChangeLock.synchronized {
      if (enterState(STATE.INITED) != STATE.INITED) {
        config = conf;
        try {
          serviceInit(config);
          if (isInState(STATE.INITED)) {
            //if the service ended up here during init,
            //notify the listeners
            notifyListeners(this);
          }
        } catch {
          case ex: Exception => stopQuietly()
        }
      }
    }
  }

  def start() {
    if (isInState(STATE.STARTED)) return
    stateChangeLock.synchronized {
      if (enterState(STATE.STARTED) != STATE.STARTED)
        try {
          startTime = System.currentTimeMillis();
          serviceStart();
          if (isInState(STATE.STARTED)) {

            println("Service " + name + " is started");

            notifyListeners(this);
          }
        } catch {
          case ex: Exception => stopQuietly()

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
          notifyListeners(this);
        }
      }
    }

  }
	
  
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
  
  
  def getServiceState(): STATE = {
    getState();
  }

  def setConfig(conf: Configuration) {
    this.config = conf;
  }
  
  def getStartTime():Long={
	 startTime
	}
  
 def stopQuietly(){
   stop()
 }
  
  @throws(classOf[Exception])
  def serviceInit(conf: Configuration)
  @throws(classOf[Exception])
  def serviceStart()

  @throws(classOf[Exception])
  def serviceStop()
}