package de.veenix.j2tsd.printers;

import de.veenix.j2tsd.models.Model;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;

public class EnumPrinter extends Printer {

    public EnumPrinter(Model model, File outDir) throws IOException {
        super(model, outDir);
    }

    @Override
    public boolean printModel() {
        try {
            write("declare enum " + getModel().getClassName() + " {\n");

            if(getModel().getFields().size() > 0) {
                for(Field field : getModel().getFields()) {
                    if(field.getName().equals("$VALUES")) {
                        continue;
                    }

                    write("  " + field.getName() + ",\n");
                }
            }

            write("}\n");
            return true;
        } catch (IOException exception) {
            exception.printStackTrace();
            return false;
        }
    }
}