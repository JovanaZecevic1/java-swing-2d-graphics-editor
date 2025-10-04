package mvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

import geometry.Shape;


public class ModelDrawing{

	private List<Shape> shapes;
	private List<Shape> selectedShapes;
	
	public ModelDrawing() {
		this.shapes = new ArrayList<>();
		this.selectedShapes = new ArrayList<>();
	}
	
	public void add(Shape shape) {
		shapes.add(shape);
	}
	
	public void addAt(int index, Shape shape) {
		shapes.add(index, shape);
	}
	
	public void remove(Shape shape) {
		shapes.remove(shape);
		selectedShapes.remove(shape);
	}
	
	public List<Shape> getAll(){
		return shapes;
	}
	
	public Shape get (int index) {
		if(index >= 0 && index<shapes.size()) {
			return shapes.get(index);
		}
		return null;
	}
	
	public int getSize() {
		return shapes.size();
	}
	
	public int indexOf(Shape shape) {
		return shapes.indexOf(shape);
	}
	
	//selekcija
	
	public void selectShape(int x, int y) {
		Shape clickedShape = null;
		
		
		//pronaci oblik na koji je kliknuto
		for(Shape shape:shapes) {
			if(shape.contains(x, y)) {
				clickedShape = shape;
				break;
			}
		}
		
		//ako nije kliknuto, deselektovati 
		
		if (clickedShape == null) {
			clearSelection();
			return;
		}
		
		//da li je kliknuto na vec selektovan oblik
		if(selectedShapes.contains(clickedShape)) {
			//toggle - deselektovati
			clickedShape.setSelected(false);
			selectedShapes.remove(clickedShape);
		}else {
			//deselektovati sve ostalo i selektovati novi
			clearSelection();
			clickedShape.setSelected(true);
			selectedShapes.add(clickedShape);		}
	}
	
	public List<Shape> getSelected(){
		return new ArrayList<>(selectedShapes);
	}
	
	public boolean hasSelected() {
		return !selectedShapes.isEmpty();	
	}
	
	public int getSelectedCount() {
		return selectedShapes.size();
	}
	
	public void clearSelection() {
		for(Shape shape: selectedShapes) {
			shape.setSelected(false);
		}
		selectedShapes.clear();
	}
	
	public void removeFromSelection(Shape shape) {
		selectedShapes.remove(shape);
		shape.setSelected(false);
	}
	
	
}
