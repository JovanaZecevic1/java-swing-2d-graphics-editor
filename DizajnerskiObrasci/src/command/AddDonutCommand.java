package command;

import geometry.Donut;
import mvc.ModelDrawing;

public class AddDonutCommand implements Command {
    
    private ModelDrawing model;
    private Donut donut;
    
    public AddDonutCommand(ModelDrawing model, Donut donut) {
        this.model = model;
        this.donut = donut;
    }
    
    @Override
    public void execute() {
        model.add(donut);
    }
    
    @Override
    public void unexecute() {
        model.remove(donut);
    }
}