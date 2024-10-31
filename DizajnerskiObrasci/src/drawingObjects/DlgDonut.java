package drawingObjects;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import geometry.Donut;
import geometry.Point;
import geometry.Shape;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class DlgDonut extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textFieldXcenter;
	private JTextField textFieldYcenter;
	private JTextField textFieldRadius;
	private JTextField textFieldInner;
	private boolean isOk;
	private Color innerColor;
	private Color outlineColor;
	private Donut donut;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DlgDonut dialog = new DlgDonut();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DlgDonut() {
		setModal(true);
		innerColor = Color.WHITE;
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
				JLabel lblDonut = new JLabel("Enter Donut Parameters");
				lblDonut.setForeground(new Color(255, 255, 255));
				lblDonut.setBackground(new Color(64, 0, 64));
				lblDonut.setFont(new Font("Georgia", Font.BOLD, 17));
				lblDonut.setHorizontalAlignment(SwingConstants.TRAILING);
				panel.add(lblDonut);
			}
		}
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel, BorderLayout.CENTER);
			GridBagLayout gbl_panel = new GridBagLayout();
			gbl_panel.columnWidths = new int[]{0, 248, 62, 0, 0};
			gbl_panel.rowHeights = new int[]{36, 31, 30, 33, 33, 50, 0, 0};
			gbl_panel.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
			gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
			panel.setLayout(gbl_panel);
			{
				JLabel lblXcenter = new JLabel("Enter the x-coordinate");
				lblXcenter.setFont(new Font("Georgia", Font.BOLD, 15));
				GridBagConstraints gbc_lblXcenter = new GridBagConstraints();
				gbc_lblXcenter.anchor = GridBagConstraints.EAST;
				gbc_lblXcenter.insets = new Insets(0, 0, 5, 5);
				gbc_lblXcenter.gridx = 1;
				gbc_lblXcenter.gridy = 1;
				panel.add(lblXcenter, gbc_lblXcenter);
			}
			{
				textFieldXcenter = new JTextField();
				textFieldXcenter.setFont(new Font("Georgia", Font.BOLD, 15));
				GridBagConstraints gbc_textFieldXcenter = new GridBagConstraints();
				gbc_textFieldXcenter.fill = GridBagConstraints.BOTH;
				gbc_textFieldXcenter.insets = new Insets(0, 0, 5, 5);
				gbc_textFieldXcenter.gridx = 2;
				gbc_textFieldXcenter.gridy = 1;
				panel.add(textFieldXcenter, gbc_textFieldXcenter);
				textFieldXcenter.setColumns(10);
			}
			{
				JLabel lblYcenter = new JLabel("Enter the y-coordinate");
				lblYcenter.setFont(new Font("Georgia", Font.BOLD, 15));
				GridBagConstraints gbc_lblYcenter = new GridBagConstraints();
				gbc_lblYcenter.anchor = GridBagConstraints.EAST;
				gbc_lblYcenter.insets = new Insets(0, 0, 5, 5);
				gbc_lblYcenter.gridx = 1;
				gbc_lblYcenter.gridy = 2;
				panel.add(lblYcenter, gbc_lblYcenter);
			}
			{
				textFieldYcenter = new JTextField();
				textFieldYcenter.setFont(new Font("Georgia", Font.BOLD, 15));
				GridBagConstraints gbc_textFieldYcenter = new GridBagConstraints();
				gbc_textFieldYcenter.insets = new Insets(0, 0, 5, 5);
				gbc_textFieldYcenter.fill = GridBagConstraints.BOTH;
				gbc_textFieldYcenter.gridx = 2;
				gbc_textFieldYcenter.gridy = 2;
				panel.add(textFieldYcenter, gbc_textFieldYcenter);
				textFieldYcenter.setColumns(10);
			}
			{
				JLabel lblRadiusDonut = new JLabel("Enter radius:");
				lblRadiusDonut.setFont(new Font("Georgia", Font.BOLD, 15));
				GridBagConstraints gbc_lblRadiusDonut = new GridBagConstraints();
				gbc_lblRadiusDonut.anchor = GridBagConstraints.EAST;
				gbc_lblRadiusDonut.insets = new Insets(0, 0, 5, 5);
				gbc_lblRadiusDonut.gridx = 1;
				gbc_lblRadiusDonut.gridy = 3;
				panel.add(lblRadiusDonut, gbc_lblRadiusDonut);
			}
			{
				textFieldRadius = new JTextField();
				textFieldRadius.setForeground(new Color(255, 255, 255));
				textFieldRadius.setBackground(new Color(64, 0, 64));
				textFieldRadius.setFont(new Font("Georgia", Font.BOLD, 15));
				GridBagConstraints gbc_textFieldRadius = new GridBagConstraints();
				gbc_textFieldRadius.insets = new Insets(0, 0, 5, 5);
				gbc_textFieldRadius.fill = GridBagConstraints.BOTH;
				gbc_textFieldRadius.gridx = 2;
				gbc_textFieldRadius.gridy = 3;
				panel.add(textFieldRadius, gbc_textFieldRadius);
				textFieldRadius.setColumns(10);
			}
			{
				JLabel lblInner = new JLabel("Enter inner radius:");
				lblInner.setFont(new Font("Georgia", Font.BOLD, 15));
				GridBagConstraints gbc_lblInner = new GridBagConstraints();
				gbc_lblInner.anchor = GridBagConstraints.EAST;
				gbc_lblInner.insets = new Insets(0, 0, 5, 5);
				gbc_lblInner.gridx = 1;
				gbc_lblInner.gridy = 4;
				panel.add(lblInner, gbc_lblInner);
			}
			{
				JButton btnNewButton = new JButton("Outline");
				btnNewButton.setForeground(new Color(255, 255, 255));
				btnNewButton.setBackground(new Color(0, 0, 108));
				btnNewButton.setFont(new Font("Georgia", Font.BOLD | Font.ITALIC, 13));
				btnNewButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						 outlineColor = JColorChooser.showDialog(null,
		                        "Choose a color", Color.BLACK);
					}
				});
				{
					textFieldInner = new JTextField();
					textFieldInner.setForeground(new Color(255, 255, 255));
					textFieldInner.setBackground(new Color(64, 0, 64));
					textFieldInner.setFont(new Font("Georgia", Font.BOLD, 15));
					GridBagConstraints gbc_textFieldInner = new GridBagConstraints();
					gbc_textFieldInner.insets = new Insets(0, 0, 5, 5);
					gbc_textFieldInner.fill = GridBagConstraints.BOTH;
					gbc_textFieldInner.gridx = 2;
					gbc_textFieldInner.gridy = 4;
					panel.add(textFieldInner, gbc_textFieldInner);
					textFieldInner.setColumns(10);
				}
				GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
				gbc_btnNewButton.anchor = GridBagConstraints.EAST;
				gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
				gbc_btnNewButton.gridx = 1;
				gbc_btnNewButton.gridy = 5;
				panel.add(btnNewButton, gbc_btnNewButton);
			}
			{
				JButton btnNewButton_1 = new JButton("Inner");
				btnNewButton_1.setBackground(new Color(255, 255, 255));
				btnNewButton_1.setForeground(new Color(0, 0, 108));
				btnNewButton_1.setFont(new Font("Georgia", Font.BOLD | Font.ITALIC, 13));
				btnNewButton_1.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						innerColor = JColorChooser.showDialog(null,
		                        "Choose a color", Color.WHITE);
					}
				});
				GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
				gbc_btnNewButton_1.anchor = GridBagConstraints.WEST;
				gbc_btnNewButton_1.insets = new Insets(0, 0, 5, 5);
				gbc_btnNewButton_1.gridx = 2;
				gbc_btnNewButton_1.gridy = 5;
				panel.add(btnNewButton_1, gbc_btnNewButton_1);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setFont(new Font("Georgia", Font.BOLD, 13));
				okButton.setBackground(new Color(255, 255, 255));
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							if (textFieldXcenter.getText().isEmpty() || textFieldYcenter.getText().isEmpty() || textFieldRadius.getText().isEmpty() || textFieldInner.getText().isEmpty()) {
						        JOptionPane.showMessageDialog(null, "All fields must be filled!", "Error", JOptionPane.ERROR_MESSAGE);
						        return;
						    }
						     int x = Integer.parseInt(textFieldXcenter.getText());
						     int y = Integer.parseInt(textFieldYcenter.getText());
						     int radius = Integer.parseInt(textFieldRadius.getText());
						     int innerRadius = Integer.parseInt(textFieldInner.getText());
						     donut = new Donut(new Point(x,y), radius, innerRadius);
						     setVisible(false);
						     isOk=true;
						}
						catch(NumberFormatException exc){
							JOptionPane.showMessageDialog(null, "Enter number only!", "Error", JOptionPane.ERROR_MESSAGE);
						}catch(IllegalArgumentException ex)
						{
							JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
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



	
	

	public JTextField getTextFieldXcenter() {
		return textFieldXcenter;
	}

	public void setTextFieldXcenter(JTextField textFieldXcenter) {
		this.textFieldXcenter = textFieldXcenter;
	}

	public JTextField getTextFieldYcenter() {
		return textFieldYcenter;
	}

	public void setTextFieldYcenter(JTextField textFieldYcenter) {
		this.textFieldYcenter = textFieldYcenter;
	}

	public JTextField getTextFieldRadius() {
		return textFieldRadius;
	}

	public void setTextFieldRadius(JTextField textFieldRadius) {
		this.textFieldRadius = textFieldRadius;
	}

	public JTextField getTextFieldInner() {
		return textFieldInner;
	}

	public void setTextFieldInner(JTextField textFieldInner) {
		this.textFieldInner = textFieldInner;
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

	public void setDonut(Donut donut) {
		this.donut = donut;
	}

	public Donut getDonut() {
		return donut;
	}

}
