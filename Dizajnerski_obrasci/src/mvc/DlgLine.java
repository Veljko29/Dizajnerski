package mvc;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import geometry.Line;
import geometry.Point;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class DlgLine extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textFieldX1;
	private JTextField textFieldY1;
	
	private Line line = null;
	private Color color = null;
	private JTextField textFieldX2;
	private JTextField textFieldY2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DlgLine dialog = new DlgLine();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DlgLine() {
		setResizable(false);
		setModal(true);
		setTitle("Line");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(10, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{70, 324, 0};
		gbl_contentPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		{
			JLabel lblStartPoint = new JLabel("Start Point");
			lblStartPoint.setFont(new Font("Tahoma", Font.PLAIN, 16));
			GridBagConstraints gbc_lblStartPoint = new GridBagConstraints();
			gbc_lblStartPoint.anchor = GridBagConstraints.WEST;
			gbc_lblStartPoint.insets = new Insets(0, 0, 5, 0);
			gbc_lblStartPoint.gridx = 1;
			gbc_lblStartPoint.gridy = 0;
			contentPanel.add(lblStartPoint, gbc_lblStartPoint);
		}
		{
			JLabel lblEnterX = new JLabel("Enter X:");
			GridBagConstraints gbc_lblEnterX = new GridBagConstraints();
			gbc_lblEnterX.anchor = GridBagConstraints.EAST;
			gbc_lblEnterX.insets = new Insets(0, 0, 5, 5);
			gbc_lblEnterX.gridx = 0;
			gbc_lblEnterX.gridy = 1;
			contentPanel.add(lblEnterX, gbc_lblEnterX);
		}
		{
			textFieldX1 = new JTextField();
			GridBagConstraints gbc_textFieldX1 = new GridBagConstraints();
			gbc_textFieldX1.insets = new Insets(0, 0, 5, 0);
			gbc_textFieldX1.fill = GridBagConstraints.HORIZONTAL;
			gbc_textFieldX1.gridx = 1;
			gbc_textFieldX1.gridy = 1;
			contentPanel.add(textFieldX1, gbc_textFieldX1);
			textFieldX1.setColumns(10);
		}
		{
			JLabel lblEnterY = new JLabel("Enter Y:");
			GridBagConstraints gbc_lblEnterY = new GridBagConstraints();
			gbc_lblEnterY.anchor = GridBagConstraints.EAST;
			gbc_lblEnterY.insets = new Insets(0, 0, 5, 5);
			gbc_lblEnterY.gridx = 0;
			gbc_lblEnterY.gridy = 2;
			contentPanel.add(lblEnterY, gbc_lblEnterY);
		}
		{
			textFieldY1 = new JTextField();
			GridBagConstraints gbc_textFieldY1 = new GridBagConstraints();
			gbc_textFieldY1.insets = new Insets(0, 0, 5, 0);
			gbc_textFieldY1.fill = GridBagConstraints.HORIZONTAL;
			gbc_textFieldY1.gridx = 1;
			gbc_textFieldY1.gridy = 2;
			contentPanel.add(textFieldY1, gbc_textFieldY1);
			textFieldY1.setColumns(10);
		}
		{
			JButton btnChangeColor = new JButton("Change Color");
			btnChangeColor.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					color = JColorChooser.showDialog(null, "Choose color", color);
					if(color == null) {
						color = Color.black;
					}
				}
			});
			{
				JLabel lblEndPoint = new JLabel("End Point");
				lblEndPoint.setFont(new Font("Tahoma", Font.PLAIN, 16));
				GridBagConstraints gbc_lblEndPoint = new GridBagConstraints();
				gbc_lblEndPoint.anchor = GridBagConstraints.WEST;
				gbc_lblEndPoint.insets = new Insets(0, 0, 5, 0);
				gbc_lblEndPoint.gridx = 1;
				gbc_lblEndPoint.gridy = 3;
				contentPanel.add(lblEndPoint, gbc_lblEndPoint);
			}
			{
				JLabel lblEnterX2 = new JLabel("Enter X:");
				GridBagConstraints gbc_lblEnterX2 = new GridBagConstraints();
				gbc_lblEnterX2.anchor = GridBagConstraints.EAST;
				gbc_lblEnterX2.insets = new Insets(0, 0, 5, 5);
				gbc_lblEnterX2.gridx = 0;
				gbc_lblEnterX2.gridy = 4;
				contentPanel.add(lblEnterX2, gbc_lblEnterX2);
			}
			{
				textFieldX2 = new JTextField();
				textFieldX2.setColumns(10);
				GridBagConstraints gbc_textFieldX2 = new GridBagConstraints();
				gbc_textFieldX2.insets = new Insets(0, 0, 5, 0);
				gbc_textFieldX2.fill = GridBagConstraints.HORIZONTAL;
				gbc_textFieldX2.gridx = 1;
				gbc_textFieldX2.gridy = 4;
				contentPanel.add(textFieldX2, gbc_textFieldX2);
			}
			{
				JLabel lblEnterY2 = new JLabel("Enter Y:");
				GridBagConstraints gbc_lblEnterY2 = new GridBagConstraints();
				gbc_lblEnterY2.anchor = GridBagConstraints.EAST;
				gbc_lblEnterY2.insets = new Insets(0, 0, 5, 5);
				gbc_lblEnterY2.gridx = 0;
				gbc_lblEnterY2.gridy = 5;
				contentPanel.add(lblEnterY2, gbc_lblEnterY2);
			}
			{
				textFieldY2 = new JTextField();
				textFieldY2.setColumns(10);
				GridBagConstraints gbc_textFieldY2 = new GridBagConstraints();
				gbc_textFieldY2.insets = new Insets(0, 0, 5, 0);
				gbc_textFieldY2.fill = GridBagConstraints.HORIZONTAL;
				gbc_textFieldY2.gridx = 1;
				gbc_textFieldY2.gridy = 5;
				contentPanel.add(textFieldY2, gbc_textFieldY2);
			}
			GridBagConstraints gbc_btnChangeColor = new GridBagConstraints();
			gbc_btnChangeColor.gridwidth = 2;
			gbc_btnChangeColor.gridx = 0;
			gbc_btnChangeColor.gridy = 6;
			contentPanel.add(btnChangeColor, gbc_btnChangeColor);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							int x1=Integer.parseInt(textFieldX1.getText());
							int y1=Integer.parseInt(textFieldY1.getText());
							int x2=Integer.parseInt(textFieldX2.getText());
							int y2=Integer.parseInt(textFieldY2.getText());
							line = new Line(new Point(x1, y1), new Point(x2, y2), color);
							setVisible(false);
						} 
						catch(NumberFormatException e1) {
							JOptionPane.showMessageDialog(null, "You didn't enter a number!");
						}
						catch(IllegalArgumentException e2) {
							JOptionPane.showMessageDialog(null, e2.getMessage());
						}	
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						setVisible(false);
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	
	public Line getLine() {
		return line;
	}
	
	public void setLine(Point startPoint, Point endPoint) {
		textFieldX1.setText("" + startPoint.getX());
		textFieldY1.setText("" + startPoint.getY());
		
		textFieldX2.setText("" + endPoint.getX());
		textFieldY2.setText("" + endPoint.getY());
	}
	
	public void setLine(Line line) {
		setColor(line.getColor());
		setLine(line.getStartPoint(), line.getEndPoint());
	}
	
	public void setColor(Color color) {
		this.color = color;
	}
}
