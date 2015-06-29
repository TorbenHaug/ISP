package de.mill.model;

import de.mill.enums.MillColor;
import de.mill.enums.PlayerState;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Player {
    public final String NAME;
    public final MillColor COLOR;
    private int stock;
    private final ArrayList<Integer> onField;
    private PlayerState state = PlayerState.Await;
    private final boolean computer;

    public Player(MillColor color, String name, boolean computer){
        this.stock = 9;
        this.NAME = name;
        this.COLOR = color;
        onField = new ArrayList<>();
        this.computer = computer;
    }

    public Player(Player player){
        this.NAME = player.NAME;
        this.COLOR = player.COLOR;
        this.stock = player.stock;
        this.onField = new ArrayList<>(player.onField);
        this.state = player.state;
        this.computer = player.computer;
    }

    public boolean isComputer(){
        return computer;
    }

    public boolean hasStoneInStock(){
        return stock > 0;
    }

    MillColor getStoneFromStock(){
        stock--;
        return COLOR;
    }

    void addStoneToStock(Integer stone){
        onField.remove(stone);
        stock++;
    }

    void removeFromFieldList(Integer stone){
        onField.remove(stone);
    }

    public List<Integer> getStonesOnField(){
        return onField;
    }

    public int getStock() {
        return stock;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Player player = (Player) o;

        return COLOR == player.COLOR;

    }

    @Override
    public int hashCode() {
        return COLOR != null ? COLOR.hashCode() : 0;
    }
}
