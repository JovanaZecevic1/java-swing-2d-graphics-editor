package command;

import geometry.Line;
import geometry.Point;
import mvc.ModelDrawing;

import java.awt.Color;

public class UpdateLineCommand implements Command {
    
    private Line line;
    private Line oldState;
    private Line newState;
    
    
    public UpdateLineCommand(Line line, Line newState) {
        this.line = line;
        this.oldState = line.clone();
        this.newState = newState;
       
    }
    
    @Override
    public void execute() {
    	 line.setStartPoint(newState.getStartPoint());
         line.setEndPoint(newState.getEndPoint());
         line.setOutlineColor(newState.getOutlineColor());
    }
    
    @Override
    public void unexecute() {
    	line.setStartPoint(oldState.getStartPoint());
        line.setEndPoint(oldState.getEndPoint());
        line.setOutlineColor(oldState.getOutlineColor());
         
       
    }
    
    
    	@Override
    	public String executeLog() {
    	    return "Updated: " + 
    	           oldState.toString() + "->" + 
    	           newState.toString() ;
    	}
}