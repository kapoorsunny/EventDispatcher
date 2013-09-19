package org.abhi.dispatcher.service

trait ServiceStateChangeListener {
	def stateChanged(service:Service)
}