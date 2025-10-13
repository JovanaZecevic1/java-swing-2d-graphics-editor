package command;

import geometry.Circle;
import geometry.Point;
import mvc.ModelDrawing;

import java.awt.Color;

public class UpdateCircleCommand implements Command {
    
    private Circle circle;
    private Circle oldState = new Circle();
    private Circle newState;
 
    
    public UpdateCircleCommand(Circle circle, Circle newState) {
        this.circle = circle;
        this.oldState = new Circle();
        this.newState = newState;
        
    }
    
    @Override
    public void execute() {
    	
    	/*donut.setInnerRadius(newState.getInnerRadius());
        donut.setOutlineColor(newState.getOutlineColor());
        donut.setInnerColor(newState.getInnerColor());*/
        oldState.clone(circle);
        circle.clone(newState);
        
    }
    
    @Override
    public void unexecute() {
      /*donut.setInnerRadius(oldState.getInnerRadius());
        donut.setOutlineColor(oldState.getOutlineColor());
        donut.setInnerColor(oldState.getInnerColor());*/
    	
    	if (oldState != null) {
    		circle.clone(oldState);  
        }
        return;
       
    }
    @Override
    public String executeLog() {
        return "Updated: " + 
               oldState.toString() + "->" + 
               newState.toString();
    }
}