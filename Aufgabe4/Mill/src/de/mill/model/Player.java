package de.mill.model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * Created by abq329 on 18.06.2015.
 */
public class Player {
    public final MillColor COLOR;
    private final Stack<Stone> stock = new Stack<>();
    private PlayerState state = PlayerState.Await;

    public Player(MillColor color){
        this.COLOR = color;
        for(int i=0; i < 9; i++){
            addStoneToStock(new Stone(color));
        }
    }
    public boolean hasStonsInStock(){
        return stock.size() > 0;
    }

    Stone getStoneFromStock(){
        return stock.pop();
    }

    void addStoneToStock(Stone stone){
        stock.push(stone);
    }

    void setState(PlayerState state) {
        this.state = state;
    }
    public PlayerState getState(){
        return state;
    }
}
