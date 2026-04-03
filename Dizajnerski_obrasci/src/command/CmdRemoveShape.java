package command;

import geometry.Point;
import geometry.Shape;
import mvc.DrawingModel;

public class CmdRemoveShape implements Command {
	private DrawingModel model;
	private Shape shape;
    

	public CmdRemoveShape(DrawingModel model, Shape shape) {
	  this.model = model;
	  this.shape = shape;
	}
	 @Override
	 public void execute() {
		model.remove(shape);
	 }
	 
	 @Override
	 public void unexecute() {
		model.add(shape);
	 }
}
