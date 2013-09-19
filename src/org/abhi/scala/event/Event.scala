package org.abhi.scala.event

trait EventT[T <: Enumeration]{
	
  def getType():T
  def getTimestamp():Long;
  def toString():String;
  
}


class Event extends EventT[Enumeration]{
  
  def getType():Event={
    this
  }
  def getTimestamp():Long={
    System.currentTimeMillis()
  }
  def toString():String={
    "Event"
  }
  
  
}