package mvc;

import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import geometry.Shape;
import observer.Observer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JToggleButton;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.border.LineBorder;
import javax.swing.border.CompoundBorder;



public class FrameDrawing extends JFrame implements Observer{
	
	private JPanel panelCenter;
    private JPanel panelEast;
    private JPanel panelWest;
    private JPanel panelNorth;
    private JPanel panelSouth;

	private JPanel contentPane;
	private ModelDrawing model;
	private ViewDrawing view;
	private ControllerDrawing controller;
	private final ButtonGroup group1 = new ButtonGroup();
	private final ButtonGroup group2 = new ButtonGroup();
	private JToggleButton tglbtnDraw;
	private JButton btnUndo;
    private JButton btnRedo;
    private JToggleButton tglbtnSelect;
    private JButton btnModify;
    private JButton btnDelete;
    private JButton btnToFront;
    private JButton btnToBack;
    private JButton btnBringToFront;
    private JButton btnBringToBack;
    private JButton btnEdgeColor;
    private JButton btnFillColor;
    private JButton btnSave;
    private JButton btnOpen;

    private JToggleButton tglbtnPoint;
    private JToggleButton tglbtnLine;
    private JToggleButton tglbtnCircle;
    private JToggleButton tglbtnRectangle;
    private JToggleButton tglbtnDonut;
    private JToggleButton tglbtnHexagon;
    private JPanel panel;
    private JTextArea logTextArea;
    private JScrollPane logScrollPane;
    
    // Profesionalne neutralne boje
    private final Color HEADER_BG = new Color(64, 0, 64);
    private final Color BUTTON_BG = new Color(240, 240, 240);
    private final Color BUTTON_SELECTED = new Color(64, 0, 64);
    private final Color PANEL_BG = new Color(250, 250, 250);
    private final Color BORDER_COLOR = new Color(180, 180, 180);



	/**
	 * Create the frame.
	 */
	public FrameDrawing(ModelDrawing model) {
	
	    this.model = model;
	    this.view = new ViewDrawing();
	    this.controller = new ControllerDrawing(model, this);
	    this.view.setModel(model);
	   		
		Dimension btnSize = new Dimension(140, 40);
		Dimension shapeBtnSize = new Dimension(100, 38);
		Dimension modeBtnSize = new Dimension(140, 45);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1000, 750);
		setLocationRelativeTo(null);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		contentPane.setBackground(PANEL_BG);
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		// NORTH PANEL - Shape buttons
		panelNorth = new JPanel();
		panelNorth.setLayout(new FlowLayout(FlowLayout.CENTER, 8, 10));
		panelNorth.setBackground(PANEL_BG);
		panelNorth.setBorder(new EmptyBorder(8, 10, 8, 10));
		contentPane.add(panelNorth, BorderLayout.NORTH);
		
		tglbtnPoint = createShapeButton("Point");
		panelNorth.add(tglbtnPoint);
		group1.add(tglbtnPoint);
		
		tglbtnLine = createShapeButton("Line");
		panelNorth.add(tglbtnLine);
		group1.add(tglbtnLine);
		
		tglbtnCircle = createShapeButton("Circle");
		panelNorth.add(tglbtnCircle);
		group1.add(tglbtnCircle);
		
		tglbtnRectangle = createShapeButton("Rectangle");
		panelNorth.add(tglbtnRectangle);
		group1.add(tglbtnRectangle);
		
		tglbtnDonut = createShapeButton("Donut");
		panelNorth.add(tglbtnDonut);
		group1.add(tglbtnDonut);
		
		tglbtnHexagon = createShapeButton("Hexagon");
		panelNorth.add(tglbtnHexagon);
		group1.add(tglbtnHexagon);
		
		panelNorth.add(createHorizontalSpace(20));
		
		btnEdgeColor = new JButton("Outline");
		btnEdgeColor.setPreferredSize(new Dimension(100, 38));
		btnEdgeColor.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnEdgeColor.setBackground(Color.BLACK);
		btnEdgeColor.setForeground(Color.WHITE);
		btnEdgeColor.setFocusPainted(false);
		btnEdgeColor.setBorder(new LineBorder(BORDER_COLOR, 2));
		btnEdgeColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Color chosen = JColorChooser.showDialog(null, "Select Outline Color", btnEdgeColor.getBackground());
		        if (chosen != null)
		            controller.setActiveEdgeColor(chosen);
			}
		});
		panelNorth.add(btnEdgeColor);
		
		btnFillColor = new JButton("Inner");
		btnFillColor.setPreferredSize(new Dimension(100, 38));
		btnFillColor.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnFillColor.setBackground(Color.WHITE);
		btnFillColor.setForeground(Color.BLACK);
		btnFillColor.setFocusPainted(false);
		btnFillColor.setBorder(new LineBorder(BORDER_COLOR, 2));
		btnFillColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Color chosen = JColorChooser.showDialog(null, "Select Fill Color", btnFillColor.getBackground());
		        if (chosen != null)
		            controller.setActiveFillColor(chosen);
			}
		});
		panelNorth.add(btnFillColor);
		
		// WEST PANEL - Control buttons
		JPanel panelWest = new JPanel();
		contentPane.add(panelWest, BorderLayout.WEST);
		panelWest.setLayout(new BoxLayout(panelWest, BoxLayout.Y_AXIS));
		panelWest.setBackground(PANEL_BG);
		panelWest.setBorder(new CompoundBorder(
		    new LineBorder(BORDER_COLOR, 1),
		    new EmptyBorder(10, 8, 10, 8)
		));
		
		// Mode section
		JLabel lblMode = new JLabel("  MODE");
		lblMode.setFont(new Font("Arial", Font.BOLD, 11));
		lblMode.setForeground(new Color(60, 60, 60));
		lblMode.setAlignmentX(CENTER_ALIGNMENT);
		panelWest.add(lblMode);
		panelWest.add(createVerticalSpace(5));
		
		tglbtnDraw = createModeButton("Draw", modeBtnSize);
		panelWest.add(tglbtnDraw);
		group2.add(tglbtnDraw);
		tglbtnDraw.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 controller.switchToDrawMode(); 
					tglbtnPoint.setEnabled(true);
					tglbtnLine.setEnabled(true);
					tglbtnCircle.setEnabled(true);
					tglbtnRectangle.setEnabled(true);
					tglbtnDonut.setEnabled(true);
					tglbtnHexagon.setEnabled(true);
			}
		});
		panelWest.add(createVerticalSpace(3));
		
		tglbtnSelect = createModeButton("Select", modeBtnSize);
		panelWest.add(tglbtnSelect);
		group2.add(tglbtnSelect);
		tglbtnSelect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				group1.clearSelection();
				tglbtnPoint.setEnabled(false);
				tglbtnLine.setEnabled(false);
				tglbtnCircle.setEnabled(false);
				tglbtnRectangle.setEnabled(false);
				tglbtnDonut.setEnabled(false);
				tglbtnHexagon.setEnabled(false);
			}
		});
		
		panelWest.add(createVerticalSpace(15));
		addSeparator(panelWest);
		panelWest.add(createVerticalSpace(10));
		
		// Edit section
		JLabel lblEdit = new JLabel("  EDIT");
		lblEdit.setFont(new Font("Arial", Font.BOLD, 11));
		lblEdit.setForeground(new Color(60, 60, 60));
		lblEdit.setAlignmentX(CENTER_ALIGNMENT);
		panelWest.add(lblEdit);
		panelWest.add(createVerticalSpace(5));
		
		btnModify = createControlButton("Modify", btnSize);
		btnModify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.modifySelected();
			}
		});
		panelWest.add(btnModify);
		panelWest.add(createVerticalSpace(3));
		
		btnDelete = createControlButton("Delete", btnSize);
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.deleteSelected();
			}
		});
		panelWest.add(btnDelete);
		
		panelWest.add(createVerticalSpace(10));
		
		btnUndo = createControlButton("Undo", btnSize);
		btnUndo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.undo();
			}
		});
		panelWest.add(btnUndo);
		panelWest.add(createVerticalSpace(3));
		
		btnRedo = createControlButton("Redo", btnSize);
		btnRedo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.redo();
			}
		});
		panelWest.add(btnRedo);
		
		panelWest.add(createVerticalSpace(15));
		addSeparator(panelWest);
		panelWest.add(createVerticalSpace(10));
		
		// Z-Order section
		JLabel lblOrder = new JLabel("  Z-ORDER");
		lblOrder.setFont(new Font("Arial", Font.BOLD, 11));
		lblOrder.setForeground(new Color(60, 60, 60));
		lblOrder.setAlignmentX(CENTER_ALIGNMENT);
		panelWest.add(lblOrder);
		panelWest.add(createVerticalSpace(5));
		
		btnToFront = createControlButton("To Front", btnSize);
		btnToFront.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.toFront();
			}
		});
		panelWest.add(btnToFront);
		panelWest.add(createVerticalSpace(3));
		
		btnToBack = createControlButton("To Back", btnSize);
		btnToBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.toBack();
			}
		});
		panelWest.add(btnToBack);
		panelWest.add(createVerticalSpace(3));
		
		btnBringToFront = createControlButton("Bring To Front", btnSize);
		btnBringToFront.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.bringToFront();
			}
		});
		panelWest.add(btnBringToFront);
		panelWest.add(createVerticalSpace(3));
		
		btnBringToBack = createControlButton("Bring To Back", btnSize);
		btnBringToBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.bringToBack();
			}
		});
		panelWest.add(btnBringToBack);
		
		// CENTER - Drawing area
        getContentPane().add(view, BorderLayout.CENTER);
        view.setBackground(Color.WHITE);
        view.setEnabled(true);
        view.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				controller.mouseClicked(e);
			}
		});
		
		// SOUTH PANEL - File operations
		panelSouth = new JPanel();
		panelSouth.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 10));
		panelSouth.setBackground(PANEL_BG);
		panelSouth.setBorder(new CompoundBorder(
		    new LineBorder(BORDER_COLOR, 1, false),
		    new EmptyBorder(5, 10, 5, 10)
		));
		contentPane.add(panelSouth, BorderLayout.SOUTH);
		
		btnSave = createFileButton("Save", new Dimension(130, 40));
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] options = {"Save Log", "Save Drawing"};
		        int choice = JOptionPane.showOptionDialog(
		                null,
		                "What do you want to save?",
		                "Save Options",
		                JOptionPane.DEFAULT_OPTION,
		                JOptionPane.QUESTION_MESSAGE,
		                null,
		                options,
		                options[0]
		        );

		        if (choice == 0) {
		            controller.saveLog();
		        } else if (choice == 1) {
		            controller.saveDrawing();
		        }
			}
		});
		panelSouth.add(btnSave);
		
		btnOpen = createFileButton("Open", new Dimension(130, 40));
		btnOpen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			    String[] options = {"Load Log", "Load Drawing"};
		        int choice = JOptionPane.showOptionDialog(
		                null,
		                "What do you want to load?",
		                "Load Options",
		                JOptionPane.DEFAULT_OPTION,
		                JOptionPane.QUESTION_MESSAGE,
		                null,
		                options,
		                options[0]
		        );

		        if (choice == 0) {
		            controller.loadLogFile();
		        } else if (choice == 1) {
		            controller.loadDrawing();
		        }
			}
		});
		panelSouth.add(btnOpen);
		
		// EAST PANEL - Log
		JPanel panelEast = new JPanel();
	    panelEast.setLayout(new BorderLayout());
	    panelEast.setBackground(PANEL_BG);
	    panelEast.setBorder(new CompoundBorder(
	        new LineBorder(BORDER_COLOR, 1),
	        new EmptyBorder(5, 5, 5, 5)
	    ));
	    contentPane.add(panelEast, BorderLayout.EAST);
	    
	    JLabel lblLog = new JLabel("Command Log");
	    lblLog.setFont(new Font("Arial", Font.BOLD, 12));
	    lblLog.setForeground(new Color(60, 60, 60));
	    lblLog.setBorder(new EmptyBorder(5, 5, 8, 5));
	    panelEast.add(lblLog, BorderLayout.NORTH);
	    
	    logTextArea = new JTextArea();
	    logTextArea.setEditable(false);
	    logTextArea.setFont(new Font("Monospaced", Font.PLAIN, 11));
	    logTextArea.setBackground(Color.WHITE);
	    logScrollPane = new JScrollPane(logTextArea);
	    logScrollPane.setPreferredSize(new Dimension(250, 0));
	    logScrollPane.setBorder(new LineBorder(BORDER_COLOR, 1));
	    panelEast.add(logScrollPane, BorderLayout.CENTER);
	    
	    model.addObserver(this);
        update();
        btnUndo.setEnabled(false);
        btnRedo.setEnabled(false);
	}
	
	// Helper methods for creating styled buttons
	private JToggleButton createShapeButton(String text) {
	    JToggleButton btn = new JToggleButton(text);
	    btn.setPreferredSize(new Dimension(100, 38));
	    btn.setFont(new Font("Tahoma", Font.BOLD, 13));
	    btn.setBackground(BUTTON_BG);
	    btn.setForeground(new Color(40, 40, 40));
	    btn.setFocusPainted(false);
	    btn.setBorder(new LineBorder(BORDER_COLOR, 1));
	    return btn;
	}
	
	private JToggleButton createModeButton(String text, Dimension size) {
	    JToggleButton btn = new JToggleButton(text);
	    btn.setMaximumSize(size);
	    btn.setAlignmentX(CENTER_ALIGNMENT);
	    btn.setFont(new Font("Tahoma", Font.BOLD, 14));
	    btn.setBackground(BUTTON_SELECTED);
	    btn.setForeground(Color.WHITE);
	    btn.setFocusPainted(false);
	    btn.setBorder(new CompoundBorder(
	        new LineBorder(new Color(30, 0, 50), 2),
	        new EmptyBorder(8, 10, 8, 10)
	    ));
	    return btn;
	}
	
	private JButton createControlButton(String text, Dimension size) {
	    JButton btn = new JButton(text);
	    btn.setMaximumSize(size);
	    btn.setAlignmentX(CENTER_ALIGNMENT);
	    btn.setFont(new Font("Tahoma", Font.PLAIN, 13));
	    btn.setBackground(BUTTON_BG);
	    btn.setForeground(new Color(40, 40, 40));
	    btn.setFocusPainted(false);
	    btn.setBorder(new CompoundBorder(
	        new LineBorder(BORDER_COLOR, 1),
	        new EmptyBorder(8, 10, 8, 10)
	    ));
	    return btn;
	}
	
	private JButton createFileButton(String text, Dimension size) {
	    JButton btn = new JButton(text);
	    btn.setPreferredSize(size);
	    btn.setFont(new Font("Tahoma", Font.BOLD, 13));
	    btn.setBackground(BUTTON_SELECTED);
	    btn.setForeground(Color.WHITE);
	    btn.setFocusPainted(false);
	    btn.setBorder(new CompoundBorder(
	        new LineBorder(new Color(30, 0, 50), 2),
	        new EmptyBorder(8, 15, 8, 15)
	    ));
	    return btn;
	}
	
	private JPanel createVerticalSpace(int height) {
	    JPanel space = new JPanel();
	    space.setPreferredSize(new Dimension(0, height));
	    space.setMaximumSize(new Dimension(Integer.MAX_VALUE, height));
	    space.setBackground(PANEL_BG);
	    return space;
	}
	
	private JPanel createHorizontalSpace(int width) {
	    JPanel space = new JPanel();
	    space.setPreferredSize(new Dimension(width, 0));
	    space.setBackground(PANEL_BG);
	    return space;
	}
	
	private void addSeparator(JPanel panel) {
	    JPanel separator = new JPanel();
	    separator.setBackground(BORDER_COLOR);
	    separator.setMaximumSize(new Dimension(Integer.MAX_VALUE, 1));
	    separator.setPreferredSize(new Dimension(0, 1));
	    panel.add(separator);
	}
	
	public void update() {
        updateButtonStates();
    }
    
    private void updateButtonStates() {
        List<Shape> selectedShapes = model.getSelected();
        boolean hasSelection = selectedShapes.size() > 0;
        boolean singleSelection = selectedShapes.size() == 1;
        
        Shape selected = singleSelection ? selectedShapes.get(0) : null;
        int index = selected != null ? model.indexOf(selected) : -1;
        int totalShapes = model.getSize();
        
        btnDelete.setEnabled(hasSelection);
        btnModify.setEnabled(singleSelection);
        
        btnToFront.setEnabled(singleSelection && index < totalShapes - 1);
        btnToBack.setEnabled(singleSelection && index > 0);
        btnBringToFront.setEnabled(singleSelection && index < totalShapes - 1);
        btnBringToBack.setEnabled(singleSelection && index > 0);
        
        btnSave.setEnabled(totalShapes > 0);
    }

	public JScrollPane getLogScrollPane() {
	    return logScrollPane;
	}

	public JToggleButton getTglbtnDraw() {
		return tglbtnDraw;
	}

	public JButton getBtnUndo() {
		return btnUndo;
	}

	public JButton getBtnRedo() {
		return btnRedo;
	}

	public JToggleButton getTglbtnSelect() {
		return tglbtnSelect;
	}

	public JButton getBtnModify() {
		return btnModify;
	}

	public JButton getBtnDelete() {
		return btnDelete;
	}

	public JButton getBtnToFront() {
		return btnToFront;
	}

	public JButton getBtnToBack() {
		return btnToBack;
	}

	public JButton getBtnBringToFront() {
		return btnBringToFront;
	}

	public JButton getBtnBringToBack() {
		return btnBringToBack;
	}

	public JButton getBtnEdgeColor() {
		return btnEdgeColor;
	}

	public JButton getBtnFillColor() {
		return btnFillColor;
	}

	public JButton getBtnSave() {
		return btnSave;
	}

	public JButton getBtnOpen() {
		return btnOpen;
	}

	public JToggleButton getTglbtnPoint() {
		return tglbtnPoint;
	}

	public JToggleButton getTglbtnLine() {
		return tglbtnLine;
	}

	public JToggleButton getTglbtnCircle() {
		return tglbtnCircle;
	}

	public JToggleButton getTglbtnRectangle() {
		return tglbtnRectangle;
	}

	public JToggleButton getTglbtnDonut() {
		return tglbtnDonut;
	}

	public JToggleButton getTglbtnHexagon() {
		return tglbtnHexagon;
	}

	public ViewDrawing getView() {
		return view;
	}

	public void setView(ViewDrawing view) {
		this.view = view;
	}

	public ControllerDrawing getController() {
		return controller;
	}

	public void setController(ControllerDrawing controller) {
		this.controller = controller;
	}

	public void setUndoEnabled(boolean enabled) {
	    btnUndo.setEnabled(enabled);
	}

	public void setRedoEnabled(boolean enabled) {
	    btnRedo.setEnabled(enabled);
	}

	public void setModifyEnabled(boolean enabled) {
	    btnModify.setEnabled(enabled);
	}

	public void setDeleteEnabled(boolean enabled) {
	    btnDelete.setEnabled(enabled);
	}

	public void setToFrontEnabled(boolean enabled) {
	    btnToFront.setEnabled(enabled);
	}

	public void setToBackEnabled(boolean enabled) {
	    btnToBack.setEnabled(enabled);
	}

	public void setBringToFrontEnabled(boolean enabled) {
	    btnBringToFront.setEnabled(enabled);
	}

	public void setBringToBackEnabled(boolean enabled) {
	    btnBringToBack.setEnabled(enabled);
	}

	public void setSaveEnabled(boolean enabled) {
	    btnSave.setEnabled(enabled);
	}

	public void setOpenEnabled(boolean enabled) {
	    btnOpen.setEnabled(enabled);
	}
}