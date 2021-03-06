package de.mill.gui;

import de.mill.Options;
import de.mill.enums.PlayerState;
import de.mill.interfaces.ButtonRefresh;
import de.mill.interfaces.MessageReceiver;
import de.mill.interfaces.MillGame;
import de.mill.interfaces.Refresheable;
import de.mill.enums.MillColor;
import de.mill.model.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

/**
 * Created by abq329 on 18.06.2015.
 */
public class MainGui implements Refresheable {
    private final JFrame mainWindow;
    private final Options options;
    private JPanel gamePanel;
    private StatisticsPanel statisticsPanel;
    private MessagePanel messagePanel;
    private final List<StoneButton> stoneButtons = new ArrayList<>();
    private MillGame gameModel;
    private final MessageReceiver receiver;
    private final JMenuBar menuBar;
    private final JMenu startMenu;
    private final JMenu extrasMenu;
    private final JMenuItem pvp;
    private final JMenuItem cvp;
    private final JMenuItem pvc;
    private final JMenuItem cvc;
    private final JMenuItem optionsMenuItem;
    private final Color backGroundColor = new Color(255, 216, 127);
    private Color gameBackgroundColor = backGroundColor;
    private final ButtonRefresh buttonRefresh;

    public MainGui(ActionListener pvpListener, ActionListener cvpListener, ActionListener pvcListener, ActionListener cvcListener, Options options){
        this.receiver = new MessageReceiver() {
            @Override
            public void receiveMessage(String message) {
                printMessage(message);
            }
        };
        this.buttonRefresh = new ButtonRefresh() {
            @Override
            public void refresh() {
                refreshButtons();
            }
        };
        this.options = options;
        mainWindow = new JFrame();
        mainWindow.setLayout(null);
        mainWindow.setSize(960, 870);
        mainWindow.setResizable(false);
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
        cvc = new JMenuItem("Computer vs. Computer");
        cvc.addActionListener(cvcListener);
        startMenu.add(cvc);

        extrasMenu = new JMenu("Extras");
        optionsMenuItem = new JMenuItem("Optionen");
        optionsMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                OptionsDialog dialog = new OptionsDialog(mainWindow, options);
                dialog.setVisible(true);
            }
        });
        extrasMenu.add(optionsMenuItem);

        menuBar.add(startMenu);
        menuBar.add(extrasMenu);
        mainWindow.setJMenuBar(menuBar);


        mainWindow.getContentPane().setBackground(backGroundColor);
        mainWindow.setVisible(true);

    }

    public MessageReceiver getMessageReceiver() {
        return receiver;
    }

    @Override
    public void refresh() {
        Iterator<MillColor> it1 = gameModel.getCurrentField().iterator();
        Iterator<StoneButton> it2 = stoneButtons.iterator();

        while (it1.hasNext()){
            it2.next().setColor(it1.next());
        }

        if(statisticsPanel != null){
            Player white = gameModel.getWhitePlayer();
            Player black = gameModel.getBlackPlayer();
            statisticsPanel.setCurrentPlayerName(gameModel.getCurrentPlayer().NAME);
            statisticsPanel.setCurrentPlayerState(gameModel.getPlayerState().toString());
            statisticsPanel.setGameState(gameModel.getGameState().toString());
            statisticsPanel.setWhiteName(white.NAME);
            statisticsPanel.setWhiteState(white.getState().toString());
            statisticsPanel.setWhiteStock("" + white.getStock());
            statisticsPanel.setWhiteStones("" + white.stonesInGame());
            statisticsPanel.setBlackName(black.NAME);
            statisticsPanel.setBlackState(black.getState().toString());
            statisticsPanel.setBlackStock("" + black.getStock());
            statisticsPanel.setBlackStones("" + black.stonesInGame());
        }
        buttonRefresh.refresh();
        //System.out.println("MainGui 88: next possible moves: " + gameModel.nextPossibleMove());
    }

    private void printMessage(String message){
        if(messagePanel != null)
            messagePanel.addMessage(message);
    }

    private void refreshButtons(){
        Map<Integer, List<Integer>> possibleMoves = gameModel.nextPossibleMove();

        if (gameModel.getCurrentPlayer().getState() == PlayerState.Set) {
            List<Integer> settables = possibleMoves.get(-1);
            for (StoneButton button : stoneButtons) {
                button.setPossible(settables.contains(button.POS));
            }
        }else if(gameModel.getCurrentPlayer().getState() == PlayerState.Move){
            if(StoneButton.getMoveFrom() == -1){
                Set<Integer> possibleFrom = possibleMoves.keySet();
                for (StoneButton button : stoneButtons) {
                    button.setPossible(possibleFrom.contains(button.POS));
                }
            }else{
                List<Integer> possibleFrom = possibleMoves.get(StoneButton.getMoveFrom());
                for (StoneButton button : stoneButtons) {
                    button.setPossible(possibleFrom.contains(button.POS));
                }
            }
        } else if(gameModel.getCurrentPlayer().getState() == PlayerState.Remove){
            Set<Integer> settables = possibleMoves.keySet();
            for (StoneButton button : stoneButtons) {
                button.setPossible(settables.contains(button.POS));
            }
        }
        else {
            for (StoneButton button : stoneButtons) {
                button.setPossible(false);
            }
        }
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
        if(!(statisticsPanel == null)){
            mainWindow.remove(statisticsPanel);
        }
        gamePanel = new GamePanel();
        gamePanel.setLayout(null);
        gamePanel.setSize(600, 600);
        gamePanel.setLocation(10, 10);
        gamePanel.setBorder(BorderFactory.createBevelBorder(0));
        gamePanel.setBackground(gameBackgroundColor);

        if(!(messagePanel == null)){
            mainWindow.remove(messagePanel);
        }

        stoneButtons.clear();
        for(int i=0; i< 24; i++){
            StoneButton btn = new StoneButton(i, gameModel, receiver, buttonRefresh);
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

        statisticsPanel = new StatisticsPanel();
        statisticsPanel.setLocation(630, 10);
        statisticsPanel.setBorder(BorderFactory.createBevelBorder(0));
        statisticsPanel.setBackground(gameBackgroundColor);

        messagePanel = new MessagePanel();
        messagePanel.setLocation(10, 630);
        messagePanel.setBorder(BorderFactory.createBevelBorder(0));
        messagePanel.setBackground(gameBackgroundColor);

        mainWindow.add(statisticsPanel);
        mainWindow.add(gamePanel);
        mainWindow.add(messagePanel);
        mainWindow.repaint();
    }
}
