package command;

import geometry.Shape;
import mvc.DrawingModel;

public class CmdToBack implements Command {
    private Shape shape;
    private DrawingModel model;
    private int currentIndex;

    public CmdToBack(DrawingModel model, Shape shape) {
        this.model = model;
        this.shape = shape;
    }

    @Override
    public void execute() {
        currentIndex = model.indexOf(shape);
        if (currentIndex > 0) {
            model.remove(shape);
            model.add(currentIndex - 1, shape);
        }
    }

    @Override
    public void unexecute() {
        if (currentIndex >= 0 && currentIndex < model.getShapes().size() - 1) {
            model.remove(shape);
            model.add(currentIndex + 1, shape);
        }
    }

    @Override
    public String toString() {
        return "To back " + shape + "\n";
    }
}
