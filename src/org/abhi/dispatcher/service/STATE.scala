package org.abhi.dispatcher.service

  class STATE(value: Int, statename: String) {

  
  override def toString(): String =  statename
  def getValue():Int= value
  
}

object STATE extends STATE(0, "NOTINITED"){
  val NOTINITED = new STATE(0, "NOTINITED")
  val INITED = new STATE(1, "INITED")
  val STARTED = new STATE(2, "STARTED")
  val STOPPED = new STATE(3, "STOPPED")

}

