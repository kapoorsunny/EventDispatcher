package org.abhi.dispatcher.service



trait Service extends ServiceOperations{

   def name:String
   def getServiceState():STATE 
   def isInState(state:STATE):Boolean


}