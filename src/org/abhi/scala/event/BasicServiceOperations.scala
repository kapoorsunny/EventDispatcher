package org.abhi.scala.event

import scala.collection.mutable.ListBuffer

trait BasicServiceOperations extends ServiceOperations {
  
		val listeners = ListBuffer[ServiceStateChangeListener]()
			
		abstract override def stop(service:Service){super.stop(service)}
		abstract override def start(service:Service){super.start(service)}
	    abstract override def getName():String={super.getName}
		
		def stopQuietly(service:Service):Unit =	stopQuietly(service)
		
		def registerServiceListener(l:ServiceStateChangeListener){
		  if(!listeners.contains(l))  listeners +=  l
      }
	
		def unregisterServiceListener(l:ServiceStateChangeListener) = listeners -= l
		
		def reset()= listeners.clear
		
		def notifyListeners(service:Service)= for(l <- listeners.toList) l.stateChanged(service)
		
}