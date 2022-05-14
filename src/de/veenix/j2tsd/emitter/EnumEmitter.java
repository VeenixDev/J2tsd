package de.veenix.j2tsd.emitter;

import de.veenix.j2tsd.models.Model;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class EnumEmitter extends Emitter {
    public EnumEmitter(File outDir, List<Model> models) {
        super(outDir, models);
    }

    @Override
    public boolean emit() {
        AtomicBoolean success = new AtomicBoolean(true);
        getModels().forEach(m -> {
            try {
                switch (m.getType()) {
                    case ENUM, CLASS, INTERFACE -> emitAsEnum(m);
                }
            } catch (IOException exception) {
                exception.printStackTrace();
                success.set(false);
            }
        });
        return success.get();
    }
}