package de.mill.interfaces;

import de.mill.enums.MoveState;
import de.mill.enums.Position;
import de.mill.excepitons.NotAllowedException;

/**
 * Created by abq329 on 17.06.2015.
 */
public interface GameField {

    public MoveState move(Stone stone, Position position) throws NotAllowedException;

    public void remove(Stone stone) throws NotAllowedException;

    public void set(Stone stone) throws NotAllowedException;

    public MillGame getClone();

}
