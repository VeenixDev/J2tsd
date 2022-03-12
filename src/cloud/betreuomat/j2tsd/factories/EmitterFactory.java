package cloud.betreuomat.j2tsd.factories;

import cloud.betreuomat.j2tsd.models.Model;
import cloud.betreuomat.j2tsd.emitter.*;

import java.io.File;
import java.util.List;

public class EmitterFactory {

    public static Emitter getEmitter(File outDirectory, List<Model> models, EmitterTypes type) {
        return switch (type) {
            case AUTODETECT -> new AutoEmitter(outDirectory, models);
            case INTERFACE -> new InterfaceEmitter(outDirectory, models);
            case CLASS -> new ClassEmitter(outDirectory, models);
            case ENUM -> new EnumEmitter(outDirectory, models);
            case CLASS_AS_INTERFACE -> new ClassAsInterfaceEmitter(outDirectory, models);
        };
    }
}
