package org.abhi.dispatcher.service

import org.abhi.dispatcher.config.Configuration

trait BasicServiceOperations extends ServiceOperations {
  
  var listeners = List[ServiceStateChangeListener]()

  def notifyListeners(service: Service) {
    val callbacks = listeners.toArray
    for (l <- callbacks) {
      l.stateChanged(service)
    }
  }

  private def isListener(listener:ServiceStateChangeListener) = listeners.exists(_==listener)
  
  	override abstract def init(config:Configuration){
  	  super.init(config)
  	}
  	
	override abstract def stop(){
	 super.stop()
	}
	
	override abstract def stopQuietly(){
	  super.stopQuietly
	}
	
	override def registerServiceListener(listener:ServiceStateChangeListener){
		  if (!isListener(listener))  listeners = listener :: listeners
    
	}
	
    override def unregisterServiceListener( listener:ServiceStateChangeListener){
      listeners = listeners.filter(_!=listener)
    }

	override abstract def getStartTime():Long={
	  super.getStartTime
	}
  
}