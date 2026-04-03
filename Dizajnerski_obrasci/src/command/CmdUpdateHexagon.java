package command;

import adapter.HexagonAdapter;

public class CmdUpdateHexagon implements Command {
    private HexagonAdapter original; 
    private HexagonAdapter newState; 
    private HexagonAdapter oldState;  
    
    public CmdUpdateHexagon(HexagonAdapter original, HexagonAdapter newState) {
        this.original = original;
        this.newState = newState;
        
        this.oldState = original.clone();  // KORISTI PRAVU clone() METODU
    }
    
    @Override
    public void execute() {
        // Primeni nove vrednosti na original
        original.setCenterX(newState.getCenterX());
        original.setCenterY(newState.getCenterY());
        original.setRadius(newState.getRadius());
        original.setColor(newState.getColor());
        original.setFillColor(newState.getFillColor());
    }
    
    @Override
    public void unexecute() {
        // Vrati original na staro stanje IZ KOPIJE
        original.setCenterX(oldState.getCenterX());
        original.setCenterY(oldState.getCenterY());
        original.setRadius(oldState.getRadius());
        original.setColor(oldState.getColor());
        original.setFillColor(oldState.getFillColor());
    }
    
    @Override
    public String toString() {
        return "Update hexagon: " + oldState.toString() + " => " + newState.toString();
    }
}