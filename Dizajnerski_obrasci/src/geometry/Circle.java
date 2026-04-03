 package geometry;
import java.awt.Color;
import java.awt.Graphics;

public class Circle extends Shape {
	protected Point center = new Point();
	protected int radius;
	public Circle() {

	}

	public Circle(Point center, int radius) {
		this.center = center;
		setRadius(radius);
	}
	
	public Circle(Point center, int radius, Color outlineColor, Color fillColor) {
		this(center, radius);  
	    this.color = outlineColor;  
	    this.fillColor = fillColor;
		  System.out.println("Circle Constructor with colors");
		    System.out.println("Parameter outlineColor: " + outlineColor);
		    System.out.println("Parameter fillColor: " + fillColor);
	   
	    System.out.println("this.color set to: " + this.color);
	    System.out.println("this.fillColor set to: " + this.fillColor);
	}

	public Circle(Point center, int radius, boolean selected) {
		this(center, radius);
		super.setSelected(selected);

		super.setSelected(selected); // ili setSelected(selected); 
	}
	public boolean equals(Object obj){
		if(obj instanceof Circle){ 
			Circle pomocna=(Circle) obj; 
			if(this.center==pomocna.center && this.radius==pomocna.radius){
				return true;
			}
		}
		return false;
	}
	
	public void fill(Graphics g) {
		g.setColor(this.fillColor);
		g.fillOval(this.getCenter().getX() - this.radius + 1, this.getCenter().getY() - this.radius + 1, radius * 2 - 2, radius * 2 - 2);
	}
	
	public void draw(Graphics g) {
		System.out.println("Drawing circle - color: " + color + ", fillColor: " + fillColor);
		g.setColor(this.color);
		g.drawOval(center.getX() - radius, center.getY() - radius, 
				2 * radius, 2 * radius);
		this.fill(g);
		if (this.isSelected()) {
			g.setColor(Color.BLUE);
			g.drawRect(this.center.getX() - 2, this.center.getY() - 2, 4, 4);
			g.drawRect(this.center.getX() - this.radius - 2, this.center.getY() - 2, 4, 4);
			g.drawRect(this.center.getX() + this.radius - 2, this.center.getY() - 2, 4, 4);
			g.drawRect(this.center.getX() - 2, this.center.getY() - this.radius - 2, 4, 4);
			g.drawRect(this.center.getX() - 2, this.center.getY() + this.radius - 2, 4, 4);;
		}
	}
    public Circle clone(Circle circle) {
    	//circle = new Circle();
    	circle.getCenter().setX(this.getCenter().getX());
    	circle.getCenter().setY(this.getCenter().getY());
    	try {
			circle.setRadius(this.getRadius());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	circle.setColor(this.getColor());
    	circle.setFillColor(this.getFillColor());
    	return circle;
    }
	public double area() {
		return radius * getRadius() * Math.PI;
	}

	public double circumference() {
		return 2 * radius * Math.PI;
	}

	public Point getCenter() {
		return center;
	}

	public void setCenter(Point center) {
		this.center = center;
	}

	public int getRadius() {
		return radius;
	}

	public void setRadius(int radius) throws IllegalArgumentException {
		if(radius <= 0){
			throw new IllegalArgumentException("Radius must be positive!");
		}
		this.radius = radius;
	}

	public String toString() {
		// Center=(x,y), radius= radius
		return "Center=" + center + ", radius=" + radius; 
	}
	public boolean contains(int x, int y) {
		return center.distance(x, y) <= radius;
	}

	public boolean contains(Point clickPoint) {
		return center.distance(clickPoint.getX(), clickPoint.getY()) <= radius;
	}

	@Override
	public void moveTo(int x, int y) {
		this.center.moveTo(x, y);
		
	}

	@Override
	public void moveBy(int byX, int byY) {
		this.center.moveBy(byX, byY);
		
	}

	@Override
	public int compareTo(Object obj) {
		if(obj instanceof Circle) {
			Circle shapeToCompare=(Circle)obj;
			return (int)(this.area()-shapeToCompare.area());
		}
		return 0;
	}
	}


