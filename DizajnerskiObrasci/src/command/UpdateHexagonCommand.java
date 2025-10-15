package command;

import geometry.HexagonAdapter;
import mvc.ModelDrawing;

public class UpdateHexagonCommand implements Command{

	    private HexagonAdapter hexagon;
	    private HexagonAdapter oldState;
	    private HexagonAdapter newState;
	    

	    public UpdateHexagonCommand(HexagonAdapter hexagon, HexagonAdapter newState) {
	        this.hexagon = hexagon;
	        this.oldState = hexagon.clone(); 
	        this.newState = newState;
	       
	    }

	    @Override
	    public void execute() {
	    	hexagon.setCenter(newState.getCenter());
	        hexagon.setRadius(newState.getRadius());
	        hexagon.setOutlineColor(newState.getOutlineColor());
	        hexagon.setInnerColor(newState.getInnerColor());
	    }

	    @Override
	    public void unexecute() {
	    	hexagon.setCenter(oldState.getCenter());
	        hexagon.setRadius(oldState.getRadius());
	        hexagon.setOutlineColor(oldState.getOutlineColor());
	        hexagon.setInnerColor(oldState.getInnerColor());
	       
	    }
	    @Override
	    public String executeLog() {
	        return "Updated: " + 
	               oldState.toString() + "->" + 
	               newState.toString();
	    }
}
