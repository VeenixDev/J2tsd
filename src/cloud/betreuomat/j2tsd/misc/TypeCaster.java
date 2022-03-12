package cloud.betreuomat.j2tsd.misc;

import java.lang.reflect.Field;

public class TypeCaster {

    public static String detectType(Field field) {
        return switch(field.getType().getSimpleName()) {
            case "Number", "Integer", "int", "Float", "float", "Double", "double", "Short", "short", "Byte", "byte", "Long", "long" -> "number";
            case "String", "Character", "char" -> "string";
            case "Boolean", "boolean" -> "boolean";
            case "List" -> "Array<" + getGenericType(field.getGenericType().getTypeName()) + ">";
            default -> null;
        };
    }

    private static String getGenericType(String typeName) {
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
                sb.append(split[split.length - 1].toLowerCase());
            }
        }

        return sb.toString();
    }

}
