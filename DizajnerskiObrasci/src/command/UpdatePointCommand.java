package command;

import geometry.Point;
import mvc.ModelDrawing;

public class UpdatePointCommand implements Command {
    
    private Point point;
    private Point oldState;
    private Point newState;

    
    public UpdatePointCommand(Point point, Point newState) {
        this.point = point;
        this.oldState = new Point();
        this.newState = newState;
       
    }
    
    @Override
    public void execute() {
    	/*point.setX(newState.getX());
        point.setY(newState.getY());
        point.setOutlineColor(newState.getOutlineColor());*/
       oldState.clone(point);
       point.clone(newState);
       
    }
    
    @Override
    public void unexecute() {
    	/* point.setX(oldState.getX());
        point.setY(oldState.getY());
        point.setOutlineColor(oldState.getOutlineColor());*/
    	 if(oldState != null) {
    		 point.clone(oldState);
         }
         return;
    }
    
   
    @Override
    public String executeLog() {
        return "Updated: " + 
               oldState.toString() + "->" + 
               newState.toString();
    }
}