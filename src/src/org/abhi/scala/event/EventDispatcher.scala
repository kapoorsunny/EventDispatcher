package org.abhi.scala.event

import scala.collection.mutable.HashMap




class EventDispatcher(name: String, stateModel: ServiceStateModelT) extends Service(name: String, stateModel: ServiceStateModelT) with BasicServiceOperations with Dispatcher {

  var config: Configuration = new Configuration
  val eventDispatchers = new HashMap[Event, EventHandler[Event]] 
		  
  override def serviceInit(conf: Configuration) {
    println("serviceInit->Service initing")
    
  }

  override def serviceStart() {
    println("serviceStart->Service Starting")
  }

  override def serviceStop() {
    println("serviceStop->Serivce Stopping")
  }
  
   override def getEventHandler():EventHandler[Event]=new GenericEventHandler
  
   
   override def register(eventType:Event, handler:EventHandler[Event]){
     val registeredHandler = eventDispatchers.get(eventType)
     if(registeredHandler == None){
       eventDispatchers.put(eventType, handler);
     }
   }
   
   
   override def act(){
     react{
       
     }
   }
   
}

