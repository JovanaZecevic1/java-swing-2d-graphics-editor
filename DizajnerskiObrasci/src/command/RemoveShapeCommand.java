package command;

import geometry.Shape;
import mvc.ModelDrawing;

public class RemoveShapeCommand implements Command {
    
    private ModelDrawing model;
    private Shape shape;  
    private int index;
    
    public RemoveShapeCommand(ModelDrawing model, Shape shape) {
        this.model = model;
        this.shape = shape;  
    }
    
    @Override
    public void execute() {
        index = model.indexOf(shape); 
        model.remove(shape);          
    }
    
    @Override
    public void unexecute() {
        model.addAt(index, shape);  
    }
}