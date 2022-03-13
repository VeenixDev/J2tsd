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

    @SuppressWarnings("ResultOfMethodCallIgnored")
    private void setupFile(File file) throws IOException {
        if (file.exists()) {
            return;
        }

        //noinspection ResultOfMethodCallIgnored
        file.getParentFile().mkdirs();
        file.createNewFile();
    }

    @SuppressWarnings("UnusedReturnValue")
    public boolean print() throws IOException {
        write("""
                /**
                 * This is a auto generated file
                 *\s
                 * @author J2tsd - Java to TypeScript Definition generator
                 * @since {{since}}
                 * @package {{package}}
                 */
                 
                """.replace("{{since}}", new Date().toString()).replace("{{package}}", getModel().getClassPackage().getName()));

        for (String ref : getModel().getReferences()) {
            write("/// <reference path=\"" + ref + ".d.ts\" />\n");
        }

        if (getModel().getReferences().size() > 0) {
            write("\n");
        }

        String className = getModel().getClassName().isEmpty() ? null : getModel().getClassName();

        if (className == null) {
            return false;
        }

        boolean ret = printModel();
        getWriter().flush();
        getWriter().close();

        return ret;
    }

    protected void write(String str) throws IOException {
        bufferedWriter.write(str);
    }

    protected abstract boolean printModel();

    @SuppressWarnings("unused")
    protected final boolean needsReference(String type) {
        return !model.getClassName().equals(type.replace("[]", ""));
    }

    @SuppressWarnings("unused")
    protected File getOutFile() {
        return outFile;
    }

    protected Model getModel() {
        return model;
    }

    private BufferedWriter getWriter() {
        return bufferedWriter;
    }
}
