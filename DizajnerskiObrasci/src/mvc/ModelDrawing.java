package mvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

import geometry.Shape;
import observer.Observable;
import observer.Observer;


public class ModelDrawing implements Observable{

	private List<Shape> shapes = new ArrayList<Shape>();
	private List<Shape> selectedShapes = new ArrayList<Shape>(); 
	private List<Observer> observers = new ArrayList<Observer>();
	
	public ModelDrawing() {
		this.shapes = new ArrayList<>();
		this.selectedShapes = new ArrayList<>();
	}
	
	public void add(Shape shape) {
		shapes.add(shape);
		notifyObservers();
	}
	
	public void addAt(int index, Shape shape) {
		shapes.add(index, shape);
		notifyObservers();
	}
	
	public void remove(Shape shape) {
		shapes.remove(shape);
		selectedShapes.remove(shape);
		notifyObservers();
	}
	
	public void updateShape(Shape shape) {
	    // samo obaveštava
	    notifyObservers();
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
		for(int i=shapes.size()-1; i>=0; i--) {
			Shape shape = shapes.get(i);
			if(shape.contains(x, y)){
				clickedShape = shape;
				break; //uzima poslednji(najvisi po z osi)
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
		notifyObservers();
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
		notifyObservers();
	}
	
	public void removeFromSelection(Shape shape) {
		selectedShapes.remove(shape);
		shape.setSelected(false);
	}

	@Override
	public void addObserver(Observer observer) {
		observers.add(observer);
		
	}

	@Override
	public void removeObserver(Observer observer) {
		observers.remove(observer);
		
	}

	@Override
	public void notifyObservers() {
		Iterator<Observer> it = observers.iterator();
		while(it.hasNext()) {
			it.next().update();		
			
		}
		
	}
	
	
}
