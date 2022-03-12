package cloud.betreuomat.j2tsd.emitter;

import cloud.betreuomat.j2tsd.models.Model;

import java.io.File;
import java.util.List;

public class AutoEmitter extends Emitter {

    public AutoEmitter(File outDir, List<Model> models) {
        super(outDir, models);
    }

    @Override
    public boolean emit() {
        getModels().forEach(m -> {
            System.out.println("// package: " + m.getClassPackage().getName());
            System.out.println(m.getClassName());
            m.getFields().forEach(field -> System.out.println("field " + field.getName() + ": " + field.getType().getSimpleName()));
            m.getMethods().forEach(method -> System.out.println("function" + method.getName() + ": " + method.getReturnType().getSimpleName()));
        });
        return true;
    }
}
