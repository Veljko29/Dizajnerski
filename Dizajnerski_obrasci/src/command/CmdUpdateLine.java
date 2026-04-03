package command;

import geometry.Line;

public class CmdUpdateLine implements Command {
      private Line oldState;
      private Line newState;
      private Line original = new Line();
      public CmdUpdateLine(Line oldState, Line newState) {
    	  this.oldState = oldState;
    	  this.newState = newState;
      }
      
      
  	@Override
  	public void execute() {
  	       oldState.clone(original);  
  		   newState.clone(oldState);
  		}

  		@Override
  		public void unexecute() {
  			   original.clone(oldState);
  			
  		}
	@Override
	public String toString() {
		return "Update " + " " + this.oldState + " " + "=>" + " " + this.newState;
	}
	
}
