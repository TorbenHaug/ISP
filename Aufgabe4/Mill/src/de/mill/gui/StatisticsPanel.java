package de.mill.gui;

import javax.swing.*;
import java.awt.*;

/**
 * Created by torbenhaug on 20.06.15.
 */
public class StatisticsPanel extends JPanel {
    private JLabel currentPlayerHeadline;
    private JLabel currentPlayerNameLbl;
    private JLabel currentPlayerNameVal;
    private JLabel currentPlayerStateLbl;
    private JLabel currentPlayerStateVal;
    private JLabel currentGameStateLbl;
    private JLabel currentGameStateVal;
    private JLabel blackHeadline;
    private JLabel blackState;
    private JLabel blackStateVal;
    private JLabel blackName;
    private JLabel blackNameVal;
    private JLabel blackStock;
    private JLabel blackStockVal;
    private JLabel blackStones;
    private JLabel blackStonesVal;
    private JLabel whiteHeadline;
    private JLabel whiteState;
    private JLabel whiteStateVal;
    private JLabel whiteName;
    private JLabel whiteNameVal;
    private JLabel whiteStock;
    private JLabel whiteStockVal;
    private JLabel whiteStones;
    private JLabel whiteStonesVal;
    private String standartFont;
    private Font headlineFont;
    private final Font listFont;
    public StatisticsPanel() {
        setLayout(null);
        setSize(300,600);
        currentPlayerHeadline = new JLabel("Current Player:");
        standartFont = currentPlayerHeadline.getFont().getFontName();
        headlineFont = new Font(standartFont,Font.BOLD,30);
        listFont = new Font(standartFont, Font.PLAIN, 18);
        currentPlayerHeadline.setSize(300, 35);
        currentPlayerHeadline.setLocation(10,10);
        currentPlayerHeadline.setFont(headlineFont);

        currentPlayerNameLbl = new JLabel("Name: ");
        currentPlayerNameLbl.setSize(100,20);
        currentPlayerNameLbl.setLocation(30, 45);
        currentPlayerNameLbl.setFont(listFont);

        currentPlayerNameVal = new JLabel("Unknown");
        currentPlayerNameVal.setSize(100,20);
        currentPlayerNameVal.setLocation(130, 45);
        currentPlayerNameVal.setFont(listFont);

        currentPlayerStateLbl = new JLabel("Status: ");
        currentPlayerStateLbl.setSize(100,20);
        currentPlayerStateLbl.setLocation(30, 65);
        currentPlayerStateLbl.setFont(listFont);

        currentPlayerStateVal = new JLabel("Unknown");
        currentPlayerStateVal.setSize(100,20);
        currentPlayerStateVal.setLocation(130, 65);
        currentPlayerStateVal.setFont(listFont);

        currentGameStateLbl = new JLabel("Game: ");
        currentGameStateLbl.setSize(100,20);
        currentGameStateLbl.setLocation(30, 85);
        currentGameStateLbl.setFont(listFont);

        currentGameStateVal = new JLabel("Unknown");
        currentGameStateVal.setSize(100,20);
        currentGameStateVal.setLocation(130, 85);
        currentGameStateVal.setFont(listFont);

        whiteHeadline = new JLabel("White Player");
        whiteHeadline.setSize(300, 35);
        whiteHeadline.setLocation(10,115);
        whiteHeadline.setFont(headlineFont);

        whiteName = new JLabel("Name: ");
        whiteName.setSize(100,20);
        whiteName.setLocation(30, 150);
        whiteName.setFont(listFont);

        whiteNameVal = new JLabel("Unknown");
        whiteNameVal.setSize(100,20);
        whiteNameVal.setLocation(130, 150);
        whiteNameVal.setFont(listFont);

        whiteState = new JLabel("State: ");
        whiteState.setSize(100,20);
        whiteState.setLocation(30, 170);
        whiteState.setFont(listFont);

        whiteStateVal = new JLabel("Unknown");
        whiteStateVal.setSize(100,20);
        whiteStateVal.setLocation(130, 170);
        whiteStateVal.setFont(listFont);

        whiteStock = new JLabel("Stock: ");
        whiteStock.setSize(100,20);
        whiteStock.setLocation(30, 190);
        whiteStock.setFont(listFont);

        whiteStockVal = new JLabel("Unknown");
        whiteStockVal.setSize(100,20);
        whiteStockVal.setLocation(130, 190);
        whiteStockVal.setFont(listFont);

        whiteStones = new JLabel("Stones: ");
        whiteStones.setSize(100,20);
        whiteStones.setLocation(30, 210);
        whiteStones.setFont(listFont);

        whiteStonesVal = new JLabel("Unknown");
        whiteStonesVal.setSize(100,20);
        whiteStonesVal.setLocation(130, 210);
        whiteStonesVal.setFont(listFont);

        blackHeadline = new JLabel("Black Player");
        blackHeadline.setSize(300, 35);
        blackHeadline.setLocation(10,240);
        blackHeadline.setFont(headlineFont);

        blackName = new JLabel("Name: ");
        blackName.setSize(100,20);
        blackName.setLocation(30, 275);
        blackName.setFont(listFont);

        blackNameVal = new JLabel("Unknown");
        blackNameVal.setSize(100,20);
        blackNameVal.setLocation(130, 275);
        blackNameVal.setFont(listFont);

        blackState = new JLabel("State: ");
        blackState.setSize(100,20);
        blackState.setLocation(30, 295);
        blackState.setFont(listFont);

        blackStateVal = new JLabel("Unknown");
        blackStateVal.setSize(100,20);
        blackStateVal.setLocation(130, 295);
        blackStateVal.setFont(listFont);

        blackStock = new JLabel("Stock: ");
        blackStock.setSize(100,20);
        blackStock.setLocation(30, 315);
        blackStock.setFont(listFont);

        blackStockVal = new JLabel("Unknown");
        blackStockVal.setSize(100,20);
        blackStockVal.setLocation(130, 315);
        blackStockVal.setFont(listFont);

        blackStones = new JLabel("Stones: ");
        blackStones.setSize(100,20);
        blackStones.setLocation(30, 335);
        blackStones.setFont(listFont);

        blackStonesVal = new JLabel("Unknown");
        blackStonesVal.setSize(100,20);
        blackStonesVal.setLocation(130, 335);
        blackStonesVal.setFont(listFont);

        this.add(currentPlayerHeadline);
        this.add(currentPlayerNameLbl);
        this.add(currentPlayerNameVal);
        this.add(currentPlayerStateLbl);
        this.add(currentPlayerStateVal);
        this.add(currentGameStateLbl);
        this.add(currentGameStateVal);
        this.add(whiteHeadline);
        this.add(whiteName);
        this.add(whiteNameVal);
        this.add(whiteState);
        this.add(whiteStateVal);
        this.add(whiteStock);
        this.add(whiteStockVal);
        this.add(whiteStones);
        this.add(whiteStonesVal); 
        this.add(blackHeadline);
        this.add(blackName);
        this.add(blackNameVal);
        this.add(blackState);
        this.add(blackStateVal);
        this.add(blackStock);
        this.add(blackStockVal);
        this.add(blackStones);
        this.add(blackStonesVal);

    }

    public void setCurrentPlayerName(String name){
        currentPlayerNameVal.setText(name);
    }

    public void setCurrentPlayerState(String state){
        currentPlayerStateVal.setText(state);
    }
    
    public void setGameState(String state){ 
        currentGameStateVal.setText(state); 
    }
    
    public void setWhiteName(String name){ 
        whiteNameVal.setText(name); 
    }
    
    public void setWhiteState(String state){
        whiteStateVal.setText(state);
    }
    
    public void setWhiteStock(String stock){
        whiteStockVal.setText(stock);
    }
    
    public void setWhiteStones(String stones){
        whiteStonesVal.setText(stones);
    }
    
    public void setBlackName(String name){ 
        blackNameVal.setText(name); 
    }
    
    public void setBlackState(String state){
        blackStateVal.setText(state);
    }
    
    public void setBlackStock(String stock){
        blackStockVal.setText(stock);
    }
    
    public void setBlackStones(String stones){
        blackStonesVal.setText(stones);
    }
    
}
