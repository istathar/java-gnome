/*
 * SeparatorDemo.java
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
import org.gnome.gtk.HBox;
import org.gnome.gtk.Label;
import org.gnome.gtk.Frame;
import org.gnome.gtk.HSeparator;
import org.gnome.gtk.VSeparator;
import org.gnome.gtk.Label;

/**
 * Demonstration of some Separator widgets
 */
public class SeparatorDemo implements DemoPanel {
        
    
    public Widget getWidget() {
        VBox v = new VBox(false, 50);

        v.packStart(new Label("Hello"));
        v.packStart(new Label("Test 1"));
        v.packStart(new HSeparator());

        HBox h = new HBox(false, 50);
        h.packStart(new Label("Test 2"));
        h.packStart(new VSeparator());
        h.packStart(new Label("Test 3"));
        v.packStart(h);
        
        Label markupLabel = new Label("<markup><span foreground=\"blue\" size=\"large\"><b>Demo</b></span></markup>");
        markupLabel.setUseMarkup(true);
        Frame frame = new Frame("not shown text");
        frame.setLabel(markupLabel);

        frame.add(v);

        return frame;
    }
}