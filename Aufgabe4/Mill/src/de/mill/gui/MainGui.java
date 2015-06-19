package de.mill.gui;

import de.mill.interfaces.MessageReceiver;
import de.mill.interfaces.Refresheable;
import de.mill.model.MillColor;
import de.mill.model.MillGame;

import javax.swing.*;
import javax.swing.plaf.ColorUIResource;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by abq329 on 18.06.2015.
 */
public class MainGui implements Refresheable {
    private final JFrame mainWindow;
    private JPanel gamePanel;
    private final List<StoneButton> stoneButtons = new ArrayList<>();
    private MillGame gameModel;
    private final MessageReceiver receiver;
    private final JMenuBar menuBar;
    private final JMenu startMenu;
    private final JMenuItem pvp;
    private final JMenuItem cvp;
    private final JMenuItem pvc;
    private final Color backGroundColor = new Color(255, 216, 127);
    private Color gameBackgroundColor = backGroundColor;

    public MainGui(ActionListener pvpListener, ActionListener cvpListener, ActionListener pvcListener){
        this.receiver = new MessageReceiver() {
            @Override
            public void receiveMessage(String message) {
                printMessage(message);
            }
        };
        mainWindow = new JFrame();
        mainWindow.setLayout(null);
        mainWindow.setSize(670, 670);
        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        menuBar = new JMenuBar();
        startMenu = new JMenu("Start");
        pvp = new JMenuItem("Player vs. Player");
        pvp.addActionListener(pvpListener);
        startMenu.add(pvp);
        cvp = new JMenuItem("Computer vs. Player");
        cvp.addActionListener(cvpListener);
        startMenu.add(cvp);
        pvc = new JMenuItem("Player vs. Computer");
        pvc.addActionListener(pvcListener);
        startMenu.add(pvc);
        menuBar.add(startMenu);
        mainWindow.setJMenuBar(menuBar);


        mainWindow.getContentPane().setBackground(backGroundColor);
        mainWindow.setVisible(true);
    }

    @Override
    public void refresh() {
        Iterator<MillColor> it1 = gameModel.getCurrentField().iterator();
        Iterator<StoneButton> it2 = stoneButtons.iterator();

        while (it1.hasNext()){
            it2.next().setColor(it1.next());
        }
    }

    private void printMessage(String message){
        System.out.println(message);
    }

    public void setGameModel(MillGame gameModel){
        this.gameModel = gameModel;
        initGame();
        refresh();
    }

    void initGame(){
        if(!(gamePanel == null)) {
            mainWindow.remove(gamePanel);
        }
        gamePanel = new GamePanel();
        gamePanel.setLayout(null);
        gamePanel.setSize(600, 600);
        gamePanel.setLocation(10, 10);
        gamePanel.setBackground(gameBackgroundColor);
        stoneButtons.clear();


        for(int i=0; i< 24; i++){
            StoneButton btn = new StoneButton(i, gameModel, receiver);
            stoneButtons.add(btn);
            btn.setSize(42,42);
            btn.setLocation(0, 0);
            btn.repaint();
            gamePanel.add(btn);
        }

        stoneButtons.get(0).setLocation(4, 4);
        stoneButtons.get(1).setLocation(284, 4);
        stoneButtons.get(2).setLocation(555, 4);
        stoneButtons.get(3).setLocation(86, 85);
        stoneButtons.get(4).setLocation(284, 85);
        stoneButtons.get(5).setLocation(474, 85);
        stoneButtons.get(6).setLocation(168, 167);
        stoneButtons.get(7).setLocation(284, 167);
        stoneButtons.get(8).setLocation(392, 167);
        stoneButtons.get(9).setLocation(4,285);
        stoneButtons.get(10).setLocation(86,285);
        stoneButtons.get(11).setLocation(168,285);
        stoneButtons.get(12).setLocation(392,285);
        stoneButtons.get(13).setLocation(474,285);
        stoneButtons.get(14).setLocation(555,285);
        stoneButtons.get(15).setLocation(168,391);
        stoneButtons.get(16).setLocation(284,391);
        stoneButtons.get(17).setLocation(392,391);
        stoneButtons.get(18).setLocation(86,474);
        stoneButtons.get(19).setLocation(284,474);
        stoneButtons.get(20).setLocation(474,474);
        stoneButtons.get(21).setLocation(4,555);;
        stoneButtons.get(22).setLocation(284,555);;
        stoneButtons.get(23).setLocation(555,555);
        mainWindow.repaint();
        mainWindow.add(gamePanel);
    }
}
