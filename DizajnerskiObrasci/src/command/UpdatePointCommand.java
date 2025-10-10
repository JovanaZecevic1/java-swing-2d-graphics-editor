package command;

import geometry.Point;

public class UpdatePointCommand implements Command {
    
    private Point point;
    private Point oldState;
    private Point newState;
    
    public UpdatePointCommand(Point point, Point newState) {
        this.point = point;
        this.oldState = point.clone();
        this.newState = newState;
    }
    
    @Override
    public void execute() {
        point.setX(newState.getX());
        point.setY(newState.getY());
        point.setOutlineColor(newState.getOutlineColor());
    }
    
    @Override
    public void unexecute() {
        point.setX(oldState.getX());
        point.setY(oldState.getY());
        point.setOutlineColor(oldState.getOutlineColor());
    }
}