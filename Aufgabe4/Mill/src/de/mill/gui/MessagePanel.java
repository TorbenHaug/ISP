package de.mill.gui;

import javax.swing.*;
import javax.swing.text.DefaultCaret;
import java.awt.*;

/**
 * Created by torbenhaug on 20.06.15.
 */
public class MessagePanel extends JPanel {

    private JTextArea messageBox;
    private JScrollPane messageScrollPane;
    public MessagePanel() {
        setLayout(null);
        setSize(920,180);


        messageBox = new JTextArea();
        messageBox.setLocation(0, 0);
        messageBox.setSize(900, 160);
        messageBox.setEditable(false);
        DefaultCaret caret = (DefaultCaret)messageBox.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);

        messageScrollPane = new JScrollPane(messageBox);
        messageScrollPane.setLocation(10, 10);
        messageScrollPane.setSize(900, 160);
        messageScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);



        this.add(messageScrollPane);

    }

    public void addMessage(String text){
        messageBox.append(text + "\n");
    }
}
