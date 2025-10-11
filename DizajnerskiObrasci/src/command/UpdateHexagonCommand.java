package command;

import geometry.HexagonAdapter;
import mvc.ModelDrawing;

public class UpdateHexagonCommand implements Command{

	 private HexagonAdapter hexagon;
	    private HexagonAdapter oldState;
	    private HexagonAdapter newState;
	    private ModelDrawing model;

	    public UpdateHexagonCommand(ModelDrawing model, HexagonAdapter hexagon, HexagonAdapter newState) {
	        this.hexagon = hexagon;
	        this.oldState = (HexagonAdapter) hexagon.clone();
	        this.newState = newState;
	        this.model = model;
	    }

	    @Override
	    public void execute() {
	        hexagon.moveTo(newState.getX(), newState.getY());
	        hexagon.setR(newState.getR());
	        hexagon.setOutlineColor(newState.getOutlineColor());
	        hexagon.setInnerColor(newState.getInnerColor());
	        model.updateShape(hexagon);
	    }

	    @Override
	    public void unexecute() {
	        hexagon.moveTo(oldState.getX(), oldState.getY());
	        hexagon.setR(oldState.getR());
	        hexagon.setOutlineColor(newState.getOutlineColor());
	        hexagon.setInnerColor(newState.getInnerColor());
	        model.updateShape(hexagon);
	    }
	    @Override
	    public String executeLog() {
	        return "Updated: " + hexagon.getShapeName() + " [" + 
	               oldState + "] -> [" + 
	               newState + "]";
	    }
}
