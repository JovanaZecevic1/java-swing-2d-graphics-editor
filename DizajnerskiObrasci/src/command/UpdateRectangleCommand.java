package command;

import geometry.Rectangle;
import mvc.ModelDrawing;
import geometry.Point;
import java.awt.Color;

public class UpdateRectangleCommand implements Command {
    
    private Rectangle rectangle;
    private Rectangle oldState;
    private Rectangle newState;
    private ModelDrawing model;
    
    public UpdateRectangleCommand(ModelDrawing model, Rectangle rectangle, Rectangle newState) {
        this.rectangle = rectangle;
        this.oldState = rectangle.clone();
        this.newState = newState;
        this.newState = newState;
        this.model = model;
        
    }
    
    @Override
    public void execute() {
        rectangle.getUpperLeftPoint().setX(newState.getUpperLeftPoint().getX());
        rectangle.getUpperLeftPoint().setY(newState.getUpperLeftPoint().getY());
        rectangle.setWidth(newState.getWidth());
        rectangle.setHeight(newState.getHeight());
        rectangle.setOutlineColor(newState.getOutlineColor());
        rectangle.setInnerColor(newState.getInnerColor());
        model.updateShape(rectangle);
    }
    
    @Override
    public void unexecute() {
        rectangle.getUpperLeftPoint().setX(oldState.getUpperLeftPoint().getX());
        rectangle.getUpperLeftPoint().setY(oldState.getUpperLeftPoint().getY());
        rectangle.setWidth(oldState.getWidth());
        rectangle.setHeight(oldState.getHeight());
        rectangle.setOutlineColor(oldState.getOutlineColor());
        rectangle.setInnerColor(oldState.getInnerColor());
        model.updateShape(rectangle);
    }
    @Override
    public String executeLog() {
        return "Updated: " + rectangle.getShapeName() + " [" + 
               oldState + "] -> [" + 
               newState + "]";
    }
}