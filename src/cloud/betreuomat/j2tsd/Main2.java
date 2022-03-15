package cloud.betreuomat.j2tsd;

import cloud.betreuomat.j2tsd.emitter.Emitter;
import cloud.betreuomat.j2tsd.factories.EmitterFactory;
import cloud.betreuomat.j2tsd.factories.EmitterTypes;
import cloud.betreuomat.j2tsd.scanner.JarScanner;

import java.io.File;
import java.util.Date;

public class Main2 {

    public static void main(String[] args) {
        /*
        #############################
            CONFIGURATION SECTION
        #############################
         */
        File outDir = new File("D:/random/types/");
        File jarFile = new File("D:/random/tsd/backend.jar");
        String[] include = new String[] { "cloud.betreuomat.backend.persistence.entity" };
        EmitterTypes emitterType = EmitterTypes.CLASS_AS_INTERFACE;


        /*
        ##########################
            SOURCE CODE BEGINS
        ##########################
         */
        long started = new Date().getTime();
        JarScanner scanner = new JarScanner(jarFile, include);
        Emitter emitter = EmitterFactory.getEmitter(outDir, scanner.scan(), emitterType);

        if(emitter.emit()) {
            System.out.print("Succeeded");
        } else {
            System.out.println("Failed");
        }

        System.out.println(" in " + ((float) (new Date().getTime() - started) / 1000) + "s");
    }

}
