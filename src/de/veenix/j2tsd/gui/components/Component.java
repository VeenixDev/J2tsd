package de.veenix.j2tsd.gui.components;

import de.veenix.j2tsd.gui.components.misc.ComponentInfo;

import javax.swing.*;
import java.util.ArrayList;

public abstract class Component extends JComponent {

    private final ArrayList<JComponent> components = new ArrayList<>();
    private ComponentInfo info;

    public Component() {
        onCreate();
        showComponents();
    }

    public Component(ComponentInfo info) {
        this.setBackground(info.getColorValue("backgroundColor"));
        this.info = info;

        onCreate();
        showComponents();
    }

    public void showComponents() {
        for(JComponent component : components) {
            this.add(component);
        }
    }

    public boolean addComponent(JComponent component) {
        if(info != null) {

            if(info.getIntValue("minHeight") != -1 && component.getHeight() - info.getIntValue("minHeight") > 0) {
                component.setBounds(component.getX(), component.getY(), component.getWidth(), info.getIntValue("minHeight"));
            }
            if(info.getIntValue("minWidth") != -1 && component.getWidth() - info.getIntValue("minWidth") > 0) {
                component.setBounds(component.getX(), component.getY(), info.getIntValue("minWidth"), component.getHeight());
            }
            if(info.getIntValue("maxHeight") != -1 && component.getHeight() - info.getIntValue("maxHeight") < 0) {
                component.setBounds(component.getX(), component.getY(), component.getWidth(), info.getIntValue("maxHeight"));
            }
            if(info.getIntValue("maxWidth") != -1 && component.getWidth() - info.getIntValue("maxWidth") < 0) {
                component.setBounds(component.getX(), component.getY(), info.getIntValue("maxWidth"), component.getHeight());
            }
        }

        System.out.println("Added component with bounds: " + component.getBounds());
        return this.components.add(component);
    }

    abstract void onCreate();
}
