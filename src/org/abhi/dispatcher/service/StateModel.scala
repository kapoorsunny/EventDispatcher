package org.abhi.dispatcher.service

trait StateModel {
    
    def getServiceState():STATE
    def isInState(proposed:STATE):Boolean
    def ensureCurrentState(expectedState:STATE)
	def enterState(proposed:STATE):STATE
	def checkStateTransition(name:String, state:STATE, proposed:STATE)
	def isValidStateTransition(current:STATE, proposed:STATE):Boolean
	
}