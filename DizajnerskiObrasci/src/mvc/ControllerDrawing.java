package mvc;

public class ControllerDrawing {

	private ModelDrawing model;
	private ViewDrawing view;
	
	public ControllerDrawing(ModelDrawing model, ViewDrawing view) {
		this.model = model;
		this.view=view;
	}
	
	public void selectShape(int x, int y) {
		model.selectShape(x, y);
		view.repaint();
	}
}
