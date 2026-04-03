package command;

import java.util.ArrayList;

import geometry.Shape;

public class DeselectShape implements Command {
	
	private Shape shape;
	private ArrayList<Shape> selectedShapeList = new ArrayList<Shape>();
	
	//public DeselectShape

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void unexecute() {
		// TODO Auto-generated method stub
		
	}

}
