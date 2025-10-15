package command;

import geometry.Shape;
import mvc.ModelDrawing;

public class DeselectCommand implements Command {
    
    private ModelDrawing model;
    private Shape shape;
    private boolean wasInSelection;
    
    public DeselectCommand(ModelDrawing model, Shape shape) {
        this.model = model;
        this.shape = shape;
    }

    @Override
    public void execute() {
        if (shape != null) {
            wasInSelection = model.getSelected().contains(shape);
            if (shape.isSelected()) {
                shape.setSelected(false);
                model.removeFromSelection(shape);
            }
        }
    }

    @Override
    public void unexecute() {
        if (shape != null && wasInSelection) {
        	  if (shape != null && wasInSelection) {
                  model.addToSelection(shape); 
              }
        }
    }

    @Override
    public String executeLog() {
        return "Deselected: " + shape.toString();
    }
}