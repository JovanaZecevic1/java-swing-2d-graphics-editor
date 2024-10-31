package sort;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import geometry.Circle;
import geometry.Point;
import stack.DlgStack;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class FrmSort extends JFrame {

	private JPanel contentPane;
	private DefaultListModel<Circle> dlm = new DefaultListModel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmSort frame = new FrmSort();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FrmSort() {
		setTitle("Zecevic Jovana IT41/22");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(64, 0, 64));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panelCenter = new JPanel();
		panelCenter.setBackground(new Color(64, 0, 64));
		contentPane.add(panelCenter, BorderLayout.NORTH);
		
		JLabel lblSort = new JLabel("SORT CIRCLES");
		lblSort.setForeground(new Color(255, 255, 255));
		lblSort.setFont(new Font("Georgia", Font.BOLD, 20));
		panelCenter.add(lblSort);
		
		JList<Circle> listCirclesSort = new JList<>();
		listCirclesSort.setFont(new Font("Georgia", Font.BOLD, 12));
		contentPane.add(listCirclesSort, BorderLayout.CENTER);
		listCirclesSort.setModel(dlm);
		
		JPanel panelEast = new JPanel();
		panelEast.setBackground(new Color(64, 0, 64));
		contentPane.add(panelEast, BorderLayout.EAST);
		
		JButton btnSort = new JButton("SORT");
		btnSort.setBackground(new Color(255, 255, 255));
		btnSort.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(dlm.isEmpty()) {
					JOptionPane.showMessageDialog(null, "List is empty! ", "Error", JOptionPane.WARNING_MESSAGE);
				} else {
				ArrayList<Circle> listCircles = Collections.list(dlm.elements());
                listCircles.sort(new Comparator<Circle>() {
                	@Override
                    public int compare(Circle c1, Circle c2) {
                        return Double.compare(c1.getRadius() * c1.getRadius() * Math.PI, c2.getRadius() * c2.getRadius() * Math.PI);
                    }
                
                });
                dlm.removeAllElements();
                for (Circle circle : listCircles) {
                    dlm.addElement(circle);
                }
				}}}
		);
		btnSort.setFont(new Font("Georgia", Font.BOLD, 17));
		btnSort.setHorizontalAlignment(SwingConstants.RIGHT);
		panelEast.add(btnSort);
		
		JPanel panelWest = new JPanel();
		panelWest.setBackground(new Color(64, 0, 64));
		contentPane.add(panelWest, BorderLayout.WEST);
		
		JButton btnAdd = new JButton("ADD");
		btnAdd.setForeground(new Color(255, 255, 255));
		btnAdd.setBackground(new Color(0, 198, 255));
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DlgSort dlg = new DlgSort();
				dlg.setVisible(true);
				if(dlg.isOkay() == true)
				{   try {
				    if (dlg.getTextX().getText().isEmpty() || dlg.getTextY().getText().isEmpty() || dlg.getTextRadius().getText().isEmpty()) {
				        JOptionPane.showMessageDialog(null, "All fields must be filled!", "Error", JOptionPane.ERROR_MESSAGE);
				        return;
				    }

				    int x = Integer.parseInt(dlg.getTextX().getText());
				    int y = Integer.parseInt(dlg.getTextY().getText());
				    int radius = Integer.parseInt(dlg.getTextRadius().getText());

				    Circle circle = new Circle(new Point(x, y), radius);

				    dlm.add(0, circle);
				} catch (NumberFormatException ex) {
				    JOptionPane.showMessageDialog(null, "Invalid number input.", "Error", JOptionPane.ERROR_MESSAGE);
				} catch (IllegalArgumentException ex) {
				    JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
				}
				}
		});
			
		btnAdd.setFont(new Font("Georgia", Font.BOLD, 17));
		panelWest.add(btnAdd);
	}

}
