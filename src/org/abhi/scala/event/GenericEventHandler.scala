package org.abhi.scala.event

class GenericEventHandler extends EventHandler[Event]{
	
	def handle(event:GenericEvent){
		println("Got generic event")
	}
}