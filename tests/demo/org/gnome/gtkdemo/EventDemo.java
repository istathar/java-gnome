/*
 * EventDemo.java
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
 * Demonstration of the event capabilities
 */
public class EventDemo implements DemoPanel {
    
    Entry eventText;

    public Widget getWidget() {
        eventText = new Entry();
        eventText.setText("focus and press a key ..");
        eventText.setEditable(false);
        eventText.connect(new Widget.KEY_PRESS_EVENT() {
                public boolean onKeyPressEvent(Widget source, EventKey event) {
                    System.out.println("getKeyVal(): "+ event.getKeyVal());
                    System.out.println("event.getType(): "+ event.getType());
                    eventText.setText("keyval="+event.getKeyVal()+" event-type="+event.getType());
                    return false;
                }
            });

        VBox v = new VBox(false, 20);
        v.packStart(eventText);
        return v;
    }
}