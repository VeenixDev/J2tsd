package de.veenix.j2tsd.gui.components.dialogs;

import de.veenix.j2tsd.gui.components.FileInput;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.io.File;

public class FileDialog extends JDialog {

    private File file;
    private String path;
    private boolean selectDirectory;
    private final FileInput fileInput;

    private JFileChooser fileChooser;

    public FileDialog(FileInput fileInput, boolean selectDirectory) {
        this.fileInput = fileInput;
        this.selectDirectory = selectDirectory;
        this.setSize(500, 500);
        this.setLocationRelativeTo(null);
        this.setUndecorated(true);

        loadComponents();
    }

    private void loadComponents() {
        fileChooser = new JFileChooser();
        fileChooser.setBounds(0, 0, 500, 500);

        if(file != null) {
            fileChooser.setCurrentDirectory(file.getParentFile());
        }

        fileChooser.addActionListener(e -> {
            if(selectDirectory) {
                fileChooser.showOpenDialog(null);
            }
            switch (e.getActionCommand()) {
                case "CancelSelection":
                    file = null;
                    path = "";
                    break;
                case "ApproveSelection":
                    file = fileChooser.getSelectedFile();
                    path = file.getPath();
                    fileInput.setLabelText(path);
                    break;
                default:
                    break;
            }
            hideDialog();
        });
        fileChooser.setVisible(true);

        this.add(fileChooser);
    }

    public void setFileFilter(FileFilter fileFilter) {
        fileChooser.setFileFilter(fileFilter);
    }

    public void showDialog() {
        this.setVisible(true);
    }

    public void hideDialog() {
        this.setVisible(false);
    }

    public File getFile() {
        return file;
    }

    public String getPath() {
        return path;
    }

    public void setSelectDirectory(boolean selectDirectory) {
        if(selectDirectory) {
            fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            fileChooser.setAcceptAllFileFilterUsed(false);
        } else {
            fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
            fileChooser.setAcceptAllFileFilterUsed(true);
        }
    }

}
