package de.mill.model;

import de.mill.exceptions.AlreadyAquiredException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by abq329 on 18.06.2015.
 */
public class Position {
    private final List<Position> neighbours = new ArrayList<>();
    private Stone aquiringStone = new Stone(MillColor.Non);
    public Position() {

    }

    public void addNeighbour(Position position) {
        neighbours.add(position);
    }
    void setAquiringStone(Stone stone) throws AlreadyAquiredException {
        if(aquiringStone.COLOR == MillColor.Non) {
            this.aquiringStone = stone;
        }else {
            throw new AlreadyAquiredException();
        }
    }

    public MillColor getColor() {
        return aquiringStone.COLOR;
    }
}
