package command;

import geometry.Circle;
import geometry.Point;
import java.awt.Color;

public class UpdateCircleCommand implements Command {
    
    private Circle circle;
    private Circle oldState;
    private Circle newState;
    
    public UpdateCircleCommand(Circle circle, Circle newState) {
        this.circle = circle;
        
        Point oldCenter = new Point(circle.getCenter().getX(), circle.getCenter().getY());
        this.oldState = new Circle(oldCenter, circle.getRadius());
        this.oldState.setOutlineColor(circle.getOutlineColor());
        this.oldState.setInnerColor(circle.getInnerColor());
        this.oldState.setSelected(circle.isSelected());
        
        this.newState = newState;
    }
    
    @Override
    public void execute() {
        circle.getCenter().setX(newState.getCenter().getX());
        circle.getCenter().setY(newState.getCenter().getY());
        circle.setRadius(newState.getRadius());
        circle.setOutlineColor(newState.getOutlineColor());
        circle.setInnerColor(newState.getInnerColor());
    }
    
    @Override
    public void unexecute() {
        circle.getCenter().setX(oldState.getCenter().getX());
        circle.getCenter().setY(oldState.getCenter().getY());
        circle.setRadius(oldState.getRadius());
        circle.setOutlineColor(oldState.getOutlineColor());
        circle.setInnerColor(oldState.getInnerColor());
    }
}