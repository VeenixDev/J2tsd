package cloud.betreuomat.j2tsd.builders;

import cloud.betreuomat.j2tsd.models.Model;
import cloud.betreuomat.j2tsd.models.ModelType;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;

public class ModelBuilder {

    private final ModelType type;
    private final Field[] fields;
    private final Method[] methods;
    private final String className;
    private final Package classPackage;

    public ModelBuilder(Class<?> clazz) {
        this.type = ModelType.of(clazz);
        this.className = clazz.getSimpleName();
        this.classPackage = clazz.getPackage();
        this.fields = clazz.getDeclaredFields();
        this.methods = clazz.getDeclaredMethods();
    }

    public Model build() {
        Model model = new Model(type, className, classPackage);

        Arrays.stream(fields).forEach(model::addField);
        Arrays.stream(methods).forEach(model::addMethod);

        return model;
    }

}
