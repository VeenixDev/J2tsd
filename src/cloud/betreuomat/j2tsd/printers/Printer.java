package cloud.betreuomat.j2tsd.printers;

import cloud.betreuomat.j2tsd.models.Model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

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

    public abstract boolean printModel();

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
