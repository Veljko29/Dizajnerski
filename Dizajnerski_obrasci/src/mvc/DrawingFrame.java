package mvc;

import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JToggleButton;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import geometry.Point;
import strategy.SerializationStrategy;
import strategy.TextLogStrategy;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.FlowLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class DrawingFrame extends JFrame {

	private JFrame frame;
	private DrawingView view = new DrawingView();
	private DrawingController controller;
	private DrawingModel model = new DrawingModel();
	private JPanel contentPane;
	private final ButtonGroup tglbtnGroupShapes = new ButtonGroup();
	
	// default colors
	private Color color = Color.black;
	private Color fillColor = Color.white;
	
	private Point startPoint = null;
	private JButton btnModify;
	private JButton btnDelete;
	private JToggleButton tglbtnSelect;
	private JToggleButton tglbtnCircle;
	private JToggleButton tglbtnPoint;
	private JToggleButton tglbtnLine;
	private JToggleButton tglbtnRectangle;
	private JToggleButton tglbtnDonut;
	private JButton btnToFront;
	private JButton btnToBack;
	private JButton btnBringToBack;
	private JButton btnBringToFront;
	private JPanel panel_1;
	private JButton btnUndo;
	private JButton btnRedo;
	private JPanel panel_2;
	private JToggleButton tglbtnHexagon;
	private JButton btnEdgeColor;
    private JButton btnInnerColor;
    private JLabel lblEdgeColor;
    private JLabel lblInnerColor;
    
    private JTextArea logTextArea;
    private JScrollPane logScrollPane;
    private JButton btnSaveBinary;
    private JButton btnLoadBinary;
    private JButton btnSaveText;
    private JButton btnLoadText;
	
	public DrawingFrame() {
        initialize();
    }

	/**
	 * Create the application.
	 */
	private void initialize() {
		setTitle("Veljko Antonic IT29/2021");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(20, 20, 705, 403);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.WEST);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{41, 0};
		gbl_panel.rowHeights = new int[]{15, 0, 0, 0, 0, 10, 0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		tglbtnPoint = new JToggleButton("Point");
		tglbtnPoint.setSelected(true);
		tglbtnGroupShapes.add(tglbtnPoint);
		GridBagConstraints gbc_tglbtnPoint = new GridBagConstraints();
		gbc_tglbtnPoint.fill = GridBagConstraints.HORIZONTAL;
		gbc_tglbtnPoint.insets = new Insets(0, 0, 5, 0);
		gbc_tglbtnPoint.anchor = GridBagConstraints.NORTH;
		gbc_tglbtnPoint.gridx = 0;
		gbc_tglbtnPoint.gridy = 0;
		panel.add(tglbtnPoint, gbc_tglbtnPoint);
		
		tglbtnLine = new JToggleButton("Line");
		tglbtnGroupShapes.add(tglbtnLine);
		GridBagConstraints gbc_tglbtnLine = new GridBagConstraints();
		gbc_tglbtnLine.fill = GridBagConstraints.HORIZONTAL;
		gbc_tglbtnLine.insets = new Insets(0, 0, 5, 0);
		gbc_tglbtnLine.gridx = 0;
		gbc_tglbtnLine.gridy = 1;
		panel.add(tglbtnLine, gbc_tglbtnLine);
		
		tglbtnRectangle = new JToggleButton("Rectangle");
		tglbtnGroupShapes.add(tglbtnRectangle);
		GridBagConstraints gbc_tglbtnRectangle = new GridBagConstraints();
		gbc_tglbtnRectangle.fill = GridBagConstraints.HORIZONTAL;
		gbc_tglbtnRectangle.insets = new Insets(0, 0, 5, 0);
		gbc_tglbtnRectangle.gridx = 0;
		gbc_tglbtnRectangle.gridy = 2;
		panel.add(tglbtnRectangle, gbc_tglbtnRectangle);
		
		tglbtnCircle = new JToggleButton("Circle");
		tglbtnGroupShapes.add(tglbtnCircle);
		GridBagConstraints gbc_tglbtnCircle = new GridBagConstraints();
		gbc_tglbtnCircle.fill = GridBagConstraints.HORIZONTAL;
		gbc_tglbtnCircle.insets = new Insets(0, 0, 5, 0);
		gbc_tglbtnCircle.gridx = 0;
		gbc_tglbtnCircle.gridy = 3;
		panel.add(tglbtnCircle, gbc_tglbtnCircle);
		
		tglbtnDonut = new JToggleButton("Donut");
		tglbtnGroupShapes.add(tglbtnDonut);
		GridBagConstraints gbc_tglbtnDonut = new GridBagConstraints();
		gbc_tglbtnDonut.fill = GridBagConstraints.HORIZONTAL;
		gbc_tglbtnDonut.insets = new Insets(0, 0, 5, 0);
		gbc_tglbtnDonut.gridx = 0;
		gbc_tglbtnDonut.gridy = 4;
		panel.add(tglbtnDonut, gbc_tglbtnDonut);
		
		tglbtnHexagon = new JToggleButton("Hexagon");
		tglbtnGroupShapes.add(tglbtnHexagon);
		GridBagConstraints gbc_tglbtnHexagon = new GridBagConstraints();
		gbc_tglbtnHexagon.fill = GridBagConstraints.BOTH;
		gbc_tglbtnHexagon.insets = new Insets(0, 0, 5, 0);
		gbc_tglbtnHexagon.gridx = 0;
		gbc_tglbtnHexagon.gridy = 5;
		panel.add(tglbtnHexagon, gbc_tglbtnHexagon);
		
		btnModify = new JButton("Modify");
		btnModify.setEnabled(false);
		GridBagConstraints gbc_btnModify = new GridBagConstraints();
		gbc_btnModify.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnModify.insets = new Insets(0, 0, 5, 0);
		gbc_btnModify.gridx = 0;
		gbc_btnModify.gridy = 6;
		panel.add(btnModify, gbc_btnModify);
		
		btnDelete = new JButton("Delete");
		btnDelete.setEnabled(false);
		GridBagConstraints gbc_btnDelete = new GridBagConstraints();
		gbc_btnDelete.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnDelete.insets = new Insets(0, 0, 5, 0);
		gbc_btnDelete.gridx = 0;
		gbc_btnDelete.gridy = 7;
		panel.add(btnDelete, gbc_btnDelete);
		
		tglbtnSelect = new JToggleButton("Select");
		GridBagConstraints gbc_tglbtnSelect = new GridBagConstraints();
		gbc_tglbtnSelect.fill = GridBagConstraints.HORIZONTAL;
		gbc_tglbtnSelect.gridx = 0;
		gbc_tglbtnSelect.gridy = 8;
		panel.add(tglbtnSelect, gbc_tglbtnSelect);
		
		view.setForeground(Color.WHITE);
		view.setBackground(Color.WHITE);
		contentPane.add(view, BorderLayout.CENTER);
		
		panel_2 = new JPanel();
		contentPane.add(panel_2, BorderLayout.NORTH);
		
		btnUndo = new JButton("Undo");
		btnUndo.setEnabled(false);
		
		btnRedo = new JButton("Redo");
		btnRedo.setEnabled(false);
		
		btnToFront = new JButton("ToFront");
		btnToFront.setEnabled(false);
		
		btnToBack = new JButton("ToBack");
		btnToBack.setEnabled(false);
		
		btnBringToFront = new JButton("BringToFront");
		btnBringToFront.setEnabled(false);
		
		btnBringToBack = new JButton("BringToBack");
		btnBringToBack.setEnabled(false);
		
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		 gl_panel_2.setHorizontalGroup(
		            gl_panel_2.createParallelGroup(Alignment.LEADING)
		                .addGroup(gl_panel_2.createSequentialGroup()
		                    .addContainerGap()
		                    .addComponent(btnUndo, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
		                    .addPreferredGap(ComponentPlacement.RELATED)
		                    .addComponent(btnRedo, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
		                    .addGap(18)
		                    .addComponent(btnToFront)
		                    .addPreferredGap(ComponentPlacement.RELATED)
		                    .addComponent(btnToBack)
		                    .addGap(18)
		                    .addComponent(btnBringToFront)
		                    .addPreferredGap(ComponentPlacement.RELATED)
		                    .addComponent(btnBringToBack)
		                    .addContainerGap(238, Short.MAX_VALUE))
		        );
		        gl_panel_2.setVerticalGroup(
		            gl_panel_2.createParallelGroup(Alignment.LEADING)
		                .addGroup(gl_panel_2.createSequentialGroup()
		                    .addContainerGap()
		                    .addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
		                        .addComponent(btnUndo)
		                        .addComponent(btnRedo)
		                        .addComponent(btnToFront)
		                        .addComponent(btnToBack)
		                        .addComponent(btnBringToFront)
		                        .addComponent(btnBringToBack))
		                    .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		        );
			panel_2.setLayout(gl_panel_2);
		
		  view.setModel(model);      //POVEZUJE MODEL I VIEW
		  
		  JPanel logPanel = new JPanel(new BorderLayout(5, 5));
	        logTextArea = new JTextArea(15, 30);
	        logTextArea.setEditable(false);
	        logScrollPane = new JScrollPane(logTextArea);
	        
	        JPanel fileButtonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));
	        btnSaveBinary = new JButton("Save Binary");
	        btnLoadBinary = new JButton("Load Binary");
	        btnSaveText = new JButton("Save Text");
	        btnLoadText = new JButton("Load Text");
	        
	        fileButtonPanel.add(btnSaveBinary);
	        fileButtonPanel.add(btnLoadBinary);
	        fileButtonPanel.add(btnSaveText);
	        fileButtonPanel.add(btnLoadText);
	        
	        logPanel.add(new JLabel("Command Log:"), BorderLayout.NORTH);
	        logPanel.add(logScrollPane, BorderLayout.CENTER);
	        logPanel.add(fileButtonPanel, BorderLayout.SOUTH);
	        
	        contentPane.add(logPanel, BorderLayout.EAST);
		    
		    controller = new DrawingController(this, view);
	 // panel za boje
    JPanel colorPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    colorPanel.setBorder(BorderFactory.createTitledBorder("Colors"));

    btnEdgeColor = new JButton("");
    btnEdgeColor.setBackground(color);
    btnEdgeColor.setForeground(Color.WHITE);
    btnEdgeColor.setPreferredSize(new Dimension(100, 30));

    btnInnerColor = new JButton("");
    btnInnerColor.setBackground(fillColor);
    btnInnerColor.setForeground(Color.WHITE);
    btnInnerColor.setPreferredSize(new Dimension(100, 30));

    lblEdgeColor = new JLabel("Edge:");
    lblEdgeColor.setOpaque(true);
    lblEdgeColor.setPreferredSize(new Dimension(50, 25));

    lblInnerColor = new JLabel("Fill:");
    lblInnerColor.setOpaque(true);
    lblInnerColor.setPreferredSize(new Dimension(50, 25));

    colorPanel.add(lblEdgeColor);
    colorPanel.add(btnEdgeColor);
    colorPanel.add(Box.createHorizontalStrut(10));
    colorPanel.add(lblInnerColor);
    colorPanel.add(btnInnerColor);

    contentPane.add(colorPanel, BorderLayout.SOUTH);
    
   /* view.setForeground(Color.WHITE);
    view.setBackground(Color.WHITE);
    view.setModel(model);
    
    controller = new DrawingController(this, view);
    
    contentPane.add(view, BorderLayout.CENTER);
    */
    btnEdgeColor.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            Color newColor = JColorChooser.showDialog(
                DrawingFrame.this, 
                "Choose Edge Color", 
                color
            );
            if (newColor != null) {
                color = newColor;
                btnEdgeColor.setBackground(color);
                //lblEdgeColor.setBackground(color);
                controller.setColor(color); 
                controller.applyEdgeColorToSelected(); 
            }
        }
    });

    btnInnerColor.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            Color newColor = JColorChooser.showDialog(
                DrawingFrame.this, 
                "Choose Fill Color", 
                fillColor
            );
            if (newColor != null) {
                fillColor = newColor;
                btnInnerColor.setBackground(fillColor);
                controller.setFillColor(fillColor);  
                controller.applyFillColorToSelected(); 
            }
        }
    });
    setupFileButtonListeners();
}
	 private void setupFileButtonListeners() {
	        btnSaveBinary.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                JFileChooser fileChooser = new JFileChooser();
	                fileChooser.setDialogTitle("Save Drawing (Binary)");
	                fileChooser.setSelectedFile(new java.io.File("drawing.ser"));
	                
	                int userSelection = fileChooser.showSaveDialog(DrawingFrame.this);
	                if (userSelection == JFileChooser.APPROVE_OPTION) {
	                    String filePath = fileChooser.getSelectedFile().getAbsolutePath();
	                    controller.setSaveStrategy(new SerializationStrategy());
	                    controller.saveToFile(filePath);
	                }
	            }
	        });
	        
	        btnLoadBinary.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                JFileChooser fileChooser = new JFileChooser();
	                fileChooser.setDialogTitle("Load Drawing (Binary)");
	                
	                int userSelection = fileChooser.showOpenDialog(DrawingFrame.this);
	                if (userSelection == JFileChooser.APPROVE_OPTION) {
	                    String filePath = fileChooser.getSelectedFile().getAbsolutePath();
	                    controller.setSaveStrategy(new SerializationStrategy());
	                    controller.loadFromFile(filePath);
	                }
	            }
	        });
	        btnSaveText.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                JFileChooser fileChooser = new JFileChooser();
	                fileChooser.setDialogTitle("Save Command Log (Text)");
	                fileChooser.setSelectedFile(new java.io.File("log.txt"));
	                
	                int userSelection = fileChooser.showSaveDialog(DrawingFrame.this);
	                if (userSelection == JFileChooser.APPROVE_OPTION) {
	                    String filePath = fileChooser.getSelectedFile().getAbsolutePath();
	                    controller.setSaveStrategy(new TextLogStrategy());
	                    controller.saveToFile(filePath);
	                }
	            }
	        });
	        
	        btnLoadText.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                JFileChooser fileChooser = new JFileChooser();
	                fileChooser.setDialogTitle("Load and Execute Log (Text)");
	                
	                int userSelection = fileChooser.showOpenDialog(DrawingFrame.this);
	                if (userSelection == JFileChooser.APPROVE_OPTION) {
	                    String filePath = fileChooser.getSelectedFile().getAbsolutePath();
	                    controller.setSaveStrategy(new TextLogStrategy());
	                    controller.loadFromFile(filePath);
	                }
	            }
	        });
	 }
	
	// === GETTER METODE ===
	    public DrawingView getView() {
	        return view;
	    }

	    public void setController(DrawingController controller) {
	        this.controller = controller;
	    }
	    
	    public JButton getBtnModify() {
	        return btnModify;
	    }
	    
	    public JButton getBtnDelete() {
	        return btnDelete;
	    }
	    
	    public JToggleButton getTglBtnSelect() {
	        return tglbtnSelect;
	    }
	    
	    public JToggleButton getTglBtnPoint() {
	        return tglbtnPoint;
	    }
	    
	    public JToggleButton getTglBtnLine() {
	        return tglbtnLine;
	    }
	    
	    public JToggleButton getTglBtnRectangle() {
	        return tglbtnRectangle;
	    }
	    
	    public JToggleButton getTglBtnCircle() {
	        return tglbtnCircle;
	    }
	    
	    public JToggleButton getTglBtnDonut() {
	        return tglbtnDonut;
	    }
	    
	    public JToggleButton getTglBtnHexagon() {
	        return tglbtnHexagon;
	    }
	    
	    public JButton getUndoBtn() {
	        return btnUndo; 
	    }

	    public JButton getRedoBtn() {
	        return btnRedo;
	    }
	    
	    public JButton getToFrontBtn() {
	        return btnToFront;
	    }
	    
	    public JButton getToBackBtn() {
	        return btnToBack;
	    }
	    
	    public JButton getBringToFrontBtn() {
	        return btnBringToFront;
	    }
	    
	    public JButton getBringToBackBtn() {
	        return btnBringToBack;
	    }
	    
	    public Color getColor() {
	        return color;
	    }
	    
	    public Color getFillColor() {
	        return fillColor;
	    }
	    
	    public void setColor(Color color) {
	        this.color = color;
	        btnEdgeColor.setBackground(color);
	        lblEdgeColor.setBackground(color);
	    }
	    
	    public void setFillColor(Color fillColor) {
	        this.fillColor = fillColor;
	        btnInnerColor.setBackground(fillColor);
	        lblInnerColor.setBackground(fillColor);
	    }
	    public JButton getBtnSaveBinary() {
	        return btnSaveBinary;
	    }

	    public JButton getBtnLoadBinary() {
	        return btnLoadBinary;
	    }

	    public JButton getBtnSaveText() {
	        return btnSaveText;
	    }

	    public JButton getBtnLoadText() {
	        return btnLoadText;
	    }
	    public JButton getBtnEdgeColor() {
			return btnEdgeColor;
		}


		public JButton getBtnInnerColor() {
			return btnInnerColor;
		}
}