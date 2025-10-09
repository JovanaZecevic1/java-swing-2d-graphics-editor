package command;

import geometry.Rectangle;
import mvc.ModelDrawing;

public class AddRectangleCommand implements Command {
    
    private ModelDrawing model;
    private Rectangle rectangle;
    
    public AddRectangleCommand(ModelDrawing model, Rectangle rectangle) {
        this.model = model;
        this.rectangle = rectangle;
    }
    
    @Override
    public void execute() {
        model.add(rectangle);
    }
    
    @Override
    public void unexecute() {
        model.remove(rectangle);
    }
}