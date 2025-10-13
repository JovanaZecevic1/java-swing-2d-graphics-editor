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
    	model.bringToFront(shape);
    }
    
    @Override
    public void unexecute() {
    	model.bringToBack(shape);
    }
    @Override
    public String executeLog() {
        return "Bring To Front: " + shape.toString();
    }

}
