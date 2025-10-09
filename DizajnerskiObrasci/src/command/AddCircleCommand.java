package command;

import geometry.Circle;
import mvc.ModelDrawing;

public class AddCircleCommand implements Command {
    
    private ModelDrawing model;
    private Circle circle;
    
    public AddCircleCommand(ModelDrawing model, Circle circle) {
        this.model = model;
        this.circle = circle;
    }
    
    @Override
    public void execute() {
        model.add(circle);
    }
    
    @Override
    public void unexecute() {
        model.remove(circle);
    }
}