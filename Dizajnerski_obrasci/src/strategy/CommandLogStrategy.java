package strategy;

import mvc.DrawingModel;
import geometry.*;
import command.*;
import java.io.*;
import java.util.*;
import java.awt.Color;

import javax.swing.JOptionPane;

import adapter.HexagonAdapter;

public class CommandLogStrategy implements SaveStrategy {
    private List<Command> commandHistory = new ArrayList<>();
    private Map<String, Shape> shapeMap = new HashMap<>(); // Za čuvanje referenci
    
    @Override
    public void save(DrawingModel model, String filePath) throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
            for (Command cmd : commandHistory) {
                writer.println(cmd.toString());
            }
        }
    }
    
    @Override
    public DrawingModel load(String filePath) throws IOException {
        DrawingModel model = new DrawingModel();
        shapeMap.clear(); // Očisti mapu
        
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            int lineNumber = 0;
            
            while ((line = reader.readLine()) != null) {
                lineNumber++;
                
                if (line.trim().isEmpty()) continue;
                
                try {
                    Command command = parseCommand(line, model);
                    if (command != null) {
                        int response = JOptionPane.showConfirmDialog(null,
                            "Execute command: " + line + "?\n\nCommand " + lineNumber,
                            "Execute Command?",
                            JOptionPane.YES_NO_OPTION);
                        
                        if (response == JOptionPane.YES_OPTION) {
                            command.execute();
                            
                            // Sačuvaj referencu ako je AddShape komanda
                            if (command instanceof CmdAddShape) {
                                CmdAddShape addCmd = (CmdAddShape) command;
                                // Možeš koristiti hash code ili neki ID
                                String key = addCmd.getShape().toString();
                                shapeMap.put(key, addCmd.getShape());
                            }
                        }
                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null,
                        "Error parsing line " + lineNumber + ":\n" + line + "\n\n" + e.getMessage(),
                        "Parse Error",
                        JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        
        return model;
    }
    
    private Command parseCommand(String line, DrawingModel model) {
        // ADD COMMANDS
        if (line.startsWith("Add point:")) {
            Point point = parsePoint(line);
            return new CmdAddShape(model, point);
        }
        else if (line.startsWith("Add line:")) {
            Line lineObj = parseLine(line);
            return new CmdAddShape(model, lineObj);
        }
        else if (line.startsWith("Add rectangle:")) {
            Rectangle rect = parseRectangle(line);
            return new CmdAddShape(model, rect);
        }
        else if (line.startsWith("Add circle:")) {
            Circle circle = parseCircle(line);
            return new CmdAddShape(model, circle);
        }
        else if (line.startsWith("Add donut:")) {
            Donut donut = parseDonut(line);
            return new CmdAddShape(model, donut);
        }
        else if (line.startsWith("Add hexagon:")) {
            HexagonAdapter hexagon = parseHexagon(line);
            return new CmdAddShape(model, hexagon);
        }
        
        // REMOVE COMMANDS - PARSIRAMO IH!
        else if (line.startsWith("Remove ")) {
            // Format: "Remove Point[x=10,y=20]" ili "Remove Line[start=...]"
            String shapeStr = line.substring(7).trim(); // Skini "Remove "
            Shape shape = findShapeByString(shapeStr);
            if (shape != null) {
                return new CmdRemoveShape(model, shape);
            }
        }
        
        // UPDATE COMMANDS - PARSIRAMO IH!
        else if (line.startsWith("Update point:")) {
            // Format: "Update point: Point[x=10,y=20] => Point[x=15,y=25]"
            String[] parts = line.split("=>");
            if (parts.length == 2) {
                String oldStr = parts[0].replace("Update point:", "").trim();
                String newStr = parts[1].trim();
                
                Shape oldShape = findShapeByString(oldStr);
                Point newPoint = parsePoint("Add point: " + newStr);
                
                if (oldShape instanceof Point && newPoint != null) {
                    return new CmdUpdatePoint((Point)oldShape, newPoint);
                }
            }
        }
        else if (line.startsWith("Update line:")) {
            String[] parts = line.split("=>");
            if (parts.length == 2) {
                String oldStr = parts[0].replace("Update line:", "").trim();
                String newStr = parts[1].trim();
                
                Shape oldShape = findShapeByString(oldStr);
                Line newLine = parseLine("Add line: " + newStr);
                
                if (oldShape instanceof Line && newLine != null) {
                    return new CmdUpdateLine((Line)oldShape, newLine);
                }
            }
        }
        else if (line.startsWith("Update rectangle:")) {
            String[] parts = line.split("=>");
            if (parts.length == 2) {
                String oldStr = parts[0].replace("Update rectangle:", "").trim();
                String newStr = parts[1].trim();
                
                Shape oldShape = findShapeByString(oldStr);
                Rectangle newRect = parseRectangle("Add rectangle: " + newStr);
                
                if (oldShape instanceof Rectangle && newRect != null) {
                    return new CmdUpdateRectangle((Rectangle)oldShape, newRect);
                }
            }
        }
        else if (line.startsWith("Update circle:")) {
            String[] parts = line.split("=>");
            if (parts.length == 2) {
                String oldStr = parts[0].replace("Update circle:", "").trim();
                String newStr = parts[1].trim();
                
                Shape oldShape = findShapeByString(oldStr);
                Circle newCircle = parseCircle("Add circle: " + newStr);
                
                if (oldShape instanceof Circle && newCircle != null) {
                    return new CmdUpdateCircle((Circle)oldShape, newCircle);
                }
            }
        }
        else if (line.startsWith("Update donut:")) {
            String[] parts = line.split("=>");
            if (parts.length == 2) {
                String oldStr = parts[0].replace("Update donut:", "").trim();
                String newStr = parts[1].trim();
                
                Shape oldShape = findShapeByString(oldStr);
                Donut newDonut = parseDonut("Add donut: " + newStr);
                
                if (oldShape instanceof Donut && newDonut != null) {
                    return new CmdUpdateDonut((Donut)oldShape, newDonut);
                }
            }
        }
        else if (line.startsWith("Update hexagon:")) {
            String[] parts = line.split("=>");
            if (parts.length == 2) {
                String oldStr = parts[0].replace("Update hexagon:", "").trim();
                String newStr = parts[1].trim();
                
                Shape oldShape = findShapeByString(oldStr);
                HexagonAdapter newHex = parseHexagon("Add hexagon: " + newStr);
                
                if (oldShape instanceof HexagonAdapter && newHex != null) {
                    return new CmdUpdateHexagon((HexagonAdapter)oldShape, newHex);
                }
            }
        }
        
        // Z-ORDER COMMANDS
        else if (line.startsWith("To front ")) {
            String shapeStr = line.substring(9).trim();
            Shape shape = findShapeByString(shapeStr);
            if (shape != null) {
                return new CmdToFront(model, shape);
            }
        }
        else if (line.startsWith("To back ")) {
            String shapeStr = line.substring(8).trim();
            Shape shape = findShapeByString(shapeStr);
            if (shape != null) {
                return new CmdToBack(model, shape);
            }
        }
        else if (line.startsWith("Bring to front ")) {
            String shapeStr = line.substring(15).trim();
            Shape shape = findShapeByString(shapeStr);
            if (shape != null) {
                return new CmdBringToFront(model, shape);
            }
        }
        else if (line.startsWith("Bring to back ")) {
            String shapeStr = line.substring(14).trim();
            Shape shape = findShapeByString(shapeStr);
            if (shape != null) {
                return new CmdBringToBack(model, shape);
            }
        }
        
        return null;
    }
    
    // Metoda za pronalaženje oblika po string reprezentaciji
    private Shape findShapeByString(String shapeStr) {
        // Prvo pokušaj da nađeš tačan match
        for (Shape shape : shapeMap.values()) {
            if (shape.toString().equals(shapeStr)) {
                return shape;
            }
        }
        
        // Ako ne nađeš, probaj parsiranje
        if (shapeStr.startsWith("Point")) {
            return parsePoint("Add point: " + shapeStr);
        }
        else if (shapeStr.startsWith("Line")) {
            return parseLine("Add line: " + shapeStr);
        }
        else if (shapeStr.startsWith("Rectangle")) {
            return parseRectangle("Add rectangle: " + shapeStr);
        }
        else if (shapeStr.startsWith("Circle")) {
            return parseCircle("Add circle: " + shapeStr);
        }
        else if (shapeStr.startsWith("Donut")) {
            return parseDonut("Add donut: " + shapeStr);
        }
        else if (shapeStr.startsWith("Hexagon")) {
            return parseHexagon("Add hexagon: " + shapeStr);
        }
        
        return null;
    }
    
    // ==================== OSTALE PARSER METODE OSTAJU ISTE ====================
    // (parsePoint, parseLine, parseRectangle, parseCircle, parseDonut, parseHexagon,
    //  extractInt, extractColor, extractInnerColor - sve isto kao pre)
    
    private Point parsePoint(String line) {
        int x = extractInt(line, "x=");
        int y = extractInt(line, "y=");
        Color color = extractColor(line);
        
        Point point = new Point(x, y);
        if (color != null) point.setColor(color);
        return point;
    }
    
    private Line parseLine(String line) {
        int startX = extractInt(line, "start=Point[x=", ",", 1);
        int startY = extractIntAfter(line, "y=", ",", "start");
        int endX = extractInt(line, "end=Point[x=", ",", 1);
        int endY = extractIntAfter(line, "y=", "]", "end");
        Color color = extractColor(line);
        
        Point start = new Point(startX, startY);
        Point end = new Point(endX, endY);
        Line lineObj = new Line(start, end);
        if (color != null) lineObj.setColor(color);
        return lineObj;
    }
    
    private Rectangle parseRectangle(String line) {
        int x = extractInt(line, "Upper left point: Point[x=", ",", 1);
        int y = extractIntAfter(line, "y=", "]", "Upper left point");
        int width = extractInt(line, "width =");
        int height = extractInt(line, "height =");
        Color color = extractColor(line);
        Color innerColor = extractInnerColor(line);
        
        Point upperLeft = new Point(x, y);
        Rectangle rect = new Rectangle(upperLeft, width, height);
        if (color != null) rect.setColor(color);
        if (innerColor != null) rect.setFillColor(innerColor);
        return rect;
    }
    
    private Circle parseCircle(String line) {
        int x = extractInt(line, "Center: Point[x=", ",", 1);
        int y = extractIntAfter(line, "y=", "]", "Center");
        int radius = extractInt(line, "radius=");
        Color color = extractColor(line);
        Color innerColor = extractInnerColor(line);
        
        Point center = new Point(x, y);
        Circle circle = new Circle(center, radius);
        if (color != null) circle.setColor(color);
        if (innerColor != null) circle.setFillColor(innerColor);
        return circle;
    }
    
    private Donut parseDonut(String line) {
        int x = extractInt(line, "Center: Point[x=", ",", 1);
        int y = extractIntAfter(line, "y=", "]", "Center");
        int radius = extractInt(line, "radius=");
        int innerRadius = extractInt(line, "innerRadius=");
        Color color = extractColor(line);
        Color innerColor = extractInnerColor(line);
        
        Point center = new Point(x, y);
        Donut donut = new Donut(center, radius, innerRadius);
        if (color != null) donut.setColor(color);
        if (innerColor != null) donut.setFillColor(innerColor);
        return donut;
    }
    
    private HexagonAdapter parseHexagon(String line) {
        int x = extractInt(line, "x=");
        int y = extractInt(line, "y=", 1);
        int radius = extractInt(line, "radius=");
        Color color = extractColor(line);
        Color innerColor = extractInnerColor(line);
        
        HexagonAdapter hexagon = new HexagonAdapter(x, y, radius);
        if (color != null) hexagon.setColor(color);
        if (innerColor != null) hexagon.setFillColor(innerColor);
        return hexagon;
    }
    
    private int extractInt(String text, String prefix) {
        return extractInt(text, prefix, ",", 0);
    }
    
    private int extractInt(String text, String prefix, int offset) {
        return extractInt(text, prefix, ",", offset);
    }
    
    private int extractInt(String text, String prefix, String delimiter, int offset) {
        try {
            int start = text.indexOf(prefix) + prefix.length() + offset;
            int end = text.indexOf(delimiter, start);
            if (end == -1) end = text.indexOf("]", start);
            if (end == -1) end = text.length();
            return Integer.parseInt(text.substring(start, end).trim());
        } catch (Exception e) {
            return 0;
        }
    }
    
    private int extractIntAfter(String text, String prefix, String delimiter, String after) {
        try {
            int afterIndex = text.indexOf(after);
            int start = text.indexOf(prefix, afterIndex) + prefix.length();
            int end = text.indexOf(delimiter, start);
            if (end == -1) end = text.indexOf("]", start);
            return Integer.parseInt(text.substring(start, end).trim());
        } catch (Exception e) {
            return 0;
        }
    }
    
    private Color extractColor(String text) {
        try {
            int colorStart = text.indexOf("color=java.awt.Color[r=");
            if (colorStart == -1) return null;
            
            int r = extractInt(text.substring(colorStart), "r=", ",", 0);
            int g = extractInt(text.substring(colorStart), "g=", ",", 0);
            int b = extractInt(text.substring(colorStart), "b=", "]", 0);
            
            return new Color(r, g, b);
        } catch (Exception e) {
            return Color.BLACK;
        }
    }
    
    private Color extractInnerColor(String text) {
        try {
            int colorStart = text.indexOf("innerColor=java.awt.Color[r=");
            if (colorStart == -1) return null;
            
            int r = extractInt(text.substring(colorStart), "r=", ",", 0);
            int g = extractInt(text.substring(colorStart), "g=", ",", 0);
            int b = extractInt(text.substring(colorStart), "b=", "]", 0);
            
            return new Color(r, g, b);
        } catch (Exception e) {
            return Color.WHITE;
        }
    }
    
    public void setCommandHistory(List<Command> commandHistory) {
        this.commandHistory = commandHistory;
    }
}