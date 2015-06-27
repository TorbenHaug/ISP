package de.mill.ai;

import de.mill.enums.GameState;
import de.mill.enums.PlayerState;
import de.mill.interfaces.MillGame;
import de.mill.model.GameField;
import de.mill.model.MillGameImpl;
import de.mill.model.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by abq329 on 24.06.2015.
 */
public class Node {

    public int fromPos;
    public int toPos;
    private MillGameImpl millGame;

    public Node(MillGameImpl millGame){
        this.millGame = millGame;
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
    public int eval(){
        PlayerState currentState = millGame.getCurrentPlayer().getState();
        if (currentState == PlayerState.Set) {
            return 18 * recClosedMill() + 26 * millDiff() + 1 * blockedStones() + 9 * stoneDiff(); //+ 10 * twoPiece();
        } else if (currentState == PlayerState.Move || currentState == PlayerState.Remove ||
                currentState == PlayerState.Win || currentState == PlayerState.Loose || currentState == PlayerState.Tie) {
            if (millGame.getCurrentPlayer().stonesInGame() > 3) {
                return 14 * recClosedMill() + 43 * millDiff() + 10 * blockedStones() + 11 * stoneDiff() + 1086 * winLoose();
            } else {
                return 16 * recClosedMill() + 1190 * winLoose();
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
    private int recClosedMill(){
        if (millGame.getCurrentPlayer().getState() == PlayerState.Remove){
            return 1;
        } else if (millGame.getOpponent().getState() == PlayerState.Remove){
            return -1;
        } else {
            return 0;
        }
    }

    // Regel 2
    private int millDiff(){
        return millGame.getMillDiff();
    }

    // Regel 3:
    private int blockedStones(){
        int blockStonesCurrent = 0;
        int blockStonesOpponent = 0;

        for (Integer stone : millGame.getCurrentPlayer().getStonesOnField()){
            if (millGame.isBlocked(stone)){
                blockStonesCurrent++;
            }
        }

        for (Integer stone : millGame.getOpponent().getStonesOnField()){
            if (millGame.isBlocked(stone)){
                blockStonesOpponent++;
            }
        }

        return blockStonesOpponent - blockStonesCurrent;
    }

    // Regel 4:
    private int stoneDiff(){
        return millGame.getCurrentPlayer().stonesInGame() - millGame.getOpponent().stonesInGame();
    }

//    // Regel 5:
//    private int twoPiece(){
//        return millGame.twoPiece();
//    }

    // Regel 8:
    private int winLoose(){
        if (millGame.getCurrentPlayer().getState() == PlayerState.Win){
            return 1;
        } else if (millGame.getCurrentPlayer().getState() == PlayerState.Loose){
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
