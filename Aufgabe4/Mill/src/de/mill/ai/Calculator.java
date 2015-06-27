package de.mill.ai;

import de.mill.enums.PlayerState;
import de.mill.model.MillGameControl;
import de.mill.model.MillGameImpl;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Calculator {
    public int maxTreeDepth = -1;
    private Node bestNode = null;

    public long startCalculating(MillGameControl millGame, int maxTreeDepth){
        long start = System.currentTimeMillis();
        this.maxTreeDepth = maxTreeDepth;
        max(new Node(new MillGameImpl(millGame.MILLGAME)), maxTreeDepth, Integer.MIN_VALUE, Integer.MAX_VALUE);
        millGame.exec(this.bestNode.fromPos, this.bestNode.toPos);
        return System.currentTimeMillis() - start;
    }


    private int max(Node node, int treeDepth, int alpha, int beta){
        if (treeDepth <= 0 || node.isLeaf()){
            int evalCalc = node.eval();
            return evalCalc;
        }

        int best = Integer.MIN_VALUE;

        //if(!node.getCurrentPlayer().isComputer()) { System.out.println("Calculator Max Currentplayer is Not Computer: "); }

        for (Node succNode : node.succ()) {
            int value = 0;
            if(best > alpha){
                alpha = best;
            }

            if (succNode.getCurrentPlayer().COLOR == node.getCurrentPlayer().COLOR){
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
            if(best >= beta){
                return best;
            }
        }

        return best;

    }


    private int min(Node node, int treeDepth, int alpha, int beta) {

        if (treeDepth <= 0 || node.isLeaf()){
            int evalCalc = node.eval();
            return -evalCalc;
        }

        int best = Integer.MAX_VALUE;

        //if(node.getCurrentPlayer().isComputer()) { System.out.println("Calculator Min Currentplayer is Not Human: "); }

        for (Node succNode : node.succ()) {
            int value = 0;
            if(best < beta){
                beta = best;
            }
            if (succNode.getCurrentPlayer().COLOR == node.getCurrentPlayer().COLOR){
                value = min(succNode, treeDepth - 1, alpha, beta);
            } else {
                value = max(succNode, treeDepth - 1, alpha, beta);
            }
            if (value < best){
                best = value;
            }
            if(alpha >= best){
                return best;
            }
        }

        return best;
    }
}
