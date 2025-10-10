package command;

import geometry.Shape;
import mvc.ModelDrawing;

public class ToFrontCommand implements Command {
	private ModelDrawing model;
    private Shape shape;
    private int oldIndex;
    private int newIndex;
    
    public ToFrontCommand(ModelDrawing model, Shape shape) {
        this.model = model;
        this.shape = shape;
    }
    
    @Override
    public void execute() {
    	oldIndex = model.indexOf(shape);
        if (oldIndex == -1 || oldIndex == model.getSize() - 1) return;

        model.getAll().remove(shape);
        newIndex = oldIndex + 1;
        model.addAt(newIndex, shape);
    }
    
    @Override
    public void unexecute() {
        if (oldIndex != newIndex) {
        	 model.getAll().remove(shape);
             model.addAt(oldIndex, shape);
        }
    }

}
