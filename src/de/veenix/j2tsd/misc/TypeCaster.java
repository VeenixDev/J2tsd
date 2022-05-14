package de.veenix.j2tsd.misc;

public class TypeCaster {

    public static DetectionResult detectType(String name, String genericType) {
        return switch(name) {
            case "List" -> new DetectionResult(true, getGenericType(genericType), "Array<" + getGenericType(genericType) + ">");
            case "Map" -> new DetectionResult(true, getGenericType(genericType), "Map<" + getGenericType(genericType) + ">");
            case "Set" -> new DetectionResult(true, getGenericType(genericType), "Set<" + getGenericType(genericType) + ">");
            case "Number", "Integer", "int", "Float", "float", "Double", "double", "Short", "short", "Byte", "byte", "Long", "long" -> new DetectionResult(false, null, "number");
            case "String", "Character", "char" -> new DetectionResult(false, null, "string");
            case "Boolean", "boolean" -> new DetectionResult(false, null, "boolean");
            default -> new DetectionResult(false, null, null);
        };
    }

    private static String getGenericType(String type) {
        if(type == null) {
            return "null";
        }

        if(!(type.contains("<") && type.contains(">"))) {
            return "";
        }
        String genericTypes = type.split("<")[1].split(">")[0];
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
                    String t = detectType(clazz.getSimpleName(), null).getType();
                    sb.append(t == null ? clazz.getSimpleName() : t);
                    continue;
                } catch (ClassNotFoundException ignored) {}

                sb.append(split[split.length - 1]);
            }
        }

        return sb.toString();
    }

}
