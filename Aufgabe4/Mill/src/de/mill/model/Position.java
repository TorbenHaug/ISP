package de.mill.model;

import de.mill.exceptions.AlreadyAquiredException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by abq329 on 18.06.2015.
 */
public class Position {
    public final int ID;
    private Stone aquiringStone = new Stone(MillColor.Non);
    public Position(int id) {
        this.ID = id;
    }

    void setAquiringStone(Stone stone) throws AlreadyAquiredException {
        if(aquiringStone.COLOR == MillColor.Non || stone.COLOR == MillColor.Non) {
            this.aquiringStone = stone;
            this.aquiringStone.setPosition(this.ID);
        }else {
            throw new AlreadyAquiredException();
        }
    }

    Stone getAquiringStone(){
        return aquiringStone;
    }
    public MillColor getColor() {
        return aquiringStone.COLOR;
    }
}
