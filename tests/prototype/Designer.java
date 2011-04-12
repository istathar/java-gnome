/*
 * java-gnome, a UI library for writing GTK and GNOME programs from Java!
 *
 * Copyright © 2007-2011 Operational Dynamics Consulting, Pty Ltd
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

import java.io.FileNotFoundException;
import java.text.ParseException;

import org.gnome.gdk.Event;
import org.gnome.gdk.EventKey;
import org.gnome.gtk.Builder;
import org.gnome.gtk.Gtk;
import org.gnome.gtk.Label;
import org.gnome.gtk.Widget;
import org.gnome.gtk.Window;

/**
 * A skeleton program to test using the <code>.ui</code> XML output from a
 * GNOME user interface designer translated into live Widgets with
 * <code>GtkBuilder</code>.
 * 
 * @author Andrew Cowie
 */
public final class Designer
{
    private static void setupUserInterface() throws FileNotFoundException, ParseException {
        final Builder builder;
        final Window w;
        final Label l;

        builder = new Builder();
        builder.addFromFile("tests/prototype/simple.ui");

        l = (Label) builder.getObject("label1");
        System.out.println("Extracted label text: \n\"" + l.getText() + "\"");

        w = (Window) builder.getObject("simple");

        w.connect(new Window.DeleteEvent() {
            public boolean onDeleteEvent(Widget source, Event event) {
                Gtk.mainQuit();
                return false;
            }
        });

        l.connect(new Widget.KeyPressEvent() {
            public boolean onKeyPressEvent(Widget source, EventKey event) {
                System.out.println("Pressed:  " + event.getKeyval());
                return false;
            }
        });

        l.connect(new Widget.KeyReleaseEvent() {
            public boolean onKeyReleaseEvent(Widget source, EventKey event) {
                System.out.println("Released: " + event.getKeyval());
                return false;
            }
        });

        l.selectRegion(0, 0);
    }

    public static void main(String[] args) throws FileNotFoundException, ParseException {
        Gtk.init(args);

        setupUserInterface();

        Gtk.main();

        System.gc();
        System.out.println("Bye now.");
    }
}
