package de.veenix.j2tsd.gui.components.misc;

import java.awt.*;

public class ComponentInfo {

    private final Color backgroundColor;
    private final int minHeight;
    private final int minWidth;
    private final int maxHeight;
    private final int maxWidth;

    public ComponentInfo(Color backgroundColor, int minHeight, int minWidth, int maxHeight, int maxWidth) {
        this.backgroundColor = backgroundColor;
        this.minHeight = minHeight;
        this.minWidth = minWidth;
        this.maxHeight = maxHeight;
        this.maxWidth = maxWidth;
    }

    public int getIntValue(String name) {
        return switch (name) {
            case "minHeight" -> minHeight;
            case "minWidth" -> minWidth;
            case "maxHeight" -> maxHeight;
            case "maxWidth" -> maxWidth;
            default -> throw new IllegalStateException("Unexpected value: " + name);
        };
    }

    public Color getColorValue(String name) {
        return switch (name) {
            case "backgroundColor" -> backgroundColor;
            default -> throw new IllegalStateException("Unexpected value: " + name);
        };
    }

}
