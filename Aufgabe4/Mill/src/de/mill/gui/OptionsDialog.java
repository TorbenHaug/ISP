package de.mill.gui;

import de.mill.Options;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by torbenhaug on 27.06.15.
 */
public class OptionsDialog extends JDialog {

    private final Options options;

    private final OptionsDialog self;

    private final JLabel title = new JLabel("<html><font size=\"8\">Optionen</font></html>");
    private final JLabel levelLbl = new JLabel("Level: ");
    private final JTextField levelField;
    private final JButton okButton = new JButton("OK");
    private final JButton cancelButton = new JButton("Cancel");

    public OptionsDialog(JFrame parent, Options options) {
        super(parent, "Options", true);
        this.setLayout(null);

        this.options = options;
        this.setSize(400,180);
        this.setResizable(false);
        this.self = this;

        title.setSize(300,35);
        levelLbl.setSize(90, 35);
        levelLbl.setLocation(10, 50);
        levelField = new JTextField(String.valueOf(options.getLevel()));
        levelField.setSize(200,35);
        levelField.setLocation(100,50);

        okButton.setSize(120,35);
        okButton.setLocation(10, 180 - 80);
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    options.setLevel(Integer.parseInt(levelField.getText()));
                    self.setVisible(false);
                }catch(NumberFormatException e1) {
                    e1.printStackTrace();
                }
            }
        });

        cancelButton.setSize(120,35);
        cancelButton.setLocation(180, 180 - 80);
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                self.setVisible(false);
            }
        });


        this.add(title);
        this.add(levelLbl);
        this.add(levelField);
        this.add(okButton);
        this.add(cancelButton);

    }
}
