package cloud.betreuomat.j2tsd.emitter;

import cloud.betreuomat.j2tsd.models.Model;

import java.io.File;
import java.util.List;

public class ClassAsInterfaceEmitter extends Emitter {
    public ClassAsInterfaceEmitter(File outDir, List<Model> models) {
        super(outDir, models);
    }

    @Override
    public boolean emit() {
        return false;
    }
}
