package cloud.betreuomat.j2tsd;

import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class Main {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        String jarPath = "D:/Workspace/Betreuomat/Temp/out/artifacts/test/test.jar";
        JarFile jarFile = new JarFile(jarPath);
        Enumeration<JarEntry> entries = jarFile.entries();

        URL[] urls = { new URL("jar:file:" + jarPath + "!/") };
        URLClassLoader classLoader = URLClassLoader.newInstance(urls);

        int entryCounter = 0;

        while(entries.hasMoreElements()) {
            entryCounter++;
            JarEntry entry = entries.nextElement();
            if(entry.isDirectory() || !entry.getName().endsWith(".class")) {
                continue;
            }

            String className = entry.getName().substring(0, entry.getName().length() - 6);
            className = className.replace('/', '.');
            Class<?> clazz = classLoader.loadClass(className);

            System.out.println("// from package " + clazz.getPackageName());
            System.out.println("declare " + (clazz.isEnum() ? "enum" : "interface") + " " + clazz.getSimpleName() + " {");
            for(Field field : clazz.getDeclaredFields()) {
                System.out.println("  " + field.getName() + ": " + castType(field.getType().getSimpleName()));
            }
            System.out.println("}");
        }

        System.out.println("\nFinished!\nEntry count: " + entryCounter);
    }

    private static String castType(String type) {
        return switch (type) {
            case "String" -> "string";
            case "Integer", "Float", "Short", "Double", "byte" -> "number";
            default -> type;
        };
    }

}
