package de.mill.gui;

import de.mill.exceptions.AlreadyAquiredException;
import de.mill.exceptions.MoveNotAllowedException;
import de.mill.exceptions.UnableToRemoveStoneException;
import de.mill.interfaces.MessageReceiver;
import de.mill.model.GameState;
import de.mill.model.MillColor;
import de.mill.model.MillGame;
import de.mill.model.PlayerState;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

/**
 * Created by abq329 on 18.06.2015.
 */
public class StoneButton extends JButton implements ActionListener {
    private static ImageIcon WHITE;
    private static ImageIcon BLACK;
    private static ImageIcon NON;
    private static int moveFrom = -1;
    private static final Border noBorder = BorderFactory.createEmptyBorder();
    private static final Border mouseoverBorder = BorderFactory.createLineBorder(Color.RED);
    private static final Border selectedBorder = BorderFactory.createLineBorder(Color.BLUE);

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
    private Border tmpBorder;
    public StoneButton(int pos, MillGame gameModel, MessageReceiver receiver){
        super(NON);
        this.receiver = receiver;
        this.gameModel =  gameModel;
        this.pos = pos;
        setBackground(new Color(255, 255, 255, 0));
        setOpaque(false);
        addActionListener(this);
        this.setBorder(noBorder);
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                ((StoneButton) e.getSource()).setBorder(tmpBorder);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                tmpBorder = ((StoneButton) e.getSource()).getBorder();
                ((StoneButton) e.getSource()).setBorder(mouseoverBorder);
            }
        });
    }

    public void setColor(MillColor color){
        if(pos == moveFrom)
            this.setBorder(selectedBorder);
        else
            this.setBorder(noBorder);

        if(color == MillColor.Black)
            this.setIcon(BLACK);
        else if(color == MillColor.White)
            this.setIcon(WHITE);
        else
            this.setIcon(NON);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == this && gameModel.getGameState() != GameState.Finished){
            if(gameModel.getPlayerState() == PlayerState.Set) {
                try {
                    receiver.receiveMessage("Set " + gameModel.getCurrentPlayer().COLOR + " Stone to Position " + pos);
                    gameModel.setStone(gameModel.getCurrentPlayer(), pos);
                } catch (AlreadyAquiredException e1) {
                    receiver.receiveMessage("Not allowed to set at pos: " + pos);
                }
            }else if(gameModel.getPlayerState() == PlayerState.Move){
                if (moveFrom == -1){
                    moveFrom = pos;
                    setBorder(selectedBorder);
                    tmpBorder = selectedBorder;
                }else if (moveFrom == pos){
                    moveFrom = -1;
                    setBorder(mouseoverBorder);
                    tmpBorder = noBorder;
                }else {
                    int tmpMoveFrom = moveFrom;
                    try {
                        moveFrom = -1;
                        receiver.receiveMessage("Move Stone from " + tmpMoveFrom + " Stone to Position " + pos);
                        gameModel.moveStone(gameModel.getCurrentPlayer(),tmpMoveFrom,pos);
                    } catch (MoveNotAllowedException e1) {
                        moveFrom = tmpMoveFrom;
                        receiver.receiveMessage("Move Stone from " + moveFrom + " Stone to Position " + pos + " not allowed;");
                    }

                }
            }else if(gameModel.getPlayerState() == PlayerState.Remove){
                try {
                    receiver.receiveMessage("Remove Stone from Position " + pos);
                    gameModel.removeStone(gameModel.getCurrentPlayer(), pos);
                } catch (UnableToRemoveStoneException e1) {
                    receiver.receiveMessage("Not allowed to remove at pos: " + pos);
                }
            }
        }
    }
}
