package org.abhi.dispatcher.service

import org.abhi.dispatcher.config.Configuration

trait ServiceOperations {
	def init(config:Configuration)
	def stop()
	def stopQuietly()
	def registerServiceListener(listener:ServiceStateChangeListener)
    def unregisterServiceListener( listener:ServiceStateChangeListener)
	def getStartTime():Long
	def notifyListeners(service: Service)
	
}