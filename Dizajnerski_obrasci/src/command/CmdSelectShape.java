package command;

import java.util.ArrayList;

import geometry.Shape;
import mvc.DrawingController;

public class CmdSelectShape implements Command {
    DrawingController controller;
	private Shape shape;
    ArrayList<Shape> selectedShape = new ArrayList<Shape>();
    public CmdSelectShape (DrawingController controller, ArrayList<Shape> selectedShape) {
    	this.controller = controller;
    	this.selectedShape=selectedShape;
    }
	@Override
	public void execute() {
		 shape.setSelected(true);
		
	}
	@Override
	public void unexecute() {
		shape.setSelected(false);
	}
	@Override
	public String toString() {
		return "Selected " + this.shape + "\n";
	}
    
}
