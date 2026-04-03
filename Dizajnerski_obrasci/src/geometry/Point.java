package geometry;

import java.awt.Color;
import java.awt.Graphics;

public class Point extends Shape {
		/*
		 * public int x; public int y; public boolean selected;
		 */
		private int x;
		private int y;
		
		public Point() {

		}

		public Point(int x, int y) {
			setX(x);
			setY(y);
		}
		
		public Point(int x, int y, Color color) {
			this(x, y);
			this.color = color;
		}

		public Point(int x, int y, boolean selected) {
			this(x, y);
			setSelected(selected);
		}
		public boolean equals(Object obj){
			if(obj instanceof Point){ 
				Point pomocna=(Point) obj; 
				if(this.x==pomocna.x && this.y==pomocna.y){
					return true;
				}
			}
			return false;
		}
		public boolean contains(int x, int y) {
			return this.distance(x, y) <= 2;
		}
		
		public boolean contains(Point clickPoint) {
			return distance(clickPoint.x, clickPoint.getY()) <= 2;
		}
		public double distance(int xPoint2, int yPoint2) {
			double dx = this.x - xPoint2;
			double dy = this.y - yPoint2;
			double d = Math.sqrt(dx * dx + dy * dy);
			return d;
		}
		public void draw (Graphics g){
			g.setColor(this.color);
			g.drawLine(x-2,y, x+2, y);
			g.drawLine(x,y-2, x, y+2);
			if(this.selected) {
				g.setColor(Color.BLUE);
				g.drawRect(x-2, y-2, 4, 4);
			}
		}

		public void setX (int x) throws IllegalArgumentException{
			if(x<0)
				throw new IllegalArgumentException("You must insert positive value!");
			this.x = x;
		}

		public int getX() {
			return this.x;
		}

		public int getY() {
			return y;
		}

		public void setY(int y) throws IllegalArgumentException{
				if(y<0)
					throw new IllegalArgumentException("You must insert positive value!");
			this.y = y;
		}

		public String toString(){ 
			return "("+x+" , "+y+")";                                                                                                                                                      
		}

		@Override
		public void moveTo(int x, int y) {
			this.x = x;
	
				setY(y);
			
		}

		@Override
		public void moveBy(int byX, int byY) {
			x += byX;
				setY(y + byY);
			
		}

		@Override
		public int compareTo(Object obj) {
			if(obj instanceof Point) {
				Point shapeToCompare=(Point)obj;
				return(int)( this.distance(0, 0)-shapeToCompare.distance(0, 0));
			}
			return 0;
		}
		public Point clone(Point point) {
			//point = new Point();
			point.setX(this.getX());
			point.setY(this.getY());
            point.setColor(this.getColor());		
			return point;

		}
}
