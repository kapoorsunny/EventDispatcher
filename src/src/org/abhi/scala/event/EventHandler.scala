package org.abhi.scala.event

trait EventHandler[T <: Event[Enumeration]] {
	def handle(event:T)
}