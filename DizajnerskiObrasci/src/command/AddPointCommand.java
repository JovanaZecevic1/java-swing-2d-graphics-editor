package command;

import geometry.Point;
import mvc.ModelDrawing;

public class AddPointCommand implements Command{
	 private ModelDrawing model;
	    private Point point;
	    
	    public AddPointCommand(ModelDrawing model, Point point) {
	        this.model = model;
	        this.point = point;
	    }
	    
	    @Override
	    public void execute() {
	        model.add(point);
	    }
	    
	    @Override
	    public void unexecute() {
	        model.remove(point);
	    }

}
