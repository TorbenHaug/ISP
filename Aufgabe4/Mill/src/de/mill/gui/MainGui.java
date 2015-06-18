package de.mill.gui;

import de.mill.interfaces.Repaintable;
import de.mill.model.MillColor;
import de.mill.model.MillGame;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by abq329 on 18.06.2015.
 */
public class MainGui implements Repaintable {
    private final JFrame mainWindow;
    private final JPanel menuPanel;
    private final JPanel gamePanel;
    private final List<StoneButton> stoneButtons = new ArrayList<>();
    private final MillGame gameModel;

    public MainGui(MillGame gameModel){
        this.gameModel = gameModel;
        mainWindow = new JFrame();
        mainWindow.setLayout(null);
        mainWindow.setSize(700, 750);
        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        menuPanel = new JPanel();
        menuPanel.setLayout(null);
        menuPanel.setSize(600, 50);
        menuPanel.setLocation(0, 0);
        menuPanel.setBackground(Color.GREEN);

        gamePanel = new GamePanel();
        gamePanel.setLayout(null);
        gamePanel.setSize(600, 600);
        gamePanel.setLocation(0, 50);

        mainWindow.add(menuPanel);
        mainWindow.add(gamePanel);

        for(int i=0; i< 24; i++){
            StoneButton btn = new StoneButton(i, gameModel);
            stoneButtons.add(btn);
            btn.setSize(55,55);
            btn.setLocation(0, 0);
            btn.repaint();
            gamePanel.add(btn);
        }

        stoneButtons.get(0).setLocation(0, 0);
        stoneButtons.get(1).setLocation((600/2 - 55/2), 0);
        stoneButtons.get(2).setLocation((600 - 55), 0);
        stoneButtons.get(3).setLocation(75, 75);
        stoneButtons.get(4).setLocation((600/2 - 55/2), 75);
        stoneButtons.get(5).setLocation(470, 75);
        stoneButtons.get(6).setLocation(155, 155);
        stoneButtons.get(7).setLocation((600/2 - 55/2), 155);
        stoneButtons.get(8).setLocation(380, 155);
        stoneButtons.get(9).setLocation(0,(600/2 - 55/2));
        stoneButtons.get(10).setLocation(75,(600/2 - 55/2));
        stoneButtons.get(11).setLocation(155,(600/2 - 55/2));
        stoneButtons.get(12).setLocation(380,(600/2 - 55/2));
        stoneButtons.get(13).setLocation(470,(600/2 - 55/2));
        stoneButtons.get(14).setLocation((600 - 55),(600/2 - 55/2));
        stoneButtons.get(15).setLocation(155,385);
        stoneButtons.get(16).setLocation((600/2 - 55/2),385);
        stoneButtons.get(17).setLocation(380,385);
        stoneButtons.get(18).setLocation(75,460);
        stoneButtons.get(19).setLocation((600/2 - 55/2),460);
        stoneButtons.get(20).setLocation(470,460);
        stoneButtons.get(21).setLocation(0,(600 - 55));;
        stoneButtons.get(22).setLocation((600/2 - 55/2),(600 - 55));;
        stoneButtons.get(23).setLocation((600 - 55),(600 - 55));

        mainWindow.setVisible(true);
    }

    @Override
    public void rePaint() {
        Iterator<MillColor> it1 = gameModel.getCurrentField().iterator();
        Iterator<StoneButton> it2 = stoneButtons.iterator();

        while (it1.hasNext()){
            it2.next().setColor(it1.next());
        }
    }
}
