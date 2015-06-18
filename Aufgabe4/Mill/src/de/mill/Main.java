package de.mill;

import de.mill.gui.MainGui;
import de.mill.model.MillGame;

/**
 * Created by abq329 on 18.06.2015.
 */
public class Main {
    public static void main(String[] args){
        MillGame gameModel = new MillGame();
        MainGui gui = new MainGui(gameModel);
        gameModel.addRepaintable(gui);
    }
}
