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

public class DrawingModel implements DrawingObservable {
    private ArrayList<Shape> shapes = new ArrayList<Shape>();
    private List<DrawingObserver> observers = new ArrayList<>();
    
    public DrawingModel() {
    }
    
   
    public ArrayList<Shape> getShapes() {
		return shapes;
	}
	public void add(Shape s) {
		shapes.add(s);
		notifyObservers();
	}
	public void add(int index, Shape s) {
		shapes.add(index, s);
		notifyObservers();
	}
	public void remove(Shape s) {
		shapes.remove(s);
		notifyObservers();
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
		notifyObservers();
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
	public int indexOf(Shape shape) {
		return shapes.indexOf(shape);
	}
	@Override
	public void addObserver(DrawingObserver observer) {
		observers.add(observer);
		
	}
	@Override
	public void removeObserver(DrawingObserver observer) {
		observers.remove(observer);
		
	}

	@Override
	public void notifyObservers() {
		 for (DrawingObserver observer : observers) {
	            observer.update();
	        }
		
	}
	// Za Z-order provere
    public boolean canMoveForward(Shape shape) {
        if (shape == null || shapes.size() < 2) return false;
        int index = shapes.indexOf(shape);
        return index >= 0 && index < shapes.size() - 1;
    }
    
    public boolean canMoveBackward(Shape shape) {
        if (shape == null || shapes.size() < 2) return false;
        int index = shapes.indexOf(shape);
        return index > 0;
    }
    
}