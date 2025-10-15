package command;

import geometry.Circle;
import geometry.Point;
import mvc.ModelDrawing;

import java.awt.Color;

public class UpdateCircleCommand implements Command {
    
    private Circle circle;
    private Circle oldState;
    private Circle newState;
 
    
    public UpdateCircleCommand(Circle circle, Circle newState) {
        this.circle = circle;
        this.oldState =  circle.clone();
        this.newState = newState;
        
    }
    
    @Override
    public void execute() {
    	circle.setCenter(new Point(newState.getCenter().getX(), newState.getCenter().getY()));
        circle.setRadius(newState.getRadius());
        circle.setOutlineColor(newState.getOutlineColor());
        circle.setInnerColor(newState.getInnerColor());
        
    }
    
    @Override

    public void unexecute() {    	
    	circle.setCenter(new Point(oldState.getCenter().getX(), oldState.getCenter().getY()));
        circle.setRadius(oldState.getRadius());
        circle.setOutlineColor(oldState.getOutlineColor());
        circle.setInnerColor(oldState.getInnerColor());
       
    }
    @Override
    public String executeLog() {
        return "Updated: " + 
               oldState.toString() + "->" + 
               newState.toString();
    }
}