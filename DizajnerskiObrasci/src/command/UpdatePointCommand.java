package command;

import geometry.Point;
import mvc.ModelDrawing;

public class UpdatePointCommand implements Command {
    
    private Point point;
    private Point oldState;
    private Point newState;
    private ModelDrawing model;
    
    public UpdatePointCommand(ModelDrawing model, Point point, Point newState) {
        this.point = point;
        this.oldState = point.clone();
        this.newState = newState;
        this.model = model;
    }
    
    @Override
    public void execute() {
        point.setX(newState.getX());
        point.setY(newState.getY());
        point.setOutlineColor(newState.getOutlineColor());
        model.updateShape(point);
    }
    
    @Override
    public void unexecute() {
        point.setX(oldState.getX());
        point.setY(oldState.getY());
        point.setOutlineColor(oldState.getOutlineColor());
        model.updateShape(point);
    }
    
   
    @Override
    public String executeLog() {
        return "Updated: " + point.getShapeName() + " [" + 
               oldState + "] -> [" + 
               newState + "]";
    }
}