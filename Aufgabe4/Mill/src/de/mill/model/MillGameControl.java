package de.mill.model;

import de.mill.ai.Calculator;
import de.mill.enums.GameState;
import de.mill.enums.MillColor;
import de.mill.enums.PlayerState;
import de.mill.exceptions.AlreadyAquiredException;
import de.mill.exceptions.MoveNotAllowedException;
import de.mill.exceptions.UnableToRemoveStoneException;
import de.mill.exceptions.WrongStateException;
import de.mill.interfaces.MessageReceiver;
import de.mill.interfaces.MillGame;
import de.mill.interfaces.Refresheable;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by abq329 on 24.06.2015.
 */
public class MillGameControl implements MillGame {
    public final MillGameImpl MILLGAME;
    private final MillGameControl currentInstance;
    private final List<MessageReceiver> messageReceivers = new ArrayList<>();

    public MillGameControl(Player player1, Player player2){
        MILLGAME = new MillGameImpl(player1, player2);
        currentInstance = this;
        //startComputing();
    }

    @Override
    public void setStone(Player player, int pos) throws AlreadyAquiredException, WrongStateException {
        MILLGAME.setStone(player, pos);
        sendMessage(player.NAME + ": Set Stone to " + pos + ".");
        startComputing();
    }

    @Override
    public void removeStone(Player player, int pos) throws UnableToRemoveStoneException {
        MILLGAME.removeStone(player, pos);
        sendMessage(player.NAME + ": Remove Stone from " + pos + ".");
        startComputing();
    }

    @Override
    public Player getWhitePlayer() {
        return MILLGAME.getWhitePlayer();
    }

    @Override
    public Player getBlackPlayer() {
        return MILLGAME.getBlackPlayer();
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
        sendMessage(player.NAME + ": Move Stone from " + from + " to " + to + ".");
        startComputing();
    }

    @Override
    public Map<Integer, List<Integer>> nextPossibleMove() {
        return MILLGAME.nextPossibleMove();
    }

    @Override
    public void exec(int fromPos, int toPos) throws RuntimeException {
        try {
            if (fromPos == -1) {
                sendMessage(getCurrentPlayer().NAME + ": Set Stone to " + toPos + ".");
            } else if (toPos == -1) {
                sendMessage(getCurrentPlayer().NAME + ": Remove Stone from " + fromPos + ".");
            } else {
                sendMessage(getCurrentPlayer().NAME + ": Move Stone from " + fromPos + " to " + toPos + ".");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        MILLGAME.exec(fromPos, toPos);
        startComputing();
    }

    public void startComputing() {
        if(getCurrentPlayer().isComputer() && (getGameState() != GameState.Finished)) {
            MILLGAME.setGameState(GameState.Computing);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    (new Calculator()).startCalculating(currentInstance, 8);
                }
            }).start();
        } else if (getGameState() != GameState.Finished){
            MILLGAME.setGameState(GameState.Running);
        }
        MILLGAME.anounceRepaintable();
    }

    public void addMessageReceiver(MessageReceiver messageReceiver){
        this.messageReceivers.add(messageReceiver);
        messageReceiver.receiveMessage("added");
    }

    private void sendMessage(String message){
        for (MessageReceiver messageReceiver: messageReceivers){
            messageReceiver.receiveMessage(message);
        }
    }
}
