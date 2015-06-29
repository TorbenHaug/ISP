package de.mill.ai;

import de.mill.enums.GameState;
import de.mill.enums.PlayerState;
import de.mill.interfaces.MillGame;
import de.mill.model.GameField;
import de.mill.model.MillGameImpl;
import de.mill.model.Player;

import java.util.*;

/**
 * Created by abq329 on 24.06.2015.
 */
public class Node {

    private final Player owner;
    public int fromPos;
    public int toPos;
    private MillGameImpl millGame;

    public Node(MillGameImpl millGame){
        this.millGame = millGame;
        owner = millGame.getCurrentPlayer();
    }

    public Node(MillGameImpl millGame, int fromPos, int toPos){
        this(millGame);
        this.fromPos = fromPos;
        this.toPos = toPos;
        millGame.exec(fromPos, toPos);
    }

    public boolean isLeaf(){
        return millGame.getGameState() == GameState.Finished;
    }

    // heuristic
    public int eval(Player maxPlayer){
        Player currentMaxPlayer = maxPlayer.equals(millGame.getBlackPlayer()) ? millGame.getBlackPlayer() : millGame.getWhitePlayer();
        Player currentMinPlayer = maxPlayer.equals(millGame.getBlackPlayer()) ? millGame.getWhitePlayer() : millGame.getBlackPlayer();
        PlayerState currentState = millGame.getCurrentPlayer().getState();
        if (currentState == PlayerState.Set) {
            return 18 * recClosedMill(currentMaxPlayer, currentMinPlayer) + 26 * millDiff(currentMaxPlayer) + 1 * blockedStones(currentMaxPlayer, currentMinPlayer) + 9 * stoneDiff(currentMaxPlayer, currentMinPlayer); //+ 10 * twoPiece();
        } else if (currentState == PlayerState.Move || currentState == PlayerState.Remove ||
                currentState == PlayerState.Win || currentState == PlayerState.Loose || currentState == PlayerState.Tie) {
            if (millGame.getCurrentPlayer().stonesInGame() > 3) {
                return 14 * recClosedMill(currentMaxPlayer, currentMinPlayer) + 43 * millDiff(currentMaxPlayer) + 10 * blockedStones(currentMaxPlayer, currentMinPlayer) + 11 * stoneDiff(currentMaxPlayer, currentMinPlayer) + 1086 * winLoose(currentMaxPlayer);
            } else {
                return 16 * recClosedMill(currentMaxPlayer, currentMinPlayer) + 1190 * winLoose(currentMaxPlayer);
            }
        } else {
            throw new RuntimeException("Wrong state to eval");
        }
    }

//        Evaluation function for Phase 1 = 18 * (1) + 26 * (2) + 1 * (3) + 9 * (4) + 10 * (5) + 7 * (6)
//
//        Evaluation function for Phase 2 = 14 * (1) + 43 * (2) + 10 * (3) + 11 * (4) + 8 * (7) + 1086 * (8)
//
//        Evaluation function for Phase 3 = 16 * (1) + 10 * (5) + 1 * (6) + 1190 * (8)
//        https://kartikkukreja.wordpress.com/2014/03/17/heuristicevaluation-function-for-nine-mens-morris/

    // Regel 1
    private int recClosedMill(Player maxPlayer, Player minPlayer){
        if (maxPlayer.getState() == PlayerState.Remove){
            return 1;
        } else if (minPlayer.getState() == PlayerState.Remove){
            return -1;
        } else {
            return 0;
        }
    }

    // Regel 2
    private int millDiff(Player maxPlayer){
        return millGame.getMillDiff(maxPlayer);
    }

    // Regel 3:
    private int blockedStones(Player currentMaxPlayer, Player currentMinPlayer){
        int blockStonesCurrent = 0;
        int blockStonesOpponent = 0;

        for (Integer stone : currentMaxPlayer.getStonesOnField()){
            if (millGame.isBlocked(stone)){
                blockStonesCurrent++;
            }
        }

        for (Integer stone : currentMinPlayer.getStonesOnField()){
            if (millGame.isBlocked(stone)){
                blockStonesOpponent++;
            }
        }

        return blockStonesOpponent - blockStonesCurrent;
    }

    // Regel 4:
    private int stoneDiff(Player currentMaxPlayer, Player currentMinPlayer){
        return currentMaxPlayer.stonesInGame() - currentMinPlayer.stonesInGame();
    }

//    // Regel 5:
//    private int twoPiece(){
//        return millGame.twoPiece();
//    }

    // Regel 8:
    private int winLoose(Player currentMaxPlayer){
        if (currentMaxPlayer.getState() == PlayerState.Win){
            return 1;
        } else if (currentMaxPlayer.getState() == PlayerState.Loose){
            return -1;
        } else {
            return 0;
        }
    }

    public List<Node> succ(){
        List<Node> succNodeList = new ArrayList<>();
        Map<Integer, List<Integer>> possibleMoves = millGame.nextPossibleMove();

        if (millGame.getCurrentPlayer().getState() == PlayerState.Set) {
            List<Integer> settables = possibleMoves.get(-1);
            for (int toPos : settables) {
                succNodeList.add(new Node(new MillGameImpl(millGame), -1, toPos));
            }
        } else if (millGame.getCurrentPlayer().getState() == PlayerState.Move) {
            for (Map.Entry<Integer, List<Integer>> entry : possibleMoves.entrySet()) {
                for (int toPos : entry.getValue()) {
                    succNodeList.add(new Node(new MillGameImpl(millGame), entry.getKey(), toPos));
                }
            }
        } else if (millGame.getCurrentPlayer().getState() == PlayerState.Remove) {
            Set<Integer> settables = possibleMoves.keySet();
            for (int fromPos : settables) {
                succNodeList.add(new Node(new MillGameImpl(millGame), fromPos, -1));
            }

        }
        Collections.shuffle(succNodeList);
        return succNodeList;
    }

    public Player getCurrentPlayer(){
        return millGame.getCurrentPlayer();
    }

    @Override
    public String toString() {
        return "Node{" +
                ", fromPos=" + fromPos +
                ", toPos=" + toPos +
                '}';
    }

}
