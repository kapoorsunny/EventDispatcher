package org.abhi.dispatcher.service.test

import org.abhi.dispatcher.config.Configuration
import org.abhi.dispatcher.service.AbstractService
import org.abhi.dispatcher.service.BasicServiceOperations
import org.abhi.dispatcher.service.BasicServiceStateModel

class TestService  extends AbstractService with BasicServiceOperations with BasicServiceStateModel {
  
  override def name = "TestService"
    
  @throws(classOf[Exception])
 override  def serviceInit(conf: Configuration){
    println("Service Initing")
  }
   @throws(classOf[Exception])
 override def serviceStart(){
    println("Service starting")
  }

  @throws(classOf[Exception])
  override def serviceStop(){
    println("Service stoping")
  }
}

object TestService extends TestService{
  def main(args: Array[String]) {
   val config =  new Configuration
   registerServiceListener(new TestServiceStateChangeListener)
  init(config)
  start
  stop
}
}