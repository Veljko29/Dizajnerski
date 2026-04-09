package mvc;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Stack;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

import adapter.HexagonAdapter;
import command.CmdAddShape;
import command.CmdBringToBack;
import command.CmdBringToFront;
import command.CmdRemoveShape;
import command.CmdUpdateCircle;
import command.CmdUpdateDonut;
import command.CmdUpdateHexagon;
import command.CmdUpdateLine;
import command.CmdUpdatePoint;
import command.CmdUpdateRectangle;
import command.Command;
import command.CmdToBack;
import command.CmdToFront;
import mvc.DlgCircle;
import mvc.DlgDonut;
import mvc.DlgHexagon;
import mvc.DlgLine;
import mvc.DlgPoint;
import mvc.DlgRectangle;
import strategy.SaveStrategy;
import strategy.SerializationStrategy;
import strategy.TextLogStrategy;
import geometry.Circle;
import geometry.Donut;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import geometry.Shape;

public class DrawingController implements DrawingObserver {
	private DrawingModel model;
	private DrawingFrame frame;
	private DrawingView view;
	private Point startPoint = null;
	private Color color = Color.black;
	private Color fillColor = Color.white;
	private Stack<Command> undoStack = new Stack<>();
	private Stack<Command> redoStack = new Stack<>();
	private SaveStrategy saveStrategy;
	private List<Command> commandHistory = new ArrayList<>();
	 private JTextArea logTextArea; 

	public void setFrame(DrawingFrame frame) {
		this.frame = frame;
	}

	public DrawingController(DrawingModel model, DrawingFrame frame, DrawingView view) {
		this.model = model;
	}

	public DrawingController(DrawingFrame frame, DrawingView view) {
		this.frame = frame;
		this.view = frame.getView();
		this.model = view.getModel();
		 this.model.addObserver(this);
		 //updateButtons();
		view.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				Point clickPosition;
				try {
					clickPosition = new Point(e.getX(), e.getY());
				} catch (IllegalArgumentException exception) {
					JOptionPane.showMessageDialog(null, exception.getMessage());
					return;
				}

				 // SELECT LOGIKA 
    	        if(frame.getTglBtnSelect().isSelected()) {
    	            boolean ctrlPressed = e.isControlDown();
    	            
    	            // Deselectuj sve ako nije Ctrl
    	            if (!ctrlPressed) {
    	                model.deselectAll();
    	            }
    	            
    	            boolean shapeSelected = false;
    	            for (int i = model.getShapes().size() - 1; i >= 0; i--) {
    	                Shape shape = model.getShapes().get(i);
    	                if (shape.contains(clickPosition.getX(), clickPosition.getY())) {
    	                    if (ctrlPressed) {
    	                        shape.setSelected(!shape.isSelected());
    	                    } else {
    	                        shape.setSelected(true);
    	                    }
    	                    shapeSelected = true;
    	                    break;
    	                }
    	            }
    	            
    	            if (shapeSelected) {
    	                model.notifyObservers();
    	                logSelection();
    	            }
    	            return;
    	        }

				Shape shape = null;
				if (frame.getTglBtnPoint().isSelected()) {
					DlgPoint dlgPoint = new DlgPoint();
					dlgPoint.setPoint(clickPosition);
					dlgPoint.setColor(color);
					dlgPoint.setVisible(true);
					shape = dlgPoint.getPoint();
					startPoint = null;
				} else if (frame.getTglBtnLine().isSelected()) {
					if (startPoint == null) {
						startPoint = clickPosition;
						return;
					} else {
						DlgLine dlgLine = new DlgLine();
						dlgLine.setLine(startPoint, clickPosition);
						dlgLine.setColor(color);
						dlgLine.setVisible(true);
						shape = dlgLine.getLine();
						startPoint = null;
					}
				} else if (frame.getTglBtnRectangle().isSelected()) {
					DlgRectangle dlgRectangle = new DlgRectangle();
					dlgRectangle.setPoint(clickPosition);
					dlgRectangle.setColors(color, fillColor);
					dlgRectangle.setVisible(true);
					shape = dlgRectangle.getRectangle();
					startPoint = null;
				} else if (frame.getTglBtnCircle().isSelected()) {
					DlgCircle dlgCircle = new DlgCircle();
					dlgCircle.setPoint(clickPosition);
					dlgCircle.setColors(color, fillColor);
					dlgCircle.setVisible(true);
					shape = dlgCircle.getCircle();
					startPoint = null;
				} else if (frame.getTglBtnDonut().isSelected()) {
					DlgDonut dlgDonut = new DlgDonut();
					dlgDonut.setPoint(clickPosition);
					dlgDonut.setColors(color, fillColor);
					dlgDonut.setVisible(true);
					shape = dlgDonut.getDonut();
					startPoint = null;
				} else if (frame.getTglBtnHexagon().isSelected()) {
					DlgHexagon dlgHexagon = new DlgHexagon();
					dlgHexagon.textFieldX.setText(Integer.toString(e.getX()));
					dlgHexagon.textFieldY.setText(Integer.toString(e.getY()));
					dlgHexagon.setColors(color, fillColor);
					dlgHexagon.setVisible(true);
					shape = dlgHexagon.getHexagon();
					startPoint = null;
				}

				if (shape != null) {
					executeCommand(new CmdAddShape(model, shape));
				}
				//model.deselectAll();
				frame.getBtnModify().setEnabled(false);
				frame.getBtnDelete().setEnabled(false);
			}
		});

		frame.getBtnDelete().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (JOptionPane.showConfirmDialog(null, "Do you really want to delete selected shape?", "Prompt",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == 0) {
					Shape shape = model.getSelected();
					if (shape != null) {
						executeCommand(new CmdRemoveShape(model, shape));
						frame.getBtnModify().setEnabled(false);
						frame.getBtnDelete().setEnabled(false);
					}
				}
			}
		});

		frame.getBtnModify().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Shape shape = model.getSelected();
				if (shape == null) {
					JOptionPane.showMessageDialog(null, "No objects selected!");
					return;
				}

				if (shape instanceof Point) {
					DlgPoint dlgPoint = new DlgPoint();
					dlgPoint.setPoint((Point) shape);
					dlgPoint.setVisible(true);
					if (dlgPoint.getPoint() != null) {
						executeCommand(new CmdUpdatePoint((Point) shape, dlgPoint.getPoint()));
					}
				} else if (shape instanceof Line) {
					DlgLine dlgLine = new DlgLine();
					dlgLine.setLine((Line) shape);
					dlgLine.setVisible(true);
					if (dlgLine.getLine() != null) {
						executeCommand(new CmdUpdateLine((Line) shape, dlgLine.getLine()));
					}
				} else if (shape instanceof Rectangle) {
					DlgRectangle dlgRectangle = new DlgRectangle();
					dlgRectangle.setRectangle((Rectangle) shape);
					dlgRectangle.setVisible(true);
					if (dlgRectangle.getRectangle() != null) {
						executeCommand(new CmdUpdateRectangle((Rectangle) shape, dlgRectangle.getRectangle()));
					}
				} else if (shape instanceof Donut) {
					DlgDonut dlgDonut = new DlgDonut();
					dlgDonut.setDonut((Donut) shape);
					dlgDonut.setVisible(true);
					if (dlgDonut.getDonut() != null) {
						executeCommand(new CmdUpdateDonut((Donut) shape, dlgDonut.getDonut()));
					}
				} else if (shape instanceof Circle) {
					DlgCircle dlgCircle = new DlgCircle();
					dlgCircle.setCircle((Circle) shape);
					dlgCircle.setVisible(true);
					if (dlgCircle.getCircle() != null) {
						executeCommand(new CmdUpdateCircle((Circle) shape, dlgCircle.getCircle()));
					}
				} else if (shape instanceof HexagonAdapter) {
					DlgHexagon dlgHexagon = new DlgHexagon();
					dlgHexagon.setHexagon((HexagonAdapter) shape);
					dlgHexagon.setVisible(true);
					if (dlgHexagon.getHexagon() != null) {
						executeCommand(new CmdUpdateHexagon((HexagonAdapter) shape, dlgHexagon.getHexagon()));
					}
				}
				frame.getTglBtnSelect().setSelected(false);
				frame.getView().repaint();
			}
		});
		frame.getTglBtnPoint().addActionListener(e -> updateButtons());
		frame.getTglBtnLine().addActionListener(e -> updateButtons());
		frame.getTglBtnRectangle().addActionListener(e -> updateButtons());
		frame.getTglBtnCircle().addActionListener(e -> updateButtons());
		frame.getTglBtnDonut().addActionListener(e -> updateButtons());
		frame.getTglBtnHexagon().addActionListener(e -> updateButtons());
		frame.getTglBtnSelect().addActionListener(e -> updateButtons());
		 // Undo/Redo dugmići
        frame.getUndoBtn().addActionListener(e -> undo());
        frame.getRedoBtn().addActionListener(e -> redo());
        
        // Z-order dugmići
        frame.getToFrontBtn().addActionListener(e -> toFront());
        frame.getToBackBtn().addActionListener(e -> toBack());
        frame.getBringToFrontBtn().addActionListener(e -> bringToFront());
        frame.getBringToBackBtn().addActionListener(e -> bringToBack());
	}

	private void executeCommand(Command command) {
		command.execute();
        undoStack.push(command);
        redoStack.clear();
       
        commandHistory.add(command);
        logCommand(command);
        
        updateButtons();
        frame.repaint();
	}
	private List<Shape> getSelectedShapes() {
        List<Shape> selected = new ArrayList<>();
        for (Shape shape : model.getShapes()) {
            if (shape.isSelected()) {
                selected.add(shape);
            }
        }
        return selected;
    }

	public void undo() {
		if (!undoStack.isEmpty()) {
			Command command = undoStack.pop();
			command.unexecute();
			redoStack.push(command);
			updateButtons();
			frame.repaint();
		}
	}

	public void redo() {
		if (!redoStack.isEmpty()) {
			Command command = redoStack.pop();
			command.execute();
			undoStack.push(command);
			updateButtons();
			frame.repaint();
		}
	}
	// Z-order metode
    public void toFront() {
        List<Shape> selectedShapes = getSelectedShapes();
        if (selectedShapes.size() == 1) {
            Shape selected = selectedShapes.get(0);
            if (selected != null && model.getShapes().size() >= 2) {
                int index = model.indexOf(selected);
                if (index < model.getShapes().size() - 1) {
                    Command cmdToFront = new CmdToFront(model, selected);
                    executeCommand(cmdToFront);
                }
            }
        }
    }
    
    public void toBack() {
        List<Shape> selectedShapes = getSelectedShapes();
        if (selectedShapes.size() == 1) {
            Shape selected = selectedShapes.get(0);
            if (selected != null && model.getShapes().size() >= 2) {
                int index = model.indexOf(selected);
                if (index > 0) {
                    Command cmdToBack = new CmdToBack(model, selected);
                    executeCommand(cmdToBack);
                }
            }
        }
    }
    
    public void bringToFront() {
        List<Shape> selectedShapes = getSelectedShapes();
        if (selectedShapes.size() == 1) {
            Shape selected = selectedShapes.get(0);
            if (selected != null && model.getShapes().size() >= 2) {
                int index = model.indexOf(selected);
                if (index < model.getShapes().size() - 1) {
                    Command cmdBringToFront = new CmdBringToFront(model, selected);
                    executeCommand(cmdBringToFront);
                }
            }
        }
    }
    
    public void bringToBack() {
        List<Shape> selectedShapes = getSelectedShapes();
        if (selectedShapes.size() == 1) {
            Shape selected = selectedShapes.get(0);
            if (selected != null && model.getShapes().size() >= 2) {
                int index = model.indexOf(selected);
                if (index > 0) {
                    Command cmdBringToBack = new CmdBringToBack(model, selected);
                    executeCommand(cmdBringToBack);
                }
            }
        }
    }

    private void updateButtons() {
        List<Shape> selectedShapes = getSelectedShapes();
        int selectedCount = selectedShapes.size();
        int shapeCount = model.getShapes().size();
        
        // 1. Undo/redo dugmići
        frame.getUndoBtn().setEnabled(!undoStack.isEmpty());
        frame.getRedoBtn().setEnabled(!redoStack.isEmpty());
        
        // 2. Delete dugme
        frame.getBtnDelete().setEnabled(selectedCount > 0);
        
        // 3. Modify dugme
        frame.getBtnModify().setEnabled(selectedCount == 1);
        
        // 4. Z-order dugmići 
        if (selectedCount == 1 && shapeCount >= 2) {
            Shape selected = selectedShapes.get(0);
            int index = model.indexOf(selected);
            
            boolean canMoveForward = index < shapeCount - 1;
            frame.getToFrontBtn().setEnabled(canMoveForward);
            frame.getBringToFrontBtn().setEnabled(canMoveForward);
            
            boolean canMoveBackward = index > 0;
            frame.getToBackBtn().setEnabled(canMoveBackward);
            frame.getBringToBackBtn().setEnabled(canMoveBackward);
        } else {
            disableZOrderButtons();
        }
        if (selectedCount == 1) {
            Shape selected = selectedShapes.get(0);
            if (selected instanceof Point || selected instanceof Line) {
                frame.getBtnInnerColor().setEnabled(false);
            } else {
                frame.getBtnInnerColor().setEnabled(true);
            }
        } else {
            frame.getBtnInnerColor().setEnabled(true); 
        }
        
        if (selectedCount == 1) {
            Shape selected = selectedShapes.get(0);
            if (selected instanceof Point || selected instanceof Line) {
                frame.getBtnInnerColor().setEnabled(false);
            } else {
                frame.getBtnInnerColor().setEnabled(true);
            }
        } else {
            if (frame.getTglBtnLine().isSelected() || frame.getTglBtnPoint().isSelected()) {
                frame.getBtnInnerColor().setEnabled(false);
            } else {
                frame.getBtnInnerColor().setEnabled(true);
            }
        }
        frame.getBtnModify().setEnabled(selectedCount == 1);
        
        frame.getBtnModify().setEnabled(selectedCount == 1);

    }
    @Override
    public void update() {
        updateButtons();
        frame.repaint();
    }
    private void logMessage(String message) {
        if (logTextArea != null) {
            SwingUtilities.invokeLater(() -> {
                logTextArea.append(message + "\n");
                logTextArea.setCaretPosition(logTextArea.getDocument().getLength());
            });
        }
    }
//Save / load metode
    
    public void saveToFile(String filePath) {
        if (saveStrategy == null) {
            JOptionPane.showMessageDialog(frame, "Save strategy not set!");
            return;
        }
        
        try {
            saveStrategy.save(model, filePath);
            logMessage("SAVED to: " + filePath);
            JOptionPane.showMessageDialog(frame, "Saved successfully to: " + filePath);
        } catch (IOException e) {
            logMessage("ERROR saving: " + e.getMessage());
            JOptionPane.showMessageDialog(frame, "Error saving: " + e.getMessage(), 
                "Save Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void loadFromFile(String filePath) {
        if (saveStrategy == null) {
            JOptionPane.showMessageDialog(frame, "Save strategy not set!");
            return;
        }
        
        try {
            DrawingModel loadedModel = saveStrategy.load(filePath);
            
            this.model = loadedModel;
            view.setModel(model);
            model.addObserver(this);
            
            // Očisti istoriju
            undoStack.clear();
            redoStack.clear();
            commandHistory.clear();
            
            logMessage("LOADED from: " + filePath);
            update();
            JOptionPane.showMessageDialog(frame, "Loaded successfully from: " + filePath);
        } catch (Exception e) {
            logMessage("ERROR loading: " + e.getMessage());
            JOptionPane.showMessageDialog(frame, "Error loading: " + e.getMessage(), 
                "Load Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    public void saveBinary(String filePath) {
        setSaveStrategy(new SerializationStrategy());
        saveToFile(filePath);
    }
    
    public void loadBinary(String filePath) {
        setSaveStrategy(new SerializationStrategy());
        loadFromFile(filePath);
    }
    
    public void saveText(String filePath) {
        setSaveStrategy(new TextLogStrategy());
        saveToFile(filePath);
    }
    
    public void loadText(String filePath) {
        setSaveStrategy(new TextLogStrategy());
        loadFromFile(filePath);
    }
 // Getteri za log i istoriju
    public List<Command> getCommandHistory() {
        return new ArrayList<>(commandHistory);
    }
    
    public List<String> getLogEntries() {
        List<String> entries = new ArrayList<>();
        for (Command cmd : commandHistory) {
            entries.add(cmd.toString());
        }
        return entries;
    }
    
 //log metode
    
    private void logCommand(Command command) {
        String logEntry = String.format("[%s] %s", 
            new Date().toString().substring(11, 19), // HH:mm:ss
            command.toString());
        logMessage(logEntry);
    }
    
    private void logSelection() {
        List<Shape> selected = getSelectedShapes();
        if (!selected.isEmpty()) {
            String shapes = "";
            for (Shape shape : selected) {
                shapes += shape.getClass().getSimpleName() + " ";
            }
            logMessage("SELECTED: " + selected.size() + " shape(s): " + shapes.trim());
        } else {
            logMessage("DESELECTED: All shapes");
        }
    }
    
    private void disableZOrderButtons() {
        frame.getToFrontBtn().setEnabled(false);
        frame.getToBackBtn().setEnabled(false);
        frame.getBringToFrontBtn().setEnabled(false);
        frame.getBringToBackBtn().setEnabled(false);
    }
 // Za edge boju
    public void applyEdgeColorToSelected() {
        List<Shape> selectedShapes = getSelectedShapes();
        if (selectedShapes.isEmpty()) return;
        
        for (Shape shape : selectedShapes) {
            shape.setColor(this.color);
        }
        view.repaint();
    }

    // Za fill boju  
    public void applyFillColorToSelected() {
        List<Shape> selectedShapes = getSelectedShapes();
        if (selectedShapes.isEmpty()) return;
        
        for (Shape shape : selectedShapes) {
            shape.setFillColor(this.fillColor);
        }
        view.repaint();
    }
    private Color getFillColorFromShape(Shape shape) {
        if (shape instanceof Circle) {
            return ((Circle) shape).getFillColor();
        } else if (shape instanceof Rectangle) {
            return ((Rectangle) shape).getFillColor();
        } else if (shape instanceof Donut) {
            return ((Donut) shape).getFillColor();
        } else if (shape instanceof HexagonAdapter) {
            return ((HexagonAdapter) shape).getFillColor();
        }
        return null;
    }
    

	public void setColor(Color color) {
		this.color = color;
	}

	public void setFillColor(Color fillColor) {
		this.fillColor = fillColor;
	}
	public void setSaveStrategy(SaveStrategy saveStrategy) {
        this.saveStrategy = saveStrategy;
    }
	  public void setLogTextArea(JTextArea logTextArea) {
	        this.logTextArea = logTextArea;
	    }

}