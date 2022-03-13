package cloud.betreuomat.j2tsd.factories;

import cloud.betreuomat.j2tsd.models.Model;
import cloud.betreuomat.j2tsd.models.ModelType;
import cloud.betreuomat.j2tsd.printers.ClassPrinter;
import cloud.betreuomat.j2tsd.printers.EnumPrinter;
import cloud.betreuomat.j2tsd.printers.InterfacePrinter;
import cloud.betreuomat.j2tsd.printers.Printer;

import java.io.File;
import java.io.IOException;

public class PrinterFactory {

    @SuppressWarnings("unused")
    public static Printer getPrinter(Model model, File outDir) throws IOException {
        return switch (model.getType()) {
            case CLASS -> new ClassPrinter(model, outDir);
            case INTERFACE -> new InterfacePrinter(model, outDir);
            case ENUM -> new EnumPrinter(model, outDir);
        };
    }

    public static Printer getPrinter(Model model, File outDir, ModelType modelType) throws IOException {
        return switch (modelType) {
            case CLASS -> new ClassPrinter(model, outDir);
            case INTERFACE -> new InterfacePrinter(model, outDir);
            case ENUM -> new EnumPrinter(model, outDir);
        };
    }
}
