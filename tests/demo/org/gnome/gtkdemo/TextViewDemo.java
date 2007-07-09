/*
 * TextViewDemo.java
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
import org.gnome.gtk.TextView;
import org.gnome.gtk.PolicyType;
import org.gnome.gtk.ScrolledWindow;

/**
 * Demonstration of the TextView widget
 */
public class TextViewDemo implements DemoPanel {
            
    public Widget getWidget() {

        TextView textView = new TextView();
        textView.getBuffer().setText("This sould be "
                                         +"\na long text,"
                                         +"\nbut it is boring to write so much words");
        // just for testing
        textView.setEditable(false);
        if (textView.isEditable())
            throw new RuntimeException("TextView#setEditable() or TextView#isEditable() do not work correctly");        
        textView.setEditable(true);

        ScrolledWindow scrolledWindow = new ScrolledWindow();
        scrolledWindow.setPolicy(PolicyType.AUTOMATIC, PolicyType.AUTOMATIC); 
        scrolledWindow.addWithViewport(textView);
        return scrolledWindow;
    }
}