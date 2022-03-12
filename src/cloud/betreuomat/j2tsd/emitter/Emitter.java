package cloud.betreuomat.j2tsd.emitter;

import cloud.betreuomat.j2tsd.models.Model;

import java.io.File;
import java.util.List;

public abstract class Emitter {

    private final File outDir;
    private final List<Model> models;

    public Emitter(File outDir, List<Model> models) {
        this.outDir = outDir;
        this.models = models;
    }

    public abstract boolean emit();
    private boolean emitAsClass() {return true;}
    private boolean emitAsInterface() {return true;}
    private boolean emitAsEnum() {return true;}

    public final File getOutDir() {
        return outDir;
    }

    public final List<Model> getModels() {
        return models;
    }


}
