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

        this.add(currentPlayerHeadline);
        this.add(currentPlayerNameLbl);
        this.add(currentPlayerNameVal);
        this.add(currentPlayerStateLbl);
        this.add(currentPlayerStateVal);
    }

    public void setCurrentPlayerName(String name){
        currentPlayerNameVal.setText(name);
    }

    public void setCurrentPlayerState(String state){
        currentPlayerStateVal.setText(state);
    }
}
