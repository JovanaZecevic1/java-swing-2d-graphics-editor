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
    	oldIndex = model.indexOf(shape);
        if (oldIndex <= 0) return;

        model.getAll().remove(shape);
        model.addAt(0, shape); // dodaje na početak liste (najniža pozicija)
    }
    
    @Override
    public void unexecute() {
    	 model.getAll().remove(shape);
         model.addAt(oldIndex, shape);
    }

}
