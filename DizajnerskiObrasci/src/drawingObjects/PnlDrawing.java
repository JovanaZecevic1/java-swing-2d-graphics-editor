package drawingObjects;
import java.awt.color.*;
import java.util.List;

import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.*;
import java.awt.*;

import geometry.Shape;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import geometry.Point;
import geometry.Line;
import geometry.Rectangle;
import geometry.Donut;
import geometry.Circle;
public class PnlDrawing extends JPanel {
	private ArrayList <Shape> shapes;
	private FrmDrawing frame;
	private Shape selected;
	

	/**
	 * Create the panel.
	 */
	
	public void selected(int x, int y) {
	    Shape lastSelected = selected;
	    if (lastSelected != null && !lastSelected.contains(x, y)) {
	        lastSelected.setSelected(false);
	        selected = null;
	    }
	    boolean clickedOnShape = false;
	    for (Shape shape : shapes) {
	        if (shape.contains(x, y)) {
	            if (shape == lastSelected) {
	               
	                shape.setSelected(false);
	                selected = null;
	            } else {
	                shape.setSelected(true);
	                selected = shape;
	            }
	            clickedOnShape = true;
	        } else {
	            shape.setSelected(false);
	        }
	    }
	    if (clickedOnShape && selected != null) {
	        for (Shape shape : shapes) {
	            if (shape != selected) {
	                shape.setSelected(false);
	            }
	        }
	    }

	    repaint();
	}


	public void delete() {
		Iterator<Shape> it = shapes.iterator();
		boolean selectedShape=false;
		while(it.hasNext()) {
			Shape shape = it.next();
			if(shape.isSelected()) {
			selectedShape=true;
			}
		}
		it = shapes.iterator();//vracanje iteratora na pocetak liste
		if(selectedShape) {
			while(it.hasNext()) {
				Shape shape = it.next();
				if(shape.isSelected()) {
					int result = JOptionPane.showConfirmDialog(PnlDrawing.this, "Aru you sure?", "Confirmation", JOptionPane.YES_NO_OPTION);
					if(result==JOptionPane.YES_OPTION) {
						it.remove();
					}
				}
			}
		}
		else {
			JOptionPane.showMessageDialog(PnlDrawing.this, "There is no selected shape!", "Error", JOptionPane.ERROR_MESSAGE);
		}
		repaint();
	}

	public void modify() {
		Iterator<Shape> it = shapes.iterator();
		boolean selectedShape=false;
		while(it.hasNext()) {
			Shape shape = it.next();
			if(shape.isSelected()) {
			selectedShape=true;
			}
		}
		it = shapes.iterator();
		if(selectedShape) {
			while(it.hasNext()) {
				Shape shape = it.next();
				if(shape.isSelected()) {
					if(shape instanceof Point) {
						DlgPoint dlg = new DlgPoint();
						Point point = (Point)shape;
						dlg.getTextPointX().setText(String.valueOf(point.getX()));
						dlg.getTextPointY().setText(String.valueOf(point.getY()));
						dlg.setVisible(true);
						if(dlg.isOk()) {
							point.setX(Integer.parseInt(dlg.getTextPointX().getText()));
							point.setY(Integer.parseInt(dlg.getTextPointY().getText()));
							point.setOutlineColor(dlg.getColor());
						}
					}
					if(shape instanceof Line) {
						DlgLine dlg = new DlgLine();
						Line line = (Line)shape;
						dlg.getTextFieldX1().setText(String.valueOf(line.getStartPoint().getX()));
						dlg.getTextFieldY1().setText(String.valueOf(line.getStartPoint().getY()));
						dlg.getTextFieldX2().setText(String.valueOf(line.getEndPoint().getX()));
						dlg.getTextFieldY2().setText(String.valueOf(line.getEndPoint().getY()));
						dlg.setVisible(true);
						if(dlg.isOk()) {
							line.getStartPoint().setX(Integer.parseInt(dlg.getTextFieldX1().getText()));
							line.getStartPoint().setY(Integer.parseInt(dlg.getTextFieldY1().getText()));
							line.getEndPoint().setX(Integer.parseInt(dlg.getTextFieldX2().getText()));
							line.getEndPoint().setY(Integer.parseInt(dlg.getTextFieldY2().getText()));
							line.setOutlineColor(dlg.getColor());
							}
					}
					if(shape instanceof Rectangle) {
						DlgRectangle dlg = new DlgRectangle();
						Rectangle rectangle = (Rectangle)shape;
						dlg.getTextFieldX().setText(String.valueOf(rectangle.getUpperLeftPoint().getX()));
						dlg.getTextFieldY().setText(String.valueOf(rectangle.getUpperLeftPoint().getY()));
						dlg.getTextFieldHeight().setText(String.valueOf(rectangle.getHeight()));
						dlg.getTextFieldWidth().setText(String.valueOf(rectangle.getWidth()));
						dlg.setVisible(true);
						if(dlg.isOk()) {
							rectangle.getUpperLeftPoint().setX(Integer.parseInt(dlg.getTextFieldX().getText()));
							rectangle.getUpperLeftPoint().setY(Integer.parseInt(dlg.getTextFieldY().getText()));
							rectangle.setHeight(Integer.parseInt(dlg.getTextFieldHeight().getText()));
							rectangle.setWidth(Integer.parseInt(dlg.getTextFieldWidth().getText()));
							rectangle.setOutlineColor(dlg.getOutlineColor());
							rectangle.setInnerColor(dlg.getInnerColor());
						}
					}
					if(shape instanceof Donut) {
						DlgDonut dlg = new DlgDonut();
						Donut donut = (Donut) shape;
						dlg.getTextFieldXcenter().setText(String.valueOf(donut.getCenter().getX()));
						dlg.getTextFieldYcenter().setText(String.valueOf(donut.getCenter().getY()));
						dlg.getTextFieldRadius().setText(String.valueOf(donut.getRadius()));
						dlg.getTextFieldInner().setText(String.valueOf(donut.getInnerRadius()));
						dlg.setVisible(true);
						if(dlg.isOk()) {
							donut.getCenter().setX(Integer.parseInt(dlg.getTextFieldXcenter().getText()));
							donut.getCenter().setY(Integer.parseInt(dlg.getTextFieldYcenter().getText()));
							donut.setRadius(Integer.parseInt(dlg.getTextFieldRadius().getText()));
							donut.setInnerRadius(Integer.parseInt(dlg.getTextFieldInner().getText()));
							donut.setOutlineColor(dlg.getOutlineColor());
							donut.setInnerColor(dlg.getInnerColor());
							
						}
					}
					else if(shape instanceof Circle) {
						DlgCircle dlg = new DlgCircle();
						Circle circle = (Circle) shape;
						dlg.getTextFieldXCenter().setText(String.valueOf(circle.getCenter().getX()));
						dlg.getTextFieldYCenter().setText(String.valueOf(circle.getCenter().getY()));
						dlg.getTextFieldRadius().setText(String.valueOf(circle.getRadius()));
						dlg.setVisible(true);
						if(dlg.isOk()) {
							circle.getCenter().setX(Integer.parseInt(dlg.getTextFieldXCenter().getText()));
							circle.getCenter().setY(Integer.parseInt(dlg.getTextFieldYCenter().getText()));
							circle.setRadius(Integer.parseInt(dlg.getTextFieldRadius().getText()));
							circle.setOutlineColor(dlg.getOutlineColor());
							circle.setInnerColor(dlg.getInnerColor());
						}
					}
				}
			}
		}
		else {
			JOptionPane.showMessageDialog(PnlDrawing.this, "There is no selected shape!", "Error", JOptionPane.ERROR_MESSAGE);
		}
		repaint();
	}

		/**
		 * Create the panel.
		 */
	    public PnlDrawing() {
	    	shapes = new ArrayList<>();
	    }
	  
		
		public void paint(Graphics g) {
			super.paintComponent(g);
			Iterator<Shape> it = shapes.iterator();
			while(it.hasNext()) {
		      Shape shape = it.next();
			  shape.draw(g);
			}
		}
		public void addShape(Shape shape, Color color) {
			shapes.add(shape);
			shape.setOutlineColor(color);
			repaint();
		}
		
		public void addShape(Shape shape, Color innerColor, Color outlineColor) {
			shapes.add(shape);
			shape.setOutlineColor(outlineColor);
			shape.setInnerColor(innerColor);
			repaint();
		}
		
		
		
	}