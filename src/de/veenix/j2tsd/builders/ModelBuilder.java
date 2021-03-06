package de.veenix.j2tsd.builders;

import de.veenix.j2tsd.misc.DetectionResult;
import de.veenix.j2tsd.misc.TypeCaster;
import de.veenix.j2tsd.models.Model;
import de.veenix.j2tsd.models.ModelType;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SuppressWarnings("ALL")
public class ModelBuilder {

    private final ModelType type;
    private final Field[] fields;
    private final Method[] methods;
    private final String className;
    private final Package classPackage;
    private final List<String> references = new ArrayList<>();

    private String[] includePackages = new String[0];

    public ModelBuilder(Class<?> clazz) {
        this.type = ModelType.of(clazz);
        this.className = clazz.getSimpleName();
        this.classPackage = clazz.getPackage();
        this.fields = clazz.getDeclaredFields();
        this.methods = clazz.getDeclaredMethods();
    }

    public ModelBuilder includes(String[] includes) {
        includePackages = includes;
        return this;
    }

    public Model build() {
        Model model = new Model(type, className, classPackage, references);

        for(Field field : fields) {
            DetectionResult type = TypeCaster.detectType(field.getType().getSimpleName(), field.getGenericType().getTypeName());

            includeType(type, field);
            model.addField(field);
        }

        Arrays.stream(methods).forEach(model::addMethod);

        return model;
    }

    private void includeType(DetectionResult type, Field field) {
        if(type.getType() == null) {
            boolean included = false;

            for(String s : includePackages) {
                if(field.getType().getName().contains(s)) {
                    included = true;
                    break;
                }
            }

            if(included && !field.getType().getSimpleName().equals(className)) {
                references.add(field.getType().getSimpleName());
            }
        } else if(type.isGeneric()) {
            boolean included = false;

            for (String s : includePackages) {
                if (field.getGenericType().getTypeName().contains(s)) {
                    included = true;
                    break;
                }
            }

            System.out.print("\n");

            if(included && !type.getGenericType().equals(className)) {
                DetectionResult temp = TypeCaster.detectType(type.getGenericType(), null);

                if (temp.getType() == null) {
                    references.add(type.getGenericType());
                }
            }
        }
    }

    public String[] getIncludePackages() {
        return includePackages;
    }

}
