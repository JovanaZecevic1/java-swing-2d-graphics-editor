package command;

import geometry.Donut;
import mvc.ModelDrawing;

public class UpdateDonutCommand implements Command {
    
    private Donut donut;
    private Donut oldState;
    private Donut newState;
    private ModelDrawing model;
    
    public UpdateDonutCommand(ModelDrawing model, Donut donut, Donut newState) {
        this.donut = donut;
        this.oldState = donut.clone();  
        this.newState = newState;
        this.model = model;
    }
    
    @Override
    public void execute() {
        donut.getCenter().setX(newState.getCenter().getX());
        donut.getCenter().setY(newState.getCenter().getY());
        donut.setRadius(newState.getRadius());
        donut.setInnerRadius(newState.getInnerRadius());
        donut.setOutlineColor(newState.getOutlineColor());
        donut.setInnerColor(newState.getInnerColor());
        model.updateShape(donut);
    }
    
    @Override
    public void unexecute() {
        donut.getCenter().setX(oldState.getCenter().getX());
        donut.getCenter().setY(oldState.getCenter().getY());
        donut.setRadius(oldState.getRadius());
        donut.setInnerRadius(oldState.getInnerRadius());
        donut.setOutlineColor(oldState.getOutlineColor());
        donut.setInnerColor(oldState.getInnerColor());
        model.updateShape(donut);
    }
    @Override
    public String executeLog() {
        return "Updated: " + donut.getShapeName() + " [" + 
               oldState + "] -> [" + 
               newState + "]";
    }
}