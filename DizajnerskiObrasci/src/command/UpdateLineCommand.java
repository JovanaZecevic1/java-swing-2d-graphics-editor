package command;

import geometry.Line;
import geometry.Point;
import mvc.ModelDrawing;

import java.awt.Color;

public class UpdateLineCommand implements Command {
    
    private Line line;
    private Line oldState;
    private Line newState;
    private ModelDrawing model;
    
    public UpdateLineCommand(ModelDrawing model, Line line, Line newState) {
        this.line = line;
        this.oldState = line.clone();
        this.newState = newState;
        this.newState = newState;
        this.model = model;
    }
    
    @Override
    public void execute() {
        line.getStartPoint().setX(newState.getStartPoint().getX());
        line.getStartPoint().setY(newState.getStartPoint().getY());
        line.getEndPoint().setX(newState.getEndPoint().getX());
        line.getEndPoint().setY(newState.getEndPoint().getY());
        line.setOutlineColor(newState.getOutlineColor());
        model.updateShape(line);
    }
    
    @Override
    public void unexecute() {
        line.getStartPoint().setX(oldState.getStartPoint().getX());
        line.getStartPoint().setY(oldState.getStartPoint().getY());
        line.getEndPoint().setX(oldState.getEndPoint().getX());
        line.getEndPoint().setY(oldState.getEndPoint().getY());
        line.setOutlineColor(oldState.getOutlineColor());
        model.updateShape(line);
    }
    
    
    	@Override
    	public String executeLog() {
    	    return "Updated: " + line.getShapeName() + " [" + 
    	           oldState + "] -> [" + 
    	           newState + "]";
    	}
}