package mvc;

import geometry.*;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.util.Stack;

import javax.swing.JOptionPane;

import command.Command;
import command.AddShapeCommand;      
import command.RemoveShapeCommand;
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
	
	public ControllerDrawing(ModelDrawing model, FrameDrawing frame) {
		this.model = model;
		this.frame=frame;
	}
	
	public boolean canUndo() {
	    return !undoStack.isEmpty();
	}

	public boolean canRedo() {
	    return !redoStack.isEmpty();
	}

	// Pomoćna metoda
	private void executeCommand(Command cmd) {                                  
	    undoStack.push(cmd);              
	    redoStack.clear();  
	    cmd.execute(); 
	    
	}

	public void undo() {
	    if (canUndo()) {
	        Command cmd = undoStack.pop();             
	        redoStack.push(cmd);
	        cmd.unexecute(); 
	    }
	    
	}

	public void redo() {
	    if (canRedo()) {
	        Command cmd = redoStack.pop();         
	        undoStack.push(cmd);
	        cmd.execute();  
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
                dlg.setVisible(true);
                if(dlg.isOk()==true) {
                	Point point = dlg.getPoint();
                	Color color = dlg.getColor();
                	point.setOutlineColor(color);
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
					dlg.setVisible(true);
					if(dlg.isOk()==true) {
						Line line = dlg.getLine();
						Color color = dlg.getColor();
						line.setOutlineColor(color); 
                        executeCommand(new AddShapeCommand(model, line));
					}
					startPoint = null;
				}
			}if(frame.getTglbtnRectangle().isSelected()) {
				DlgRectangle dlg = new DlgRectangle();
				dlg.getTextFieldX().setText(String.valueOf(x));
				dlg.getTextFieldY().setText(String.valueOf(y));
				dlg.setVisible(true);
				if(dlg.isOk()==true) {
					Rectangle rectangle = dlg.getRectangle();
					Color outlineColor = dlg.getOutlineColor();
					Color innerColor = dlg.getInnerColor();
					rectangle.setOutlineColor(outlineColor);  
	                rectangle.setInnerColor(innerColor);
	                executeCommand(new AddShapeCommand(model, rectangle));
				}
			}
			if(frame.getTglbtnCircle().isSelected()) {
				DlgCircle dlg = new DlgCircle();
				dlg.getTextFieldXCenter().setText(String.valueOf(x));
				dlg.getTextFieldYCenter().setText(String.valueOf(y));
				dlg.setVisible(true);
				if(dlg.isOk()) {
					Circle circle = dlg.getCircle();
					Color innerColor = dlg.getInnerColor();
					Color outlineColor = dlg.getOutlineColor();
					circle.setOutlineColor(outlineColor); 
	                circle.setInnerColor(innerColor);
	                executeCommand(new AddShapeCommand(model, circle));
					
				}
			}
			if(frame.getTglbtnDonut().isSelected()) {
				DlgDonut dlg = new DlgDonut();
				dlg.getTextFieldXcenter().setText(String.valueOf(x));
                dlg.getTextFieldYcenter().setText(String.valueOf(y));
                dlg.setVisible(true);
                if(dlg.isOk()) {
                	Donut donut = dlg.getDonut();
                	Color innerColor = dlg.getInnerColor();
                	Color outlineColor = dlg.getOutlineColor();
                    donut.setOutlineColor(outlineColor); 
                    donut.setInnerColor(innerColor);
                    executeCommand(new AddShapeCommand(model, donut));
                	}
                }
			if (frame.getTglbtnHexagon().isSelected()) {
			    DlgHexagon dlg = new DlgHexagon();
			    dlg.getTextFieldX().setText(String.valueOf(x));
			    dlg.getTextFieldY().setText(String.valueOf(y));
			    dlg.setVisible(true);

			    if (dlg.isOk()) {
			        HexagonAdapter hexagon = dlg.getHexagon();
			        Color innerColor = dlg.getInnerColor();
			        Color outlineColor = dlg.getOutlineColor();
			        hexagon.setOutlineColor(outlineColor); 
			        hexagon.setInnerColor(innerColor);     
			        executeCommand(new AddShapeCommand(model, hexagon));
			    }
			}

		}
		if(frame.getTglbtnSelect().isSelected()) {
			model.selectShape(x,y);
		}
	}
	

	
	public void deleteSelected() {
	    if (!model.hasSelected()) {
	        JOptionPane.showMessageDialog(null, "There is no selected shape!", "Error", JOptionPane.ERROR_MESSAGE);
	        return;
	    }
	    
	    for (Shape shape : model.getSelected()) {
	        int result = JOptionPane.showConfirmDialog(null, "Are you sure?", "Confirmation", JOptionPane.YES_NO_OPTION);
	        if (result == JOptionPane.YES_OPTION) {
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
	    dlg.setVisible(true);
	    
	    if (dlg.isOk()) {
	    	 Point newState = new Point(
	    	            Integer.parseInt(dlg.getTextPointX().getText()),
	    	            Integer.parseInt(dlg.getTextPointY().getText())
	    	        );
	    	        newState.setOutlineColor(dlg.getColor());
	    	        //Command pattern
	    	        executeCommand(new UpdatePointCommand(model, point, newState));
	    	        
	    }
	}
	private void modifyLine(Line line) {
	    DlgLine dlg = new DlgLine();
	    dlg.getTextFieldX1().setText(String.valueOf(line.getStartPoint().getX()));
	    dlg.getTextFieldY1().setText(String.valueOf(line.getStartPoint().getY()));
	    dlg.getTextFieldX2().setText(String.valueOf(line.getEndPoint().getX()));
	    dlg.getTextFieldY2().setText(String.valueOf(line.getEndPoint().getY()));
	    dlg.setVisible(true);
	    
	    if (dlg.isOk()) {
	    	Line newState = new Line(
	                new Point(Integer.parseInt(dlg.getTextFieldX1().getText()),
	                         Integer.parseInt(dlg.getTextFieldY1().getText())),
	                new Point(Integer.parseInt(dlg.getTextFieldX2().getText()),
	                         Integer.parseInt(dlg.getTextFieldY2().getText()))
	            );
	            newState.setOutlineColor(dlg.getColor());
	            
	            executeCommand(new UpdateLineCommand(model, line, newState));
	           
	    }
	}
	private void modifyRectangle(Rectangle rectangle) {
	    DlgRectangle dlg = new DlgRectangle();
	    dlg.getTextFieldX().setText(String.valueOf(rectangle.getUpperLeftPoint().getX()));
	    dlg.getTextFieldY().setText(String.valueOf(rectangle.getUpperLeftPoint().getY()));
	    dlg.getTextFieldHeight().setText(String.valueOf(rectangle.getHeight()));
	    dlg.getTextFieldWidth().setText(String.valueOf(rectangle.getWidth()));
	    dlg.setVisible(true);
	    
	    if (dlg.isOk()) {
	    	Rectangle newState = new Rectangle(
	                new Point(Integer.parseInt(dlg.getTextFieldX().getText()),
	                         Integer.parseInt(dlg.getTextFieldY().getText())),
	                Integer.parseInt(dlg.getTextFieldWidth().getText()),
	                Integer.parseInt(dlg.getTextFieldHeight().getText())
	            );
	            newState.setOutlineColor(dlg.getOutlineColor());
	            newState.setInnerColor(dlg.getInnerColor());
	            
	            executeCommand(new UpdateRectangleCommand(model, rectangle, newState));
	           
	    }
	}
	
	private void modifyCircle(Circle circle) {
	    DlgCircle dlg = new DlgCircle();
	    dlg.getTextFieldXCenter().setText(String.valueOf(circle.getCenter().getX()));
	    dlg.getTextFieldYCenter().setText(String.valueOf(circle.getCenter().getY()));
	    dlg.getTextFieldRadius().setText(String.valueOf(circle.getRadius()));
	    dlg.setVisible(true);
	    
	    if (dlg.isOk()) {
	    	Circle newState = new Circle(
	                new Point(Integer.parseInt(dlg.getTextFieldXCenter().getText()),
	                         Integer.parseInt(dlg.getTextFieldYCenter().getText())),
	                Integer.parseInt(dlg.getTextFieldRadius().getText())
	            );
	            newState.setOutlineColor(dlg.getOutlineColor());
	            newState.setInnerColor(dlg.getInnerColor());
	            
	            executeCommand(new UpdateCircleCommand(model, circle, newState));
	           
	    }
	}
	private void modifyDonut(Donut donut) {
	    DlgDonut dlg = new DlgDonut();
	    dlg.getTextFieldXcenter().setText(String.valueOf(donut.getCenter().getX()));
	    dlg.getTextFieldYcenter().setText(String.valueOf(donut.getCenter().getY()));
	    dlg.getTextFieldRadius().setText(String.valueOf(donut.getRadius()));
	    dlg.getTextFieldInner().setText(String.valueOf(donut.getInnerRadius()));
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
	    	        
	    	        executeCommand(new UpdateDonutCommand(model, donut, newState));
	    	      
	    }
	}
	private void modifyHexagon(HexagonAdapter hexagon) {
	    DlgHexagon dlg = new DlgHexagon();
	    dlg.getTextFieldX().setText(String.valueOf(hexagon.getX()));
	    dlg.getTextFieldY().setText(String.valueOf(hexagon.getY()));
	    dlg.getTextFieldRadius().setText(String.valueOf(hexagon.getR()));
	    dlg.setOutlineColor(hexagon.getOutlineColor());  // ✅ Shape metoda
	    dlg.setInnerColor(hexagon.getInnerColor());      // ✅ Shape metoda
	    dlg.setVisible(true);
	    
	    if (dlg.isOk()) {
	        HexagonAdapter newState = new HexagonAdapter(
	            Integer.parseInt(dlg.getTextFieldX().getText()),
	            Integer.parseInt(dlg.getTextFieldY().getText()),
	            Integer.parseInt(dlg.getTextFieldRadius().getText())
	        );
	        newState.setOutlineColor(dlg.getOutlineColor());  // ✅ Shape metoda
	        newState.setInnerColor(dlg.getInnerColor());      // ✅ Shape metoda
	        
	        executeCommand(new UpdateHexagonCommand(model, hexagon, newState));
	    }
	}
	
}
