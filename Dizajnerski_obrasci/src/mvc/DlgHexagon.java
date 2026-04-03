package mvc;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import adapter.HexagonAdapter;
import geometry.Circle;
import geometry.Point;
import geometry.Shape;
import hexagon.Hexagon;

public class DlgHexagon extends JDialog {

	private final JPanel contentPanel = new JPanel();
	public JTextField textFieldX;
	public JTextField textFieldY;
	public JTextField textFieldRadius;

	HexagonAdapter hexagon = null;
	private Color outlineColor = null;
	private Color fillColor = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DlgHexagon dialog = new DlgHexagon();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DlgHexagon() {
		setTitle("Hexagon");
		setModal(true);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{0, 0, 0};
		gbl_contentPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		{
			JLabel lblEnterX = new JLabel("Enter X:");
			GridBagConstraints gbc_lblEnterX = new GridBagConstraints();
			gbc_lblEnterX.insets = new Insets(0, 0, 5, 5);
			gbc_lblEnterX.anchor = GridBagConstraints.EAST;
			gbc_lblEnterX.gridx = 0;
			gbc_lblEnterX.gridy = 0;
			contentPanel.add(lblEnterX, gbc_lblEnterX);
		}
		{
			textFieldX = new JTextField();
			GridBagConstraints gbc_textFieldX = new GridBagConstraints();
			gbc_textFieldX.insets = new Insets(0, 0, 5, 0);
			gbc_textFieldX.fill = GridBagConstraints.HORIZONTAL;
			gbc_textFieldX.gridx = 1;
			gbc_textFieldX.gridy = 0;
			contentPanel.add(textFieldX, gbc_textFieldX);
			textFieldX.setColumns(10);
		}
		{
			JLabel lblEnterY = new JLabel("Enter Y:");
			GridBagConstraints gbc_lblEnterY = new GridBagConstraints();
			gbc_lblEnterY.anchor = GridBagConstraints.EAST;
			gbc_lblEnterY.insets = new Insets(0, 0, 5, 5);
			gbc_lblEnterY.gridx = 0;
			gbc_lblEnterY.gridy = 1;
			contentPanel.add(lblEnterY, gbc_lblEnterY);
		}
		{
			textFieldY = new JTextField();
			GridBagConstraints gbc_textFieldY = new GridBagConstraints();
			gbc_textFieldY.insets = new Insets(0, 0, 5, 0);
			gbc_textFieldY.fill = GridBagConstraints.HORIZONTAL;
			gbc_textFieldY.gridx = 1;
			gbc_textFieldY.gridy = 1;
			contentPanel.add(textFieldY, gbc_textFieldY);
			textFieldY.setColumns(10);
		}
		{
			JLabel lblEnterRadius = new JLabel("Enter the radius:");
			GridBagConstraints gbc_lblEnterRadius = new GridBagConstraints();
			gbc_lblEnterRadius.anchor = GridBagConstraints.EAST;
			gbc_lblEnterRadius.insets = new Insets(0, 0, 5, 5);
			gbc_lblEnterRadius.gridx = 0;
			gbc_lblEnterRadius.gridy = 2;
			contentPanel.add(lblEnterRadius, gbc_lblEnterRadius);
		}
		{
			textFieldRadius = new JTextField();
			GridBagConstraints gbc_textFieldRadius = new GridBagConstraints();
			gbc_textFieldRadius.insets = new Insets(0, 0, 5, 0);
			gbc_textFieldRadius.fill = GridBagConstraints.HORIZONTAL;
			gbc_textFieldRadius.gridx = 1;
			gbc_textFieldRadius.gridy = 2;
			contentPanel.add(textFieldRadius, gbc_textFieldRadius);
			textFieldRadius.setColumns(10);
		}
		{
			JButton btnColor = new JButton("Change Color");
			btnColor.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					outlineColor = JColorChooser.showDialog(null, "Choose color", outlineColor);
					if(outlineColor == null) {
						outlineColor = Color.black;
					}
				}
			});
			GridBagConstraints gbc_btnColor = new GridBagConstraints();
			gbc_btnColor.fill = GridBagConstraints.HORIZONTAL;
			gbc_btnColor.insets = new Insets(0, 0, 5, 5);
			gbc_btnColor.gridx = 0;
			gbc_btnColor.gridy = 3;
			contentPanel.add(btnColor, gbc_btnColor);
		}
		{
			JButton btnFillColor = new JButton("Change Fill Color");
			btnFillColor.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					fillColor = JColorChooser.showDialog(null, "Choose fill color", fillColor);
					if(fillColor == null) {
						fillColor = Color.white;
					}
				}
			});
			GridBagConstraints gbc_btnFillColor = new GridBagConstraints();
			gbc_btnFillColor.fill = GridBagConstraints.HORIZONTAL;
			gbc_btnFillColor.insets = new Insets(0, 0, 0, 5);
			gbc_btnFillColor.gridx = 0;
			gbc_btnFillColor.gridy = 4;
			contentPanel.add(btnFillColor, gbc_btnFillColor);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						try {
							int x=Integer.parseInt(getXtextField().getText());
							int y=Integer.parseInt(getYtextField().getText());
							int r=Integer.parseInt(getRadiustextField().getText());
							Point center = new Point(x, y);
							hexagon=new HexagonAdapter(new Hexagon(x,y,r), outlineColor, fillColor);
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
					public void actionPerformed(ActionEvent e) {
						setVisible(false);
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

	public JTextField getXtextField() {
		return textFieldX;
	}

	public void setXtextField(JTextField xtextField) {
		textFieldX = xtextField;
	}

	public JTextField getYtextField() {
		return textFieldY;
	}

	public HexagonAdapter getHexagon() {
		return hexagon;
	}
	
	
	public void setColors(Color outlineColor, Color fillColor) {
		this.outlineColor = outlineColor;
		this.fillColor = fillColor;
	}
	public void setHexagon(HexagonAdapter h) {
		setPoint(new Point(h.getCenterX(), h.getCenterY()));
		setColors(h.getColor(), h.getFillColor());
		textFieldRadius.setText("" + h.getRadius());
	}
	
	public void setPoint(Point p) {
		textFieldX.setText("" + p.getX());
		textFieldY.setText("" + p.getY());
	}

	public void setYtextField(JTextField ytextField) {
		textFieldY = ytextField;
	}

	public JTextField getRadiustextField() {
		return textFieldRadius;
	}

	public void setRadiustextField(JTextField radiustextField) {
		textFieldRadius = radiustextField;
	}

}
