package mvc;

import geometry.*;

import javax.swing.JOptionPane;

import drawingObjects.*;

import drawingObjects.DlgPoint;

public class ControllerDrawing {

	private ModelDrawing model;
	private ViewDrawing view;
	
	public ControllerDrawing(ModelDrawing model, ViewDrawing view) {
		this.model = model;
		this.view=view;
	}
	
	public void selectShape(int x, int y) {
		model.selectShape(x, y);
		view.repaint();
	}
	
	public void addPoint(int x, int y) {
		DlgPoint dlg = new DlgPoint();
		 dlg.getTextPointX().setText(String.valueOf(x));
		 dlg.getTextPointY().setText(String.valueOf(y));
		 dlg.setVisible(true);
		 
		 if (dlg.isOk()) {
		        Point point = dlg.getPoint();
		        point.setOutlineColor(dlg.getColor());
		        model.add(point);
		        view.repaint();
		    }
		
	}
	
	public void addLine(Point startPoint, int x, int y) {
	    DlgLine dlg = new DlgLine();
	    dlg.getTextFieldX1().setText(String.valueOf(startPoint.getX()));
	    dlg.getTextFieldY1().setText(String.valueOf(startPoint.getY()));
	    dlg.getTextFieldX2().setText(String.valueOf(x));
	    dlg.getTextFieldY2().setText(String.valueOf(y));
	    dlg.setVisible(true);
	    
	    if (dlg.isOk()) {
	        Line line = dlg.getLine();
	        line.setOutlineColor(dlg.getColor());
	        model.add(line);
	        view.repaint();
	    }
	}
	
	public void addCircle(int x, int y) {
	    DlgCircle dlg = new DlgCircle();
	    dlg.getTextFieldXCenter().setText(String.valueOf(x));
	    dlg.getTextFieldYCenter().setText(String.valueOf(y));
	    dlg.setVisible(true);
	    
	    if (dlg.isOk()) {
	        Circle circle = dlg.getCircle();
	        circle.setInnerColor(dlg.getInnerColor());
	        circle.setOutlineColor(dlg.getOutlineColor());
	        model.add(circle);
	        view.repaint();
	    }
	}
	
	public void addRectangle(int x, int y) {
	    DlgRectangle dlg = new DlgRectangle();
	    dlg.getTextFieldX().setText(String.valueOf(x));
	    dlg.getTextFieldY().setText(String.valueOf(y));
	    dlg.setVisible(true);
	    
	    if (dlg.isOk()) {
	        Rectangle rectangle = dlg.getRectangle();
	        rectangle.setOutlineColor(dlg.getOutlineColor());
	        rectangle.setInnerColor(dlg.getInnerColor());
	        model.add(rectangle);
	        view.repaint();
	    }
	}
	
	public void addDonut(int x, int y) {
	    DlgDonut dlg = new DlgDonut();
	    dlg.getTextFieldXcenter().setText(String.valueOf(x));
	    dlg.getTextFieldYcenter().setText(String.valueOf(y));
	    dlg.setVisible(true);
	    
	    if (dlg.isOk()) {
	        Donut donut = dlg.getDonut();
	        donut.setInnerColor(dlg.getInnerColor());
	        donut.setOutlineColor(dlg.getOutlineColor());
	        model.add(donut);
	        view.repaint();
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
