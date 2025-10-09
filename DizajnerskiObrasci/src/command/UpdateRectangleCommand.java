package command;

import geometry.Rectangle;
import geometry.Point;
import java.awt.Color;

public class UpdateRectangleCommand implements Command {
    
    private Rectangle rectangle;
    private Rectangle oldState;
    private Rectangle newState;
    
    public UpdateRectangleCommand(Rectangle rectangle, Rectangle newState) {
        this.rectangle = rectangle;
        
        Point oldUpperLeft = new Point(rectangle.getUpperLeftPoint().getX(), 
                                        rectangle.getUpperLeftPoint().getY());
        this.oldState = new Rectangle(oldUpperLeft, 
                                       rectangle.getWidth(), 
                                       rectangle.getHeight());
        this.oldState.setOutlineColor(rectangle.getOutlineColor());
        this.oldState.setInnerColor(rectangle.getInnerColor());
        this.oldState.setSelected(rectangle.isSelected());
        
        this.newState = newState;
    }
    
    @Override
    public void execute() {
        rectangle.getUpperLeftPoint().setX(newState.getUpperLeftPoint().getX());
        rectangle.getUpperLeftPoint().setY(newState.getUpperLeftPoint().getY());
        rectangle.setWidth(newState.getWidth());
        rectangle.setHeight(newState.getHeight());
        rectangle.setOutlineColor(newState.getOutlineColor());
        rectangle.setInnerColor(newState.getInnerColor());
    }
    
    @Override
    public void unexecute() {
        rectangle.getUpperLeftPoint().setX(oldState.getUpperLeftPoint().getX());
        rectangle.getUpperLeftPoint().setY(oldState.getUpperLeftPoint().getY());
        rectangle.setWidth(oldState.getWidth());
        rectangle.setHeight(oldState.getHeight());
        rectangle.setOutlineColor(oldState.getOutlineColor());
        rectangle.setInnerColor(oldState.getInnerColor());
    }
}