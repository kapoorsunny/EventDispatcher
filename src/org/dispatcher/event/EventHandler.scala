package org.dispatcher.event

trait EventHandler[T <: Event[Enumeration]] {
	def handle(event:T)
}