package de.veenix.j2tsd.misc;

public class DetectionResult {

    private final boolean isGeneric;
    private final String genericType;
    private final String type;

    public DetectionResult(boolean isGeneric, String genericType, String type) {
        this.isGeneric = isGeneric;
        this.genericType = genericType;
        this.type = type;
    }
    public String getGenericType() {
        return genericType;
    }

    public String getType() {
        return type;
    }

    public boolean isGeneric() {
        return isGeneric;
    }
}
