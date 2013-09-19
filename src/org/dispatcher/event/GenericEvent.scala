package org.dispatcher.event

class GenericEvent extends Event[Enumeration]{
  
  def getType():GenericEventType={
    this.getType
  }
 
  def getTimestamp():Long={
    System.currentTimeMillis()
  }
  
  override def toString():String={
	"Generic Event"	  
  }
  
}