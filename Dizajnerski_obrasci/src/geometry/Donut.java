package geometry;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.AlphaComposite;

public class Donut extends Circle {
    private int innerRadius;

    public Donut() {}

    public Donut(Point center, int radius, int innerRadius) {
        super(center, radius);
        setInnerRadius(innerRadius);
    }
    
    public Donut(Point center, int radius, int innerRadius, Color outlineColor, Color fillColor) {
        this(center, radius, innerRadius);
        this.color = outlineColor;
        this.fillColor = fillColor;
    }

    public Donut(Point center, int radius, int innerRadius, boolean selected) {
        super(center, radius, selected);
        this.innerRadius = innerRadius;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Donut) {
            Donut other = (Donut) obj;
            return super.equals(other) && innerRadius == other.innerRadius;
        }
        return false;
    }
    
    @Override
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        int cx = getCenter().getX();
        int cy = getCenter().getY();
        int r  = getRadius();
        int ir = innerRadius;

        // Napravi "prsten" kao razliku spoljašnjeg i unutrašnjeg kruga
        java.awt.geom.Ellipse2D outer = new java.awt.geom.Ellipse2D.Double(cx - r,  cy - r,  r  * 2, r  * 2);
        java.awt.geom.Ellipse2D inner = new java.awt.geom.Ellipse2D.Double(cx - ir, cy - ir, ir * 2, ir * 2);

        java.awt.geom.Area donutArea = new java.awt.geom.Area(outer);
        donutArea.subtract(new java.awt.geom.Area(inner));

        // Popuni samo prsten
        g2d.setColor(getFillColor());
        g2d.fill(donutArea);

        // Iscrtaj obe ivice
        g2d.setColor(getColor());
        g2d.draw(outer);
        g2d.draw(inner);
        
        if (this.isSelected()) {
            g.setColor(Color.BLUE);
            g.drawRect(cx - 2, cy - 2, 4, 4);
            g.drawRect(cx - r - 2, cy - 2, 4, 4);
            g.drawRect(cx + r - 2, cy - 2, 4, 4);
            g.drawRect(cx - 2, cy - r - 2, 4, 4);
            g.drawRect(cx - 2, cy + r - 2, 4, 4);
        }
    }
    
    @Override
    /*public void fill(Graphics g) {
        // Prazno – sve je u draw()
    }
    
    public Donut clone(Donut donut) {
        donut.getCenter().setX(this.getCenter().getX());
        donut.getCenter().setY(this.getCenter().getY());
        try {
            donut.setRadius(this.getRadius());
        } catch (Exception e) {
            e.printStackTrace();
        }
        donut.setInnerRadius(this.getInnerRadius());
        donut.setColor(this.getColor());
        donut.setFillColor(this.getFillColor());
        return donut;
    }
    */
    public double area() {
        return super.area() - innerRadius * innerRadius * Math.PI;
    }

    public int getInnerRadius() {
        return innerRadius;
    }

    public void setInnerRadius(int innerRadius) {
        if (innerRadius <= 0) {
            throw new IllegalArgumentException("Inner radius must be positive!");
        }
        if (innerRadius >= getRadius()) {
            throw new IllegalArgumentException("Outer radius must be bigger than inner radius!");
        }
        this.innerRadius = innerRadius;
    }

    public String toString() {
        return super.toString() + ", innerRadius=" + innerRadius;
    }

    public boolean contains(int x, int y) {
        return super.contains(x, y) && getCenter().distance(x, y) >= innerRadius;
    }

    public boolean contains(Point p) {
        return contains(p.getX(), p.getY());
    }
}