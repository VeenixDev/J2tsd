# Note

Here is all information that won't fit anywhere else

## Raw execution

```java
public class Main {

    public static void main(String[] args) {
        /*
        #############################
            CONFIGURATION SECTION
        #############################
         */
        File outDir = new File("outDir");
        File jarFile = new File("inFile.jar");
        String[] include = new String[] { "package.to.include" };
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
```