package mvc;

import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import geometry.Point;

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
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DrawingFrame window = new DrawingFrame();
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public DrawingFrame() {
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
		
		btnToBack = new JButton("ToBack");
		btnBringToFront = new JButton("BringToFront");
		
		btnBringToBack = new JButton("BringToBack");
		
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
		    
		    controller = new DrawingController(this, view);
	}
	
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
}