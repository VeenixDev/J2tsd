package cloud.betreuomat.j2tsd.models;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class Model {

    private final ModelType type;
    private final List<Field> fields = new ArrayList<>();
    private final List<Method> methods = new ArrayList<>();
    private final List<String> references;
    private final String className;
    private final Package classPackage;

    public Model(ModelType type, String className, Package classPackage, List<String> references) {
        this.type = type;
        this.className = className;
        this.classPackage = classPackage;
        this.references = references;
    }

    public void addField(Field f) {
        fields.add(f);
    }

    public void addMethod(Method m) {
        methods.add(m);
    }

    public ModelType getType() {
        return type;
    }

    public List<Field> getFields() {
        return fields;
    }

    public List<Method> getMethods() {
        return methods;
    }

    public String getClassName() {
        return className;
    }

    public Package getClassPackage() {
        return classPackage;
    }

    public List<String> getReferences() {
        return references;
    }
}
