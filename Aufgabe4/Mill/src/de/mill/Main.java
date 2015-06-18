package de.mill;

import de.mill.gui.MainGui;
import de.mill.model.MillColor;
import de.mill.model.MillGame;
import de.mill.model.Player;

/**
 * Created by abq329 on 18.06.2015.
 */
public class Main {
    public static void main(String[] args){
        Player player1 = new Player(MillColor.White);
        Player player2 = new Player(MillColor.Black);
        MillGame gameModel = new MillGame(player1,player2);
        MainGui gui = new MainGui(gameModel);
        gameModel.addRepaintable(gui);
    }
}
