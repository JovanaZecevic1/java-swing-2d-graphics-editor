package drawingObjects;

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

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagLayout;
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
	private JTextField textFieldX2;
	private JTextField textFieldY2;
	private boolean isOk;
	private Color color;
	private Line line;

	

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
		setModal(true);
		color =color.black;
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panelTitle = new JPanel();
			panelTitle.setBackground(new Color(64, 0, 64));
			contentPanel.add(panelTitle, BorderLayout.NORTH);
			{
				JLabel lblLine = new JLabel("Enter Line Coordinates");
				lblLine.setFont(new Font("Georgia", Font.BOLD, 17));
				lblLine.setForeground(new Color(255, 255, 255));
				panelTitle.add(lblLine);
			}
		}
		{
			JPanel panel = new JPanel();
			panel.setBackground(new Color(226,226,226));
			contentPanel.add(panel, BorderLayout.CENTER);
			GridBagLayout gbl_panel = new GridBagLayout();
			gbl_panel.columnWidths = new int[]{93, 0, 78, 117, 0};
			gbl_panel.rowHeights = new int[]{25, 22, 0, 13, 0, 24, 29, 0, 35, 37, 0, 0, 0, 0, 0};
			gbl_panel.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
			gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
			panel.setLayout(gbl_panel);
			{
			}
			{
				JLabel lblX1 = new JLabel("Enter x1 coordinate ");
				lblX1.setBackground(new Color(0, 0, 0));
				lblX1.setForeground(new Color(64, 0, 64));
				lblX1.setFont(new Font("Georgia", Font.BOLD, 15));
				GridBagConstraints gbc_lblX1 = new GridBagConstraints();
				gbc_lblX1.insets = new Insets(0, 0, 5, 5);
				gbc_lblX1.gridx = 1;
				gbc_lblX1.gridy = 4;
				panel.add(lblX1, gbc_lblX1);
			}
			{
				textFieldX1 = new JTextField();
				textFieldX1.setFont(new Font("Georgia", Font.BOLD, 15));
				GridBagConstraints gbc_textFieldX1 = new GridBagConstraints();
				gbc_textFieldX1.fill = GridBagConstraints.HORIZONTAL;
				gbc_textFieldX1.insets = new Insets(0, 0, 5, 5);
				gbc_textFieldX1.gridx = 2;
				gbc_textFieldX1.gridy = 4;
				panel.add(textFieldX1, gbc_textFieldX1);
				textFieldX1.setColumns(10);
			}
			{
				JLabel lblY1 = new JLabel("Enter y1 coordinate ");
				lblY1.setForeground(new Color(64, 0, 64));
				lblY1.setFont(new Font("Georgia", Font.BOLD, 15));
				GridBagConstraints gbc_lblY1 = new GridBagConstraints();
				gbc_lblY1.insets = new Insets(0, 0, 5, 5);
				gbc_lblY1.gridx = 1;
				gbc_lblY1.gridy = 5;
				panel.add(lblY1, gbc_lblY1);
			}
			{
				textFieldY1 = new JTextField();
				textFieldY1.setFont(new Font("Georgia", Font.BOLD, 15));
				GridBagConstraints gbc_textFieldY1 = new GridBagConstraints();
				gbc_textFieldY1.insets = new Insets(0, 0, 5, 5);
				gbc_textFieldY1.fill = GridBagConstraints.HORIZONTAL;
				gbc_textFieldY1.gridx = 2;
				gbc_textFieldY1.gridy = 5;
				panel.add(textFieldY1, gbc_textFieldY1);
				textFieldY1.setColumns(10);
			}
			{
				JLabel lblX2 = new JLabel("Enter x2 coordinate ");
				lblX2.setForeground(new Color(0, 0, 0));
				lblX2.setFont(new Font("Georgia", Font.BOLD, 15));
				GridBagConstraints gbc_lblX2 = new GridBagConstraints();
				gbc_lblX2.anchor = GridBagConstraints.EAST;
				gbc_lblX2.insets = new Insets(0, 0, 5, 5);
				gbc_lblX2.gridx = 1;
				gbc_lblX2.gridy = 6;
				panel.add(lblX2, gbc_lblX2);
			}
			{
				textFieldX2 = new JTextField();
				textFieldX2.setForeground(new Color(255, 255, 255));
				textFieldX2.setFont(new Font("Georgia", Font.BOLD, 15));
				textFieldX2.setBackground(new Color(64, 0, 64));
				GridBagConstraints gbc_textFieldX2 = new GridBagConstraints();
				gbc_textFieldX2.fill = GridBagConstraints.HORIZONTAL;
				gbc_textFieldX2.insets = new Insets(0, 0, 5, 5);
				gbc_textFieldX2.gridx = 2;
				gbc_textFieldX2.gridy = 6;
				panel.add(textFieldX2, gbc_textFieldX2);
				textFieldX2.setColumns(10);
			}
			{
				JLabel lblY2 = new JLabel("Enter y2 coordinate ");
				lblY2.setFont(new Font("Georgia", Font.BOLD, 15));
				GridBagConstraints gbc_lblY2 = new GridBagConstraints();
				gbc_lblY2.anchor = GridBagConstraints.EAST;
				gbc_lblY2.insets = new Insets(0, 0, 5, 5);
				gbc_lblY2.gridx = 1;
				gbc_lblY2.gridy = 7;
				panel.add(lblY2, gbc_lblY2);
			}
			{
				textFieldY2 = new JTextField();
				textFieldY2.setForeground(new Color(255, 255, 255));
				textFieldY2.setFont(new Font("Georgia", Font.BOLD, 15));
				textFieldY2.setBackground(new Color(64, 0, 64));
				GridBagConstraints gbc_textFieldY2 = new GridBagConstraints();
				gbc_textFieldY2.insets = new Insets(0, 0, 5, 5);
				gbc_textFieldY2.fill = GridBagConstraints.HORIZONTAL;
				gbc_textFieldY2.gridx = 2;
				gbc_textFieldY2.gridy = 7;
				panel.add(textFieldY2, gbc_textFieldY2);
				textFieldY2.setColumns(10);
			}
			JButton btnNewButton = new JButton("Choose Color");
			btnNewButton.setFont(new Font("Georgia", Font.BOLD | Font.ITALIC, 13));
			btnNewButton.setForeground(new Color(255, 255, 255));
			btnNewButton.setBackground(new Color(0, 0, 108));
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					color = JColorChooser.showDialog(null,
		                        "Choose a color", Color.BLACK);
				}
			});
			GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
			gbc_btnNewButton.anchor = GridBagConstraints.SOUTHEAST;
			gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
			gbc_btnNewButton.gridx = 1;
			gbc_btnNewButton.gridy = 8;
			panel.add(btnNewButton, gbc_btnNewButton);
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
							
							if (textFieldX1.getText().isEmpty() || textFieldY1.getText().isEmpty() || textFieldX2.getText().isEmpty()|| textFieldY2.getText().isEmpty()) {
						        JOptionPane.showMessageDialog(null, "All fields must be filled!", "Error", JOptionPane.ERROR_MESSAGE);
						        return;
						    }
							
							int x = Integer.parseInt(textFieldX1.getText());
							int y = Integer.parseInt(textFieldY1.getText());
							int x2 = Integer.parseInt(textFieldX2.getText());
							int y2 = Integer.parseInt(textFieldY2.getText());
							Point startPoint = new Point(x,y);
							Point endPoint = new Point(x2,y2);
							line = new Line(startPoint, endPoint);
							isOk = true;
							setVisible(false);
							
							}catch(NumberFormatException exc) {
								JOptionPane.showInternalMessageDialog(null, "Please enter numbers only!", "Warning", JOptionPane.WARNING_MESSAGE);
							}catch(IllegalArgumentException ex)
							{
								JOptionPane.showMessageDialog(DlgLine.this, "Invalid input!", "Error", JOptionPane.ERROR_MESSAGE);
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

	
	
	public JTextField getTextFieldX1() {
		return textFieldX1;
	}

	public boolean isOk() {
		return isOk;
	}

	public JTextField getTextFieldY1() {
		return textFieldY1;
	}

	public void setTextFieldY1(JTextField textFieldY1) {
		this.textFieldY1 = textFieldY1;
	}

	public JTextField getTextFieldX2() {
		return textFieldX2;
	}

	public void setTextFieldX2(JTextField textFieldX2) {
		this.textFieldX2 = textFieldX2;
	}

	public JTextField getTextFieldY2() {
		return textFieldY2;
	}

	public void setTextFieldY2(JTextField textFieldY2) {
		this.textFieldY2 = textFieldY2;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public Line getLine() {
		return line;
	}

	public void setLine(Line line) {
		this.line = line;
	}

	public void setTextFieldX1(JTextField textFieldX1) {
		this.textFieldX1 = textFieldX1;
	}

	public void setOk(boolean isOk) {
		this.isOk = isOk;
	}


	
		
	}


