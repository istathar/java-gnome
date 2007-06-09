/*
 * ValidateThreadStability.java
 *
 * Copyright (c) 2007 Operational Dynamics Consulting Pty Ltd
 * Copyright (c) 2007 Vreixo Formoso
 * 
 * The code in this file, and the library it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" See the LICENCE file for the terms governing usage and
 * redistribution.
 */
package org.gnome.gtk;

import org.gnome.gdk.Event;
import org.gnome.gdk.EventExpose;

/**
 * Verify concurrent behaviour is correct. We briefly had a tentative
 * architecture that evaluated what would happen of the GDK lock was released
 * while signal handlers were running. This simple example that shows the
 * problem cited by Owen Taylor as the reason that we can't do this. TODO this
 * class needs to be refactored as a unit test.
 * 
 * @author Vreixo Formoso
 */
public final class ValidateThreadStability
{
    VBox x1;

    VBox x2;

    BadThread thread;

    private ValidateThreadStability() {
        final Window w;

        w = new Window();

        x1 = new VBox(false, 3);

        w.add(x1);

        x2 = new VBox(false, 0);
        x1.add(x2);

        w.setTitle("Exp");
        w.showAll();

        w.connect(new Window.DELETE_EVENT() {
            public boolean onDeleteEvent(Widget source, Event event) {
                System.out.println("I was deleted!");
                Gtk.mainQuit();
                return false;
            }
        });

        x2.connect(new Widget.EXPOSE_EVENT() {
            public boolean onExposeEvent(Widget source, EventExpose event) {
                System.out.println("Expose event. VBOX");
                Thread.yield();
                return false;
            }
        });

        w.showAll();

        thread = new BadThread();
        thread.setDaemon(true);
        thread.start();

    }

    class BadThread extends Thread
    {

        public void run() {
            while (true) {
                Button b = new Button("Hello");
                b.setRelief(ReliefStyle.NORMAL);
                x1.add(b);
                b.show();
                Thread.yield();
                x1.remove(b);
            }
        }
    }

    public static void main(String[] args) {
        Gtk.init(args);

        new ValidateThreadStability();

        Gtk.main();

        System.gc();

        System.out.println("Bye now.");
    }
}
