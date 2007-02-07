/*
 * Designer.java
 *
 * Copyright (c) 2007 Operational Dynamics Consulting Pty Ltd
 * 
 * The code in this file, and the library it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" See the LICENCE file for the terms governing usage and
 * redistribution.
 */

import java.io.FileNotFoundException;

import org.gnome.glade.Glade;
import org.gnome.glade.Xml;
import org.gnome.gtk.Gtk;
import org.gnome.gtk.Label;
import org.gnome.gtk.Object;
import org.gnome.gtk.Widget;
import org.gnome.gtk.Window;

/**
 * A skeleton program to test using the <code>.glade</code> XML output from
 * a GNOME user interface designer translated into live Widgets with
 * <code>libglade</code>.
 * 
 * @author Andrew Cowie
 */
public final class Designer
{
    private Designer() throws FileNotFoundException {
        final Xml glade;
        final Window w;
        final Label l;

        glade = Glade.parse("tests/prototype/simple.glade", "simple");

        l = (Label) glade.getWidget("label1");
        System.out.println("Extracted label text: \n\"" + l.getText() + "\"");

        w = (Window) glade.getWidget("simple");

        w.connect(new Window.DELETE() {
            public boolean onDeleteEvent(Widget source, Object event) {
                Gtk.mainQuit();
                return false;
            }
        });
    }

    public static void main(String[] args) throws FileNotFoundException {
        Gtk.init(args);

        new Designer();

        Gtk.main();

        System.gc();
        System.out.println("Bye now.");
    }
}
