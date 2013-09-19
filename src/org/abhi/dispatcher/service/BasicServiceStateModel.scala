package org.abhi.dispatcher.service

trait BasicServiceStateModel extends StateModel {
  
   def name:String
	
	var state = STATE.NOTINITED
   
  var statemap = Array(
          Array(false, true,  false,  true),
          Array(false, true,  true,   true),
          Array(false, false, true,   true),
          Array(false, false, false,  true)
    )


  override def getState(): STATE = {
    state
  }
  override def isInState(proposed: STATE): Boolean = {
    state == proposed
  }
  
  @throws(classOf[Exception])
  override def ensureCurrentState(expectedState: STATE) {
    if (state != expectedState) {
      throw new Exception(name + ": for this operation, the " +
        "current service state must be "
        + expectedState
        + " instead of " + state);
    }
  }
  
  override def enterState(proposed:STATE):STATE={
    checkStateTransition(name, state, proposed)
    var oldState = state;
    state = proposed;
    oldState;
  }
   
  @throws(classOf[Exception])
  override def checkStateTransition(name:String, state:STATE, proposed:STATE) {
    if (!isValidStateTransition(state, proposed)) {
      throw new Exception(name + " cannot enter state "
                                      + proposed + " from state " + state);
    }
  }
  
 
   override def isValidStateTransition(current:STATE,  proposed:STATE):Boolean={
    val row = statemap(current.getValue());
    row(proposed.getValue());
  }
  
    override def toString():String= {
    	(name + ": " + state.toString())
  }
  
}