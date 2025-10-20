package mvc;

import geometry.*;
import strategy.*;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;


import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import command.Command;
import command.DeselectCommand;
import command.AddShapeCommand;
import command.BringToBackCommand;
import command.BringToFrontCommand;
import command.RemoveShapeCommand;
import command.SelectCommand;
import command.ToBackCommand;
import command.ToFrontCommand;
import command.UpdateCircleCommand;
import command.UpdateDonutCommand;
import command.UpdateHexagonCommand;
import command.UpdateLineCommand;
import command.UpdatePointCommand;
import command.UpdateRectangleCommand;
import drawingObjects.*;

public class ControllerDrawing {

	private ModelDrawing model;
	//private ViewDrawing view;
	private FrameDrawing frame;
	private Point startPoint;

	
    private Stack<Command> undoStack = new Stack<>();
    private Stack<Command> redoStack = new Stack<>();
    
    private Color activeEdgeColor = Color.BLACK;
    private Color activeFillColor = Color.WHITE;
    private List<String> commandLog = new ArrayList<>();
    
    private SaveManager save = new SaveManager();
    private LoadManager load = new LoadManager();
    
    private CommandParser commandParser;
	
	public ControllerDrawing(ModelDrawing model, FrameDrawing frame) {
		this.model = model;
		this.frame=frame;
		this.commandParser = new CommandParser(this);
	}
	
	public boolean canUndo() {
	    return !undoStack.isEmpty();
	}

	public boolean canRedo() {
	    return !redoStack.isEmpty();
	}

	
	public void executeCommand(Command cmd) {                                  
	    undoStack.push(cmd);              
	    redoStack.clear();  
	    cmd.execute(); 
	    logCommand(cmd.executeLog());
	    frame.repaint();
	   updateButtonStates(); 
	    
	}

	public void undo() {
	    if (canUndo()) {
	        Command cmd = undoStack.pop();             
	        redoStack.push(cmd);
	        cmd.unexecute(); 
	        logCommand("Undo: " + cmd.executeLog());
	        frame.repaint();
	       updateButtonStates(); 
	        
	    }
	    
	}
	
	

	public void redo() {
	    if (canRedo()) {
	        Command cmd = redoStack.pop();         
	        undoStack.push(cmd);
	        cmd.execute();  
	        logCommand("Redo: " + cmd.executeLog());
	        frame.repaint();
	        updateButtonStates(); 
	    }
	    
	}

	


	
	public void mouseClicked(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		if(frame.getTglbtnDraw().isSelected()) {
			if(frame.getTglbtnPoint().isSelected()) {
				DlgPoint dlg = new DlgPoint();
				dlg.getTextPointX().setText(String.valueOf(x));
                dlg.getTextPointY().setText(String.valueOf(y));
                dlg.setColor(activeEdgeColor);
                dlg.setVisible(true);
                if(dlg.isOk()==true) {
                	Point point = dlg.getPoint();
                	Color color = dlg.getColor();
                	point.setOutlineColor(color);
                	activeEdgeColor = color;
                    frame.getBtnEdgeColor().setBackground(color);
                	executeCommand(new AddShapeCommand(model, point));
                    
                }
			}
			if(frame.getTglbtnLine().isSelected()) {
				if(startPoint == null) {
					startPoint = new Point(x,y);
				}
				else {
					DlgLine dlg = new DlgLine();
					dlg.getTextFieldX1().setText(String.valueOf(startPoint.getX()));
					dlg.getTextFieldY1().setText(String.valueOf(startPoint.getY()));
					dlg.getTextFieldX2().setText(String.valueOf(x));
					dlg.getTextFieldY2().setText(String.valueOf(y));
					dlg.setColor(activeEdgeColor);
					dlg.setVisible(true);
					if(dlg.isOk()==true) {
						Line line = dlg.getLine();
						Color color = dlg.getColor();
						line.setOutlineColor(color);
						activeEdgeColor = color;
				        frame.getBtnEdgeColor().setBackground(color);
                        executeCommand(new AddShapeCommand(model, line));
					}
					startPoint = null;
				}
			}if(frame.getTglbtnRectangle().isSelected()) {
				DlgRectangle dlg = new DlgRectangle();
				dlg.getTextFieldX().setText(String.valueOf(x));
				dlg.getTextFieldY().setText(String.valueOf(y));
				dlg.setOutlineColor(activeEdgeColor);
			    dlg.setInnerColor(activeFillColor);
				dlg.setVisible(true);
				if(dlg.isOk()==true) {
					Rectangle rectangle = dlg.getRectangle();
					Color outlineColor = dlg.getOutlineColor();
					Color innerColor = dlg.getInnerColor();
					rectangle.setOutlineColor(outlineColor);  
	                rectangle.setInnerColor(innerColor);
	                activeEdgeColor = outlineColor;
	                activeFillColor = innerColor;
	                frame.getBtnEdgeColor().setBackground(outlineColor);
	                frame.getBtnFillColor().setBackground(innerColor);
	                executeCommand(new AddShapeCommand(model, rectangle));
				}
			}
			if(frame.getTglbtnCircle().isSelected()) {
				DlgCircle dlg = new DlgCircle();
				dlg.getTextFieldXCenter().setText(String.valueOf(x));
				dlg.getTextFieldYCenter().setText(String.valueOf(y));
				dlg.setOutlineColor(activeEdgeColor);
			    dlg.setInnerColor(activeFillColor);
				dlg.setVisible(true);
				if(dlg.isOk()) {
					Circle circle = dlg.getCircle();
					Color innerColor = dlg.getInnerColor();
					Color outlineColor = dlg.getOutlineColor();
					circle.setOutlineColor(outlineColor); 
	                circle.setInnerColor(innerColor);
	                activeEdgeColor = outlineColor;
	                activeFillColor = innerColor;
	                frame.getBtnEdgeColor().setBackground(outlineColor);
	                frame.getBtnFillColor().setBackground(innerColor);
	                executeCommand(new AddShapeCommand(model, circle));
					
				}
			}
			if(frame.getTglbtnDonut().isSelected()) {
				DlgDonut dlg = new DlgDonut();
				dlg.getTextFieldXcenter().setText(String.valueOf(x));
                dlg.getTextFieldYcenter().setText(String.valueOf(y));
                dlg.setOutlineColor(activeEdgeColor);
                dlg.setInnerColor(activeFillColor);
                dlg.setVisible(true);
                if(dlg.isOk()) {
                	Donut donut = dlg.getDonut();
                	Color innerColor = dlg.getInnerColor();
                	Color outlineColor = dlg.getOutlineColor();
                    donut.setOutlineColor(outlineColor); 
                    donut.setInnerColor(innerColor);
                    activeEdgeColor = outlineColor;
                    activeFillColor = innerColor;
                    frame.getBtnEdgeColor().setBackground(outlineColor);
                    frame.getBtnFillColor().setBackground(innerColor);
                    executeCommand(new AddShapeCommand(model, donut));
                	}
                }
			if (frame.getTglbtnHexagon().isSelected()) {
			    DlgHexagon dlg = new DlgHexagon();
			    dlg.getTextFieldX().setText(String.valueOf(x));
			    dlg.getTextFieldY().setText(String.valueOf(y));
			    dlg.setOutlineColor(activeEdgeColor);
			    dlg.setInnerColor(activeFillColor);
			    dlg.setVisible(true);

			    if (dlg.isOk()) {
			        HexagonAdapter hexagon = dlg.getHexagon();
			        Color innerColor = dlg.getInnerColor();
			        Color outlineColor = dlg.getOutlineColor();
			        hexagon.setOutlineColor(outlineColor); 
			        hexagon.setInnerColor(innerColor);   
			        activeEdgeColor = outlineColor;
			        activeFillColor = innerColor;
			        frame.getBtnEdgeColor().setBackground(outlineColor);
			        frame.getBtnFillColor().setBackground(innerColor);
			        executeCommand(new AddShapeCommand(model, hexagon));
			    }
			}

		}
		if(frame.getTglbtnSelect().isSelected()) {
			/* boolean multiSelect = e.isControlDown();
			    Shape clickedShape = model.getShapeAt(e.getX(), e.getY());

			    if (clickedShape == null && !multiSelect) {
			        // Klik na prazno — deselect everything
			        for (Shape s : new ArrayList<>(model.getSelected())) {
			            executeCommand(new DeselectCommand(model, s));
			        }
			    } else {
			        executeCommand(new SelectCommand(model, e.getX(), e.getY(), multiSelect));
			    }
			    */
			
			boolean multiSelect = e.isControlDown();
		    Shape clickedShape = model.getShapeAt(e.getX(), e.getY());

		    if (clickedShape == null) {
		        // Klik na prazno - deselektuj sve (samo ako nije multiSelect)
		        if (!multiSelect) {
		            for (Shape s : new ArrayList<>(model.getSelected())) {
		                executeCommand(new DeselectCommand(model, s));
		            }
		        }
		    } else {
		        // Klik na shape - proveri da li je već selektovan
		        if (clickedShape.isSelected()) {
		            // Već je selektovan → DESELECT
		            executeCommand(new DeselectCommand(model, clickedShape));
		        } else {
		            // Nije selektovan → SELECT
		            if (!multiSelect) {
		                // Prvo deselektuj sve ostale (ako nije multiSelect)
		                for (Shape s : new ArrayList<>(model.getSelected())) {
		                    executeCommand(new DeselectCommand(model, s));
		                }
		            }
		            executeCommand(new SelectCommand(model, clickedShape));
		        }
		    }
			
		}
	}
	

	
	public void deleteSelected() {
		if (!model.hasSelected()) {
	        JOptionPane.showMessageDialog(null, "There is no selected shape!", "Error", JOptionPane.ERROR_MESSAGE);
	        return;
	    }
	    
	    List<Shape> selectedShapes = new ArrayList<>(model.getSelected());
	    
	    // Jedan dijalog za sve
	    String message;
	    if (selectedShapes.size() == 1) {
	        message = "Are you sure you want to delete this shape?";
	    } else {
	        message = "Are you sure you want to delete " + selectedShapes.size() + " shapes?";
	    }
	    
	    int result = JOptionPane.showConfirmDialog(null, message, "Confirmation", JOptionPane.YES_NO_OPTION);
	    
	    if (result == JOptionPane.YES_OPTION) {
	        // Obriši sve selektovane shape-ove
	        for (Shape shape : selectedShapes) {
	            executeCommand(new RemoveShapeCommand(model, shape));
	        }
	    }
	   
	}
	
	public void modifySelected() {
	    if (!model.hasSelected()) {
	        JOptionPane.showMessageDialog(null, "There is no selected shape!", "Error", JOptionPane.ERROR_MESSAGE);
	        return;
	    }
	    
	    for (Shape shape : model.getSelected()) {
	        if (shape instanceof Point) {
	            modifyPoint((Point) shape);
	        } else if (shape instanceof Line) {
	            modifyLine((Line) shape);
	        } else if (shape instanceof Donut) {
	            modifyDonut((Donut) shape);
	        } else if (shape instanceof Circle) {
	            modifyCircle((Circle) shape);
	        } else if (shape instanceof Rectangle) {
	            modifyRectangle((Rectangle) shape);
	        } else if (shape instanceof HexagonAdapter) {  
	            modifyHexagon((HexagonAdapter) shape);
	        }
	    }
	   
	}
	
	private void modifyPoint(Point point) {
	    DlgPoint dlg = new DlgPoint();
	    dlg.getTextPointX().setText(String.valueOf(point.getX()));
	    dlg.getTextPointY().setText(String.valueOf(point.getY()));
	    dlg.setColor(point.getOutlineColor());
	    dlg.setVisible(true);
	    
	    if (dlg.isOk()) {
	    	 Point newState = new Point(
	    	            Integer.parseInt(dlg.getTextPointX().getText()),
	    	            Integer.parseInt(dlg.getTextPointY().getText())
	    	        );
	    	        newState.setOutlineColor(dlg.getColor());
	    	        //Command pattern
	    	        executeCommand(new UpdatePointCommand(point, newState));
	    	        
	    	        
	    	        
	    }
	}
	private void modifyLine(Line line) {
	    DlgLine dlg = new DlgLine();
	    dlg.getTextFieldX1().setText(String.valueOf(line.getStartPoint().getX()));
	    dlg.getTextFieldY1().setText(String.valueOf(line.getStartPoint().getY()));
	    dlg.getTextFieldX2().setText(String.valueOf(line.getEndPoint().getX()));
	    dlg.getTextFieldY2().setText(String.valueOf(line.getEndPoint().getY()));
	    dlg.setColor(line.getOutlineColor());
	    dlg.setVisible(true);
	    
	    if (dlg.isOk()) {
	    	Line newState = new Line(
	                new Point(Integer.parseInt(dlg.getTextFieldX1().getText()),
	                         Integer.parseInt(dlg.getTextFieldY1().getText())),
	                new Point(Integer.parseInt(dlg.getTextFieldX2().getText()),
	                         Integer.parseInt(dlg.getTextFieldY2().getText()))
	            );
	            newState.setOutlineColor(dlg.getColor());
	            
	            executeCommand(new UpdateLineCommand(line, newState));
	            
	           
	           
	    }
	}
	private void modifyRectangle(Rectangle rectangle) {
	    DlgRectangle dlg = new DlgRectangle();
	    dlg.getTextFieldX().setText(String.valueOf(rectangle.getUpperLeftPoint().getX()));
	    dlg.getTextFieldY().setText(String.valueOf(rectangle.getUpperLeftPoint().getY()));
	    dlg.getTextFieldHeight().setText(String.valueOf(rectangle.getHeight()));
	    dlg.getTextFieldWidth().setText(String.valueOf(rectangle.getWidth()));
	    dlg.setOutlineColor(rectangle.getOutlineColor());
	    dlg.setInnerColor(rectangle.getInnerColor());
	    dlg.setVisible(true);
	    
	    if (dlg.isOk()) {
	    	Rectangle newState = new Rectangle(
	                new Point(Integer.parseInt(dlg.getTextFieldX().getText()),
	                         Integer.parseInt(dlg.getTextFieldY().getText())),
	                Integer.parseInt(dlg.getTextFieldHeight().getText()),
	                Integer.parseInt(dlg.getTextFieldWidth().getText())
	                
	            );
	            newState.setOutlineColor(dlg.getOutlineColor());
	            newState.setInnerColor(dlg.getInnerColor());
	            
	            executeCommand(new UpdateRectangleCommand(rectangle, newState));
	           
	           
	           
	    }
	}
	
	private void modifyCircle(Circle circle) {
	    DlgCircle dlg = new DlgCircle();
	    dlg.getTextFieldXCenter().setText(String.valueOf(circle.getCenter().getX()));
	    dlg.getTextFieldYCenter().setText(String.valueOf(circle.getCenter().getY()));
	    dlg.getTextFieldRadius().setText(String.valueOf(circle.getRadius()));
	    dlg.setOutlineColor(circle.getOutlineColor());
	    dlg.setInnerColor(circle.getInnerColor());
	    dlg.setVisible(true);
	    
	    if (dlg.isOk()) {
	    	Circle newState = new Circle(
	                new Point(Integer.parseInt(dlg.getTextFieldXCenter().getText()),
	                         Integer.parseInt(dlg.getTextFieldYCenter().getText())),
	                Integer.parseInt(dlg.getTextFieldRadius().getText())
	            );
	            newState.setOutlineColor(dlg.getOutlineColor());
	            newState.setInnerColor(dlg.getInnerColor());
	            
	            executeCommand(new UpdateCircleCommand(circle, newState));
	           
	           
	           
	    }
	}
	private void modifyDonut(Donut donut) {
	    DlgDonut dlg = new DlgDonut();
	    dlg.getTextFieldXcenter().setText(String.valueOf(donut.getCenter().getX()));
	    dlg.getTextFieldYcenter().setText(String.valueOf(donut.getCenter().getY()));
	    dlg.getTextFieldRadius().setText(String.valueOf(donut.getRadius()));
	    dlg.getTextFieldInner().setText(String.valueOf(donut.getInnerRadius()));
	    dlg.setOutlineColor(donut.getOutlineColor());
	    dlg.setInnerColor(donut.getInnerColor());
	    dlg.setVisible(true);
	    
	    if (dlg.isOk()) {
	    	 Donut newState = new Donut(
	    	            new Point(Integer.parseInt(dlg.getTextFieldXcenter().getText()),
	    	                     Integer.parseInt(dlg.getTextFieldYcenter().getText())),
	    	            Integer.parseInt(dlg.getTextFieldRadius().getText()),
	    	            Integer.parseInt(dlg.getTextFieldInner().getText())
	    	        );
	    	        newState.setOutlineColor(dlg.getOutlineColor());
	    	        newState.setInnerColor(dlg.getInnerColor());
	    	        
	    	        executeCommand(new UpdateDonutCommand(donut, newState));
	    	       
	    	       
	    	      
	    }
	}
	private void modifyHexagon(HexagonAdapter hexagon) {
	    DlgHexagon dlg = new DlgHexagon();
	    dlg.getTextFieldX().setText(String.valueOf(hexagon.getX()));
	    dlg.getTextFieldY().setText(String.valueOf(hexagon.getY()));
	    dlg.getTextFieldRadius().setText(String.valueOf(hexagon.getR()));
	    dlg.setOutlineColor(hexagon.getOutlineColor()); 
	    dlg.setInnerColor(hexagon.getInnerColor());      
	    dlg.setVisible(true);
	    
	    if (dlg.isOk()) {
	        HexagonAdapter newState = new HexagonAdapter(
	            Integer.parseInt(dlg.getTextFieldX().getText()),
	            Integer.parseInt(dlg.getTextFieldY().getText()),
	            Integer.parseInt(dlg.getTextFieldRadius().getText())
	        );
	        newState.setOutlineColor(dlg.getOutlineColor());  
	        newState.setInnerColor(dlg.getInnerColor());      
	        
	        executeCommand(new UpdateHexagonCommand(hexagon, newState));
	     
	       
	    }
	}
	
	public void toFront() {
	    if (model.getSelectedCount() == 1) {
	        Shape shape = model.getSelected().get(0);
	        executeCommand(new ToFrontCommand(model, shape));
	    }
	}

	public void toBack() {
	    if (model.getSelectedCount() == 1) {
	        Shape shape = model.getSelected().get(0);
	        executeCommand(new ToBackCommand(model, shape));
	    }
	}

	public void bringToFront() {
	    if (model.getSelectedCount() == 1) {
	        Shape shape = model.getSelected().get(0);
	        executeCommand(new BringToFrontCommand(model, shape));
	    }
	}

	public void bringToBack() {
	    if (model.getSelectedCount() == 1) {
	        Shape shape = model.getSelected().get(0);
	        executeCommand(new BringToBackCommand(model, shape));
	    }
	}
	
	private void logCommand(String cmd) {
        JScrollPane logScrollPane = frame.getLogScrollPane();
        JTextArea logTextArea = (JTextArea) logScrollPane.getViewport().getView();
        logTextArea.append(cmd + "\n");
        commandLog.add(cmd);
    }
	
	public void saveLog() {
	    JFileChooser chooser = new JFileChooser();
	    chooser.setDialogTitle("Choose location to save your command log");
	    chooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Text files (*.txt)", "txt"));

	    int result = chooser.showSaveDialog(frame);
	    if (result != JFileChooser.APPROVE_OPTION) return;

	    File selectedFile = chooser.getSelectedFile();
	    if (!selectedFile.getName().toLowerCase().endsWith(".txt")) {
	        selectedFile = new File(selectedFile.getAbsolutePath() + ".txt");
	    }

	    try {
	        // Strategy obrazac
	        save.setStrategy(new SaveLogStrategy());
	        save.save(commandLog, selectedFile.getAbsolutePath());

	        JOptionPane.showMessageDialog(
	            frame,
	            "Command log has been successfully saved!\n\nLocation:\n" + selectedFile.getAbsolutePath(),
	            "Log Saved",
	            JOptionPane.INFORMATION_MESSAGE
	        );
	    } catch (IOException e) {
	        JOptionPane.showMessageDialog(
	            frame,
	            "Error while saving the log:\n" + e.getMessage(),
	            "Saving Failed",
	            JOptionPane.ERROR_MESSAGE
	        );
	    }
	}

	public void saveDrawing() {
	    JFileChooser chooser = new JFileChooser();
	    chooser.setDialogTitle("Save your current drawing");
	    chooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Serialized files (*.ser)", "ser"));

	    int result = chooser.showSaveDialog(frame);
	    if (result != JFileChooser.APPROVE_OPTION) return;

	    File selectedFile = chooser.getSelectedFile();

	    if (!selectedFile.getName().toLowerCase().endsWith(".ser")) {
	        selectedFile = new File(selectedFile.getAbsolutePath() + ".ser");
	    }

	    try {
	        // Strategy obrazac
	        save.setStrategy(new SaveDrawingStrategy());
	        save.save(model.getAll(), selectedFile.getAbsolutePath());

	        JOptionPane.showMessageDialog(
	            frame,
	            "Drawing has been successfully serialized and saved!\n\nLocation:\n" + selectedFile.getAbsolutePath(),
	            "Drawing Saved",
	            JOptionPane.INFORMATION_MESSAGE
	        );
	    } catch (IOException e) {
	        JOptionPane.showMessageDialog(
	            frame,
	            "Error while saving the drawing:\n" + e.getMessage(),
	            "Saving Failed",
	            JOptionPane.ERROR_MESSAGE
	        );
	    }
	}
	
	
	public void loadLogFile() {
	    JFileChooser chooser = new JFileChooser();
	    chooser.setDialogTitle("Select log file to load");
	    chooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Text files (*.txt)", "txt"));

	    int result = chooser.showOpenDialog(frame);
	    if (result != JFileChooser.APPROVE_OPTION) return;

	    File selectedFile = chooser.getSelectedFile();

	    try {
	        // Učitaj sve komande iz fajla
	        List<String> commands = Files.readAllLines(Paths.get(selectedFile.getAbsolutePath()));
	        
	        if (commands.isEmpty()) {
	            JOptionPane.showMessageDialog(frame, "The log file is empty!", "Error", JOptionPane.ERROR_MESSAGE);
	            return;
	        }

	        // Resetuj parser stack-ove
	        commandParser.resetStacks();
	        
	        // Pitaj korisnika da li želi da očisti crtež
	        int confirm = JOptionPane.showConfirmDialog(
	            frame,
	            "Do you want to clear the current drawing before loading the log?",
	            "Clear Drawing",
	            JOptionPane.YES_NO_OPTION
	        );
	        
	        if (confirm == JOptionPane.YES_OPTION) {
	            model.removeAll();
	            frame.repaint();
	        }

	        // Otvori dijalog za replay
	        DlgCommandReplay dialog = new DlgCommandReplay(frame, commands, commandParser, frame);
	        dialog.setVisible(true);

	    } catch (IOException e) {
	        JOptionPane.showMessageDialog(
	            frame,
	            "Error reading log file:\n" + e.getMessage(),
	            "Loading Failed",
	            JOptionPane.ERROR_MESSAGE
	        );
	        e.printStackTrace();
	    }
	}
	
	public void loadDrawing() {
	    JFileChooser chooser = new JFileChooser();
	    chooser.setDialogTitle("Select drawing file to load");
	    chooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Serialized files (*.ser)", "ser"));

	    int result = chooser.showOpenDialog(frame);
	    if (result != JFileChooser.APPROVE_OPTION) return;

	    File selectedFile = chooser.getSelectedFile();

	    try {
	        // Učitaj crtež pomoću LoadManager-a
	        load.setStrategy(new LoadDrawingStrategy());
	        List<Shape> loadedShapes = (List<Shape>) load.load(selectedFile.getAbsolutePath());
	        
	        // Očisti trenutni model
	        model.removeAll();
	        
	        // Dodaj učitane shape-ove
	        for (Shape shape : loadedShapes) {
	            model.add(shape);
	        }
	        
	        frame.repaint();
	        
	        JOptionPane.showMessageDialog(
	            frame,
	            "Drawing loaded successfully!\n\nShapes loaded: " + loadedShapes.size(),
	            "Drawing Loaded",
	            JOptionPane.INFORMATION_MESSAGE
	        );
	        
	    } catch (Exception e) {
	        JOptionPane.showMessageDialog(
	            frame,
	            "Error loading drawing:\n" + e.getMessage(),
	            "Loading Failed",
	            JOptionPane.ERROR_MESSAGE
	        );
	        e.printStackTrace();
	    }
	}
	
	public void switchToDrawMode() {
		if (model.hasSelected()) {
	        List<Shape> currentlySelected = new ArrayList<>(model.getSelected());
	        
	        // Deselektuj svaki shape kroz komandu da bi se logovao
	        for (Shape shape : currentlySelected) {
	            executeCommand(new DeselectCommand(model, shape));
	        }
		} //updateButtonStates();
	}
	
	private void updateButtonStates() {
		
        // Undo/Redo
        frame.getBtnUndo().setEnabled(canUndo());
	    frame.getBtnRedo().setEnabled(canRedo());
	    
	    
	      /*  // Provera selekcije
	        List<Shape> selectedShapes = model.getSelected();
	        boolean hasSelection = selectedShapes.size() > 0;
	        boolean singleSelection = selectedShapes.size() == 1;
	        
	        Shape selected = singleSelection ? selectedShapes.get(0) : null;
	        int index = selected != null ? model.indexOf(selected) : -1;
	        int totalShapes = model.getSize();
	        
	        
	        frame.setDeleteEnabled(hasSelection);
	        frame.setModifyEnabled(singleSelection);
	        
	        // Z-ordering 
	        frame.setToFrontEnabled(singleSelection && index < totalShapes - 1);
	        frame.setToBackEnabled(singleSelection && index > 0);
	        frame.setBringToFrontEnabled(singleSelection && index < totalShapes - 1);
	        frame.setBringToBackEnabled(singleSelection && index > 0);
	        

	        
	        // Save/Open 
	        frame.setSaveEnabled(totalShapes > 0);
	        frame.setOpenEnabled(true);
	        */
	    
	} 


	public void setActiveFillColor(Color color) {
	    this.activeFillColor = color;
	    frame.getBtnFillColor().setBackground(color);
	}

	public Color getActiveEdgeColor() {
	    return activeEdgeColor;
	}

	public Color getActiveFillColor() {
	    return activeFillColor;
	}

	public void setActiveEdgeColor(Color color) {
		 this.activeEdgeColor = color;
		    frame.getBtnEdgeColor().setBackground(color);
		
	}

	public ModelDrawing getModel() {
		return model;
	}

	public FrameDrawing getFrame() {
		return frame;
	}
	





	
}
