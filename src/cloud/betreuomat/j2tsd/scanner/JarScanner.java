package cloud.betreuomat.j2tsd.scanner;

import cloud.betreuomat.j2tsd.builders.ModelBuilder;
import cloud.betreuomat.j2tsd.models.Model;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class JarScanner {

    private String jarPath;
    private JarFile jar;
    private Enumeration<JarEntry> jarEntries;
    private URLClassLoader classLoader;

    private int processedEntries = 0;

    public JarScanner(File inputFile) {
        init(inputFile);
    }

    private void init(File inputFile) {
        try {
            jarPath = inputFile.getAbsolutePath();
            if (!jarPath.endsWith(".jar")) {
                throw new IllegalArgumentException("inputFile should be a jar");
            }

            jar = new JarFile(jarPath);
            jarEntries = jar.entries();

            URL[] urls = new URL[] { new URL("jar:file:" + jarPath + "!/") };
            classLoader = URLClassLoader.newInstance(urls);
        } catch (IOException exception) {
            System.out.println("Couldn't open the requested jar file");
        }
    }

    public List<Model> scan() throws ClassNotFoundException {
        ArrayList<Model> ret = new ArrayList<>();

        while(jarEntries.hasMoreElements()) {
            JarEntry entry = jarEntries.nextElement();
            processedEntries++;

            if(entry.isDirectory() || !entry.getName().endsWith(".class")) {
                continue;
            }
            String className = entry.getName().substring(0, entry.getName().length() - 6);
            className = className.replace('/', '.');
            Class<?> clazz = classLoader.loadClass(className);

            ret.add(new ModelBuilder(clazz).build());
        }

        return ret;
    }

    public String getJarPath() {
        return jarPath;
    }

    public JarFile getJar() {
        return jar;
    }

    public Enumeration<JarEntry> getJarEntries() {
        return jarEntries;
    }

    public URLClassLoader getClassLoader() {
        return classLoader;
    }

    public int getProcessedEntries() {
        return processedEntries;
    }
}
