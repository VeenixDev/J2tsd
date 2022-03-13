package cloud.betreuomat.j2tsd.misc;

import java.lang.reflect.Field;
import java.lang.reflect.Type;

public class TypeCaster {

    public static String detectType(Field field) {
        return switch(field.getType().getSimpleName()) {
            case "List" -> "Array<" + getGenericType(field.getGenericType()) + ">";
            case "Map" -> "Map<" + getGenericType(field.getGenericType()) + ">";
            default -> detectType(field.getType());
        };
    }

    private static String detectType(Class<?> clazz) {
        return switch (clazz.getSimpleName()) {
            case "Number", "Integer", "int", "Float", "float", "Double", "double", "Short", "short", "Byte", "byte", "Long", "long" -> "number";
            case "String", "Character", "char" -> "string";
            case "Boolean", "boolean" -> "boolean";
            default -> null;
        };
    }

    private static String getGenericType(Type type) {
        String typeName = type.getTypeName();

        if(!(typeName.contains("<") && typeName.contains(">"))) {
            return "";
        }
        String genericTypes = typeName.split("<")[1].split(">")[0];
        String[] types = genericTypes.split(",");

        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < types.length; i++) {
            if(i != 0) {
                sb.append(", ");
            }
            String[] split = types[i].split("\\.");
            int index = split.length -1;

            if (index > 0) {
                try {
                    Class<?> clazz = Class.forName(types[i]);
                    String t = detectType(clazz);
                    sb.append(t == null ? clazz.getSimpleName() : t);
                    continue;
                } catch (ClassNotFoundException ignored) {}

                sb.append(split[split.length - 1]);
            }
        }

        return sb.toString();
    }

}
