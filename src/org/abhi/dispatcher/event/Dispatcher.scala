package org.abhi.dispatcher.event

trait Dispatcher {


  def getEventHandler(): EventHandler[Event]
  def register(evenType: Event, handler: EventHandler[Event])
}