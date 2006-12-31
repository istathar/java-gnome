/*
 * Experiment.java
 *
 * Copyright (c) 2006 Operational Dynamics Consulting Pty Ltd
 * 
 * The code in this file, and the library it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" See the LICENCE file for the terms governing usage and
 * redistribution.
 */

import org.gnome.gtk.Button;
import org.gnome.gtk.Gtk;
import org.gnome.gtk.Object;
import org.gnome.gtk.Widget;
import org.gnome.gtk.Window;

/**
 * A simple demonstration program to experiment with, and validate, the
 * end-developer design of the new java-gnome 4.0 bindings architecture.
 * 
 * @author Andrew Cowie
 */
public final class Experiment
{

    public static void main(String[] args) {
        final Window w;
        final Button b;

        Gtk.init(args);

        w = new Window();

        b = new Button("Change Me?");
        b.setLabel("Press Me!");

        w.add(b);

        w.setTitle("Exp");
        w.showAll();

        b.connect(new Button.CLICKED() {
            public void onClicked(Button source) {
                System.out.println("I was clicked... " + b.getLabel());
            }
        });

        w.connect(new Window.DELETE() {
            public boolean onDeleteEvent(Widget source, Object event) {
                System.out.println("I was deleted!");
                Gtk.mainQuit();
                return false;
            }
        });

        Gtk.main();

        // verify we make it out of the main loop without crashing
        System.out.println("Bye now.");
    }
}
