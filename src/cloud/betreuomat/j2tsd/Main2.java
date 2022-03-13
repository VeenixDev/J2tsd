package cloud.betreuomat.j2tsd;

import cloud.betreuomat.j2tsd.emitter.Emitter;
import cloud.betreuomat.j2tsd.factories.EmitterFactory;
import cloud.betreuomat.j2tsd.factories.EmitterTypes;
import cloud.betreuomat.j2tsd.scanner.JarScanner;

import java.io.File;

public class Main2 {

    public static void main(String[] args) {
        File outDir = new File("D:/random/tsd/");
        File jarFile = new File("C:/Users/Paul/Desktop/Client.jar");
        //File jarFile = new File("D:/Workspace/Betreuomat/Temp/out/artifacts/test/test.jar");
        String[] include = new String[] {/*"test"*/"de.veenixdev", "com.google"};

        JarScanner scanner = new JarScanner(jarFile, include);
        Emitter emitter = EmitterFactory.getEmitter(outDir, scanner.scan(), EmitterTypes.AUTODETECT);

        if(emitter.emit()) {
            System.out.println("Success!");
        } else {
            System.out.println("Failure");
        }
    }

}
