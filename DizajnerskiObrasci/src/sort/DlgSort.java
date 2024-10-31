package sort;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;

public class DlgSort extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textX;
	private JTextField textY;
	private JTextField textRadius;
	private boolean isOkay;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DlgSort dialog = new DlgSort();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DlgSort() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		setModal(true);
		{
			JPanel panelTitle = new JPanel();
			panelTitle.setBackground(new Color(64, 0, 128));
			contentPanel.add(panelTitle, BorderLayout.NORTH);
			{
				JLabel lblTitleSort = new JLabel("Enter Circle Parameters");
				lblTitleSort.setFont(new Font("Georgia", Font.BOLD, 17));
				lblTitleSort.setForeground(new Color(255, 255, 255));
				panelTitle.add(lblTitleSort);
			}
		}
		{
			JPanel panel = new JPanel();
			panel.setForeground(new Color(255, 255, 255));
			panel.setBackground(new Color(226, 226, 226));
			contentPanel.add(panel, BorderLayout.CENTER);
			GridBagLayout gbl_panel = new GridBagLayout();
			gbl_panel.columnWidths = new int[]{0, 0, 216, 0, 0};
			gbl_panel.rowHeights = new int[]{36, 35, 37, 28, 0};
			gbl_panel.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
			gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
			panel.setLayout(gbl_panel);
			{
				JLabel lblAddX = new JLabel("Enter the x-coordinate");
				lblAddX.setFont(new Font("Georgia", Font.BOLD, 15));
				lblAddX.setForeground(new Color(0, 0, 0));
				GridBagConstraints gbc_lblAddX = new GridBagConstraints();
				gbc_lblAddX.anchor = GridBagConstraints.EAST;
				gbc_lblAddX.insets = new Insets(0, 0, 5, 5);
				gbc_lblAddX.gridx = 2;
				gbc_lblAddX.gridy = 1;
				panel.add(lblAddX, gbc_lblAddX);
			}
			{
				textX = new JTextField();
				textX.setFont(new Font("Georgia", Font.BOLD, 13));
				textX.setForeground(new Color(255, 255, 255));
				textX.setBackground(new Color(64, 0, 128));
				GridBagConstraints gbc_textX = new GridBagConstraints();
				gbc_textX.anchor = GridBagConstraints.WEST;
				gbc_textX.fill = GridBagConstraints.VERTICAL;
				gbc_textX.insets = new Insets(0, 0, 5, 0);
				gbc_textX.gridx = 3;
				gbc_textX.gridy = 1;
				panel.add(textX, gbc_textX);
				textX.setColumns(10);
			}
			{
				JLabel lblAddY = new JLabel("Enter the y-coordinate");
				lblAddY.setFont(new Font("Georgia", Font.BOLD, 15));
				GridBagConstraints gbc_lblAddY = new GridBagConstraints();
				gbc_lblAddY.anchor = GridBagConstraints.EAST;
				gbc_lblAddY.insets = new Insets(0, 0, 5, 5);
				gbc_lblAddY.gridx = 2;
				gbc_lblAddY.gridy = 2;
				panel.add(lblAddY, gbc_lblAddY);
			}
			{
				textY = new JTextField();
				textY.setFont(new Font("Georgia", Font.BOLD, 13));
				textY.setForeground(new Color(255, 255, 255));
				textY.setBackground(new Color(64, 0, 128));
				GridBagConstraints gbc_textY = new GridBagConstraints();
				gbc_textY.fill = GridBagConstraints.VERTICAL;
				gbc_textY.anchor = GridBagConstraints.WEST;
				gbc_textY.insets = new Insets(0, 0, 5, 0);
				gbc_textY.gridx = 3;
				gbc_textY.gridy = 2;
				panel.add(textY, gbc_textY);
				textY.setColumns(10);
			}
			{
				JLabel lblAddRadius = new JLabel("Enter the radius");
				lblAddRadius.setForeground(new Color(64, 0, 128));
				lblAddRadius.setFont(new Font("Georgia", Font.BOLD, 15));
				GridBagConstraints gbc_lblAddRadius = new GridBagConstraints();
				gbc_lblAddRadius.anchor = GridBagConstraints.EAST;
				gbc_lblAddRadius.insets = new Insets(0, 0, 0, 5);
				gbc_lblAddRadius.gridx = 2;
				gbc_lblAddRadius.gridy = 3;
				panel.add(lblAddRadius, gbc_lblAddRadius);
			}
			{
				textRadius = new JTextField();
				textRadius.setFont(new Font("Georgia", Font.BOLD, 13));
				GridBagConstraints gbc_textRadius = new GridBagConstraints();
				gbc_textRadius.fill = GridBagConstraints.VERTICAL;
				gbc_textRadius.anchor = GridBagConstraints.WEST;
				gbc_textRadius.gridx = 3;
				gbc_textRadius.gridy = 3;
				panel.add(textRadius, gbc_textRadius);
				textRadius.setColumns(10);
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
						isOkay=true;
						setVisible(false);
						
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
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

	public boolean isOkay() {
		return isOkay;
	}

	public void setOkay(boolean isOkay) {
		this.isOkay = isOkay;
	}

	public JTextField getTextX() {
		return textX;
	}

	public JTextField getTextY() {
		return textY;
	}

	public JTextField getTextRadius() {
		return textRadius;
	}


	

}
