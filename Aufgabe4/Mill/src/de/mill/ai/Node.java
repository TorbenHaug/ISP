package de.mill.ai;

import de.mill.model.MillGame;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by abq329 on 24.06.2015.
 */
public class Node {

    public int best;
    public int fromPos;
    public int toPos;
    private MillGame millGame;

    public Node(MillGame millGame){
        this.millGame = millGame;
    }

    public boolean isLeaf(){
//        if (){
//
//        }

        return true;

    }

    // heuristic
    public int eval(){

        return 1;
    }


    public List<Node> succ(){
        List<Node> succNodeList = new ArrayList<>();

        return succNodeList;
    }

}
