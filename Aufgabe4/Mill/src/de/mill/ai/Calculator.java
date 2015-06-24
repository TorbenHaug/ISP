package de.mill.ai;

import de.mill.enums.PlayerState;
import de.mill.model.MillGameControl;
import de.mill.model.MillGameImpl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Calculator {
    public int maxTreeDepth = -1;
    private Node bestNode = null;
    public List<Integer> moveRatings = new ArrayList<>();

    public void startCalculating(MillGameControl millGame, int maxTreeDepth){
        this.maxTreeDepth = maxTreeDepth;
        System.out.println("Calculator Best Value: " + max(new Node(new MillGameImpl(millGame.MILLGAME)), maxTreeDepth, Integer.MIN_VALUE, Integer.MAX_VALUE));
        System.out.println("Expected Best Value: " + Collections.max(moveRatings));
        System.out.println("Expected Worst Value: " + Collections.min(moveRatings));
        System.out.println("Expected Last Value: " + moveRatings.get(moveRatings.size()-1));
        millGame.exec(this.bestNode.fromPos, this.bestNode.toPos);
    }

    private int max(Node node, int treeDepth, int alpha, int beta){
        if (treeDepth == 0 || node.isLeaf()){
            moveRatings.add(node.eval());
            return node.eval();
        }

        int best = Integer.MIN_VALUE;

        for (Node succNode : node.succ()) {
            int value = 0;
//            if(best > alpha){
//                alpha = best;
//            }
            if (node.getCurrentPlayer().getState() == PlayerState.Remove){
                value = max(succNode,  treeDepth - 1, alpha, beta);
            } else {
                value = min(succNode, treeDepth - 1, alpha, beta);
            }
            if (best < value){
                best = value;
                if(treeDepth == maxTreeDepth){
                    bestNode = succNode;
                }
            }
//            if(best >= beta){
//                return best;
//            }
        }

        return best;

    }


    private int min(Node node, int treeDepth, int alpha, int beta) {
        if (treeDepth == 0 || node.isLeaf()){
            moveRatings.add(-node.eval());
            return -node.eval();
        }

        int best = Integer.MAX_VALUE;

        for (Node succNode : node.succ()) {
            int value = 0;
//            if(best < beta){
//                beta = best;
//            }
            if (node.getCurrentPlayer().getState() == PlayerState.Remove){
                value = min(succNode, treeDepth - 1, alpha, beta);
            } else {
                value = max(succNode, treeDepth - 1, alpha, beta);
            }
            if (value < best){
                best = value;
            }
//            if(alpha >= best){
//                return best;
//            }
        }

        return best;
    }

}
