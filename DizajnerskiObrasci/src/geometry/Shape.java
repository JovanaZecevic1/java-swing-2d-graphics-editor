package geometry;

import java.awt.Color;
import java.awt.Graphics;

public abstract class Shape implements Moveable, Comparable{

	private boolean selected;
	private Color outlineColor;
	private Color innerColor;
  	public Shape() {
		
	}
  	
  	public abstract Shape clone(); 
	
	public Shape (boolean selected) {
		this.selected = selected;
	}
	
	
	public void setSelected(boolean selected) {
		this.selected = selected;
	}
	
	public boolean isSelected() {
		return this.selected;
	}
	
	public abstract boolean contains(int x, int y);
	public abstract void draw(Graphics g);

	public Color getOutlineColor() {
		return outlineColor;
	}

	public void setOutlineColor(Color outlineColor) {
		this.outlineColor = outlineColor;
	}

	public Color getInnerColor() {
		return innerColor;
	}

	public void setInnerColor(Color innerColor) {
		this.innerColor = innerColor;
	}
}