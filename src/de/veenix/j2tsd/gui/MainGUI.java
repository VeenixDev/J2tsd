package de.veenix.j2tsd.gui;

import de.veenix.j2tsd.factories.EmitterTypes;
import de.veenix.j2tsd.gui.components.FileInput;
import de.veenix.j2tsd.gui.components.misc.DirectoryFileFilter;
import de.veenix.j2tsd.gui.components.misc.GenerateActionListener;
import de.veenix.j2tsd.gui.components.misc.JarFileFilter;

import javax.swing.*;
import java.awt.*;

public class MainGUI extends JFrame {

    private final int WINDOW_WIDTH;
    private final int WINDOW_HEIGHT;

    public MainGUI() {
        WINDOW_WIDTH = 360;
        WINDOW_HEIGHT = 464;
        String VERSION = "1.0";

        this.setTitle("Java2Tsd | " + VERSION);
        this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        this.setLayout(null);
        this.setResizable(false);
        this.getContentPane().setBackground(Color.GRAY);

        loadComponents();

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        this.setVisible(true);
    }

    private void loadComponents() {
        FileInput fin = new FileInput(new JarFileFilter(), false);
        fin.setLabelPrefix("Input: ");
        fin.setBounds(0, 0, WINDOW_WIDTH, 100);
        this.add(fin);

        FileInput fout = new FileInput(new DirectoryFileFilter(), true);
        fout.setLabelPrefix("Output: ");
        fout.setBounds(0, 100, WINDOW_WIDTH, 100);
        this.add(fout);

        JComboBox<EmitterTypes> comboBox = new JComboBox<>();
        comboBox.addItem(EmitterTypes.AUTODETECT);
        comboBox.addItem(EmitterTypes.CLASS);
        comboBox.addItem(EmitterTypes.ENUM);
        comboBox.addItem(EmitterTypes.INTERFACE);
        comboBox.addItem(EmitterTypes.CLASS_AS_INTERFACE);
        comboBox.setBounds(0, 200, WINDOW_WIDTH - 15, 50);
        comboBox.setVisible(true);
        add(comboBox);

        JLabel includeLabel = new JLabel("Include: ");
        includeLabel.setBounds(0, 250, WINDOW_WIDTH, 25);
        includeLabel.setVisible(true);
        add(includeLabel);

        JTextArea includeArea = new JTextArea();
        includeArea.setBounds(0, 275, WINDOW_WIDTH, 100);
        includeArea.setVisible(true);
        add(includeArea);

        JButton generateButton = new JButton("Generate");
        generateButton.setBounds(0, 375, WINDOW_WIDTH, 50);
        generateButton.addActionListener(new GenerateActionListener(this, includeArea, fin, fout, comboBox));
        generateButton.setVisible(true);
        add(generateButton);
    }

    public int getWindowHeight() {
        return WINDOW_HEIGHT;
    }

    public int getWindowWidth() {
        return WINDOW_WIDTH;
    }
}
