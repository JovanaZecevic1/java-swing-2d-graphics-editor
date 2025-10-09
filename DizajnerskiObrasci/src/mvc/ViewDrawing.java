package mvc;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.util.Iterator;

import geometry.Shape;
import observer.Observer;

public class ViewDrawing extends JPanel implements Observer{
private ModelDrawing model;
    
    public ViewDrawing() {
      
    }
    

	public void setModel(ModelDrawing model)
	{
		this.model=model;
		this.model.addObserver(this);
	}
	@Override
	public void update() {
		repaint();
		
	}

    @Override
    protected void paintComponent(Graphics g) {
	    super.paintComponent(g);
	    Iterator<Shape> it = model.getAll().iterator();
	    while(it.hasNext()) {
	        Shape shape = it.next();
	        shape.draw(g);
	        
	    }
	}
}

