package command;

import geometry.Shape;
import mvc.ModelDrawing;

public class SelectCommand implements Command {
    
    private ModelDrawing model;
    private Shape shape;

    // Konstruktor prima Shape direktno
    public SelectCommand(ModelDrawing model, Shape shape) {
        this.model = model;
        this.shape = shape;
    }

    @Override
    public void execute() {
        if (shape != null && !shape.isSelected()) {
            model.addToSelection(shape);
        }
    }

    @Override
    public void unexecute() {
        if (shape != null && shape.isSelected()) {
            model.removeFromSelection(shape);
        }
    }

    @Override
    public String executeLog() {
        return "Selected: " + shape.toString();
    }
}