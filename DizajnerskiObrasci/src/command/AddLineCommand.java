package command;

import geometry.Line;
import mvc.ModelDrawing;

public class AddLineCommand implements Command {
    
    private ModelDrawing model;
    private Line line;
    
    public AddLineCommand(ModelDrawing model, Line line) {
        this.model = model;
        this.line = line;
    }
    
    @Override
    public void execute() {
        model.add(line);
    }
    
    @Override
    public void unexecute() {
        model.remove(line);
    }
}