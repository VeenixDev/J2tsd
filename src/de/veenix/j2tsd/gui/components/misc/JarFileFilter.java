package de.veenix.j2tsd.gui.components.misc;

import javax.swing.filechooser.FileFilter;
import java.io.File;

public class JarFileFilter extends FileFilter {

    @Override
    public boolean accept(File f) {
        return f.isDirectory() || f.getName().endsWith(".jar");
    }

    @Override
    public String getDescription() {
        return "Jar Files (.jar)";
    }
}
