package cloud.betreuomat.j2tsd;

import cloud.betreuomat.j2tsd.emitter.Emitter;
import cloud.betreuomat.j2tsd.factories.EmitterFactory;
import cloud.betreuomat.j2tsd.factories.EmitterTypes;
import cloud.betreuomat.j2tsd.scanner.JarScanner;

import java.io.File;

public class Main2 {

    public static void main(String[] args) throws ClassNotFoundException {
        File outDir = new File("D:/random/tsd/");
        File jarFile = new File("D:/Workspace/Betreuomat/Temp/out/artifacts/test/test.jar");

        JarScanner scanner = new JarScanner(jarFile);
        Emitter emitter = EmitterFactory.getEmitter(outDir, scanner.scan(), EmitterTypes.AUTODETECT);

        emitter.emit();
    }

}
