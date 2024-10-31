package drawingObjects;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import geometry.Circle;
import geometry.Point;
import geometry.Shape;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;


public class DlgCircle extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textFieldXCenter;
	private JTextField textFieldYCenter;
	private JTextField textFieldRadius;
	private boolean isOk;
	private Color innerColor;
	private Color outlineColor;
	private Circle circle;
	



	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DlgCircle dialog = new DlgCircle();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DlgCircle() {
		setModal(true);
		innerColor = Color.white;
		outlineColor = Color.black;
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			panel.setBackground(new Color(64, 0, 64));
			contentPanel.add(panel, BorderLayout.NORTH);
			{
				JLabel lblCircles = new JLabel("Enter Circle Properties");
				lblCircles.setForeground(new Color(255, 255, 255));
				lblCircles.setFont(new Font("Georgia", Font.BOLD, 17));
				panel.add(lblCircles);
			}
		}
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel, BorderLayout.CENTER);
			GridBagLayout gbl_panel = new GridBagLayout();
			gbl_panel.columnWidths = new int[]{0, 234, 59, 143, 0};
			gbl_panel.rowHeights = new int[]{41, 36, 38, 33, 42, 0, 0};
			gbl_panel.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
			gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
			panel.setLayout(gbl_panel);
			{
				JLabel lblXCenter = new JLabel("Enter the x-coordinate");
				lblXCenter.setFont(new Font("Georgia", Font.BOLD, 15));
				GridBagConstraints gbc_lblXCenter = new GridBagConstraints();
				gbc_lblXCenter.anchor = GridBagConstraints.EAST;
				gbc_lblXCenter.insets = new Insets(0, 0, 5, 5);
				gbc_lblXCenter.gridx = 1;
				gbc_lblXCenter.gridy = 1;
				panel.add(lblXCenter, gbc_lblXCenter);
			}
			{
				textFieldXCenter = new JTextField();
				textFieldXCenter.setForeground(new Color(255, 255, 255));
				textFieldXCenter.setBackground(new Color(64, 0, 64));
				textFieldXCenter.setFont(new Font("Georgia", Font.BOLD, 15));
				GridBagConstraints gbc_textFieldXCenter = new GridBagConstraints();
				gbc_textFieldXCenter.fill = GridBagConstraints.BOTH;
				gbc_textFieldXCenter.insets = new Insets(0, 0, 5, 5);
				gbc_textFieldXCenter.gridx = 2;
				gbc_textFieldXCenter.gridy = 1;
				panel.add(textFieldXCenter, gbc_textFieldXCenter);
				textFieldXCenter.setColumns(10);
			}
			{
				JLabel lblYCenter = new JLabel("Enter the x-coordinate");
				lblYCenter.setFont(new Font("Georgia", Font.BOLD, 15));
				GridBagConstraints gbc_lblYCenter = new GridBagConstraints();
				gbc_lblYCenter.anchor = GridBagConstraints.EAST;
				gbc_lblYCenter.insets = new Insets(0, 0, 5, 5);
				gbc_lblYCenter.gridx = 1;
				gbc_lblYCenter.gridy = 2;
				panel.add(lblYCenter, gbc_lblYCenter);
			}
			{
				textFieldYCenter = new JTextField();
				textFieldYCenter.setForeground(new Color(255, 255, 255));
				textFieldYCenter.setBackground(new Color(64, 0, 64));
				textFieldYCenter.setFont(new Font("Georgia", Font.BOLD, 15));
				GridBagConstraints gbc_textFieldYCenter = new GridBagConstraints();
				gbc_textFieldYCenter.insets = new Insets(0, 0, 5, 5);
				gbc_textFieldYCenter.fill = GridBagConstraints.BOTH;
				gbc_textFieldYCenter.gridx = 2;
				gbc_textFieldYCenter.gridy = 2;
				panel.add(textFieldYCenter, gbc_textFieldYCenter);
				textFieldYCenter.setColumns(10);
			}
			{
				JLabel lblRadius = new JLabel("Enter the radius");
				lblRadius.setForeground(new Color(64, 0, 64));
				lblRadius.setFont(new Font("Georgia", Font.BOLD, 15));
				GridBagConstraints gbc_lblRadius = new GridBagConstraints();
				gbc_lblRadius.anchor = GridBagConstraints.EAST;
				gbc_lblRadius.insets = new Insets(0, 0, 5, 5);
				gbc_lblRadius.gridx = 1;
				gbc_lblRadius.gridy = 3;
				panel.add(lblRadius, gbc_lblRadius);
			}
			{
				{
					textFieldRadius = new JTextField();
					textFieldRadius.setFont(new Font("Georgia", Font.BOLD, 15));
					GridBagConstraints gbc_textFieldRadius = new GridBagConstraints();
					gbc_textFieldRadius.insets = new Insets(0, 0, 5, 5);
					gbc_textFieldRadius.fill = GridBagConstraints.BOTH;
					gbc_textFieldRadius.gridx = 2;
					gbc_textFieldRadius.gridy = 3;
					panel.add(textFieldRadius, gbc_textFieldRadius);
					textFieldRadius.setColumns(10);
				}
			}
			{
				JButton btnNewButton = new JButton("Inner");
				btnNewButton.setForeground(new Color(0, 0, 108));
				btnNewButton.setBackground(new Color(255, 255, 255));
				btnNewButton.setFont(new Font("Georgia", Font.BOLD | Font.ITALIC, 13));
				btnNewButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						innerColor = JColorChooser.showDialog(null,
		                        "Choose a color", Color.WHITE);
					}
				});
				JButton btnNewButton_1 = new JButton("Outline ");
				btnNewButton_1.setForeground(new Color(255, 255, 255));
				btnNewButton_1.setBackground(new Color(0, 0, 108));
				btnNewButton_1.setFont(new Font("Georgia", Font.BOLD | Font.ITALIC, 13));
				btnNewButton_1.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						outlineColor = JColorChooser.showDialog(null,
		                        "Choose a color", Color.BLACK);
					}
				});
				GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
				gbc_btnNewButton_1.anchor = GridBagConstraints.SOUTHEAST;
				gbc_btnNewButton_1.insets = new Insets(0, 0, 5, 5);
				gbc_btnNewButton_1.gridx = 1;
				gbc_btnNewButton_1.gridy = 4;
				panel.add(btnNewButton_1, gbc_btnNewButton_1);
				GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
				gbc_btnNewButton.anchor = GridBagConstraints.SOUTHEAST;
				gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
				gbc_btnNewButton.gridx = 2;
				gbc_btnNewButton.gridy = 4;
				panel.add(btnNewButton, gbc_btnNewButton);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setFont(new Font("Georgia", Font.BOLD, 13));
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							if (textFieldXCenter.getText().isEmpty() || textFieldYCenter.getText().isEmpty() || textFieldRadius.getText().isEmpty()) {
						        JOptionPane.showMessageDialog(null, "All fields must be filled!", "Error", JOptionPane.ERROR_MESSAGE);
						        return;
						    }
							int x = Integer.parseInt(textFieldXCenter.getText());
							int y = Integer.parseInt(textFieldYCenter.getText());
							int radius = Integer.parseInt(textFieldRadius.getText());
						    circle = new Circle(new Point(x,y), radius);
						    setVisible(false);
							isOk=true;
							
						}catch(NumberFormatException exc){
							JOptionPane.showMessageDialog(null, "Enter number only!", "Error", JOptionPane.ERROR_MESSAGE);
						}catch(IllegalArgumentException ex)
						{
							JOptionPane.showMessageDialog(DlgCircle.this, "Must be positive!", "Error", JOptionPane.ERROR_MESSAGE);
						}
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setBackground(new Color(255, 255, 255));
				cancelButton.setFont(new Font("Georgia", Font.BOLD, 13));
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



	public JTextField getTextFieldXCenter() {
		return textFieldXCenter;
	}

	public void setTextFieldXCenter(JTextField textFieldXCenter) {
		this.textFieldXCenter = textFieldXCenter;
	}

	public JTextField getTextFieldYCenter() {
		return textFieldYCenter;
	}

	public void setTextFieldYCenter(JTextField textFieldYCenter) {
		this.textFieldYCenter = textFieldYCenter;
	}

	public JTextField getTextFieldRadius() {
		return textFieldRadius;
	}

	public void setTextFieldRadius(JTextField textFieldRadius) {
		this.textFieldRadius = textFieldRadius;
	}

	public boolean isOk() {
		return isOk;
	}

	public void setOk(boolean isOk) {
		this.isOk = isOk;
	}

	public Color getInnerColor() {
		return innerColor;
	}

	public void setInnerColor(Color innerColor) {
		this.innerColor = innerColor;
	}

	public Color getOutlineColor() {
		return outlineColor;
	}

	public void setOutlineColor(Color outlineColor) {
		this.outlineColor = outlineColor;
	}

	public Circle getCircle() {
		return circle;
	}

	public void setCircle(Circle circle) {
		this.circle = circle;
	}

	
	

}
