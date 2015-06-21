package de.mill.model;

import de.mill.enums.MillColor;

/**
 * Created by abq329 on 18.06.2015.
 */
public class Stone {
    public final MillColor COLOR;

    private int position = -1;

    public Stone(MillColor color){
        this.COLOR = color;
    }
    public Stone(MillColor color, int position){
        this(color);
        this.position = position;
    }

    public Stone(Stone stone){
        this.COLOR = stone.COLOR;
        this.position = stone.position;
    }

    public int getPosition() {
        return position;
    }

    void setPosition(int position) {
        this.position = position;
    }
}
