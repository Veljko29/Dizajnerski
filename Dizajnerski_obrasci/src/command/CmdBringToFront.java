package command;

import geometry.Shape;
import mvc.DrawingModel;
public class CmdBringToFront implements Command {
	private Shape shape;
	private DrawingModel model;
	private int index;
	private int currentIndex;
	
	public CmdBringToFront(DrawingModel model, Shape shape) {
		this.shape = shape;
		this.model = model;
	}
	 @Override
	public void execute() {
		index = model.indexOf(shape);
		 if (index < model.getShapes().size() - 1) {
		model.remove(shape);
		model.add(shape);//samo na kraj liste
		 }
	}
	 
	 @Override
	 public void unexecute() {
		  if (index > 0) {
			model.remove(shape);
			model.add(index, shape);
		  }
	 }
	 
	 @Override
	 public String toString() {
		 return "Bring To front " + shape + "\n";
	 }
}
