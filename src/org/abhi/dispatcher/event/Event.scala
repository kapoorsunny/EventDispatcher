package org.abhi.dispatcher.event

trait Event{

  def getType(): Event
  def getTimestamp(): Long;
  def toString(): String;

}



