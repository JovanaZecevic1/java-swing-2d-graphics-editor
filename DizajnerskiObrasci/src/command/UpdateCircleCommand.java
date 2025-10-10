package command;

import geometry.Circle;
import geometry.Point;
import mvc.ModelDrawing;

import java.awt.Color;

public class UpdateCircleCommand implements Command {
    
    private Circle circle;
    private Circle oldState;
    private Circle newState;
    private ModelDrawing model;
    
    public UpdateCircleCommand(ModelDrawing model, Circle circle, Circle newState) {
        this.circle = circle;
        this.oldState = circle.clone();
        this.newState = newState;
        this.model = model;
    }
    
    @Override
    public void execute() {
        circle.getCenter().setX(newState.getCenter().getX());
        circle.getCenter().setY(newState.getCenter().getY());
        circle.setRadius(newState.getRadius());
        circle.setOutlineColor(newState.getOutlineColor());
        circle.setInnerColor(newState.getInnerColor());
        model.updateShape(circle);
    }
    
    @Override
    public void unexecute() {
        circle.getCenter().setX(oldState.getCenter().getX());
        circle.getCenter().setY(oldState.getCenter().getY());
        circle.setRadius(oldState.getRadius());
        circle.setOutlineColor(oldState.getOutlineColor());
        circle.setInnerColor(oldState.getInnerColor());
        model.updateShape(circle);
    }
}