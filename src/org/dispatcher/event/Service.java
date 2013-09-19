package org.dispatcher.event

import java.io.Closeable;

interface Service extends Closeable{

  class STATE(value: Int, statename: String) {
    /** Constructed but not initialized */
    val NOTINITED =  new STATE(0, "NOTINITED")

    /** Initialized but not started or stopped */
    val INITED = new STATE(1, "INITED")

    /** started and not stopped */
    val STARTED = new STATE(2, "STARTED")

    /** stopped. No further state transitions are permitted */
    val STOPPED = new STATE(3, "STOPPED")

    override def toString(): String = {
      statename
    }
    
  }
  
   def init(config:Configuration)
   def start()
   def stop()
   def close();
   def registerServiceListener(listener:ServiceStateChangeListener)
   def unregisterServiceListener( listener:ServiceStateChangeListener)
   def getName():String
   def getConfig():Configuration
   def getServiceState():STATE 
   def getStartTime():Long
   def isInState(state:STATE):Boolean
   def getFailureCause():Throwable
   def getFailureState():STATE
   def waitForServiceToStop(timeout:Long):Boolean
   def getBlockers():Map[String, String]
  
   
  
}