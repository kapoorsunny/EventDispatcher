package org.abhi.dispatcher.event

trait EventHandler[T <: Event]{
 def handle(event:T)
}
