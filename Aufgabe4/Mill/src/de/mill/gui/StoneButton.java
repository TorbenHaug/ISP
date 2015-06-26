package de.mill.gui;

import de.mill.exceptions.AlreadyAquiredException;
import de.mill.exceptions.MoveNotAllowedException;
import de.mill.exceptions.UnableToRemoveStoneException;
import de.mill.interfaces.ButtonRefresh;
import de.mill.interfaces.MessageReceiver;
import de.mill.enums.GameState;
import de.mill.enums.MillColor;
import de.mill.enums.PlayerState;

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
    private static final Border possibleBorder = BorderFactory.createLineBorder(Color.YELLOW);

    static{
        try {
            WHITE = new ImageIcon(ImageIO.read(StoneButton.class.getResource("/de/mill/resources/white.png")));
            BLACK = new ImageIcon(ImageIO.read(StoneButton.class.getResource("/de/mill/resources/black.png")));
            NON = new ImageIcon(ImageIO.read(StoneButton.class.getResource("/de/mill/resources/non.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int getMoveFrom(){
        return moveFrom;
    }

    public final int POS;
    private final de.mill.interfaces.MillGame gameModel;
    private final MessageReceiver receiver;
    private Border tmpBorder;
    private boolean possible = false;
    private final ButtonRefresh buttonRefresh;

    public StoneButton(int pos, de.mill.interfaces.MillGame gameModel, MessageReceiver receiver, ButtonRefresh buttonRefresh){
        super(NON);
        this.buttonRefresh = buttonRefresh;
        this.receiver = receiver;
        this.gameModel =  gameModel;
        this.POS = pos;
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
        if(POS == moveFrom)
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
        if(e.getSource() == this && gameModel.getGameState() == GameState.Running && (this.possible || moveFrom == POS)){
            if(gameModel.getPlayerState() == PlayerState.Set) {
                try {
                    gameModel.setStone(gameModel.getCurrentPlayer(), POS);
                } catch (AlreadyAquiredException e1) {
                    receiver.receiveMessage("Not allowed to set at position: " + POS);
                }
            }else if(gameModel.getPlayerState() == PlayerState.Move){
                if (moveFrom == -1){
                    moveFrom = POS;
                    setBorder(selectedBorder);
                    tmpBorder = selectedBorder;
                }else if (moveFrom == POS){
                    moveFrom = -1;
                    setBorder(mouseoverBorder);
                    tmpBorder = noBorder;
                }else {
                    int tmpMoveFrom = moveFrom;
                    try {
                        moveFrom = -1;
                        gameModel.moveStone(gameModel.getCurrentPlayer(),tmpMoveFrom, POS);
                    } catch (MoveNotAllowedException e1) {
                        moveFrom = tmpMoveFrom;
                        receiver.receiveMessage("Move Stone from " + moveFrom + " Stone to Position " + POS + " not allowed;");
                    }

                }
            }else if(gameModel.getPlayerState() == PlayerState.Remove){
                try {
                    gameModel.removeStone(gameModel.getCurrentPlayer(), POS);
                } catch (UnableToRemoveStoneException e1) {
                    receiver.receiveMessage("Not allowed to remove at position: " + POS);
                }
            }
            buttonRefresh.refresh();
        }
    }

    public void setPossible(boolean possible) {
        this.possible = possible;
        if(gameModel.getGameState() == GameState.Running && possible){
            this.setBorder(possibleBorder);
            tmpBorder = possibleBorder;
        }else{
            if (moveFrom == POS){
                setBorder(selectedBorder);
                tmpBorder = selectedBorder;
            }else {
                this.setBorder(noBorder);
                tmpBorder = noBorder;
            }
        }
    }
}
