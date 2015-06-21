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
    private int stock;
    private final ArrayList<Integer> onField = new ArrayList<>();
    private PlayerState state = PlayerState.Await;

    public Player(MillColor color, String name){
        this.stock = 9;
        this.NAME = name;
        this.COLOR = color;
    }
    public boolean hasStoneInStock(){
        return stock > 0;
    }

    MillColor getStoneFromStock(){
        stock--;
        //Stone stone = new Stone(COLOR);
        return COLOR;
    }

    void addStoneToStock(Integer stone){
        onField.remove(stone);
        stock++;
    }

    void removeFromFieldList(Integer stone){
        onField.remove(stone);
    }

    List<Integer> getStonesOnField(){
        return onField;
    }

    void setState(PlayerState state) {
        this.state = state;
    }
    public PlayerState getState(){
        return state;
    }

    public int stonesInGame(){
        return stock + onField.size();
    }

    public void addStoneToField(int pos) {
        onField.add(pos);
    }
}
