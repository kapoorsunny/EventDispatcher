package org.abhi.scala.event

import scala.collection.mutable.ListBuffer

abstract class Operations {
		
		def stop(service:Service)
		def stopQuietly(service:Service)
		def add(l:ServiceStateChangeListener)
		def remove(l:ServiceStateChangeListener)
		def reset()
		def notifyListeners(service:Service)
		
		
		
}