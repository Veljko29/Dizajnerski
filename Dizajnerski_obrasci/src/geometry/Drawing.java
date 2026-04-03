package geometry;


import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Drawing extends JPanel {



	public static void main(String args[]) {
		JFrame frame = new JFrame("Drawing");
		frame.setSize(800, 600);
		Drawing drawingPanel = new Drawing();
		frame.getContentPane().add(drawingPanel);
		frame.setVisible(true);
	}
	
	public void paint(Graphics g) {
		Point p = new Point(300,300);
		p.draw(g);
		g.setColor(Color.red);
		Point startPoint=new Point(100,200);
		Line l = new Line(startPoint, new Point(300,400));
		l.draw(g);
		g.setColor(Color.black);
		Donut d = new Donut(new Point(350,450), 70, 50, true);
		d.draw(g);
		g.setColor(Color.blue);
		Rectangle pravug=new Rectangle(startPoint,60,70,true);
		pravug.draw(g);
		Point pocetna=new Point(400,500);
		Rectangle k1=new Rectangle(pocetna,50,50);
		k1.draw(g);
		Donut d1 = new Donut(new Point(425,525),80, (int)(50*1.41/2), true);
		d1.draw(g);
		Line l1=new Line(new Point(600,700),new Point(600,800));
		l1.draw(g);
		Line l2=new Line(new Point(550,725),new Point(650,725));
		l2.draw(g);
		
		//vezbe 8
		ArrayList <Shape> oblici=new ArrayList<Shape>();
		Point p1=new Point(300,400);
	    Circle c1=new Circle(new Point(200,300),60,true);
	    Donut d11=new Donut(new Point(100,300),60,40,true);
	    Line l11=new Line(new Point(150,550),p1,false);
	    Rectangle r1=new Rectangle(new Point(200,200),50,30,true);
	    ArrayList <Shape> oblici2=new ArrayList<Shape>();
	    oblici2.add(p1);
	    oblici2.add(l11);
	    oblici2.add(c1);
	    oblici2.add(d11);
	    oblici2.add(r1);
	    Iterator<Shape> it=oblici2.iterator();
	    //prvi zadatak vezbe 8
	    while(it.hasNext()){
	    	it.next().moveBy(10, 0);
	    	
	    }
	    //drugi zadatak
	    oblici2.get(3).draw(g);
	    oblici2.get(oblici2.size()-1).draw(g);
	    oblici2.remove(1); //pomerice se lista
	    oblici2.get(1).draw(g);
	    oblici2.get(3).draw(g);
	    oblici2.add(3,l1);
	    //treci
	    try {
			c1.setRadius(-10);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
	    System.out.println("Sve je ok");
	    System.out.println("4. zadataka");	
		it = oblici2.iterator();
		while (it.hasNext()) {
			Shape sh = it.next();
			sh.moveBy(100, 0);
			sh.setSelected(true);
			sh.draw(g);
		}
		//vezbe 9
	}	
}

