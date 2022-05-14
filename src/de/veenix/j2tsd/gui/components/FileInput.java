package de.veenix.j2tsd.gui.components;

import de.veenix.j2tsd.gui.components.dialogs.FileDialog;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.io.File;
import java.util.Optional;

public class FileInput extends Component {

    private FileDialog fileDialog;

    private JLabel label;
    private String labelPrefix = "File: ";
    private String labelText = "";

    public FileInput(FileFilter fileFilter, boolean selectDirectory) {
        fileDialog.setFileFilter(fileFilter);
        fileDialog.setSelectDirectory(selectDirectory);
    }

    @Override
    void onCreate() {
        fileDialog = new FileDialog(this, false);
        loadComponents();
        this.setVisible(true);
    }

    private void loadComponents() {
        JButton button = new JButton("Select File");
        button.setBounds(0, 0, 360, 50);
        button.setVisible(true);
        button.addActionListener(e -> fileDialog.showDialog());
        add(button);

        label = new JLabel(labelPrefix);
        label.setBounds(0, 50, 360, 30);
        label.setVisible(true);
        add(label);
    }

    private void updateLabel() {
        label.setText(labelPrefix + labelText);
    }

    public void setLabelText(String text) {
        labelText = text;
        updateLabel();
    }

    public void setLabelPrefix(String prefix) {
        labelPrefix = prefix;
        updateLabel();
    }

    public Optional<File> getFile() {
        return fileDialog.getFile() == null ? Optional.empty() : Optional.of(fileDialog.getFile());
    }

}
