package cloud.betreuomat.j2tsd.printers;

import cloud.betreuomat.j2tsd.misc.TypeCaster;
import cloud.betreuomat.j2tsd.models.Model;
import cloud.betreuomat.j2tsd.models.ModelType;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class InterfacePrinter extends Printer {

    public InterfacePrinter(Model model, File outDir) throws IOException {
        super(model, outDir);
    }

    @Override
    public boolean printModel() {
        /*
        List<String> references = new ArrayList<>();

        for(Field f : getModel().getFields()) {
            if(TypeCaster.detectType(f) == null && needsReference(f.getType().getSimpleName())) {
                references.add(f.getType().getSimpleName());
            }
        }
        */

        try {
            getWriter().write("""
                    /**
                     * This is a auto generated file
                     *\s
                     * @author J2tsd - Java to TypeScript Definition generator
                     * @since {{since}}
                     * @package {{package}}
                     */
                     
                    """.replace("{{since}}", new Date().toString()).replace("{{package}}", getModel().getClassPackage().getName()));
            for(String ref : getModel().getReferences()) {
                getWriter().write("/// <reference path=\"" + ref + ".d.ts\" />\n");
            }

            if(getModel().getReferences().size() > 0) {
                getWriter().write("\n");
            }

            String className = getModel().getClassName().isEmpty() ? null : getModel().getClassName();

            if(className == null) {
                return false;
            }

            getWriter().write("declare interface " + getModel().getClassName() + " {\n");

            if(getModel().getFields().size() > 0) {
                for(Field field : getModel().getFields()) {
                    String castName = TypeCaster.detectType(field);
                    if(getModel().getType() == ModelType.ENUM) {
                        if(field.getName().equals("$VALUES")) {
                            continue;
                        }
                    }
                    getWriter().write("  " + field.getName() + ": " + (castName == null ? field.getType().getSimpleName() : castName) + ",\n");
                }
            }
            getWriter().write("}\n");

            getWriter().flush();
            getWriter().close();
            return true;
        } catch (IOException exception) {
            exception.printStackTrace();
            return false;
        }
        //System.out.println("// package: " + getModel().getClassPackage().getName());
        //System.out.println(getModel().getClassName());
        //getModel().getFields().forEach(field -> System.out.println("field " + field.getName() + ": " + field.getType().getSimpleName()));
        //getModel().getMethods().forEach(method -> System.out.println("function" + method.getName() + ": " + method.getReturnType().getSimpleName()));
    }
}
