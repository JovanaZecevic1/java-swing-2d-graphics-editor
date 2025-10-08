package mvc;

import geometry.*;
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
}
