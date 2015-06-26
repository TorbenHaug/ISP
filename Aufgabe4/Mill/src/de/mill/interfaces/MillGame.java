package de.mill.interfaces;

import de.mill.enums.GameState;
import de.mill.enums.MillColor;
import de.mill.enums.PlayerState;
import de.mill.exceptions.AlreadyAquiredException;
import de.mill.exceptions.MoveNotAllowedException;
import de.mill.exceptions.UnableToRemoveStoneException;
import de.mill.exceptions.WrongStateException;
import de.mill.model.Player;

import java.util.List;
import java.util.Map;

/**
 * Created by abq329 on 24.06.2015.
 */
public interface MillGame {
    void setStone(Player player, int pos) throws AlreadyAquiredException, WrongStateException;

    void removeStone(Player player, int pos) throws UnableToRemoveStoneException;

    Player getWhitePlayer();

    Player getBlackPlayer();

    Player getOpponent();

    Player getCurrentPlayer();

    List<MillColor> getCurrentField();

    void addRepaintable(Refresheable refresheable);

    PlayerState getPlayerState();

    GameState getGameState();

    void moveStone(Player player, int from, int to) throws MoveNotAllowedException;

    // KI possible next moves
    //{-1,[1,2,3,8]} --> aus dem Stock auf POS 1,2,3 oder 8
    //{1, [2,3,8]} --> von 1 nach POS 2,3 oder 8
    //{1, [-1]} --> remove 1
    Map<Integer, List<Integer>> nextPossibleMove();

    void exec(int fromPos, int toPos) throws RuntimeException;

}
