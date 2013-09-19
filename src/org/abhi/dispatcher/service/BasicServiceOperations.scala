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
  
  	override abstract def initService(config:Configuration){
  	  super.initService(config)
  	}
  	
	override abstract def stopService(){
	 super.stopService()
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