package mvc;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
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
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Shape shape : model.getAll()) {
            shape.draw(g);
        }
    }

	@Override
	public void update() {
		repaint();
		
	}
}
