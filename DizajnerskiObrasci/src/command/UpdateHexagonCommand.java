package command;

import geometry.HexagonAdapter;
import mvc.ModelDrawing;

public class UpdateHexagonCommand implements Command{

	 private HexagonAdapter hexagon;
	    private HexagonAdapter oldState;
	    private HexagonAdapter newState;
	    

	    public UpdateHexagonCommand(HexagonAdapter hexagon, HexagonAdapter newState) {
	        this.hexagon = hexagon;
	        this.oldState = new HexagonAdapter(0, 0, 0);
	        this.newState = newState;
	       
	    }

	    @Override
	    public void execute() {
	       /* hexagon.moveTo(newState.getX(), newState.getY());
	        hexagon.setR(newState.getR());
	        hexagon.setOutlineColor(newState.getOutlineColor());
	        hexagon.setInnerColor(newState.getInnerColor());
	        */
	    	oldState.clone(hexagon);
		    hexagon.clone(newState);
	    }

	    @Override
	    public void unexecute() {
	   /*point.setX(oldState.getX());
        point.setY(oldState.getY());
        point.setOutlineColor(oldState.getOutlineColor());*/
	    	if (oldState != null) {
	    		hexagon.clone(oldState);  
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
