package command;

import geometry.Line;
import geometry.Point;
import java.awt.Color;

public class UpdateLineCommand implements Command {
    
    private Line line;
    private Line oldState;
    private Line newState;
    
    public UpdateLineCommand(Line line, Line newState) {
        this.line = line;
        
        Point oldStart = new Point(line.getStartPoint().getX(), line.getStartPoint().getY());
        Point oldEnd = new Point(line.getEndPoint().getX(), line.getEndPoint().getY());
        this.oldState = new Line(oldStart, oldEnd);
        this.oldState.setOutlineColor(line.getOutlineColor());
        this.oldState.setSelected(line.isSelected());
        
        this.newState = newState;
    }
    
    @Override
    public void execute() {
        line.getStartPoint().setX(newState.getStartPoint().getX());
        line.getStartPoint().setY(newState.getStartPoint().getY());
        line.getEndPoint().setX(newState.getEndPoint().getX());
        line.getEndPoint().setY(newState.getEndPoint().getY());
        line.setOutlineColor(newState.getOutlineColor());
    }
    
    @Override
    public void unexecute() {
        line.getStartPoint().setX(oldState.getStartPoint().getX());
        line.getStartPoint().setY(oldState.getStartPoint().getY());
        line.getEndPoint().setX(oldState.getEndPoint().getX());
        line.getEndPoint().setY(oldState.getEndPoint().getY());
        line.setOutlineColor(oldState.getOutlineColor());
    }
}