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
import java.awt.event.ActionEvent;



public class FrameDrawing extends JFrame {
	
	private JPanel panelCenter;
    private JPanel panelEast;
    private JPanel panelWest;
    private JPanel panelNorth;

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
    //private DefaultListModel<String> logListModel;
    //private JList<String> logList;
    
    //private Color edgeColor = Color.BLACK;
    //private Color fillColor = Color.WHITE;



	/**
	 * Create the frame.
	 */
	public FrameDrawing(ModelDrawing model) {
	
	    this.model = model;
	    this.view = new ViewDrawing();
	    this.controller = new ControllerDrawing(model, this);
	    this.view.setModel(model);
		
		
		Dimension btnSize = new Dimension(120, 40);
		Dimension shapeBtnSize = new Dimension(100,30);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setSize(900,700);

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		panelNorth = new JPanel();
		panelNorth.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 5));
		contentPane.add(panelNorth, BorderLayout.NORTH);
		
		tglbtnPoint = new JToggleButton("Point");
		panelNorth.add(tglbtnPoint);
		group1.add(tglbtnPoint);
		tglbtnPoint.setPreferredSize(shapeBtnSize);
		
		tglbtnLine = new JToggleButton("Line");
		panelNorth.add(tglbtnLine);
		group1.add(tglbtnLine);
		tglbtnLine.setPreferredSize(shapeBtnSize);
		
		tglbtnCircle = new JToggleButton("Circle");
		panelNorth.add(tglbtnCircle);
		group1.add(tglbtnCircle);
		tglbtnCircle.setPreferredSize(shapeBtnSize);
		
		tglbtnRectangle = new JToggleButton("Rectangle");
		panelNorth.add(tglbtnRectangle);
		group1.add(tglbtnRectangle);
		tglbtnRectangle.setPreferredSize(shapeBtnSize);
		
		tglbtnDonut = new JToggleButton("Donut");
		panelNorth.add(tglbtnDonut);
		group1.add(tglbtnDonut);
		tglbtnDonut.setPreferredSize(shapeBtnSize);
		
		tglbtnHexagon = new JToggleButton("Hexagon");
		panelNorth.add(tglbtnHexagon);
		group1.add(tglbtnHexagon);
		tglbtnHexagon.setPreferredSize(shapeBtnSize);
		
		JPanel panelWest = new JPanel();
		contentPane.add(panelWest, BorderLayout.WEST);
		panelWest.setLayout(new BoxLayout(panelWest, BoxLayout.Y_AXIS));
		
		tglbtnDraw = new JToggleButton("Draw");
		panelWest.add(tglbtnDraw);
		group2.add(tglbtnDraw);
		tglbtnDraw.setMaximumSize(btnSize);
		tglbtnDraw.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 controller.switchToDrawMode(); 
			}
		});
		
		tglbtnSelect = new JToggleButton("Select");
		panelWest.add(tglbtnSelect);
		group2.add(tglbtnSelect);
		tglbtnSelect.setMaximumSize(btnSize);
		tglbtnSelect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
	
		
		btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.deleteSelected();
			}
		});
		panelWest.add(btnDelete);
		btnDelete.setMaximumSize(btnSize);
		
		btnModify = new JButton("Modify");
		btnModify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.modifySelected();
			}
		});
		panelWest.add(btnModify);
		btnModify.setMaximumSize(btnSize);
		
		btnUndo = new JButton("Undo");
		btnUndo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.undo();
				
			}
		});
		panelWest.add(btnUndo);
		btnUndo.setMaximumSize(btnSize);
		
		btnRedo = new JButton("Redo");
		btnRedo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.redo();
				
			}
		});
		panelWest.add(btnRedo);
		btnRedo.setMaximumSize(btnSize);
		
		btnToFront = new JButton("To Front");
		btnToFront.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.toFront();
			}
		});
		panelWest.add(btnToFront);
		btnToFront.setMaximumSize(btnSize);
		
		btnToBack = new JButton("To Back");
		btnToBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.toBack();
			}
		});
		panelWest.add(btnToBack);
		btnToBack.setMaximumSize(btnSize);
		
		btnBringToFront = new JButton("Bring To Front");
		btnBringToFront.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.bringToFront();
			}
		});
		panelWest.add(btnBringToFront);
		btnBringToFront.setMaximumSize(btnSize);
		
		btnBringToBack = new JButton("Bring To Back");
		btnBringToBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.bringToBack();
			}
		});
		panelWest.add(btnBringToBack);
		btnBringToBack.setMaximumSize(btnSize);
		
		btnEdgeColor = new JButton("Outline");
		btnEdgeColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Color chosen = JColorChooser.showDialog(null, "Select Outline Color", btnEdgeColor.getBackground());
		        if (chosen != null)
		            controller.setActiveEdgeColor(chosen);

			}
		});
		panelWest.add(btnEdgeColor);
		btnEdgeColor.setMaximumSize(btnSize);
		
		btnFillColor = new JButton("Inner");
		btnFillColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				  Color chosen = JColorChooser.showDialog(null, "Select Edge Color", btnFillColor.getBackground());
			        if (chosen != null)
			            controller.setActiveFillColor(chosen);
			
				
			}
		});
		panelWest.add(btnFillColor);
		btnFillColor.setMaximumSize(btnSize);
		
		btnSave = new JButton("Save");
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
		            // Save Log
		            controller.saveLog();
		        } else if (choice == 1) {
		            // Save Drawing
		            controller.saveDrawing();
		        }

		      
			}
		});
		panelWest.add(btnSave);
		btnSave.setMaximumSize(btnSize);
		
		btnOpen = new JButton("Open");
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
		            // Load Log
		            controller.loadLogFile();
		        } else if (choice == 1) {
		            // Load Drawing
		            controller.loadDrawing();
		        }
			}
		});
		panelWest.add(btnOpen);
		btnOpen.setMaximumSize(btnSize);
		
		panelCenter = new JPanel();
        getContentPane().add(view, BorderLayout.CENTER);
        view.setBackground(new Color(255,255,255));
        //pnlCenter.add(drawingView, BorderLayout.CENTER);
        view.setEnabled(true);
        view.setBackground(new Color(255, 255, 255));
        view.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent e) {
				controller.mouseClicked(e);
			}
		});
		
		panel = new JPanel();
		panelCenter.add(panel);
		
		JPanel panelEast = new JPanel();
	    panelEast.setLayout(new BorderLayout());
	    contentPane.add(panelEast, BorderLayout.EAST);
	    
	    JLabel lblLog = new JLabel("Command Log:");
	    panelEast.add(lblLog, BorderLayout.NORTH);
	    
	    logTextArea = new JTextArea();
	    logTextArea.setEditable(false);
	    logScrollPane = new JScrollPane(logTextArea);
	    logScrollPane.setPreferredSize(new Dimension(250, 0));
	    panelEast.add(logScrollPane, BorderLayout.CENTER);
		
		
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


