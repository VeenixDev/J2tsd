package cloud.betreuomat.j2tsd.emitter;

import cloud.betreuomat.j2tsd.factories.PrinterFactory;
import cloud.betreuomat.j2tsd.models.Model;
import cloud.betreuomat.j2tsd.models.ModelType;
import cloud.betreuomat.j2tsd.printers.Printer;

import java.io.File;
import java.io.IOException;
import java.util.List;

public abstract class Emitter {

    private final File outDir;
    private final List<Model> models;

    public Emitter(File outDir, List<Model> models) {
        this.outDir = outDir;
        this.models = models;
    }

    public abstract boolean emit() throws IOException;

    protected void emitAsClass(Model m) throws IOException {
        Printer printer = PrinterFactory.getPrinter(m, outDir, ModelType.CLASS);
        printer.print();
    }

    protected void emitAsInterface(Model m) throws IOException {
        Printer printer = PrinterFactory.getPrinter(m, outDir, ModelType.INTERFACE);
        printer.print();
    }

    protected void emitAsEnum(Model m) throws IOException {
        m.clearReferences();
        Printer printer = PrinterFactory.getPrinter(m, outDir, ModelType.ENUM);
        printer.print();
    }

    public final File getOutDir() {
        return outDir;
    }

    public final List<Model> getModels() {
        return models;
    }


}
