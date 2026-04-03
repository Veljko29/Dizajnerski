package command;

import geometry.Point;
import geometry.Rectangle;

public class CmdUpdateRectangle implements Command {
	private Rectangle oldState;
    private Rectangle newState;
    private Rectangle original = new Rectangle(new Point(), 1, 1);
    public CmdUpdateRectangle(Rectangle oldState, Rectangle newState) {
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
