package de.veenix.j2tsd.gui.components.misc;

import javax.swing.filechooser.FileFilter;
import java.io.File;

public class DirectoryFileFilter extends FileFilter {
    @Override
    public boolean accept(File f) {
        return f.isDirectory();
    }

    @Override
    public String getDescription() {
        return "Directory";
    }
}
