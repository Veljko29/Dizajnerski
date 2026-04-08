package strategy;

import mvc.DrawingModel;
import geometry.*;
import adapter.HexagonAdapter;
import java.io.*;
import java.awt.Color;
import java.util.List;

public class TextLogStrategy implements SaveStrategy {
    
    @Override
    public void save(DrawingModel model, String filePath) throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
            List<Shape> shapes = model.getShapes();
            for (Shape shape : shapes) {
                writer.println(shapeToString(shape));
            }
        }
    }
    
    private String shapeToString(Shape shape) {
        if (shape instanceof Point) {
            Point p = (Point) shape;
            return String.format("Point:%d,%d,%d,%d,%d", 
                p.getX(), p.getY(),
                p.getColor().getRed(), p.getColor().getGreen(), p.getColor().getBlue());
        }
        else if (shape instanceof Line) {
            Line l = (Line) shape;
            return String.format("Line:%d,%d,%d,%d,%d,%d,%d",
                l.getStartPoint().getX(), l.getStartPoint().getY(),
                l.getEndPoint().getX(), l.getEndPoint().getY(),
                l.getColor().getRed(), l.getColor().getGreen(), l.getColor().getBlue());
        }
        else if (shape instanceof Rectangle) {
            Rectangle r = (Rectangle) shape;
            return String.format("Rectangle:%d,%d,%d,%d,%d,%d,%d,%d,%d,%d",
                r.getUpperLeftPoint().getX(), r.getUpperLeftPoint().getY(),
                r.getWidth(), r.getHeight(),
                r.getColor().getRed(), r.getColor().getGreen(), r.getColor().getBlue(),
                r.getFillColor().getRed(), r.getFillColor().getGreen(), r.getFillColor().getBlue());
        }
        else if (shape instanceof Donut) {
            Donut d = (Donut) shape;
            return String.format("Donut:%d,%d,%d,%d,%d,%d,%d,%d,%d,%d",
                d.getCenter().getX(), d.getCenter().getY(),
                d.getRadius(), d.getInnerRadius(),
                d.getColor().getRed(), d.getColor().getGreen(), d.getColor().getBlue(),
                d.getFillColor().getRed(), d.getFillColor().getGreen(), d.getFillColor().getBlue());
        }
        else if (shape instanceof HexagonAdapter) {
            HexagonAdapter h = (HexagonAdapter) shape;
            return String.format("Hexagon:%d,%d,%d,%d,%d,%d,%d,%d,%d",
                h.getCenterX(), h.getCenterY(), h.getRadius(),
                h.getColor().getRed(), h.getColor().getGreen(), h.getColor().getBlue(),
                h.getFillColor().getRed(), h.getFillColor().getGreen(), h.getFillColor().getBlue());
        }
        else if (shape instanceof Circle) {
            Circle c = (Circle) shape;
            return String.format("Circle:%d,%d,%d,%d,%d,%d,%d,%d,%d",
                c.getCenter().getX(), c.getCenter().getY(),
                c.getRadius(),
                c.getColor().getRed(), c.getColor().getGreen(), c.getColor().getBlue(),
                c.getFillColor().getRed(), c.getFillColor().getGreen(), c.getFillColor().getBlue());
        }
        return "";
    }
    
    @Override
    public DrawingModel load(String filePath) throws IOException {
        DrawingModel model = new DrawingModel();
        
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
                
                Shape shape = parseShape(line);
                if (shape != null) {
                    model.add(shape);
                }
            }
        }
        
        return model;
    }
    
    private Shape parseShape(String line) {
        String[] parts = line.split(",");
        String type = parts[0].split(":")[0];
        
        try {
            if (type.equals("Point")) {
                int x = Integer.parseInt(parts[0].split(":")[1]);
                int y = Integer.parseInt(parts[1]);
                int r = Integer.parseInt(parts[2]);
                int g = Integer.parseInt(parts[3]);
                int b = Integer.parseInt(parts[4]);
                
                Point p = new Point(x, y);
                p.setColor(new Color(r, g, b));
                return p;
            }
            else if (type.equals("Line")) {
                int x1 = Integer.parseInt(parts[0].split(":")[1]);
                int y1 = Integer.parseInt(parts[1]);
                int x2 = Integer.parseInt(parts[2]);
                int y2 = Integer.parseInt(parts[3]);
                int r = Integer.parseInt(parts[4]);
                int g = Integer.parseInt(parts[5]);
                int b = Integer.parseInt(parts[6]);
                
                Line newLine = new Line(new Point(x1, y1), new Point(x2, y2));
                newLine.setColor(new Color(r, g, b));
                return newLine;
            }
            else if (type.equals("Rectangle")) {
                int x = Integer.parseInt(parts[0].split(":")[1]);
                int y = Integer.parseInt(parts[1]);
                int width = Integer.parseInt(parts[2]);
                int height = Integer.parseInt(parts[3]);
                int r = Integer.parseInt(parts[4]);
                int g = Integer.parseInt(parts[5]);
                int b = Integer.parseInt(parts[6]);
                int fr = Integer.parseInt(parts[7]);
                int fg = Integer.parseInt(parts[8]);
                int fb = Integer.parseInt(parts[9]);
                
                Rectangle rect = new Rectangle(new Point(x, y), width, height);
                rect.setColor(new Color(r, g, b));
                rect.setFillColor(new Color(fr, fg, fb));
                return rect;
            }
            else if (type.equals("Circle")) {
                int x = Integer.parseInt(parts[0].split(":")[1]);
                int y = Integer.parseInt(parts[1]);
                int radius = Integer.parseInt(parts[2]);
                int r = Integer.parseInt(parts[3]);
                int g = Integer.parseInt(parts[4]);
                int b = Integer.parseInt(parts[5]);
                int fr = Integer.parseInt(parts[6]);
                int fg = Integer.parseInt(parts[7]);
                int fb = Integer.parseInt(parts[8]);
                
                Circle circle = new Circle(new Point(x, y), radius);
                circle.setColor(new Color(r, g, b));
                circle.setFillColor(new Color(fr, fg, fb));
                return circle;
            }
            else if (type.equals("Donut")) {
                int x = Integer.parseInt(parts[0].split(":")[1]);
                int y = Integer.parseInt(parts[1]);
                int radius = Integer.parseInt(parts[2]);
                int innerRadius = Integer.parseInt(parts[3]);
                int r = Integer.parseInt(parts[4]);
                int g = Integer.parseInt(parts[5]);
                int b = Integer.parseInt(parts[6]);
                int fr = Integer.parseInt(parts[7]);
                int fg = Integer.parseInt(parts[8]);
                int fb = Integer.parseInt(parts[9]);
                
                Donut donut = new Donut(new Point(x, y), radius, innerRadius);
                donut.setColor(new Color(r, g, b));
                donut.setFillColor(new Color(fr, fg, fb));
                return donut;
            }
            else if (type.equals("Hexagon")) {
                int x = Integer.parseInt(parts[0].split(":")[1]);
                int y = Integer.parseInt(parts[1]);
                int radius = Integer.parseInt(parts[2]);
                int r = Integer.parseInt(parts[3]);
                int g = Integer.parseInt(parts[4]);
                int b = Integer.parseInt(parts[5]);
                int fr = Integer.parseInt(parts[6]);
                int fg = Integer.parseInt(parts[7]);
                int fb = Integer.parseInt(parts[8]);
                
                HexagonAdapter hexagon = new HexagonAdapter(x, y, radius);
                hexagon.setColor(new Color(r, g, b));
                hexagon.setFillColor(new Color(fr, fg, fb));
                return hexagon;
            }
        } catch (Exception e) {
            System.err.println("Error parsing shape: " + line);
            e.printStackTrace();
        }
        
        return null;
    }
}