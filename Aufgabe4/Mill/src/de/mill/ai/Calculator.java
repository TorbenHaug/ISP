package de.mill.ai;

import de.mill.model.MillGame;

import java.util.List;

public class Calculator {
    public static int maxTreeDepth = -1;
    private static Node bestNode = null;

    public static void startCalculating(MillGame millGame, int maxTreeDepth){
        Calculator.maxTreeDepth = maxTreeDepth;
        max(new Node(new MillGame(millGame)), maxTreeDepth);
        millGame.exec(bestNode.fromPos, bestNode.toPos);
    }

    private static int max(Node node, int treeDepth){
        if (treeDepth == 0 || node.isLeaf()){
            return node.eval();
        }

        int best = Integer.MIN_VALUE;

        for (Node succNode : node.succ()) {
            int value = min(succNode,  treeDepth - 1);
            if (value > best){
                best = value;
                if(treeDepth == maxTreeDepth){
                    bestNode = succNode;
                }
            }
        }

        return best;

    }


    private static int min(Node node, int treeDepth) {
        if (treeDepth == 0 || node.isLeaf()){
            return node.eval();
        }

        int best = Integer.MAX_VALUE;

        for (Node succNode : node.succ()) {
            int value = max(succNode, treeDepth - 1);
            if (value < best){
                best = value;
            }
        }

        return best;
    }


}
