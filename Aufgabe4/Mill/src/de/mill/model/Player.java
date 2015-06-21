package de.mill.model;

import de.mill.enums.MillColor;
import de.mill.enums.PlayerState;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by abq329 on 18.06.2015.
 */
public class Player {
    public final String NAME;
    public final MillColor COLOR;
    private final Stack<Stone> stock = new Stack<>();
    private final ArrayList<Stone> onField = new ArrayList<>();
    private PlayerState state = PlayerState.Await;

    public Player(MillColor color, String name){
        this.NAME = name;
        this.COLOR = color;
        for(int i=0; i < 9; i++){
            addStoneToStock(new Stone(color));
        }
    }
    public boolean hasStonsInStock(){
        return stock.size() > 0;
    }

    Stone getStoneFromStock(){
        Stone stone = stock.pop();
        onField.add(stone);
        return stone;
    }

    void addStoneToStock(Stone stone){
        onField.remove(stone);
        stock.push(stone);
    }

    void removeFromFieldList(Stone stone){
        onField.remove(stone);
    }

    List<Stone> getStonesOnField(){
        return onField;
    }

    void setState(PlayerState state) {
        this.state = state;
    }
    public PlayerState getState(){
        return state;
    }

    public int stonesInGame(){
        return stock.size() + onField.size();
    }
}
