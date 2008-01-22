/*
 * ExampleSnooping.java
 *
 * Copyright (c) 2008 Operational Dynamics Consulting Pty Ltd
 *
 * The code in this file, and the library it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" See the LICENCE file for the terms governing usage and
 * redistribution.
 */
package keyboard;

import org.gnome.gdk.Event;
import org.gnome.gdk.EventKey;
import org.gnome.gdk.Keyval;
import org.gnome.gdk.ModifierType;
import org.gnome.glib.Glib;
import org.gnome.gtk.Gtk;
import org.gnome.gtk.Label;
import org.gnome.gtk.Widget;
import org.gnome.gtk.Window;

/**
 * Observing the flow of key strokes.
 * 
 * @author Andrew Cowie
 */
public class ExampleSnooping
{
    public ExampleSnooping() {
        final Window w;
        final Label l;

        w = new Window();
        l = new Label("<b>Start Typing!</b>\n" + "Start typing and details about\n"
                + "your KeyEvents will\n" + "appear on the console.");
        l.setUseMarkup(true);

        w.add(l);

        w.connect(new Widget.KEY_PRESS_EVENT() {
            public boolean onKeyPressEvent(Widget source, EventKey event) {
                final Keyval key;
                final ModifierType mod;

                key = event.getKeyval();
                mod = event.getState();

                System.out.print("Pressed: " + key.toString() + ", ");
                System.out.print("Modifier: " + mod.toString() + " ");

                if (mod == ModifierType.SHIFT_MASK) {
                    System.out.print("That's Shifty!");
                }
                if (mod.contains(ModifierType.ALT_MASK)) {
                    System.out.print("Hooray for Alt!");
                }
                if (mod.contains(ModifierType.SUPER_MASK)) {
                    System.out.print("You're Super!");
                }

                System.out.println();
                return false;
            }
        });

        w.setTitle("Key snooping");
        w.setBorderWidth(30);
        w.showAll();

        w.connect(new Window.DELETE_EVENT() {
            public boolean onDeleteEvent(Widget source, Event event) {
                Gtk.mainQuit();
                return false;
            }
        });
    }

    public static void main(String[] args) {
        Gtk.init(args);
        Glib.setProgramName("snooping");

        new ExampleSnooping();

        Gtk.main();
    }
}