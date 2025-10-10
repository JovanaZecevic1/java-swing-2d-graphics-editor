package command;

import geometry.Donut;

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
        donut.getCenter().setX(newState.getCenter().getX());
        donut.getCenter().setY(newState.getCenter().getY());
        donut.setRadius(newState.getRadius());
        donut.setInnerRadius(newState.getInnerRadius());
        donut.setOutlineColor(newState.getOutlineColor());
        donut.setInnerColor(newState.getInnerColor());
    }
    
    @Override
    public void unexecute() {
        donut.getCenter().setX(oldState.getCenter().getX());
        donut.getCenter().setY(oldState.getCenter().getY());
        donut.setRadius(oldState.getRadius());
        donut.setInnerRadius(oldState.getInnerRadius());
        donut.setOutlineColor(oldState.getOutlineColor());
        donut.setInnerColor(oldState.getInnerColor());
    }
}