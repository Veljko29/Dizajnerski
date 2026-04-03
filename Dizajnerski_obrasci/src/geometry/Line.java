package geometry;

import java.awt.Color;
import java.awt.Graphics;

public class Line extends Shape{
private Point startPoint = new Point();
private Point endPoint = new Point();
public Line() {
	
}
  public Line(Point startPoint, Point endPoint) {
	this.startPoint = startPoint;
	this.endPoint = endPoint;
 }

 public Line(Point startPoint, Point endPoint, Color color) {
	this(startPoint, endPoint);
	this.color = color;
}

 public Line(Point startPoint, Point endPoint, boolean selected) {
	//prvi nacin
	//this(startPoint, endPoint);
	//setSelected(selected);
	//drugi nacin
	super(selected);
	this.startPoint = startPoint;
	this.endPoint = endPoint;

 }
 public Line clone(Line line) {
	line.getStartPoint().setX(this.getStartPoint().getX());
	line.getStartPoint().setY(this.getStartPoint().getY());
	
	line.getEndPoint().setX(this.getEndPoint().getX());
	line.getEndPoint().setY(this.getEndPoint().getY());
	
	line.setColor(this.getColor());
	
	return line; 
 }
 public boolean equals(Object obj){
	if(obj instanceof Line){ 
		Line pomocna=(Line) obj; 
		if(this.startPoint==pomocna.startPoint && this.endPoint==pomocna.endPoint){
			return true;
		}
	}
	return false;
 }
 public double length() {
	return this.startPoint.distance (endPoint.getX(), getEndPoint().getY());  
 }
 public void draw(Graphics g) {
	g.setColor(color);
	g.drawLine(startPoint.getX(), startPoint.getY(),
			endPoint.getX(), this.endPoint.getY());
	if(this.isSelected()) {
		g.setColor(Color.BLUE);
		g.drawRect(this.startPoint.getX()-2, this.startPoint.getY()-2, 4, 4);
		g.drawRect(this.endPoint.getX()-2, this.endPoint.getY()-2, 4, 4);
	}
}
  public void setStartPoint(Point startPoint) {
	this.startPoint=startPoint;
}
 public Point getStartPoint() {
	 return this.startPoint;
 }
 public Point getEndPoint() {
	return endPoint;
}
 public void setEndPoint(Point endPoint) {
	this.endPoint = endPoint;
}
 public String toString() {
	return startPoint + "-- >" + endPoint;
}

public boolean contains(int x, int y) {
	return this.startPoint.distance(x, y) +
			this.endPoint.distance(x, y) - length() <= 2;
}

public boolean contains(Point clickPoint) {
	return this.startPoint.distance(clickPoint.getX(), clickPoint.getY()) +
			this.endPoint.distance(clickPoint.getX(), clickPoint.getY())
				- length() <= 2;
}
@Override
public void moveTo(int x, int y) {
	this.startPoint.moveTo(x, y);
	this.endPoint.moveTo(x, y);
	
}
@Override
public void moveBy(int byX, int byY) {
	this.startPoint.moveBy(byX, byY);
	this.endPoint.moveBy(byX, byY);	
	
}
@Override
public int compareTo (Object obj) {
	if(obj instanceof Line) {
		Line shapeToCompare=(Line)obj;
		return (int)(this.length() - shapeToCompare.length());
	}
	return 0;
}
}