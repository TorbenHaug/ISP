package de.mill;

import de.mill.gui.MainGui;
import de.mill.enums.MillColor;
import de.mill.model.MillGameControl;
import de.mill.model.MillGameImpl;
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
    private static de.mill.interfaces.MillGame currentGame;
    private static final ActionListener pvpListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            player1 = new Player(MillColor.White, "White", false);
            player2 = new Player(MillColor.Black, "Black", false);
            currentGame = new MillGameControl(player1,player2);
            currentGame.addRepaintable(gui);
            gui.setGameModel(currentGame);
        }
    };

    private static final ActionListener pvcListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            player1 = new Player(MillColor.White, "Human", false);
            player2 = new Player(MillColor.Black, "Computer", true);
            currentGame = new MillGameControl(player1,player2);
            currentGame.addRepaintable(gui);
            gui.setGameModel(currentGame);
        }
    };

    private static final ActionListener cvpListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            player1 = new Player(MillColor.White, "Computer", true);
            player2 = new Player(MillColor.Black, "Human", false);
            currentGame = new MillGameControl(player1,player2);
            currentGame.addRepaintable(gui);
            gui.setGameModel(currentGame);
        }
    };

    public static void main(String[] args){
        gui = new MainGui(pvpListener, cvpListener, pvcListener);
    }
}
