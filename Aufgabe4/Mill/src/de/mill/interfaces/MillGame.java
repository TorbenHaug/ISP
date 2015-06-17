package de.mill.interfaces;

import de.mill.enums.MoveState;
import de.mill.enums.Position;
import de.mill.excepitons.NotAllowedException;

/**
 * Created by abq329 on 16.06.2015.
 */
public interface MillGame {
    public MoveState move(Player player, Stone stone, Position position) throws NotAllowedException;

    public void remove(Player player, Stone stone) throws NotAllowedException;

    public void set(Player player, Stone stone) throws NotAllowedException;

    public Player getWhitePlayer();

    public Player getBlackPlayer();

    public Player getCurrentPlayer();

    public MillGame getClone();

    public void addReloadeble(Reloadeble reloadeble);

    public void removeReloadable(Reloadeble reloadeble);
}
