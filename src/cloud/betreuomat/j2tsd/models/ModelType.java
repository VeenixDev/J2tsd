package cloud.betreuomat.j2tsd.models;

public enum ModelType {
    CLASS,
    INTERFACE,
    ENUM;

    public static ModelType of(Class<?> clazz) {
        if(clazz.isEnum()) {
            return ENUM;
        } else if(clazz.isInterface()) {
            return INTERFACE;
        } else return CLASS;
    }
}
