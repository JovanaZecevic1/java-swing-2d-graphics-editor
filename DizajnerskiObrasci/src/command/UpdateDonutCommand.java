package command;

import geometry.Donut;
import mvc.ModelDrawing;

public class UpdateDonutCommand implements Command {
    
    private Donut donut;
    private Donut oldState;
    private Donut newState;
    
    
    public UpdateDonutCommand(Donut donut, Donut newState) {
        this.donut = donut;
        this.oldState = new Donut();  
        this.newState = newState;
    }
    
    @Override
    public void execute() {
    	/*donut.setInnerRadius(newState.getInnerRadius());
        donut.setOutlineColor(newState.getOutlineColor());
        donut.setInnerColor(newState.getInnerColor());*/
       oldState.clone(donut);
       donut.clone(newState);
        
    }
    
    @Override
    public void unexecute() {
    	/*donut.setInnerRadius(oldState.getInnerRadius());
        donut.setOutlineColor(oldState.getOutlineColor());
        donut.setInnerColor(oldState.getInnerColor());*/
    	if (oldState != null) {
    		donut.clone(newState);   
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