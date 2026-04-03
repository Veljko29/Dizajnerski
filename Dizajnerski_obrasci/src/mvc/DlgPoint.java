package mvc;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import geometry.Point;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DlgPoint extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textFieldX;
	private JTextField textFieldY;
	
	private Point point = null;
	private Color color = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DlgPoint dialog = new DlgPoint();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DlgPoint() {
		setResizable(false);
		setModal(true);
		setTitle("Point");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(10, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{70, 324, 0};
		gbl_contentPanel.rowHeights = new int[]{0, 0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		{
			JLabel lblEnterX = new JLabel("Enter X:");
			GridBagConstraints gbc_lblEnterX = new GridBagConstraints();
			gbc_lblEnterX.anchor = GridBagConstraints.EAST;
			gbc_lblEnterX.insets = new Insets(0, 0, 5, 5);
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
			JButton btnChangeColor = new JButton("Change Color");
			btnChangeColor.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					color = JColorChooser.showDialog(null, "Choose color", color);
					if(color == null) {
						color = Color.black;
					}
				}
			});
			GridBagConstraints gbc_btnChangeColor = new GridBagConstraints();
			gbc_btnChangeColor.gridwidth = 2;
			gbc_btnChangeColor.insets = new Insets(0, 0, 0, 5);
			gbc_btnChangeColor.gridx = 0;
			gbc_btnChangeColor.gridy = 2;
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
							int x=Integer.parseInt(textFieldX.getText());
							int y=Integer.parseInt(textFieldY.getText());
							point = new Point(x, y, color);
							setVisible(false);
						} 
						catch(NumberFormatException e1) {
							JOptionPane.showMessageDialog(null, "You didn't enter a number!");
						}
						catch(IllegalArgumentException e2) {
							JOptionPane.showMessageDialog(null, "Values must be positive!");
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
	
	public Point getPoint() {
		return point;
	}
	
	public void setPoint(Point p) {
		setColor(p.getColor());
		textFieldX.setText("" + p.getX());
		textFieldY.setText("" + p.getY());
	}
	
	public void setColor(Color color) {
		this.color = color;
	}

}
