/*
 * EntryDemo.java
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
import org.gnome.gtk.Entry;

import org.gnome.gdk.EventKey;
import org.gnome.gdk.EventType;

/**
 * Demonstration of the Entry widget
 */
public class EntryDemo implements DemoPanel {
        
    
    public Widget getWidget() {
        VBox v = new VBox(false, 50);

        Entry readonly = new Entry("not editable entry");
        readonly.setEditable(false);
        v.packStart(readonly);

        Entry invisible = new Entry("not visible entry");
        invisible.setVisibleChars(false);
        if (invisible.isVisibleChars())
            throw new RuntimeException("invisible.isVisibleChars() returns wrong result");
        v.packStart(invisible);
        
        Entry normal20 = new Entry("max 20 chars");
        normal20.setMaxLength(20);
        if (normal20.getMaxLength() != 20)
            throw new RuntimeException("Entry#getMaxLength() returns wrong result");
        v.packStart(normal20);
        
        final Entry activation = new Entry("press <enter>");
        activation.connect(new Entry.ACTIVATE() {
                public void onActivate(Entry sourceObject) {
                    activation.setText("you hit me");
                }
            });
        v.packStart(activation);

        return v;
    }
}