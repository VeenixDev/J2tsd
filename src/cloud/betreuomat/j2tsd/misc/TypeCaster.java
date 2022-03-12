package cloud.betreuomat.j2tsd.misc;

public class TypeCaster {

    public static String detectType(Class<?> clazz) {
        return switch(clazz.getSimpleName()) {
            case "Number", "Integer", "Float", "Double", "Short", "Byte", "Long" -> "number";
            case "String", "Character" -> "string";
            default -> null;
        };
    }

}
