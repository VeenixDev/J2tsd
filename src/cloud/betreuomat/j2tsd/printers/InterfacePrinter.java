package cloud.betreuomat.j2tsd.printers;

import cloud.betreuomat.j2tsd.misc.TypeCaster;
import cloud.betreuomat.j2tsd.models.Model;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class InterfacePrinter extends Printer {

    public InterfacePrinter(Model model, File outDir) throws IOException {
        super(model, outDir);
    }

    @Override
    public void printModel() {
        List<String> references = new ArrayList<>();

        for(Field f : getModel().getFields()) {
            if(TypeCaster.detectType(f.getType()) == null && needsReference(f.getType().getSimpleName())) {
                references.add(f.getType().getSimpleName());
            }
        }

        try {
            for(String ref : references) {
                getWriter().write("/// <reference path=\"" + ref + ".d.ts\" />\n");
            }

            getWriter().write("// package: " + getModel().getClassPackage().getName() + "\n");
            getWriter().write("declare interface " + getModel().getClassName() + " {\n");

            if(getModel().getFields().size() > 0) {
                for(Field field : getModel().getFields()) {
                    String castName = TypeCaster.detectType(field.getType());
                    getWriter().write("  " + field.getName() + ": " + (castName == null ? field.getType().getSimpleName() : castName) + ",\n");
                }
            }
            getWriter().write("}\n");

            getWriter().flush();
            getWriter().close();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        System.out.println("// package: " + getModel().getClassPackage().getName());
        System.out.println(getModel().getClassName());
        getModel().getFields().forEach(field -> System.out.println("field " + field.getName() + ": " + field.getType().getSimpleName()));
        getModel().getMethods().forEach(method -> System.out.println("function" + method.getName() + ": " + method.getReturnType().getSimpleName()));
    }
}
