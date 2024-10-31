package stack;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Font;

public class DlgStack extends JDialog {
	private boolean isOk;
	private JTextField textAddX;
	private JTextField textAddY;
	private JTextField textRadius;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DlgStack dialog = new DlgStack();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * Create the dialog.
	 */
	public DlgStack() {
		
		getContentPane().setBackground(new Color(255, 255, 255));
		setBounds(100, 100, 450, 300);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{436, 0};
		gridBagLayout.rowHeights = new int[]{21, 214, 31, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		setModal(true);
		
		{
			JPanel panel = new JPanel();
			panel.setBackground(new Color(2, 50, 70));
			GridBagConstraints gbc_panel = new GridBagConstraints();
			gbc_panel.anchor = GridBagConstraints.NORTH;
			gbc_panel.fill = GridBagConstraints.HORIZONTAL;
			gbc_panel.insets = new Insets(0, 0, 5, 0);
			gbc_panel.gridx = 0;
			gbc_panel.gridy = 0;
			getContentPane().add(panel, gbc_panel);
			GridBagLayout gbl_panel = new GridBagLayout();
			gbl_panel.columnWidths = new int[]{436, 0};
			gbl_panel.rowHeights = new int[]{13, 0};
			gbl_panel.columnWeights = new double[]{0.0, Double.MIN_VALUE};
			gbl_panel.rowWeights = new double[]{1.0, Double.MIN_VALUE};
			panel.setLayout(gbl_panel);
			{
				JLabel lblTitleDlg = new JLabel("Enter Circle Parameters");
				lblTitleDlg.setFont(new Font("Georgia", Font.BOLD, 20));
				lblTitleDlg.setForeground(new Color(255, 255, 255));
				lblTitleDlg.setBackground(new Color(0, 128, 192));
				lblTitleDlg.setHorizontalAlignment(SwingConstants.CENTER);
				GridBagConstraints gbc_lblTitleDlg = new GridBagConstraints();
				gbc_lblTitleDlg.gridx = 0;
				gbc_lblTitleDlg.gridy = 0;
				panel.add(lblTitleDlg, gbc_lblTitleDlg);
			}
		}
		{
			JPanel panel = new JPanel();
			panel.setBackground(new Color(246, 246, 246));
			GridBagConstraints gbc_panel = new GridBagConstraints();
			gbc_panel.insets = new Insets(0, 0, 5, 0);
			gbc_panel.gridx = 0;
			gbc_panel.gridy = 1;
			getContentPane().add(panel, gbc_panel);
			GridBagLayout gbl_panel = new GridBagLayout();
			gbl_panel.columnWidths = new int[]{0, 0, 0, 181, 0};
			gbl_panel.rowHeights = new int[]{0, 33, 32, 27, 0};
			gbl_panel.columnWeights = new double[]{0.0, 0.0, 1.0, 1.0, Double.MIN_VALUE};
			gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
			panel.setLayout(gbl_panel);
			{
				JLabel lblXadd = new JLabel("Enter the x-coordinate");
				lblXadd.setFont(new Font("Georgia", Font.BOLD, 15));
				lblXadd.setForeground(new Color(2, 50, 70));
				lblXadd.setBackground(new Color(108,138,155));
				GridBagConstraints gbc_lblXadd = new GridBagConstraints();
				gbc_lblXadd.insets = new Insets(0, 0, 5, 5);
				gbc_lblXadd.gridx = 2;
				gbc_lblXadd.gridy = 1;
				panel.add(lblXadd, gbc_lblXadd);
			}
			{
				textAddX = new JTextField();
				textAddX.setForeground(new Color(255, 255, 255));
				textAddX.setFont(new Font("Georgia", Font.BOLD, 14));
				textAddX.setBackground(new Color(2,50,70));
				GridBagConstraints gbc_textAddX = new GridBagConstraints();
				gbc_textAddX.fill = GridBagConstraints.VERTICAL;
				gbc_textAddX.anchor = GridBagConstraints.WEST;
				gbc_textAddX.insets = new Insets(0, 0, 5, 0);
				gbc_textAddX.gridx = 3;
				gbc_textAddX.gridy = 1;
				panel.add(textAddX, gbc_textAddX);
				textAddX.setColumns(10);
			}
			{
				JLabel lblYadd = new JLabel("Enter the x-coordinate");
				lblYadd.setFont(new Font("Georgia", Font.BOLD, 15));
				lblYadd.setForeground(new Color(2, 50, 70));
				lblYadd.setBackground(new Color(0, 128, 0));
				GridBagConstraints gbc_lblYadd = new GridBagConstraints();
				gbc_lblYadd.insets = new Insets(0, 0, 5, 5);
				gbc_lblYadd.gridx = 2;
				gbc_lblYadd.gridy = 2;
				panel.add(lblYadd, gbc_lblYadd);
			}
			{
				textAddY = new JTextField();
				textAddY.setFont(new Font("Georgia", Font.BOLD, 14));
				textAddY.setForeground(new Color(255, 255, 255));
				textAddY.setBackground(new Color(2,50,70));
				GridBagConstraints gbc_textAddY = new GridBagConstraints();
				gbc_textAddY.fill = GridBagConstraints.VERTICAL;
				gbc_textAddY.anchor = GridBagConstraints.WEST;
				gbc_textAddY.insets = new Insets(0, 0, 5, 0);
				gbc_textAddY.gridx = 3;
				gbc_textAddY.gridy = 2;
				panel.add(textAddY, gbc_textAddY);
				textAddY.setColumns(10);
			}
			{
				JLabel lblRadiusAdd = new JLabel("Enter the radius");
				lblRadiusAdd.setFont(new Font("Georgia", Font.BOLD, 15));
				lblRadiusAdd.setBackground(new Color(0, 0, 160));
				lblRadiusAdd.setForeground(new Color(82, 160, 141));
				GridBagConstraints gbc_lblRadiusAdd = new GridBagConstraints();
				gbc_lblRadiusAdd.insets = new Insets(0, 0, 0, 5);
				gbc_lblRadiusAdd.gridx = 2;
				gbc_lblRadiusAdd.gridy = 3;
				panel.add(lblRadiusAdd, gbc_lblRadiusAdd);
			}
			{
				textRadius = new JTextField();
				textRadius.setFont(new Font("Georgia", Font.BOLD, 14));
				textRadius.setBackground(new Color(82,160,141));
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
			buttonPane.setBackground(new Color(2, 50, 70));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			GridBagConstraints gbc_buttonPane = new GridBagConstraints();
			gbc_buttonPane.anchor = GridBagConstraints.NORTH;
			gbc_buttonPane.fill = GridBagConstraints.HORIZONTAL;
			gbc_buttonPane.gridx = 0;
			gbc_buttonPane.gridy = 2;
			getContentPane().add(buttonPane, gbc_buttonPane);
			{
				JButton okButton = new JButton("OK");
				okButton.setFont(new Font("Georgia", Font.BOLD, 12));
				okButton.setBackground(new Color(255, 255, 255));
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						isOk=true;
						setVisible(false);					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setFont(new Font("Georgia", Font.BOLD, 12));
				cancelButton.setBackground(new Color(255, 255, 255));
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
	public boolean isOk() {
		return isOk;
	}
	public JTextField getTextAddX() {
		return textAddX;
	}
	public JTextField getTextAddY() {
		return textAddY;
	}
	public JTextField getTextRadius() {
		return textRadius;
	}
}