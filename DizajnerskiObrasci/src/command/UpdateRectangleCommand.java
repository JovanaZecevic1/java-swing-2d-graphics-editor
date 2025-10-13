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
        this.oldState = new Rectangle();
        this.newState = newState; 
    }
    
    @Override
    public void execute() {
       /* rectangle.setHeight(newState.getHeight());
        rectangle.setOutlineColor(newState.getOutlineColor());
        rectangle.setInnerColor(newState.getInnerColor());*/
        oldState.clone(rectangle);
        rectangle.clone(newState);
        
    }
    
    @Override
    public void unexecute() {
    	/*rectangle.setHeight(oldState.getHeight());
        rectangle.setOutlineColor(oldState.getOutlineColor());
        rectangle.setInnerColor(oldState.getInnerColor());*/
    	if(oldState != null) {
    		rectangle.clone(oldState);
        }
        return;
       
    }
    @Override
    public String executeLog() {
        return "Updated: " + 
               oldState.toString() + " ->" + 
               newState.toString();
    }
}