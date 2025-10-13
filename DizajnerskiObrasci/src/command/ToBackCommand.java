package command;

import geometry.Shape;
import mvc.ModelDrawing;

public class ToBackCommand implements Command{
	private ModelDrawing model;
    private Shape shape;
    private int oldIndex;
    private int newIndex;
    
    public ToBackCommand(ModelDrawing model, Shape shape) {
        this.model = model;
        this.shape = shape;
    }
    
    @Override
    public void execute() {
    	model.toBack(shape);

    }
    
    @Override
    public void unexecute() {
    	 model.toFront(shape);
    }
    @Override
    public String executeLog() {
        return "To Back: " + shape.toString();
    }

}
