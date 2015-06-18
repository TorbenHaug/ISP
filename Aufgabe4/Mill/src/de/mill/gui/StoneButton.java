package de.mill.gui;

import de.mill.exceptions.AlreadyAquiredException;
import de.mill.interfaces.MessageReceiver;
import de.mill.model.MillColor;
import de.mill.model.MillGame;
import de.mill.model.PlayerState;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * Created by abq329 on 18.06.2015.
 */
public class StoneButton extends JButton implements ActionListener {
    private static ImageIcon WHITE;
    private static ImageIcon BLACK;
    private static ImageIcon NON;

    static{
        try {
            WHITE = new ImageIcon(ImageIO.read(StoneButton.class.getResource("/de/mill/resources/white.png")));
            BLACK = new ImageIcon(ImageIO.read(StoneButton.class.getResource("/de/mill/resources/black.png")));
            NON = new ImageIcon(ImageIO.read(StoneButton.class.getResource("/de/mill/resources/non.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private final int pos;
    private final MillGame gameModel;
    private final MessageReceiver receiver;
    public StoneButton(int pos, MillGame gameModel, MessageReceiver receiver){
        super(NON);
        this.receiver = receiver;
        this.gameModel =  gameModel;
        this.pos = pos;
        setBackground(new Color(255, 255, 255, 0));
        setOpaque(false);
        addActionListener(this);
        this.setBorder(BorderFactory.createEmptyBorder());
    }

    public void setColor(MillColor color){
        if(color == MillColor.Black)
            this.setIcon(BLACK);
        else if(color == MillColor.White)
            this.setIcon(WHITE);
        else
            this.setIcon(NON);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == this){
            if(gameModel.getState() == PlayerState.Set) {
                try {
                    receiver.receiveMessage("Set " + gameModel.getCurrentPlayer().COLOR + " Stone to Position " + pos);
                    gameModel.setStone(gameModel.getCurrentPlayer(), pos);
                } catch (AlreadyAquiredException e1) {
                    receiver.receiveMessage("Not allowed to set at pos: " + pos);
                }
            }else if(gameModel.getState() == PlayerState.Move){

            }else if(gameModel.getState() == PlayerState.Remove){
                
            }
        }
    }
}
