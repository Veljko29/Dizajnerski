package adapter;

import java.awt.Color;
import java.awt.Graphics;

import hexagon.Hexagon;
import geometry.Shape;

public class HexagonAdapter extends Shape{

	private Hexagon hexagon;
	
	public HexagonAdapter(Hexagon hexagon) {
		this.hexagon = hexagon;
	}
	
	public HexagonAdapter(Hexagon hexagon, Color color, Color fillColor) {
		this.hexagon = hexagon;
		this.hexagon.setBorderColor(color);
		this.hexagon.setAreaColor(fillColor);
	}
	public HexagonAdapter(int x, int y, int radius) {
	    this.hexagon = new Hexagon(x, y, radius);
	}
	
	@Override
	public HexagonAdapter clone() {
	    try {
	        HexagonAdapter cloned = (HexagonAdapter) super.clone();
	        
	        cloned.hexagon = new Hexagon(this.getCenterX(), this.getCenterY(), this.getRadius());
	        cloned.hexagon.setBorderColor(this.getColor());
	        cloned.hexagon.setAreaColor(this.getFillColor());
	        
	        return cloned;
	    } catch (CloneNotSupportedException e) {
	        return null;
	    }
	}
	
	public Color getColor() {
		return hexagon.getBorderColor();
	}
	
	public Color getFillColor() {
		return hexagon.getAreaColor();
	}
	@Override
	public void setColor(Color color) {
		super.setColor(color);
		this.hexagon.setBorderColor(color);
	}
	@Override
	public void setFillColor(Color color) {
		super.setFillColor(color);
		this.hexagon.setAreaColor(color);
	}
	
	public int getCenterX() {
		return hexagon.getX();
	}
	
	public int getCenterY() {
		return hexagon.getY();
	}
	
	public int getRadius() {
		return hexagon.getR();
	}
	
	public void setCenterX(int x) {
		this.hexagon.setX(x);
	}
	
	public void setCenterY(int y) {
		this.hexagon.setY(y);
	}
	
	public void setRadius(int r) {
		this.hexagon.setR(r);
	}
	
	public HexagonAdapter clone(HexagonAdapter hexagon) {
		
		hexagon.setCenterX(this.getCenterX());
		hexagon.setCenterY(this.getCenterY());
		hexagon.setColor(this.getColor());
		hexagon.setFillColor(this.getFillColor());
		hexagon.setRadius(this.getRadius());
		
		return hexagon;
		
	}
	
	public boolean equals(Object obj) {
		if (obj instanceof HexagonAdapter) {
			HexagonAdapter pomocni = (HexagonAdapter) obj;
			if (this.getCenterX() == pomocni.getCenterX() && this.getRadius() == pomocni.getRadius()
					&& this.getCenterY() == pomocni.getCenterY() && this.getColor().equals(pomocni.getColor())
					&& this.getFillColor().equals(pomocni.getFillColor())) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	
	public String toString() {
		return "Hexagon->Center:" + "X=" + getCenterX() + ",Y=" + getCenterY() + ",radius=" + getRadius()
				+ " Color:" + getColor() + ",InnerColor:" + getFillColor();
	}
	
	@Override
	public void moveTo(int x, int y) {
		
	}

	@Override
	public void moveBy(int byX, int byY) {
		
	}

	@Override
	public int compareTo(Object arg0) {
		return 0;
	}

	@Override
	public boolean contains(int x, int y) {
		return hexagon.doesContain(x, y);
	}

	@Override
	public void draw(Graphics g) {
		hexagon.paint(g);
	}
	
	@Override
	public void setSelected(boolean selected) {
		hexagon.setSelected(selected);
	}
	
	@Override
	public boolean isSelected() {
		return hexagon.isSelected();
	}
}
