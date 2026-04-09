package mvc;

import java.util.ArrayList;
import java.util.List;

import geometry.Point;
import geometry.Shape;

public class DrawingModel implements DrawingObservable {
    private ArrayList<Shape> shapes = new ArrayList<Shape>();
    private List<DrawingObserver> observers = new ArrayList<>();
    
    public DrawingModel() {
    }
    
    // Observer metode
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
    
    // Osnovne metode
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
    
    public void deselectAll() {
        for(int i = shapes.size() - 1; i >= 0; i--) {
            shapes.get(i).setSelected(false);
        }
        notifyObservers();
    }
    
    public boolean trySelect(Point p, boolean ctrlPressed) {
        boolean shapeSelected = false;
        
        // Ako nije Ctrl pritisnut, prvo deselectuj sve
        if (!ctrlPressed) {
            deselectAll();
        }
        
        for (int i = shapes.size() - 1; i >= 0; i--) {
            Shape shape = shapes.get(i);
            if (shape.contains(p.getX(), p.getY())) {
                if (ctrlPressed) {
                    // Ctrl + klik = toggle selektovanje
                    shape.setSelected(!shape.isSelected());
                } else {
                    // Običan klik = selectuj samo ovaj
                    shape.setSelected(true);
                }
                shapeSelected = true;
                
                // Ako nije Ctrl, izađi posle prvog (single selection)
                if (!ctrlPressed) {
                    break;
                }
            }
        }
        
        if (shapeSelected) {
            notifyObservers();
        }
        return shapeSelected;
    }
    public List<Shape> getAllSelected() {
        List<Shape> selected = new ArrayList<>();
        for (Shape shape : shapes) {
            if (shape.isSelected()) {
                selected.add(shape);
            }
        }
        return selected;
    }
    
    public Shape getSelected() {
        for(int i = shapes.size() - 1; i >= 0; i--) {
            if(shapes.get(i).isSelected()) {
                return shapes.get(i);
            }
        }
        return null;
    }
    
    public int indexOf(Shape shape) {
        return shapes.indexOf(shape);
    }
    
    // Za višestruko selektovanje
    public List<Shape> getSelectedShapes() {
        List<Shape> selected = new ArrayList<>();
        for (Shape shape : shapes) {
            if (shape.isSelected()) {
                selected.add(shape);
            }
        }
        return selected;
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