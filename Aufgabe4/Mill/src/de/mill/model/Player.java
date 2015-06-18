package de.mill.model;

/**
 * Created by abq329 on 18.06.2015.
 */
public class Player {

    public boolean hasStonsInStock(){
        return true;
    }

    public Stone getStoneFromStock(){
        return new Stone(MillColor.Black);
    }
}
