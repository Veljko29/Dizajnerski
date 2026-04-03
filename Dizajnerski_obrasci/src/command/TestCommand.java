package command;

import java.awt.Color;

import geometry.Line;
import geometry.Point;
import mvc.DrawingModel;

public class TestCommand {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DrawingModel model = new DrawingModel();
		Point p1 = new Point(10, 10, Color.RED);
		Point p2 = new Point(20, 20, Color.BLACK);
		Point p3 = new Point(30, 30, Color.RED);
		Point p4 = new Point(40, 40, Color.BLACK);
		
		
		/*// Testiranje dodavanja tačke
		CmdAddShape addPointCmd = new CmdAddShape(model, p1);
		
		addPointCmd.execute();
		System.out.println(model.getShapes().size()); // ---> 1
		//addPointCmd.unexecute();
		//System.out.println(model.getShapes().size()); // ---> 0
		
		CmdRemoveShape removePointCmd = new CmdRemoveShape(model, p1);
		removePointCmd.execute();
		System.out.println(model.getShapes().size()); // ---> 0
		removePointCmd.unexecute();
		System.out.println(model.getShapes().size());
		//CmdUpdatePoint cup = new CmdUpdatePoint(p1,p2);
		//cup.execute();
		//System.out.println(p1);
		//cup.unexecute();*/
		
		//System.out.println(p1);
		Line l1 = new Line (p1,p2,Color.GRAY);
		Line l2 = new Line(p3,p4);
	    l2	= l1.clone(l2);
		System.out.println(l2);
		CmdUpdateLine cul= new CmdUpdateLine(l1,l2);
		cul.execute();
		System.out.println(cul.toString());
		System.out.println(l1);
		CmdAddShape addLine = new CmdAddShape(model,l2);
		addLine.execute();
		System.out.println(model.getShapes().size());
		addLine.unexecute();
		System.out.println(model.getShapes().size());
		
		
		
		
		
	}

}
