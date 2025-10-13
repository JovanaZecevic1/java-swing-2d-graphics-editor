package command;

import java.util.ArrayList;
import java.util.List;

import geometry.Shape;
import mvc.ModelDrawing;

public class SelectCommand implements Command{
	private ModelDrawing model;
    private int x;
    private int y;
    private boolean multiSelect;

    
    private List<Shape> previousSelectedShapes;
   

    public SelectCommand(ModelDrawing model, int x, int y, boolean multiSelect) {
        this.model = model;
        this.x = x;
        this.y = y;
        this.multiSelect = multiSelect;
        
        
        this.previousSelectedShapes = new ArrayList<>(model.getSelected());
    }
    @Override
    public void execute() {
    	 model.selectShape(x, y, multiSelect);
    }

    @Override
    public void unexecute() {
    	 model.clearSelection();
    	 
    }

    @Override
    public String executeLog() {
        return "Selected: " + model.getShapeAt(x,y).toString();   
     }

}
