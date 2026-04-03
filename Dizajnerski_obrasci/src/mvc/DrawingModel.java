package mvc;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JPanel;

//import adapter.HexagonAdapter;
import geometry.Point;
import geometry.Shape;

public class DrawingModel {
    private ArrayList<Shape> shapes = new ArrayList<Shape>();
    
    public DrawingModel() {
    }
    
   
    public ArrayList<Shape> getShapes() {
		return shapes;
	}
	public void add(Shape s) {
		shapes.add(s);
	}
	public void remove(Shape s) {
		shapes.remove(s);
	}
	public Shape getOneShape(int index) {
		return shapes.get(index);
	}
	
	public void addShape(Shape shape) {
		shapes.add(shape);
	}
	
	public boolean trySelect(Point p) {
		deselectAll(); //prvo deselektujemo sve oblike jer samo jedan moze biti selektovan
		for(int i = shapes.size() - 1; i >= 0; i--) {
			if(shapes.get(i).contains(p.getX(), p.getY())) {
				shapes.get(i).setSelected(true);
				return true;
			}
		}
		return false;
	}
	
	public void deselectAll() {
		for(int i = shapes.size() - 1; i >= 0; i--) {
			shapes.get(i).setSelected(false);
		}
	}
	
	public Shape getSelected() {
		for(int i = shapes.size() - 1; i >= 0; i--) {
			if(shapes.get(i).isSelected()) {
				return shapes.get(i);
			}
		}
		return null;
	}
	
	public void deleteSelected() {
		for(int i = shapes.size() - 1; i >= 0; i--) {
			if(shapes.get(i).isSelected()) {
				shapes.remove(i); 
				return;
			}
		}
	}
    
}