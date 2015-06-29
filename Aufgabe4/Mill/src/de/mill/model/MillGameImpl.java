package de.mill.model;

import de.mill.enums.GameState;
import de.mill.enums.MillColor;
import de.mill.enums.PlayerState;
import de.mill.exceptions.*;
import de.mill.interfaces.MillGame;
import de.mill.interfaces.Refresheable;

import java.util.*;


public class MillGameImpl implements MillGame {
    private final GameField gameField;
    private final List<Refresheable> refresheables = new ArrayList<>();
    private final Player player1;
    private final Player player2;
    private Player currentPlayer;
    private GameState gameState = GameState.Running;
    private int tieCounter = 11;

    public MillGameImpl(Player player1, Player player2){
        this.gameField = new GameField();
        this.player1 = player1;
        this.player2 = player2;
        player1.setState(PlayerState.Set);
        currentPlayer = player1;
    }

    public MillGameImpl(MillGameImpl millGameImpl){
        this.gameField = new GameField(millGameImpl.gameField);
        this.player1 = new Player(millGameImpl.player1);
        this.player2 = new Player(millGameImpl.player2);
        if (millGameImpl.currentPlayer == millGameImpl.player1){
            this.currentPlayer = this.player1;
        } else {
            this.currentPlayer = this.player2;
        }
        this.gameState = millGameImpl.gameState;
        this.tieCounter = millGameImpl.tieCounter;
    }

    @Override
    public void setStone(Player player, int pos) throws AlreadyAquiredException, WrongStateException {
        checkRunning();
        if (player.getState() == PlayerState.Set) {
            MillColor stone = player.getStoneFromStock();
            try {
                gameField.setStone(pos, stone);
                player.addStoneToField(pos);
                if (gameField.isMill(pos)){
                    currentPlayer.setState(PlayerState.Remove);
                } else {
                    switchPlayer();
                }
            } catch (AlreadyAquiredException e) {
                player.addStoneToStock(pos);
                throw e;
            }
        } else {
            throw new WrongStateException();
        }
    }

    @Override
    public void removeStone(Player player, int pos) throws UnableToRemoveStoneException {
        checkRunning();
        if (player.getState() == PlayerState.Remove){
            MillColor colorAtPos = gameField.getColorFor(pos);
            if (!(colorAtPos == MillColor.Non) &&
                    !(colorAtPos == currentPlayer.COLOR) &&
                    (!gameField.isMill(pos) || !(gameField.isOneStoneNotInMill(getOpponent().getStonesOnField())))){
                gameField.removeStone(pos);
                getOpponent().removeFromFieldList(pos);
                currentPlayer.setState(PlayerState.Await);
                switchPlayer();
                if (currentPlayer.stonesInGame() < 3){
                    gameState = GameState.Finished;
                    getCurrentPlayer().setState(PlayerState.Loose);
                    getOpponent().setState(PlayerState.Win);
                }
            } else {
                throw new UnableToRemoveStoneException();
            }

        } else {
            throw new WrongStateException();
        }
    }

    @Override
    public Player getWhitePlayer() {
        return player1;
    }

    @Override
    public Player getBlackPlayer() {
        return player2;
    }

    private void checkRunning() {
        if (gameState == GameState.Finished){
            throw new FinishedGameException();
        }
    }

    @Override
    public Player getOpponent() {
        if (currentPlayer == player1){
            return  player2;
        } else {
            return player1;
        }
    }

    @Override
    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    @Override
    public List<MillColor> getCurrentField() {
        return gameField.getFieldStatus();
    }

    @Override
    public void addRepaintable(Refresheable refresheable){
        refresheables.add(refresheable);
    }

    public void anounceRepaintable(){
        for(Refresheable refresheable : refresheables){
            refresheable.refresh();
        }
    }

    /**
     * SWITCH PLAYER
     */
    private void switchPlayer(){
        currentPlayer.setState(PlayerState.Await);
        if (currentPlayer == player1){
            currentPlayer = player2;
        } else {
            currentPlayer = player1;
        }

        if (currentPlayer.hasStoneInStock()){
            currentPlayer.setState(PlayerState.Set);
        } else {
            if (gameField.hasMoves(currentPlayer.getStonesOnField())){
                if (!checkTie()) {
                    currentPlayer.setState(PlayerState.Move);
                } else {
                    player1.setState(PlayerState.Tie);
                    player2.setState(PlayerState.Tie);
                    this.gameState = GameState.Finished;
                }
            } else {
                currentPlayer.setState(PlayerState.Loose);
                getOpponent().setState(PlayerState.Win);
                this.gameState = GameState.Finished;
            }
        }
    }

    private boolean checkTie() {
        if ((player1.stonesInGame() == 3) && (player2.stonesInGame() == 3)){
            if (--tieCounter == 0){
                return true;
            }
        }
        return false;
    }

    @Override
    public PlayerState getPlayerState(){
        return currentPlayer.getState();
    }

    @Override
    public GameState getGameState(){
        return gameState;
    }

    public void setGameState(GameState gameState){
        this.gameState = gameState;
    }

    @Override
    public void moveStone(Player player, int from, int to) throws MoveNotAllowedException {
        checkRunning();
        if (gameField.getColorFor(from) == player.COLOR && (gameField.isNeighbour(from,to) || player.stonesInGame() == 3)){
            try {
                gameField.moveStone(from, to);
            } catch (MoveNotAllowedException e) {
                throw e;
            }
            player.removeFromFieldList(from);
            player.addStoneToField(to);
            if (gameField.isMill(to)){
                currentPlayer.setState(PlayerState.Remove);
            } else {
                switchPlayer();
            }
        }else{
            throw new MoveNotAllowedException("" + player.NAME + " From: " + from + ", To" + to);
        }
    }

    // KI possible next moves
    //{-1,[1,2,3,8]} --> aus dem Stock auf POS 1,2,3 oder 8
    //{1, [2,3,8]} --> von 1 nach POS 2,3 oder 8
    //{1, [-1]} --> remove 1
    @Override
    public Map<Integer, List<Integer>> nextPossibleMove(){
        Map<Integer, List<Integer>> map = new HashMap<>();

        if (currentPlayer.getState() == PlayerState.Set){
            map.put(-1, gameField.getNonStoneList());
        } else if (currentPlayer.getState() == PlayerState.Move) {
            if (currentPlayer.stonesInGame() == 3){
                List<Integer> nonStoneList = gameField.getNonStoneList();
                for (int pos : currentPlayer.getStonesOnField()){
                    map.put(pos, nonStoneList);
                }
            } else if (currentPlayer.stonesInGame() > 3) {
                for (int pos : currentPlayer.getStonesOnField()) {
                    List<Integer> possibleMoves = gameField.getPossibleMovesFor(pos);
                    if(!possibleMoves.isEmpty()){
                        map.put(pos,possibleMoves);
                    }
                }
            }

        } else if (currentPlayer.getState() == PlayerState.Remove) {
            for (int opponentStonePos : getOpponent().getStonesOnField()){
                if (!gameField.isMill(opponentStonePos) || !gameField.isOneStoneNotInMill(getOpponent().getStonesOnField())){
                    map.put(opponentStonePos, Arrays.asList(-1));
                }
            }
        }

        return map;
    }

    @Override
    public void exec(int fromPos, int toPos) throws RuntimeException {
        try {
            if (fromPos == -1) {
                setStone(currentPlayer, toPos);
            } else if (toPos == -1) {
                removeStone(currentPlayer, fromPos);
            } else {
                moveStone(currentPlayer, fromPos, toPos);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public boolean isBlocked(Integer stone) {
        return gameField.isBlocked(stone);

    }

    public int getMillDiff(Player maxPlayer) {
        int currentCount = 0;
        int opponentCount = 0;

        for (List<Integer> mill : GameField.mills){
            MillColor color1 = gameField.getColorFor(mill.get(0));
            MillColor color2 = gameField.getColorFor(mill.get(1));
            MillColor color3 = gameField.getColorFor(mill.get(2));

            if (color1 != MillColor.Non && color1 == color2 && color2 == color3){
                if (color1 == maxPlayer.COLOR){
                    currentCount++;
                } else {
                    opponentCount++;
                }
            }

        }
        return currentCount - opponentCount;
    }

    /**
     * Two pieces in a mill construction have the same color and the third piece is a non-stone
     * @return The difference between the two piece construction of the computer and the user
     */
    public int twoPiece() {
        int currentCount = 0;
        int opponentCount = 0;

        for (List<Integer> mill : GameField.mills) {
            MillColor color1 = gameField.getColorFor(mill.get(0));
            MillColor color2 = gameField.getColorFor(mill.get(1));
            MillColor color3 = gameField.getColorFor(mill.get(2));

            MillColor nonColor = MillColor.Non;

            if (color1 == nonColor && color2 == color3 && color2 != nonColor||
                color2 == nonColor && color1 == color3 && color1 != nonColor||
                color3 == nonColor && color2 == color1 && color2 != nonColor) {
                System.out.println("color1: " + color1 + " color2: " + color2 + " color3: " + color3);
                if (color3 == getCurrentPlayer().COLOR || color1 == getCurrentPlayer().COLOR) {
                    currentCount++;
                } else {
                    opponentCount++;
                }
            }
        }
        return currentCount - opponentCount;
    }
}
