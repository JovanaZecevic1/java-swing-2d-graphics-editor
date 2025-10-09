package mvc;

import geometry.*;

import java.awt.Color;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;

import drawingObjects.*;

public class ControllerDrawing {

	private ModelDrawing model;
	private ViewDrawing view;
	private FrameDrawing2 frame;
	private Point startPoint;
	
	public ControllerDrawing(ModelDrawing model, FrameDrawing2 frame) {
		this.model = model;
		this.frame=frame;
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
                    model.add(point);
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
						 model.add(line);
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
					 model.add(rectangle);
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
					 model.add(circle);
					
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
                	 model.add(donut);
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
	            model.remove(shape);
	        }
	    }
	    view.repaint();
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
	        }
	    }
	    view.repaint();
	}
	
	private void modifyPoint(Point point) {
	    DlgPoint dlg = new DlgPoint();
	    dlg.getTextPointX().setText(String.valueOf(point.getX()));
	    dlg.getTextPointY().setText(String.valueOf(point.getY()));
	    dlg.setVisible(true);
	    
	    if (dlg.isOk()) {
	        point.setX(Integer.parseInt(dlg.getTextPointX().getText()));
	        point.setY(Integer.parseInt(dlg.getTextPointY().getText()));
	        point.setOutlineColor(dlg.getColor());
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
	        line.getStartPoint().setX(Integer.parseInt(dlg.getTextFieldX1().getText()));
	        line.getStartPoint().setY(Integer.parseInt(dlg.getTextFieldY1().getText()));
	        line.getEndPoint().setX(Integer.parseInt(dlg.getTextFieldX2().getText()));
	        line.getEndPoint().setY(Integer.parseInt(dlg.getTextFieldY2().getText()));
	        line.setOutlineColor(dlg.getColor());
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
	        rectangle.getUpperLeftPoint().setX(Integer.parseInt(dlg.getTextFieldX().getText()));
	        rectangle.getUpperLeftPoint().setY(Integer.parseInt(dlg.getTextFieldY().getText()));
	        rectangle.setHeight(Integer.parseInt(dlg.getTextFieldHeight().getText()));
	        rectangle.setWidth(Integer.parseInt(dlg.getTextFieldWidth().getText()));
	        rectangle.setOutlineColor(dlg.getOutlineColor());
	        rectangle.setInnerColor(dlg.getInnerColor());
	    }
	}
	
	private void modifyCircle(Circle circle) {
	    DlgCircle dlg = new DlgCircle();
	    dlg.getTextFieldXCenter().setText(String.valueOf(circle.getCenter().getX()));
	    dlg.getTextFieldYCenter().setText(String.valueOf(circle.getCenter().getY()));
	    dlg.getTextFieldRadius().setText(String.valueOf(circle.getRadius()));
	    dlg.setVisible(true);
	    
	    if (dlg.isOk()) {
	        circle.getCenter().setX(Integer.parseInt(dlg.getTextFieldXCenter().getText()));
	        circle.getCenter().setY(Integer.parseInt(dlg.getTextFieldYCenter().getText()));
	        circle.setRadius(Integer.parseInt(dlg.getTextFieldRadius().getText()));
	        circle.setOutlineColor(dlg.getOutlineColor());
	        circle.setInnerColor(dlg.getInnerColor());
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
	        donut.getCenter().setX(Integer.parseInt(dlg.getTextFieldXcenter().getText()));
	        donut.getCenter().setY(Integer.parseInt(dlg.getTextFieldYcenter().getText()));
	        donut.setRadius(Integer.parseInt(dlg.getTextFieldRadius().getText()));
	        donut.setInnerRadius(Integer.parseInt(dlg.getTextFieldInner().getText()));
	        donut.setOutlineColor(dlg.getOutlineColor());
	        donut.setInnerColor(dlg.getInnerColor());
	    }
	}
	
}
