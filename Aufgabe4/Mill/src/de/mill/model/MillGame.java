package de.mill.model;

import de.mill.enums.GameState;
import de.mill.enums.MillColor;
import de.mill.enums.PlayerState;
import de.mill.exceptions.*;
import de.mill.interfaces.Refresheable;

import java.util.ArrayList;
import java.util.List;


public class MillGame {
    private GameField gameField = new GameField();
    private List<Refresheable> refresheables = new ArrayList<>();
    private final Player player1;
    private final Player player2;
    private Player currentPlayer;
    private GameState gameState = GameState.Running;

    public MillGame(Player player1, Player player2){
        this.player1 = player1;
        this.player2 = player2;
        player1.setState(PlayerState.Set);
        currentPlayer = player1;
    }

    public void setStone(Player player, int pos) throws AlreadyAquiredException, WrongStateException {
        checkRunning();
        if(player.getState() == PlayerState.Set) {
            Stone stone = player.getStoneFromStock();
            try {
                gameField.setStone(pos, stone);
                if(gameField.isMill(pos)){
                    currentPlayer.setState(PlayerState.Remove);
                }else {
                    switchPlayer();
                }
            } catch (AlreadyAquiredException e) {
                player.addStoneToStock(stone);
                throw e;
            } finally {
                anounceRepaintable();
            }
        }else{
            throw new WrongStateException();
        }
    }

    public void removeStone(Player player, int pos) throws UnableToRemoveStoneException {
        checkRunning();
        if (player.getState() == PlayerState.Remove){
            MillColor colorAtPos = gameField.getColorFor(pos);
            if(!(colorAtPos == MillColor.Non) &&
                    !(colorAtPos == currentPlayer.COLOR) &&
                    (!gameField.isMill(pos) || !(gameField.isOneStoneNotInMill(getOpponent().getStonesOnField())))){
                getOpponent().removeFromFieldList(gameField.removeStone(pos));
                currentPlayer.setState(PlayerState.Await);
                switchPlayer();
                if (currentPlayer.stonesInGame() < 3){
                    gameState = GameState.Finished;
                    getCurrentPlayer().setState(PlayerState.Loose);
                    getOpponent().setState(PlayerState.Win);
                }
                anounceRepaintable();
            }else{
                throw new UnableToRemoveStoneException();
            }

        }else {
            throw new WrongStateException();
        }
    }

    private void checkRunning() {
        if (gameState == GameState.Finished){
            throw new FinishedGameException();
        }
    }

    public Player getOpponent() {
        if (currentPlayer == player1){
            return  player2;
        }else{
            return player1;
        }
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public List<MillColor> getCurrentField() {
        return gameField.getFieldStatus();
    }

    public void addRepaintable(Refresheable refresheable){
        refresheables.add(refresheable);
    }

    private void anounceRepaintable(){
        for(Refresheable refresheable : refresheables){
            refresheable.refresh();
        }
    }

    private void switchPlayer(){
        currentPlayer.setState(PlayerState.Await);
        if(currentPlayer == player1){
            currentPlayer = player2;
        }else{
            currentPlayer = player1;
        }

        if(currentPlayer.hasStonsInStock()){
            currentPlayer.setState(PlayerState.Set);
        }else{
            currentPlayer.setState(PlayerState.Move);
        }
    }

    public PlayerState getPlayerState(){
        return currentPlayer.getState();
    }

    public GameState getGameState(){
        return gameState;
    }

    public void moveStone(Player player, int from, int to) throws MoveNotAllowedException {
        checkRunning();
        if(gameField.getColorFor(from) == player.COLOR && (gameField.isNeighbour(from,to) || player.stonesInGame() == 3)){
            try {
                gameField.moveStone(from, to);
            } catch (MoveNotAllowedException e) {
                throw e;
            }
            if(gameField.isMill(to)){
                currentPlayer.setState(PlayerState.Remove);
            }else {
                switchPlayer();
            }
            anounceRepaintable();
        }else{
            throw new MoveNotAllowedException();
        }
    }
}
