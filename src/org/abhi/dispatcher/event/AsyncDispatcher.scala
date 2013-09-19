package org.abhi.dispatcher.event

import org.abhi.dispatcher.service.AbstractService
import scala.actors.Actor
import org.abhi.dispatcher.config.Configuration
import org.abhi.dispatcher.service.BasicServiceOperations
import org.abhi.dispatcher.service.BasicServiceStateModel

class AsyncDispatcher(name:String) extends AbstractService with BasicServiceOperations with BasicServiceStateModel  with Dispatcher with Actor {
    
   @throws(classOf[Exception])
  protected def serviceInit(conf: Configuration)= {
     config=conf
   }
  @throws(classOf[Exception])
  protected def serviceStart(){
    
  }

  @throws(classOf[Exception])
  protected def serviceStop(){
    
  }
  
  override def name="AsyncDispatcher"
  
  
  def getEventHandler(): EventHandler[Event] = new GenericEventHandler
  
  def register(evenType: Event, handler: EventHandler[Event])={
    
  }
  
  
  override def act(){
    /*case e:Event => println("got it")
    case _ => ""*/
  }
  
  
  class GenericEventHandler extends EventHandler[Event] {
    def handle(event:Event){
      println("Generic Event is fired")
    }
  }
  
}