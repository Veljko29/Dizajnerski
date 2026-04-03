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
import geometry.Rectangle;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class DlgRectangle extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textFieldX;
	private JTextField textFieldY;
	private JTextField textFieldWidth;
	private JTextField textFieldHeight;
	
	private Rectangle rectangle = null;
	private Color outlineColor = null;
	private Color fillColor = null;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DlgRectangle dialog = new DlgRectangle();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DlgRectangle() {
		setResizable(false);
		setModal(true);
		setTitle("Rectangle");
		setBounds(100, 100, 450, 310);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(10, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{70, 324, 0};
		gbl_contentPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		{
			JLabel lblUpperLeftPoint = new JLabel("Upper Left Point");
			lblUpperLeftPoint.setFont(new Font("Tahoma", Font.PLAIN, 16));
			GridBagConstraints gbc_lblUpperLeftPoint = new GridBagConstraints();
			gbc_lblUpperLeftPoint.anchor = GridBagConstraints.WEST;
			gbc_lblUpperLeftPoint.insets = new Insets(0, 0, 5, 0);
			gbc_lblUpperLeftPoint.gridx = 1;
			gbc_lblUpperLeftPoint.gridy = 0;
			contentPanel.add(lblUpperLeftPoint, gbc_lblUpperLeftPoint);
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
			textFieldX = new JTextField();
			GridBagConstraints gbc_textFieldX = new GridBagConstraints();
			gbc_textFieldX.insets = new Insets(0, 0, 5, 0);
			gbc_textFieldX.fill = GridBagConstraints.HORIZONTAL;
			gbc_textFieldX.gridx = 1;
			gbc_textFieldX.gridy = 1;
			contentPanel.add(textFieldX, gbc_textFieldX);
			textFieldX.setColumns(10);
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
			textFieldY = new JTextField();
			GridBagConstraints gbc_textFieldY = new GridBagConstraints();
			gbc_textFieldY.insets = new Insets(0, 0, 5, 0);
			gbc_textFieldY.fill = GridBagConstraints.HORIZONTAL;
			gbc_textFieldY.gridx = 1;
			gbc_textFieldY.gridy = 2;
			contentPanel.add(textFieldY, gbc_textFieldY);
			textFieldY.setColumns(10);
		}
		{
			JButton btnChangeColor = new JButton("Change Color");
			btnChangeColor.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					outlineColor = JColorChooser.showDialog(null, "Choose color", outlineColor);
					if(outlineColor == null) {
						outlineColor = Color.black;
					}
				}
			});
			{
				JLabel lblDimensions = new JLabel("Dimensions");
				lblDimensions.setFont(new Font("Tahoma", Font.PLAIN, 16));
				GridBagConstraints gbc_lblDimensions = new GridBagConstraints();
				gbc_lblDimensions.anchor = GridBagConstraints.WEST;
				gbc_lblDimensions.insets = new Insets(0, 0, 5, 0);
				gbc_lblDimensions.gridx = 1;
				gbc_lblDimensions.gridy = 3;
				contentPanel.add(lblDimensions, gbc_lblDimensions);
			}
			{
				JLabel lblEnterWidth = new JLabel("Enter width:");
				GridBagConstraints gbc_lblEnterWidth = new GridBagConstraints();
				gbc_lblEnterWidth.anchor = GridBagConstraints.EAST;
				gbc_lblEnterWidth.insets = new Insets(0, 0, 5, 5);
				gbc_lblEnterWidth.gridx = 0;
				gbc_lblEnterWidth.gridy = 4;
				contentPanel.add(lblEnterWidth, gbc_lblEnterWidth);
			}
			{
				textFieldWidth = new JTextField();
				textFieldWidth.setColumns(10);
				GridBagConstraints gbc_textFieldWidth = new GridBagConstraints();
				gbc_textFieldWidth.insets = new Insets(0, 0, 5, 0);
				gbc_textFieldWidth.fill = GridBagConstraints.HORIZONTAL;
				gbc_textFieldWidth.gridx = 1;
				gbc_textFieldWidth.gridy = 4;
				contentPanel.add(textFieldWidth, gbc_textFieldWidth);
			}
			{
				JLabel lblEnterHeight = new JLabel("Enter height:");
				GridBagConstraints gbc_lblEnterHeight = new GridBagConstraints();
				gbc_lblEnterHeight.anchor = GridBagConstraints.EAST;
				gbc_lblEnterHeight.insets = new Insets(0, 0, 5, 5);
				gbc_lblEnterHeight.gridx = 0;
				gbc_lblEnterHeight.gridy = 5;
				contentPanel.add(lblEnterHeight, gbc_lblEnterHeight);
			}
			{
				textFieldHeight = new JTextField();
				textFieldHeight.setColumns(10);
				GridBagConstraints gbc_textFieldHeight = new GridBagConstraints();
				gbc_textFieldHeight.insets = new Insets(0, 0, 5, 0);
				gbc_textFieldHeight.fill = GridBagConstraints.HORIZONTAL;
				gbc_textFieldHeight.gridx = 1;
				gbc_textFieldHeight.gridy = 5;
				contentPanel.add(textFieldHeight, gbc_textFieldHeight);
			}
			GridBagConstraints gbc_btnChangeColor = new GridBagConstraints();
			gbc_btnChangeColor.fill = GridBagConstraints.HORIZONTAL;
			gbc_btnChangeColor.insets = new Insets(0, 0, 5, 5);
			gbc_btnChangeColor.gridx = 0;
			gbc_btnChangeColor.gridy = 6;
			contentPanel.add(btnChangeColor, gbc_btnChangeColor);
		}
		{
			JButton btnChangeFillColor = new JButton("Change Fill Color");
			btnChangeFillColor.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					fillColor = JColorChooser.showDialog(null, "Choose fill color", fillColor);
					if(fillColor == null) {
						fillColor = Color.white;
					}
				}
			});
			GridBagConstraints gbc_btnChangeFillColor = new GridBagConstraints();
			gbc_btnChangeFillColor.fill = GridBagConstraints.HORIZONTAL;
			gbc_btnChangeFillColor.insets = new Insets(0, 0, 0, 5);
			gbc_btnChangeFillColor.gridx = 0;
			gbc_btnChangeFillColor.gridy = 7;
			contentPanel.add(btnChangeFillColor, gbc_btnChangeFillColor);
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
							int x=Integer.parseInt(textFieldX.getText());
							int y=Integer.parseInt(textFieldY.getText());
							int width=Integer.parseInt(textFieldWidth.getText());
							int height=Integer.parseInt(textFieldHeight.getText());
							rectangle = new Rectangle(new Point(x, y), width, height, outlineColor, fillColor);
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
	
	public Rectangle getRectangle() {
		return rectangle;
	}
	
	public void setRectangle(Rectangle rectangle) {
		setPoint(rectangle.getUpperLeftPoint());
		setColors(rectangle.getColor(), rectangle.getFillColor());
		textFieldWidth.setText("" + rectangle.getWidth());
		textFieldHeight.setText("" + rectangle.getHeight());
	}
	
	public void setPoint(Point p) {
		textFieldX.setText("" + p.getX());
		textFieldY.setText("" + p.getY());
	}
	
	public void setColors(Color outlineColor, Color fillColor) {
		this.outlineColor = outlineColor;
		this.fillColor = fillColor;
	}
}
