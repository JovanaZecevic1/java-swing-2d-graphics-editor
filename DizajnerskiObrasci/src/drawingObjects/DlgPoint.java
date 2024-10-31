package drawingObjects;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import geometry.Point;

import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class DlgPoint extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textPointX;
	private JTextField textPointY;
	private boolean isOk;
	private Point point;
	private Color color;


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
		setModal(true);
		color = color.black;
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
				JLabel lblPoint = new JLabel("Enter Point Coordinates");
				lblPoint.setForeground(new Color(255, 255, 255));
				lblPoint.setFont(new Font("Georgia", Font.BOLD, 17));
				panel.add(lblPoint);
			}
		}
		{
			JPanel panel = new JPanel();
			panel.setBackground(new Color(226,226,226));
			contentPanel.add(panel, BorderLayout.CENTER);
			GridBagLayout gbl_panel = new GridBagLayout();
			gbl_panel.columnWidths = new int[]{0, 0, 249, 56, 0, 0, 114, 0};
			gbl_panel.rowHeights = new int[]{23, 32, 35, 20, 30, 0};
			gbl_panel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
			gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
			panel.setLayout(gbl_panel);
			{
				JLabel lblPointX = new JLabel("Enter the x-coordinate");
				lblPointX.setFont(new Font("Georgia", Font.BOLD, 15));
				lblPointX.setForeground(new Color(64, 0, 64));
				GridBagConstraints gbc_lblPointX = new GridBagConstraints();
				gbc_lblPointX.anchor = GridBagConstraints.EAST;
				gbc_lblPointX.insets = new Insets(0, 0, 5, 5);
				gbc_lblPointX.gridx = 2;
				gbc_lblPointX.gridy = 1;
				panel.add(lblPointX, gbc_lblPointX);
			}
			{
				textPointX = new JTextField();
				textPointX.setFont(new Font("Georgia", Font.BOLD, 15));
				textPointX.setBackground(new Color(255, 255, 255));
				textPointX.setForeground(new Color(0, 0, 0));
				GridBagConstraints gbc_textPointX = new GridBagConstraints();
				gbc_textPointX.fill = GridBagConstraints.BOTH;
				gbc_textPointX.insets = new Insets(0, 0, 5, 5);
				gbc_textPointX.gridx = 3;
				gbc_textPointX.gridy = 1;
				panel.add(textPointX, gbc_textPointX);
				textPointX.setColumns(10);
			}
			{
				JLabel lblPointY = new JLabel("Enter the y-coordinate");
				lblPointY.setForeground(new Color(0, 0, 0));
				lblPointY.setFont(new Font("Georgia", Font.BOLD, 15));
				GridBagConstraints gbc_lblPointY = new GridBagConstraints();
				gbc_lblPointY.anchor = GridBagConstraints.EAST;
				gbc_lblPointY.insets = new Insets(0, 0, 5, 5);
				gbc_lblPointY.gridx = 2;
				gbc_lblPointY.gridy = 2;
				panel.add(lblPointY, gbc_lblPointY);
			}
			{
				JButton btnNewButton = new JButton("Choose Color");
				btnNewButton.setForeground(new Color(255, 255, 255));
				btnNewButton.setBackground(new Color(0, 0, 108));
				btnNewButton.setFont(new Font("Georgia", Font.BOLD | Font.ITALIC, 15));
				btnNewButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						color = JColorChooser.showDialog(null,
		                        "Choose a color", Color.BLACK);
					}
				});
				{
					textPointY = new JTextField();
					textPointY.setFont(new Font("Georgia", Font.BOLD, 15));
					textPointY.setBackground(new Color(64, 0, 64));
					textPointY.setForeground(new Color(255, 255, 255));
					GridBagConstraints gbc_textPointY = new GridBagConstraints();
					gbc_textPointY.fill = GridBagConstraints.BOTH;
					gbc_textPointY.insets = new Insets(0, 0, 5, 5);
					gbc_textPointY.gridx = 3;
					gbc_textPointY.gridy = 2;
					panel.add(textPointY, gbc_textPointY);
					textPointY.setColumns(10);
				}
				GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
				gbc_btnNewButton.anchor = GridBagConstraints.EAST;
				gbc_btnNewButton.fill = GridBagConstraints.VERTICAL;
				gbc_btnNewButton.insets = new Insets(0, 0, 0, 5);
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
				okButton.setBackground(new Color(255, 255, 255));
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						
						try {
							
							if (textPointX.getText().isEmpty() || textPointY.getText().isEmpty()) {
						        JOptionPane.showMessageDialog(null, "All fields must be filled!", "Error", JOptionPane.ERROR_MESSAGE);
						        return;
						    }
						int x = Integer.parseInt(textPointX.getText());
						int y = Integer.parseInt(textPointY.getText());
						
						 
						point = new Point(x,y);
						setVisible(false);
						isOk = true;
						}
						catch(NumberFormatException exc) {
							JOptionPane.showInternalMessageDialog(null, "Please enter numbers only!", "Warning", JOptionPane.WARNING_MESSAGE);
						}catch(IllegalArgumentException ex)
						{
							JOptionPane.showMessageDialog(DlgPoint.this, "Must be positive!", "Error", JOptionPane.ERROR_MESSAGE);
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

	public JTextField getTextXCoord() {
		return textPointX;
	}

	public void setTextXCoord(JTextField textXCoord) {
		this.textPointX = textXCoord;
	}

	public JTextField getTextYCoord() {
		return textPointY;
	}

	public void setTextYCoord(JTextField textYCoord) {
		this.textPointY = textYCoord;
	}

	public boolean isOk() {
		return isOk;
	}

	public void setOk(boolean isOkay) {
		this.isOk = isOkay;
	}

	public JTextField getTextPointX() {
		return textPointX;
	}

	public void setTextPointX(JTextField textPointX) {
		this.textPointX = textPointX;
	}

	public JTextField getTextPointY() {
		return textPointY;
	}

	public void setTextPointY(JTextField textPointY) {
		this.textPointY = textPointY;
	}

	public Point getPoint() {
		return point;
	}

	public void setPoint(Point point) {
		this.point = point;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}
	

}
