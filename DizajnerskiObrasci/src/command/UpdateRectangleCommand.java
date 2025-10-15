package command;

import geometry.Rectangle;
import mvc.ModelDrawing;
import geometry.Point;
import java.awt.Color;

public class UpdateRectangleCommand implements Command {
    
    private Rectangle rectangle;
    private Rectangle oldState;
    private Rectangle newState;
   
    
    public UpdateRectangleCommand(Rectangle rectangle, Rectangle newState) {
        this.rectangle = rectangle;
        this.oldState = rectangle.clone();
        this.newState = newState; 
    }
    
    @Override
    public void execute() {
    	rectangle.setUpperLeftPoint(newState.getUpperLeftPoint());
        rectangle.setWidth(newState.getWidth());
        rectangle.setHeight(newState.getHeight());
        rectangle.setOutlineColor(newState.getOutlineColor());
        rectangle.setInnerColor(newState.getInnerColor());
        
    }
    
    @Override
    public void unexecute() {
    	rectangle.setUpperLeftPoint(oldState.getUpperLeftPoint());
        rectangle.setWidth(oldState.getWidth());
        rectangle.setHeight(oldState.getHeight());
        rectangle.setOutlineColor(oldState.getOutlineColor());
        rectangle.setInnerColor(oldState.getInnerColor());
       
    }
    @Override
    public String executeLog() {
        return "Updated: " + 
               oldState.toString() + " ->" + 
               newState.toString();
    }
}