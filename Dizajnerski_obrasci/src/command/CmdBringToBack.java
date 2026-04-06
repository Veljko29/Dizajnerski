package command;

import geometry.Shape;
import mvc.DrawingModel;
public class CmdBringToBack implements Command {
	private Shape shape;
	private DrawingModel model;
	private int index;
	
	public CmdBringToBack(DrawingModel model, Shape shape) {
		this.shape = shape;
		this.model = model;
	}
	 @Override
	public void execute() {
		index = model.indexOf(shape);
		if(index > 0) {
		model.remove(shape);
		model.add(0, shape);
		}//samo na pocetak liste
	}
	 
	 @Override
	 public void unexecute() {
		 if (index >= 0 && index < model.getShapes().size() - 1) {
			model.remove(shape);
			model.add(index, shape);
		 }
	 }
	 
	 @Override
	 public String toString() {
		 return "Bring To back " + shape + "\n";
	 }
}
