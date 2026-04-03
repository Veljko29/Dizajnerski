package geometry;

import java.awt.Color;
import java.awt.Graphics;

public class Rectangle extends Shape{
	private Point upperLeftPoint;
	private int width;
	private int height;
	
	public Rectangle() {
	}
	
	public Rectangle(Point upperLeftPoint, int width, int height) {
		this.upperLeftPoint = upperLeftPoint;
		setWidth(width);
		setHeight(height);
	}
	
	public Rectangle(Point upperLeftPoint, int width, int height, Color outlineColor, Color fillColor) {
		this(upperLeftPoint, width, height);
		this.color = outlineColor;
		this.fillColor = fillColor;
	}

	public Rectangle(Point upperLeftPoint, int width, int height, boolean selected) {
		this(upperLeftPoint, width, height);
		setSelected(selected);
	}
	
	public boolean equals(Object obj) {
		if (obj instanceof Rectangle) {
			Rectangle pomocna = (Rectangle) obj;
			if (this.upperLeftPoint.equals(pomocna.upperLeftPoint) && this.width == pomocna.width
					&& this.height == pomocna.height)
				return true;
			else
				return false;
		} else
			return false;
	}
	
	public void fill(Graphics g) {
		g.setColor(fillColor);
		g.fillRect(upperLeftPoint.getX() + 1, upperLeftPoint.getY() + 1, width - 1, height - 1);
	}
	
	public void draw(Graphics g) {
		g.setColor(color);
		g.drawRect(upperLeftPoint.getX(), upperLeftPoint.getY(), width, height);
		this.fill(g);
		if(this.isSelected()) {
			g.setColor(Color.blue);
			g.drawRect(this.upperLeftPoint.getX() - 2, this.upperLeftPoint.getY() - 2, 4, 4);
			g.drawRect(this.upperLeftPoint.getX() + this.width - 2, this.upperLeftPoint.getY() - 2, 4, 4);
			g.drawRect(this.upperLeftPoint.getX() - 2, this.upperLeftPoint.getY() + this.height - 2, 4, 4);
			g.drawRect(this.upperLeftPoint.getX() + this.width  - 2, this.upperLeftPoint.getY() + this.height - 2, 4, 4);
		}
	}
	public Rectangle clone(Rectangle rectangle) {
		rectangle.getUpperLeftPoint().setX(this.getUpperLeftPoint().getX());
		rectangle.getUpperLeftPoint().setY(this.getUpperLeftPoint().getY());
		rectangle.setHeight(this.getHeight());
		rectangle.setWidth(this.getWidth());
		rectangle.setColor(this.getColor());
		rectangle.setFillColor(this.getFillColor());
		return rectangle;
	}

	public int area() {
		return width * height;
	}

	public int circumference() {
		return 2 * (width + height);
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) throws IllegalArgumentException {
		if(width <= 0) {
			throw new IllegalArgumentException("Width must be positive!");
		}
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) throws IllegalArgumentException {
		if(height <= 0) {
			throw new IllegalArgumentException("Height must be positive!");
		}
		this.height = height;
	}
	public void setUpperLeftPoint(Point upperLeftPoint) {
		this.upperLeftPoint = upperLeftPoint;
	}

	public Point getUpperLeftPoint() {
		return upperLeftPoint;
	}

	public String toString() {
		return "Upper left point:" + upperLeftPoint + ", width =" + width + ",height = " + height;
	}
	
	public boolean contains(int x, int y) {
		return x >= upperLeftPoint.getX() && x <= this.getUpperLeftPoint().getX() + width
				&& y >= this.upperLeftPoint.getY() && y <= getUpperLeftPoint().getY() + this.getHeight();
	}

	public boolean contains(Point clickPoint) {
		return clickPoint.getX() >= upperLeftPoint.getX()
				&& clickPoint.getX() <= this.getUpperLeftPoint().getX() + width
				&& clickPoint.getY() >= this.upperLeftPoint.getY()
				&& clickPoint.getY() <= getUpperLeftPoint().getY() + this.getHeight();
	}
	@Override
	public void moveTo(int x, int y) {
	this.upperLeftPoint.moveTo(x, y);
		
	}
	@Override
	public void moveBy(int byX, int byY) {
		this.upperLeftPoint.moveBy(byX, byY);
		
	}
	@Override
	public int compareTo(Object obj) {
		if(obj instanceof Rectangle) {
			Rectangle shapeToCompare=(Rectangle)obj;
			return this.area()-shapeToCompare.area();
		}
		return 0;
	}
}

