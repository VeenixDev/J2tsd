package cloud.betreuomat.j2tsd.printers;

import cloud.betreuomat.j2tsd.models.Model;

import java.io.File;
import java.io.IOException;

public class ClassPrinter extends Printer {

    public ClassPrinter(Model model, File outDir) throws IOException {
        super(model, outDir);
    }

    @Override
    public void printModel() {

    }
}