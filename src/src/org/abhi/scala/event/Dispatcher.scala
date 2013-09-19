package org.abhi.scala.event

trait Dispatcher extends Actor{

  def getEventHandler():EventHandler[Event]
  
  def register(eventType:Event, handler:EventHandler[Event])
	 
}