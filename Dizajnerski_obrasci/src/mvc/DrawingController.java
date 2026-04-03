package mvc;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Stack;

import javax.swing.JOptionPane;

import command.Command;
import geometry.Circle;
import geometry.Donut;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import geometry.Shape;
public class DrawingController {
	private DrawingModel model;
	private DrawingFrame frame;
	private DrawingView view;
	
	private Point startPoint = null;
	private Color color = Color.black;
	private Color fillColor = Color.white;
	private ArrayList <Shape> selectedShapes = new ArrayList<Shape>();
	
	 public void setFrame(DrawingFrame frame) {
	        this.frame = frame;
	    }
	public DrawingController(DrawingModel model, DrawingFrame frame, DrawingView view) {
		this.model = model;
		this.frame = frame;
		this.view = frame.getView();
		view.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				Point clickPosition;
				try {
					clickPosition = new Point(e.getX(), e.getY());
				} catch(IllegalArgumentException exception) {
					JOptionPane.showMessageDialog(null, exception.getMessage());
					return;
				}
				if(frame.getTglBtnSelect().isSelected()) {
					if(model.trySelect(clickPosition)) {
						frame.getBtnModify().setEnabled(true);
						frame.getBtnDelete().setEnabled(true);
					} else {
						frame.getBtnModify().setEnabled(false);
						frame.getBtnDelete().setEnabled(false);
					}
					return;
				}
			
				if(frame.getTglBtnPoint().isSelected()) {
					DlgPoint dlgPoint = new DlgPoint();
					dlgPoint.setPoint(clickPosition);
					dlgPoint.setColor(color);
					dlgPoint.setVisible(true);
					if(dlgPoint.getPoint() != null) {
						model.addShape(dlgPoint.getPoint());
					}
					startPoint = null;
				}
				
				else if(frame.getTglBtnLine().isSelected()) {
					if (startPoint == null) {
						startPoint = clickPosition;
						return;
					}
					else {
						DlgLine dlgLine = new DlgLine();
						dlgLine.setLine(startPoint, clickPosition);
						dlgLine.setColor(color);
						dlgLine.setVisible(true);
						if(dlgLine.getLine() != null) {
							model.addShape(dlgLine.getLine());
						}
						startPoint = null;
					}					
				}
				else if(frame.getTglBtnRectangle().isSelected()) {
					DlgRectangle dlgRectangle = new DlgRectangle();
					dlgRectangle.setPoint(clickPosition);
					dlgRectangle.setColors(color, fillColor);
					dlgRectangle.setVisible(true);
					if(dlgRectangle.getRectangle() != null) {
						model.addShape(dlgRectangle.getRectangle());
					}
					startPoint = null;
				}
				

				else if(frame.getTglBtnCircle().isSelected()) {
					DlgCircle dlgCircle = new DlgCircle();
					dlgCircle.setPoint(clickPosition);
					dlgCircle.setColors(color, fillColor);
					dlgCircle.setVisible(true);
					if(dlgCircle.getCircle() != null) {
						model.addShape(dlgCircle.getCircle());
					}
					startPoint = null;
				}
				
				else if(frame.getTglBtnDonut().isSelected()) {
					DlgDonut dlgDonut = new DlgDonut();
					dlgDonut.setPoint(clickPosition);
					dlgDonut.setColors(color, fillColor);
					dlgDonut.setVisible(true);
					if(dlgDonut.getDonut() != null) {
						model.addShape(dlgDonut.getDonut());
					}
					startPoint = null;
				}
				else if(frame.getTglBtnHexagon().isSelected()) {
					DlgHexagon dlgHexagon = new DlgHexagon();
					//dlgHexagon.setPoint(clickPosition);
					dlgHexagon.textFieldX.setText(Integer.toString(e.getX()));
					dlgHexagon.textFieldY.setText(Integer.toString(e.getY()));
					dlgHexagon.setColors(color, fillColor);
					dlgHexagon.setVisible(true);
					if(dlgHexagon.getHexagon() != null ) {
						model.addShape(dlgHexagon.getHexagon());
					}
					startPoint=null;
				}
				
				model.deselectAll();
				frame.getBtnModify().setEnabled(false);
				frame.getBtnDelete().setEnabled(false);
				//frame.getView().repaint();
			}
		});

		frame.getBtnDelete().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(JOptionPane.showConfirmDialog(null, "Do you really want to delete selected shape?", "Prompt", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == 0) {
					model.deleteSelected();
					frame.getBtnModify().setEnabled(false);
					frame.getBtnDelete().setEnabled(false);
				}
				//frame.getView().repaint();	
			}
			});

frame.getBtnDelete().addActionListener(new ActionListener() {
	public void actionPerformed(ActionEvent arg0) {
		if(JOptionPane.showConfirmDialog(null, "Do you really want to delete selected shape?", "Prompt", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == 0) {
			model.deleteSelected();
			frame.getBtnModify().setEnabled(false);
			frame.getBtnDelete().setEnabled(false);
		}
	}
	});

frame.getBtnModify().addActionListener(new ActionListener() {
	public void actionPerformed(ActionEvent arg0) {
		Shape shape = model.getSelected();
		if(shape == null) {
			JOptionPane.showMessageDialog(null, "No objects selected!");
			return;
		}
		
		if(shape instanceof Point) {
			DlgPoint dlgPoint = new DlgPoint();
			dlgPoint.setPoint((Point) shape);//cast
			dlgPoint.setVisible(true);
			if(dlgPoint.getPoint() != null) {
				Point point = dlgPoint.getPoint();
				shape.moveTo(point.getX(), point.getY());
				shape.setColor(point.getColor());
			}
		}
		
		else if(shape instanceof Line) {
			DlgLine dlgLine = new DlgLine();
			dlgLine.setLine((Line) shape);//cast
			dlgLine.setVisible(true);
			if(dlgLine.getLine() != null) {
				Line line = dlgLine.getLine();
				((Line) shape).setStartPoint(line.getStartPoint());
				((Line) shape).setEndPoint(line.getEndPoint());
				shape.setColor(line.getColor());
			}
		}
		
		else if(shape instanceof Rectangle) {
			DlgRectangle dlgRectangle = new DlgRectangle();
			dlgRectangle.setRectangle((Rectangle) shape);//cast
			dlgRectangle.setVisible(true);
			if(dlgRectangle.getRectangle() != null) {
				Rectangle rectangle = dlgRectangle.getRectangle();
				shape.moveTo(rectangle.getUpperLeftPoint().getX(), rectangle.getUpperLeftPoint().getY());
				((Rectangle) shape).setWidth(rectangle.getWidth());
				((Rectangle) shape).setHeight(rectangle.getHeight());
				shape.setColor(rectangle.getColor());
				shape.setFillColor(rectangle.getFillColor());
			}
		}
		
		else if(shape instanceof Donut) {
			DlgDonut dlgDonut = new DlgDonut();
			dlgDonut.setDonut((Donut) shape);//cast
			dlgDonut.setVisible(true);
			if(dlgDonut.getDonut() != null) {
				Donut donut = dlgDonut.getDonut();
				shape.moveTo(donut.getCenter().getX(), donut.getCenter().getY());
				((Donut) shape).setRadius(donut.getRadius());
				((Donut) shape).setInnerRadius(donut.getInnerRadius());
				shape.setColor(donut.getColor());
				shape.setFillColor(donut.getFillColor());
			}
		}
		
		
		else if(shape instanceof Circle) {
			DlgCircle dlgCircle = new DlgCircle();
			dlgCircle.setCircle((Circle) shape);//cast
			dlgCircle.setVisible(true);
			if(dlgCircle.getCircle() != null) {
				Circle circle = dlgCircle.getCircle();
				shape.moveTo(circle.getCenter().getX(), circle.getCenter().getY());
				((Circle) shape).setRadius(circle.getRadius());
				shape.setColor(circle.getColor());
				shape.setFillColor(circle.getFillColor());
			}
		}
		
		frame.getTglBtnSelect().setSelected(false); //untoggle the button after modifying
	    frame.getView().repaint();
	}
});
//frame.getView().repaint();
}
}