package geometry;

import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;

public abstract class Shape implements Moveable,Comparable, Serializable, Cloneable {
	protected boolean selected;
	protected Color color;
	protected Color fillColor;
	
	public Color getFillColor() {
		return fillColor;
	}

	public void setFillColor(Color fillColor) {
		this.fillColor = fillColor;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public Shape() {
	
	}
	
	public Shape(boolean selected){};
	
	public Shape(boolean selected, Color color) {
		this(selected);
		this.color = color;
	}
	
	public Shape(boolean selected, Color color, Color fillColor) {
		this(selected, color);
		this.fillColor = fillColor;
	}
	
	public boolean isSelected() {
		return selected;
	}
	
	public abstract boolean contains(int x,int y);
	
	public abstract void draw(Graphics g);

	public void setSelected(boolean selected) {
		this.selected = selected;
	}	

}
