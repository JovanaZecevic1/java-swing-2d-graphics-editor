package command;

import geometry.Donut;
import mvc.ModelDrawing;

public class UpdateDonutCommand implements Command {
    
    private Donut donut;
    private Donut oldState;
    private Donut newState;
    
    
    public UpdateDonutCommand(Donut donut, Donut newState) {
        this.donut = donut;
        this.oldState = donut.clone();  
        this.newState = newState;
    }
    
    @Override
    public void execute() {
    	 donut.setCenter(newState.getCenter());
         donut.setRadius(newState.getRadius());
         donut.setInnerRadius(newState.getInnerRadius());
         donut.setOutlineColor(newState.getOutlineColor());
         donut.setInnerColor(newState.getInnerColor());
    }
    
    @Override
    public void unexecute() {
    	donut.setCenter(oldState.getCenter());
        donut.setRadius(oldState.getRadius());
        donut.setInnerRadius(oldState.getInnerRadius());
        donut.setOutlineColor(oldState.getOutlineColor());
        donut.setInnerColor(oldState.getInnerColor());
        
    }
    @Override
    public String executeLog() {
        return "Updated: " +
               oldState.toString() + "->" + 
               newState.toString();
    }
}