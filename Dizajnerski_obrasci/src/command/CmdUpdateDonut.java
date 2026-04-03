package command;

import geometry.Donut;

public class CmdUpdateDonut implements Command{
	private Donut oldState;
    private Donut newState;
    private Donut original = new Donut();
    public CmdUpdateDonut(Donut oldState, Donut newState) {
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
		return "Update " +" "+ this.original + " " + "=>" + " " + this.newState;
	}
}
