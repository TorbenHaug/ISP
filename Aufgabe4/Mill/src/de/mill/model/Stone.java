package de.mill.model;

/**
 * Created by abq329 on 18.06.2015.
 */
public class Stone {
    public final MillColor COLOR;

    private int position = -1;

    public Stone(MillColor color){
        this.COLOR = color;
    }

    public int getPosition() {
        return position;
    }

    void setPosition(int position) {
        this.position = position;
    }
}
