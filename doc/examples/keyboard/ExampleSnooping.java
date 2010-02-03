/*
 * java-gnome, a UI library for writing GTK and GNOME programs from Java!
 *
 * Copyright © 2008-2010 Operational Dynamics Consulting, Pty Ltd
 *
 * The code in this file, and the program it is a part of, is made available
 * to you by its authors as open source software: you can redistribute it
 * and/or modify it under the terms of the GNU General Public License version
 * 2 ("GPL") as published by the Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GPL for more details.
 *
 * You should have received a copy of the GPL along with this program. If not,
 * see http://www.gnu.org/licenses/. The authors of this program may be
 * contacted through http://java-gnome.sourceforge.net/.
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
 * Observing the flow of key strokes. This is essentially the code used to
 * generate the data about sequencing described in {@link ModifierType}.
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

        w.connect(new Widget.KeyPressEvent() {
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

        w.connect(new Window.DeleteEvent() {
            public boolean onDeleteEvent(Widget source, Event event) {
                Gtk.mainQuit();
                return false;
            }
        });
    }

    public static void main(String[] args) {
        Glib.setProgramName("snooping");
        Gtk.init(args);

        new ExampleSnooping();

        Gtk.main();
    }
}
