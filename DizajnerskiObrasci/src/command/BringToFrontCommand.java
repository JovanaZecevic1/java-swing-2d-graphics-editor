package command;

import geometry.Shape;
import mvc.ModelDrawing;

public class BringToFrontCommand implements Command{
	private ModelDrawing model;
    private Shape shape;
    private int oldIndex;
    
    
    public BringToFrontCommand(ModelDrawing model, Shape shape) {
        this.model = model;
        this.shape = shape;
    }
    
    @Override
    public void execute() {
    	oldIndex = model.indexOf(shape);
        if (oldIndex == -1 || oldIndex == model.getSize() - 1) return;

        model.getAll().remove(shape);
        model.add(shape);
    }
    
    @Override
    public void unexecute() {
    	model.getAll().remove(shape);
        model.addAt(oldIndex, shape);
    }
    @Override
    public String executeLog() {
        return "Bring To Front: " + shape.getClass().getSimpleName();
    }

}
