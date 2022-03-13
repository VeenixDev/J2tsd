package cloud.betreuomat.j2tsd.printers;

import cloud.betreuomat.j2tsd.models.Model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

public abstract class Printer {

    private final Model model;
    private final File outFile;
    private final BufferedWriter bufferedWriter;

    public Printer(Model model, File outDir) throws IOException {
        this.model = model;
        outFile = new File(outDir.getAbsolutePath() + "/" + model.getClassPackage().getName().replace('.', '/') + "/" + model.getClassName() + ".d.ts");

        setupFile(outFile);

        bufferedWriter = new BufferedWriter(new FileWriter(outFile));
    }

    private void setupFile(File file) throws IOException {
        if (file.exists()) {
            return;
        }

        file.getParentFile().mkdirs();
        file.createNewFile();
    }

    public boolean print() throws IOException {
        getWriter().write("""
                /**
                 * This is a auto generated file
                 *\s
                 * @author J2tsd - Java to TypeScript Definition generator
                 * @since {{since}}
                 * @package {{package}}
                 */
                 
                """.replace("{{since}}", new Date().toString()).replace("{{package}}", getModel().getClassPackage().getName()));

        for (String ref : getModel().getReferences()) {
            getWriter().write("/// <reference path=\"" + ref + ".d.ts\" />\n");
        }

        if (getModel().getReferences().size() > 0) {
            getWriter().write("\n");
        }

        String className = getModel().getClassName().isEmpty() ? null : getModel().getClassName();

        if (className == null) {
            return false;
        }

        return printModel();
    }

    protected abstract boolean printModel();

    protected final boolean needsReference(String type) {
        return !model.getClassName().equals(type.replace("[]", ""));
    }

    protected File getOutFile() {
        return outFile;
    }

    protected Model getModel() {
        return model;
    }

    protected BufferedWriter getWriter() {
        return bufferedWriter;
    }
}
