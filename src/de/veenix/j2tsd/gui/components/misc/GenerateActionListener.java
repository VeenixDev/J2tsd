package de.veenix.j2tsd.gui.components.misc;

import de.veenix.j2tsd.emitter.Emitter;
import de.veenix.j2tsd.factories.EmitterFactory;
import de.veenix.j2tsd.factories.EmitterTypes;
import de.veenix.j2tsd.gui.components.FileInput;
import de.veenix.j2tsd.scanner.JarScanner;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Date;
import java.util.Optional;

public class GenerateActionListener implements ActionListener {

    private final JFrame parent;
    private final JTextArea includeArea;
    private final FileInput fin;
    private final FileInput fout;
    private final JComboBox<EmitterTypes> comboBox;

    public GenerateActionListener(JFrame parent, JTextArea includeArea, FileInput fin, FileInput fout, JComboBox<EmitterTypes> comboBox) {
        this.parent = parent;
        this.includeArea = includeArea;
        this.fin = fin;
        this.fout = fout;
        this.comboBox = comboBox;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String[] includes = includeArea.getText().split("\n");
        Optional<File> outFile = fout.getFile();
        Optional<File> inFile = fin.getFile();
        EmitterTypes emitterType = (EmitterTypes) comboBox.getSelectedItem();
        if (inFile.isEmpty()) {
            JOptionPane.showMessageDialog(parent, "Please select a input file", "Error while generating types...", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (outFile.isEmpty()) {
            JOptionPane.showMessageDialog(parent, "Please select a output (root) directory", "Error while generating types...", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (includes.length == 1 && (includes[0].isBlank() || includes[0].isEmpty())) {
            JOptionPane.showMessageDialog(parent, "Please name all packages to include", "Error while generating types...", JOptionPane.WARNING_MESSAGE);
            return;
        }

        long started = System.currentTimeMillis();
        JarScanner scanner = new JarScanner(inFile.get(), includes);
        Emitter emitter = EmitterFactory.getEmitter(outFile.get(), scanner.scan(), emitterType);

        if (emitter.emit()) {
            JOptionPane.showMessageDialog(parent, "Successfully generated types in " + ((float) (new Date().getTime() - started) / 1000) + "s\nProcessed " + scanner.getProcessedEntries() + " Files", "Result", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(parent, "Couldn't generate types", "Result", JOptionPane.ERROR_MESSAGE);
        }
    }
}
