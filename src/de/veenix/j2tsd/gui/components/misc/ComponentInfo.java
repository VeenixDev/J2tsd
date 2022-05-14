package de.veenix.j2tsd.gui.components.misc;

import java.awt.*;

public record ComponentInfo(Color backgroundColor, int minHeight, int minWidth, int maxHeight, int maxWidth) {

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
