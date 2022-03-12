package cloud.betreuomat.j2tsd.printers;

import cloud.betreuomat.j2tsd.models.Model;

import java.io.File;
import java.io.IOException;

public class EnumPrinter extends Printer {

    public EnumPrinter(Model model, File outDir) throws IOException {
        super(model, outDir);
    }

    @Override
    public boolean printModel() {
        return true;
    }
}