package cloud.betreuomat.j2tsd.emitter;

import cloud.betreuomat.j2tsd.models.Model;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class InterfaceEmitter extends Emitter {

    public InterfaceEmitter(File outDir, List<Model> models) {
        super(outDir, models);
    }

    @Override
    public boolean emit() {
        AtomicBoolean success = new AtomicBoolean(true);
        getModels().forEach(m -> {
            try {
                switch (m.getType()) {
                    case INTERFACE, ENUM, CLASS -> emitAsInterface(m);
                }
            } catch (IOException exception) {
                exception.printStackTrace();
                success.set(false);
            }
        });
        return success.get();
    }
}
