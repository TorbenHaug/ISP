package de.mill;

import de.mill.gui.MainGui;
import de.mill.model.MillColor;
import de.mill.model.MillGame;
import de.mill.model.Player;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by abq329 on 18.06.2015.
 */
public class Main {
    private static MainGui gui;
    private static Player player1;
    private static Player player2;
    private static MillGame currentGame;
    private static final ActionListener pvpListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            player1 = new Player(MillColor.White, "White");
            player2 = new Player(MillColor.Black, "Black");
            currentGame = new MillGame(player1,player2);
            currentGame.addRepaintable(gui);
            gui.setGameModel(currentGame);
        }
    };

    private static final ActionListener pvcListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            throw new UnsupportedOperationException();
        }
    };

    private static final ActionListener cvpListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            throw new UnsupportedOperationException();
        }
    };

    public static void main(String[] args){
        gui = new MainGui(pvpListener,pvcListener,cvpListener);
    }
}
