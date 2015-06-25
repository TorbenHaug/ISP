package de.mill.model;

import de.mill.ai.Calculator;
import de.mill.enums.GameState;
import de.mill.enums.MillColor;
import de.mill.enums.PlayerState;
import de.mill.exceptions.AlreadyAquiredException;
import de.mill.exceptions.MoveNotAllowedException;
import de.mill.exceptions.UnableToRemoveStoneException;
import de.mill.exceptions.WrongStateException;
import de.mill.interfaces.MillGame;
import de.mill.interfaces.Refresheable;

import java.util.List;
import java.util.Map;

/**
 * Created by abq329 on 24.06.2015.
 */
public class MillGameControl implements MillGame {
    public final MillGameImpl MILLGAME;

    public MillGameControl(Player player1, Player player2){
        MILLGAME = new MillGameImpl(player1, player2);
        startComputing();
    }

    @Override
    public void setStone(Player player, int pos) throws AlreadyAquiredException, WrongStateException {
        MILLGAME.setStone(player, pos);
        startComputing();
    }

    @Override
    public void removeStone(Player player, int pos) throws UnableToRemoveStoneException {
        MILLGAME.removeStone(player, pos);
        startComputing();
    }

    @Override
    public Player getOpponent() {
        return MILLGAME.getOpponent();
    }

    @Override
    public Player getCurrentPlayer() {
        return MILLGAME.getCurrentPlayer();
    }

    @Override
    public List<MillColor> getCurrentField() {
        return MILLGAME.getCurrentField();
    }

    @Override
    public void addRepaintable(Refresheable refresheable) {
        MILLGAME.addRepaintable(refresheable);
    }

    @Override
    public PlayerState getPlayerState() {
        return MILLGAME.getPlayerState();
    }

    @Override
    public GameState getGameState() {
        return MILLGAME.getGameState();
    }

    @Override
    public void moveStone(Player player, int from, int to) throws MoveNotAllowedException {
        MILLGAME.moveStone(player, from, to);
        startComputing();
    }

    @Override
    public Map<Integer, List<Integer>> nextPossibleMove() {
        return MILLGAME.nextPossibleMove();
    }

    @Override
    public void exec(int fromPos, int toPos) throws RuntimeException {
        MILLGAME.exec(fromPos, toPos);
        startComputing();
    }

    private void startComputing() {
        if(getCurrentPlayer().isComputer() && (getGameState() != GameState.Finished)) {
            (new Calculator()).startCalculating(this, 7);
        }
    }
}
