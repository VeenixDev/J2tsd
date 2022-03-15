package cloud.betreuomat.j2tsd.misc;

public record DetectionResult(boolean isGeneric, String genericType, String type) {

    public String getGenericType() {
        return genericType;
    }

    public String getType() {
        return type;
    }
}
