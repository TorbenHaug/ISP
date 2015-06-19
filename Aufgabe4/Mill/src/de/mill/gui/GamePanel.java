package de.mill.gui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;


/**
 * Created by abq329 on 18.06.2015.
 */
public class GamePanel extends JPanel{
    private Image gameFieldImage;

    public GamePanel(){
        try {
            gameFieldImage = ImageIO.read(getClass().getResource("/de/mill/resources/MuehleSpielbrett.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(gameFieldImage, 0, 0, null);
    }
}
