package command;

import geometry.Point;

public class CmdUpdatePoint implements Command {
	  private Point oldState;
      private Point newState;
      private Point original = new Point();
      public CmdUpdatePoint(Point oldState, Point newState) {
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
