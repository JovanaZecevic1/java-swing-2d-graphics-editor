package command;

import geometry.HexagonAdapter;
import mvc.ModelDrawing;

public class UpdateHexagonCommand implements Command{

	 private HexagonAdapter hexagon;
	    private HexagonAdapter oldState;
	    private HexagonAdapter newState;
	    

	    public UpdateHexagonCommand(HexagonAdapter hexagon, HexagonAdapter newState) {
	        this.hexagon = hexagon;
	        this.oldState = (HexagonAdapter) hexagon.clone();
	        this.newState = newState;
	       
	    }

	    @Override
	    public void execute() {
	        hexagon.moveTo(newState.getX(), newState.getY());
	        hexagon.setR(newState.getR());
	        hexagon.setOutlineColor(newState.getOutlineColor());
	        hexagon.setInnerColor(newState.getInnerColor());
	        
	    }

	    @Override
	    public void unexecute() {
	        hexagon.moveTo(oldState.getX(), oldState.getY());
	        hexagon.setR(oldState.getR());
	        hexagon.setOutlineColor(newState.getOutlineColor());
	        hexagon.setInnerColor(newState.getInnerColor());
	       
	    }
	    @Override
	    public String executeLog() {
	        return "Updated: " + 
	               oldState.toString() + "->" + 
	               newState.toString();
	    }
}
