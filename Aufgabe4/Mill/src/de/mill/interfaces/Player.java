package de.mill.interfaces;

import de.mill.enums.PlayerColor;
import de.mill.enums.Position;

import java.util.List;
import java.util.Map;

/**
 * Created by abq329 on 16.06.2015.
 */
public interface Player {

    public PlayerColor getColor();

    public Map<Stone,List<Position>> getPossibleMoves();

    public int getCountOfStockStones();
}
