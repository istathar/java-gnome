/*
 * LabelDemo.java
 *
 * Copyright (c) 2007 Operational Dynamics Consulting Pty Ltd
 *
 * The code in this file, and the library it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" plus the "Classpath Exception" (you may link to this code as a
 * library into other programs provided you don't make a derivation of it).
 * See the LICENCE file for the terms governing usage and redistribution.
 */
package org.gnome.gtkdemo;

import org.gnome.gtk.Widget;
import org.gnome.gtk.VBox;
import org.gnome.gtk.Label;


/**
 * Demonstration of the label capabilities
 */
public class LabelDemo implements DemoPanel {
    
    private static final String MARKUP_TEXT = "<markup><span foreground=\"blue\" size=\"large\">Blue Label</span> with <b>markup</b></markup>";
    private static final String STRIPPED_TEXT = "Blue Label with markup";

    public Widget getWidget() {
        VBox v = new VBox(false, 20);
        v.packStart(new Label("Normal label"));
        
        Label markupLabel = new Label("");
        markupLabel.setLabel(MARKUP_TEXT);
        markupLabel.setUseMarkup(true);
        v.packStart(markupLabel);
        
        if (! markupLabel.getLabel().equals(MARKUP_TEXT))
            throw new RuntimeException("Label#getLabel() returns wrong result");

        if (! markupLabel.getText().equals(STRIPPED_TEXT))
            throw new RuntimeException("Label#getText() returns wrong result");


        Label vertLabel = new Label("Vertical label");
        vertLabel.setAngle(90);
        if (vertLabel.getAngle() != 90)
            throw new RuntimeException("Label#getAngle() returns wrong result");
        v.packStart(vertLabel);
                
        return v;
    }
}