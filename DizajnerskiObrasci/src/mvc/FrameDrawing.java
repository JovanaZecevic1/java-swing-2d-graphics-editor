package mvc;
import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.*;
import geometry.*;
import drawingObjects.*;


public class FrameDrawing extends JFrame {
	private ModelDrawing model;
    private ViewDrawing view;
    private ControllerDrawing controller;
    
    private JPanel contentPane;
    private ButtonGroup buttonGroupShapes;
    private ButtonGroup buttonGroupMode;
    
    private JToggleButton tglBtnPoint;
    private JToggleButton tglBtnLine;
    private JToggleButton tglBtnCircle;
    private JToggleButton tglBtnRect;
    private JToggleButton tglBtnDonut;
    
    private JToggleButton tglBtnDraw;
    private JToggleButton tglBtnSelect;
    
    private Point startPoint;
    
    public FrameDrawing() {
        // Kreiranje MVC komponenti
        model = new ModelDrawing();
        view = new ViewDrawing(model);
        controller = new ControllerDrawing(model, view);
        
        // Podesavanje frame-a
        setTitle("Drawing Application - MVC");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 800, 600);
        
        // Content pane
        contentPane = new JPanel();
        contentPane.setBackground(new Color(64, 0, 64));
        contentPane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout());
        setContentPane(contentPane);
        
        // Kreiranje button grupa
        buttonGroupShapes = new ButtonGroup();
        buttonGroupMode = new ButtonGroup();
        
        // Inicijalizacija komponenti
        initToolbars();
        initCanvas();
    }
    private void initToolbars() {
        // North toolbar - dugmici za oblike
        JToolBar toolBarNorth = new JToolBar();
        toolBarNorth.setBackground(new Color(64, 0, 64));
        
        tglBtnPoint = new JToggleButton("Point");
        tglBtnPoint.setFont(new Font("Georgia", Font.BOLD, 15));
        buttonGroupShapes.add(tglBtnPoint);
        toolBarNorth.add(tglBtnPoint);
        
        tglBtnLine = new JToggleButton("Line");
        tglBtnLine.setFont(new Font("Georgia", Font.BOLD, 15));
        buttonGroupShapes.add(tglBtnLine);
        toolBarNorth.add(tglBtnLine);
        
        tglBtnCircle = new JToggleButton("Circle");
        tglBtnCircle.setFont(new Font("Georgia", Font.BOLD, 15));
        buttonGroupShapes.add(tglBtnCircle);
        toolBarNorth.add(tglBtnCircle);
        
        tglBtnRect = new JToggleButton("Rectangle");
        tglBtnRect.setFont(new Font("Georgia", Font.BOLD, 15));
        buttonGroupShapes.add(tglBtnRect);
        toolBarNorth.add(tglBtnRect);
        
        tglBtnDonut = new JToggleButton("Donut");
        tglBtnDonut.setFont(new Font("Georgia", Font.BOLD, 15));
        buttonGroupShapes.add(tglBtnDonut);
        toolBarNorth.add(tglBtnDonut);
        
        contentPane.add(toolBarNorth, BorderLayout.NORTH);
        
     // South toolbar - Draw, Select, Modify, Delete
        JToolBar toolBarSouth = new JToolBar();
        toolBarSouth.setBackground(new Color(64, 0, 64));
        
        tglBtnDraw = new JToggleButton("Draw");
        tglBtnDraw.setFont(new Font("Georgia", Font.BOLD, 16));
        buttonGroupMode.add(tglBtnDraw);
        toolBarSouth.add(tglBtnDraw);
        
        tglBtnSelect = new JToggleButton("Select");
        tglBtnSelect.setFont(new Font("Georgia", Font.BOLD, 15));
        tglBtnSelect.addActionListener(e -> buttonGroupShapes.clearSelection());
        buttonGroupMode.add(tglBtnSelect);
        toolBarSouth.add(tglBtnSelect);
        
        JButton btnModify = new JButton("Modify");
        btnModify.setFont(new Font("Georgia", Font.BOLD, 15));
        btnModify.addActionListener(e -> controller.modifySelected());
        toolBarSouth.add(btnModify);
        
        JButton btnDelete = new JButton("Delete");
        btnDelete.setFont(new Font("Georgia", Font.BOLD, 15));
        btnDelete.addActionListener(e -> controller.deleteSelected());
        toolBarSouth.add(btnDelete);
        
        contentPane.add(toolBarSouth, BorderLayout.SOUTH);
    }
    
    private void initCanvas() {
        view.setBackground(Color.WHITE);
        
        view.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();
                
                if (tglBtnDraw.isSelected()) {
                    handleDrawMode(x, y);
                } else if (tglBtnSelect.isSelected()) {
                    controller.selectShape(x, y);
                }
            }
        });
        
        contentPane.add(view, BorderLayout.CENTER);
    }
    
    private void handleDrawMode(int x, int y) {
        if (tglBtnPoint.isSelected()) {
            controller.addPoint(x, y);
        } else if (tglBtnLine.isSelected()) {
            if (startPoint == null) {
                startPoint = new geometry.Point(x, y);
            } else {
                controller.addLine(startPoint, x, y);
                startPoint = null;
            }
        } else if (tglBtnCircle.isSelected()) {
            controller.addCircle(x, y);
        } else if (tglBtnRect.isSelected()) {
            controller.addRectangle(x, y);
        } else if (tglBtnDonut.isSelected()) {
            controller.addDonut(x, y);
        }
    }
    

}
