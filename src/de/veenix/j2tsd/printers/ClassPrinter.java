package de.veenix.j2tsd.printers;

import de.veenix.j2tsd.misc.TypeCaster;
import de.veenix.j2tsd.models.Model;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

public class ClassPrinter extends Printer {

    public ClassPrinter(Model model, File outDir) throws IOException {
        super(model, outDir);
    }

    @Override
    public boolean printModel() {
        try {
            write("declare class " + getModel().getClassName() + " {\n");

            if (getModel().getFields().size() > 0) {
                for (Field field : getModel().getFields()) {
                    String castName = TypeCaster.detectType(field.getType().getSimpleName(), field.getGenericType().getTypeName()).getType();

                    write("  " + field.getName() + ": " + (castName == null ?
                            field.getType().getSimpleName() : castName) + ";\n");
                }
            }

            if (getModel().getFields().size() > 0 && getModel().getMethods().size() > 0) {
                write("\n");
            }

            if (getModel().getMethods().size() > 0) {
                for (Method method : getModel().getMethods()) {
                    String returnString = TypeCaster.detectType(method.getReturnType().getSimpleName(), method.getGenericReturnType().getTypeName()).getType();
                    write("  " + method.getName() + ": (" + buildParameterString(method.getParameters())
                            + ") => " + (returnString == null ? method.getReturnType().getSimpleName() : returnString) + ";\n");
                }
            }

            write("}");
            return true;
        } catch (IOException exception) {
            exception.printStackTrace();
            return false;
        }
    }

    private String buildParameterString(Parameter[] parameters) {
        if(parameters.length <= 0) {
            return "";
        }

        StringBuilder builder = new StringBuilder();

        for(Parameter parameter : parameters) {
            String type = TypeCaster.detectType(parameter.getType().getSimpleName(), null).getType();
            builder.append(parameter.getName()).append(": ").append(type == null ?
                    parameter.getType().getSimpleName() : type).append(", ");
        }

        builder.delete(builder.length() - 2, builder.length());
        return builder.toString();
    }
}