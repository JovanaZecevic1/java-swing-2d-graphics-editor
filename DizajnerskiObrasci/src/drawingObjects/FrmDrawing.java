package drawingObjects;

import java.awt.EventQueue;


import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import geometry.Shape;

import geometry.Circle;
import geometry.Donut;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JToolBar;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JToggleButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import javax.swing.JSpinner;
import java.awt.Font;

public class FrmDrawing extends JFrame {

	private JPanel contentPane_1;
	private final ButtonGroup buttonGroup1 = new ButtonGroup();
	private final ButtonGroup buttonGroup2 = new ButtonGroup();
	private PnlDrawing panelDrawing;
	private Point startPoint;


	
	
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmDrawing frame = new FrmDrawing();
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
	public FrmDrawing() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane_1 = new JPanel();
		contentPane_1.setBackground(new Color(64, 0, 64));
		contentPane_1.setBorder(new EmptyBorder(5, 5, 5, 5));
		setTitle("Zecevic Jovana IT41/2022");
		
		panelDrawing = new PnlDrawing();
		panelDrawing.setBackground(new Color(255, 255, 255));
		
		setContentPane(contentPane_1);
		contentPane_1.setLayout(new BorderLayout(0, 0));
		
		JToolBar toolBarNorth = new JToolBar();
		toolBarNorth.setForeground(new Color(255, 255, 255));
		toolBarNorth.setBackground(new Color(64, 0, 64));
		contentPane_1.add(toolBarNorth, BorderLayout.NORTH);
		
		JToggleButton tglBtnPoint = new JToggleButton("Point");
		tglBtnPoint.setForeground(new Color(64, 0, 64));
		tglBtnPoint.setBackground(new Color(255, 255, 255));
		tglBtnPoint.setFont(new Font("Georgia", Font.BOLD, 15));
		buttonGroup1.add(tglBtnPoint);
		toolBarNorth.add(tglBtnPoint);
		
		JToggleButton tglBtnLine = new JToggleButton("Line");
		tglBtnLine.setForeground(new Color(64, 0, 64));
		tglBtnLine.setBackground(new Color(255, 255, 255));
		tglBtnLine.setFont(new Font("Georgia", Font.BOLD, 15));
		buttonGroup1.add(tglBtnLine);
		toolBarNorth.add(tglBtnLine);
		
		JToggleButton tglBtnCircle = new JToggleButton("Circle");	
		tglBtnCircle.setForeground(new Color(64, 0, 64));
		tglBtnCircle.setBackground(new Color(255, 255, 255));
		tglBtnCircle.setFont(new Font("Georgia", Font.BOLD, 15));
		buttonGroup1.add(tglBtnCircle);
		toolBarNorth.add(tglBtnCircle);
		
		JToggleButton tglBtnRect = new JToggleButton("Rectangle");
		tglBtnRect.setForeground(new Color(64, 0, 64));
		tglBtnRect.setBackground(new Color(255, 255, 255));
		tglBtnRect.setFont(new Font("Georgia", Font.BOLD, 15));
		buttonGroup1.add(tglBtnRect);
		toolBarNorth.add(tglBtnRect);
		
		JToggleButton tglBtnDonut = new JToggleButton("Donut");
		tglBtnDonut.setForeground(new Color(64, 0, 64));
		tglBtnDonut.setBackground(new Color(255, 255, 255));
		tglBtnDonut.setFont(new Font("Georgia", Font.BOLD, 15));
		buttonGroup1.add(tglBtnDonut);
		toolBarNorth.add(tglBtnDonut);

		
		JToolBar toolBarSouth = new JToolBar();
		toolBarSouth.setForeground(new Color(255, 255, 255));
		toolBarSouth.setBackground(new Color(64, 0, 64));
		toolBarSouth.setFont(new Font("Georgia", Font.BOLD, 15));
		contentPane_1.add(toolBarSouth, BorderLayout.SOUTH);
		
		JToggleButton tglbtnDraw = new JToggleButton("Draw");
		tglbtnDraw.setBackground(new Color(255, 230, 255));
		tglbtnDraw.setForeground(new Color(0, 0, 0));
		tglbtnDraw.setFont(new Font("Georgia", Font.BOLD | Font.ITALIC, 16));
		toolBarSouth.add(tglbtnDraw);
		buttonGroup2.add(tglbtnDraw);
		
		JToggleButton tglbtnSelected = new JToggleButton("Select");
		tglbtnSelected.setForeground(new Color(0, 0, 0));
		tglbtnSelected.setBackground(new Color(255, 230, 255));
		tglbtnSelected.setFont(new Font("Georgia", Font.BOLD | Font.ITALIC, 15));
		tglbtnSelected.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttonGroup1.clearSelection();
			}
		});
		toolBarSouth.add(tglbtnSelected);
		buttonGroup2.add(tglbtnSelected);
		
		JButton btnModify = new JButton("Modify");
		btnModify.setForeground(new Color(0, 0, 0));
		btnModify.setBackground(new Color(255, 230, 255));
		btnModify.setFont(new Font("Georgia", Font.BOLD | Font.ITALIC, 15));
		btnModify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelDrawing.modify();
			}
		});
		toolBarSouth.add(btnModify);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setForeground(new Color(0, 0, 0));
		btnDelete.setBackground(new Color(255, 230, 255));
		btnDelete.setFont(new Font("Georgia", Font.BOLD | Font.ITALIC, 15));
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelDrawing.delete();
			}
		});
		toolBarSouth.add(btnDelete);
		
		
		panelDrawing.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int x = e.getX();
				int y = e.getY();
				if(tglbtnDraw.isSelected()) {
					if(tglBtnPoint.isSelected()) {
						DlgPoint dlg = new DlgPoint();
						dlg.getTextPointX().setText(String.valueOf(x));
		                dlg.getTextPointY().setText(String.valueOf(y));
		                dlg.setVisible(true);
		                if(dlg.isOk()==true) {
		                	Point point = dlg.getPoint();
		                	Color color = dlg.getColor();
		                    panelDrawing.addShape(point, color);
		                }
					}
					if(tglBtnLine.isSelected()) {
						if(startPoint == null) {
							startPoint = new Point(x,y);
						}
						else {
							DlgLine dlg = new DlgLine();
							dlg.getTextFieldX1().setText(String.valueOf(startPoint.getX()));
							dlg.getTextFieldY1().setText(String.valueOf(startPoint.getY()));
							dlg.getTextFieldX2().setText(String.valueOf(x));
							dlg.getTextFieldY2().setText(String.valueOf(y));
							dlg.setVisible(true);
							if(dlg.isOk()==true) {
								Line line = dlg.getLine();
								Color color = dlg.getColor();
								panelDrawing.addShape(line, color);
							}
							startPoint = null;
						}
					}if(tglBtnRect.isSelected()) {
						DlgRectangle dlg = new DlgRectangle();
						dlg.getTextFieldX().setText(String.valueOf(x));
						dlg.getTextFieldY().setText(String.valueOf(y));
						dlg.setVisible(true);
						if(dlg.isOk()==true) {
							Rectangle rectangle = dlg.getRectangle();
							Color outlineColor = dlg.getOutlineColor();
							Color innerColor = dlg.getInnerColor();
							panelDrawing.addShape(rectangle, innerColor,outlineColor);
						}
					}
					if(tglBtnCircle.isSelected()) {
						DlgCircle dlg = new DlgCircle();
						dlg.getTextFieldXCenter().setText(String.valueOf(x));
						dlg.getTextFieldYCenter().setText(String.valueOf(y));
						dlg.setVisible(true);
						if(dlg.isOk()) {
							Circle circle = dlg.getCircle();
							Color innerColor = dlg.getInnerColor();
							Color outlineColor = dlg.getOutlineColor();
							panelDrawing.addShape(circle, innerColor, outlineColor);
							
						}
					}
					if(tglBtnDonut.isSelected()) {
						DlgDonut dlg = new DlgDonut();
						dlg.getTextFieldXcenter().setText(String.valueOf(x));
		                dlg.getTextFieldYcenter().setText(String.valueOf(y));
		                dlg.setVisible(true);
		                if(dlg.isOk()) {
		                	Donut donut = dlg.getDonut();
		                	Color innerColor = dlg.getInnerColor();
		                	Color outlineColor = dlg.getOutlineColor();
		                	panelDrawing.addShape(donut, innerColor, outlineColor);
		                	}
					}
				}
				if(tglbtnSelected.isSelected()) {
					panelDrawing.selected(x,y);
				}
			}
		});
		contentPane_1.add(panelDrawing, BorderLayout.CENTER);
	}
	
	
	
	
	
	

	
	
	

}
