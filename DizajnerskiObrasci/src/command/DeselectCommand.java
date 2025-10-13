package command;

import geometry.Shape;
import mvc.ModelDrawing;

public class DeselectCommand implements Command {
	 private ModelDrawing model;
	    private Shape shape;
	    private boolean wasSelected;
	    
	    public DeselectCommand(ModelDrawing model, Shape shape) {
	        this.model = model;
	        this.shape = shape;
	        this.wasSelected = shape.isSelected();
	    }

	    @Override
	    public void execute() {
	        model.deselectShape(shape);
	    }

	    @Override
	    public void unexecute() {
	        if (wasSelected) {
	            shape.setSelected(true);
	        }
	    }

	    @Override
	    public String executeLog() {
	        return "Deselected " + shape.toString();
	    }

}
