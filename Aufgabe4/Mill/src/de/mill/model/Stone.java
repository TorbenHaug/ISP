package de.mill.model;

/**
 * Created by abq329 on 18.06.2015.
 */
public class Stone {
    public final MillColor COLOR;

    private Position position;

    public Stone(MillColor color){
        this.COLOR = color;
    }

    public Position getPosition() {
        return position;
    }

    void setPosition(Position position) {
        this.position = position;
    }
}
