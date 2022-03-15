package cloud.betreuomat.j2tsd.printers;

import cloud.betreuomat.j2tsd.misc.TypeCaster;
import cloud.betreuomat.j2tsd.models.Model;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;

public class InterfacePrinter extends Printer {

    public InterfacePrinter(Model model, File outDir) throws IOException {
        super(model, outDir);
    }

    @Override
    public boolean printModel() {
        try {
            write("declare interface " + getModel().getClassName() + " {\n");

            if(getModel().getFields().size() > 0) {
                for(Field field : getModel().getFields()) {
                    String castName = TypeCaster.detectType(field.getType().getSimpleName(), field.getGenericType().getTypeName()).getType();

                    write("  " + field.getName() + ": " + (castName == null ? field.getType().getSimpleName() : castName) + ",\n");
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
