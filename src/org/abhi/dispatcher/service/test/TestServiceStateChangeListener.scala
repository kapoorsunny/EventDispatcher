package org.abhi.dispatcher.service.test

import org.abhi.dispatcher.service.ServiceStateChangeListener
import org.abhi.dispatcher.service.Service

class TestServiceStateChangeListener extends ServiceStateChangeListener{
	def stateChanged(service:Service){
	  println("Service "+ service.name+ "State changed called to "+ service.getServiceState)
	}
}