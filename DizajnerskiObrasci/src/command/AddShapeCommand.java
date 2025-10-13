package command;

import geometry.Shape;
import mvc.ModelDrawing;

public class AddShapeCommand implements Command {
	
	private ModelDrawing model;
    private Shape shape;
    
    public AddShapeCommand(ModelDrawing model, Shape shape) {
        this.model = model;
        this.shape = shape;
    }
    
    @Override
    public void execute() {
        model.add(shape);
    }
    
    @Override
    public void unexecute() {
        model.remove(shape);
    }
    @Override
    public String executeLog() {
    	return "Added: " + shape.toString();
    }

}
