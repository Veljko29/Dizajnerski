package command;

import geometry.Circle;
import geometry.Point;

public class CmdUpdateCircle implements Command {
	private Circle oldState;
    private Circle newState;
    private Circle original = new Circle(new Point(), 1);
    public CmdUpdateCircle(Circle oldState, Circle newState) {
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
		return "Update " + " " + this.original + " " + "=>" + " " + this.newState;
	}
}
