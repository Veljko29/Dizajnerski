package strategy;

import mvc.DrawingModel;
import geometry.Shape;
import java.io.*;
import java.util.ArrayList;

// Strategija 1: Serijalizacija (binary)
public class SerializationStrategy implements SaveStrategy {
    @Override
    public void save(DrawingModel model, String filePath) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream(filePath))) {
            oos.writeObject(model.getShapes());
        }
    }
    
    @Override
    public DrawingModel load(String filePath) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream(filePath))) {
            DrawingModel model = new DrawingModel();
            ArrayList<Shape> shapes = (ArrayList<Shape>) ois.readObject();
            for (Shape shape : shapes) {
                model.add(shape);
            }
            return model;
        }
    }
}