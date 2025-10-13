package command;

import geometry.Shape;
import mvc.ModelDrawing;

public class BringToBackCommand implements Command {
	private ModelDrawing model;
    private Shape shape;
    private int oldIndex;
    
    public BringToBackCommand(ModelDrawing model, Shape shape) {
        this.model = model;
        this.shape = shape;
    }
    
    @Override
    public void execute() {
    	model.bringToBack(shape);  
    }
    
    @Override
    public void unexecute() {
    	model.bringToFront(shape);
    }
    @Override
    public String executeLog() {
        return "Bring To Back: " + shape.toString();
    }

}
