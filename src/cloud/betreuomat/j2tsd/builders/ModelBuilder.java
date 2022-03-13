package cloud.betreuomat.j2tsd.builders;

import cloud.betreuomat.j2tsd.misc.TypeCaster;
import cloud.betreuomat.j2tsd.models.Model;
import cloud.betreuomat.j2tsd.models.ModelType;

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
            String type = TypeCaster.detectType(field);

            if(type == null) {
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
            }
            model.addField(field);
        }

        Arrays.stream(methods).forEach(model::addMethod);

        return model;
    }

    public String[] getIncludePackages() {
        return includePackages;
    }

}
