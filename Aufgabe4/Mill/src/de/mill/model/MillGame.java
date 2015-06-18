package de.mill.model;

import de.mill.exceptions.AlreadyAquiredException;
import de.mill.interfaces.Repaintable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by abq329 on 18.06.2015.
 */
public class MillGame {
    private GameField gameField = new GameField();
    private List<Repaintable> repaintables = new ArrayList<>();

    public void setStone(Player player, int pos) throws AlreadyAquiredException {
        gameField.setStone(pos,player.getStoneFromStock());
        anounceRepantable();
    }

    public Player getCurrentPlayer() {
        return new Player();
    }

    public List<MillColor> getCurrentField() {
        return gameField.getFieldStatus();
    }

    public void addRepaintable(Repaintable repaintable){
        repaintables.add(repaintable);
    }

    private void anounceRepantable(){
        for(Repaintable repaintable: repaintables){
            repaintable.rePaint();
        }
    }
}
