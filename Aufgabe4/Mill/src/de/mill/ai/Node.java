package de.mill.ai;

import de.mill.enums.GameState;
import de.mill.enums.PlayerState;
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
        if(millGame.getCurrentPlayer().getState() == PlayerState.Win){
            return Integer.MAX_VALUE;
        }
        return millGame.getCurrentPlayer().stonesInGame() - millGame.getOpponent().stonesInGame();
    }


    public List<Node> succ(){
        List<Node> succNodeList = new ArrayList<>();
        Map<Integer, List<Integer>> possibleMoves = millGame.nextPossibleMove();

        if (millGame.getCurrentPlayer().getState() == PlayerState.Set) {
            List<Integer> settables = possibleMoves.get(-1);
            for (int toPos: settables){
                succNodeList.add(new Node(new MillGameImpl(millGame), -1, toPos));
            }
        }else if(millGame.getCurrentPlayer().getState() == PlayerState.Move){
            for(Map.Entry<Integer, List<Integer>> entry: possibleMoves.entrySet()){
                for(int toPos : entry.getValue()){
                    succNodeList.add(new Node(new MillGameImpl(millGame), entry.getKey(), toPos));
                }
            }
        } else if(millGame.getCurrentPlayer().getState() == PlayerState.Remove){
            Set<Integer> settables = possibleMoves.keySet();
            for(int fromPos : settables){
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
