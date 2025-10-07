package mvc;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import geometry.Shape;

public class ViewDrawing extends JPanel {
private ModelDrawing model;
    
    public ViewDrawing(ModelDrawing model) {
        this.model = model;
        setBackground(Color.WHITE);
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Shape shape : model.getAll()) {
            shape.draw(g);
        }
    }
}
