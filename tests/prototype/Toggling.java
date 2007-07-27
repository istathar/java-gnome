/*
 * Toggling.java
 *
 * Copyright (c) 2006-2007 Operational Dynamics Consulting Pty Ltd
 * 
 * The code in this file, and the library it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" See the LICENCE file for the terms governing usage and
 * redistribution.
 */

import org.freedesktop.bindings.Debug;
import org.gnome.gdk.Event;
import org.gnome.gtk.Button;
import org.gnome.gtk.Gtk;
import org.gnome.gtk.Label;
import org.gnome.gtk.VBox;
import org.gnome.gtk.Widget;
import org.gnome.gtk.Window;

/**
 * Fork of Experiment to specifically test memory management under limited and
 * almost controllable conditions.
 * 
 * @author Andrew Cowie
 */
public final class Toggling
{
    /*
     * It is not, strictly speaking, necessary to put the UI building code
     * into a constructor; there's nothing wrong in a tiny program with doing
     * it all in static code in main().
     * 
     * What we gain, however, is that the references to all the objects
     * created become unreachable as soon as it runs, and that allowed us to
     * evaluate that the memory management is working correctly.
     */
    private Toggling() {
        final Window w;
        final VBox x;
        final Button b;
        final Button gc;
        final Label zombie;

        if (!Debug.MEMORY_MANAGEMENT) {
            System.err.println("Debug.MEMORY_MANAGEMENT must be enabled to run this class");
            System.exit(1);
        }

        w = new Window();

        x = new VBox(false, 3);

        b = new Button("See parent");
        x.packStart(b);

        gc = new Button("Run gc()");
        x.packStart(gc);
        gc.connect(new Button.CLICKED() {
            public void onClicked(Button source) {
                System.err.println("Run Java garbage collector");
                System.gc();
            }
        });

        zombie = new Label("Will not be used");
        zombie.getClass(); // supress warning

        w.add(x);

        w.setTitle("Toggling");
        w.showAll();

        b.connect(new Button.CLICKED() {
            public void onClicked(Button source) {
                System.out.println("My parent is...\t\t\t" + b.getParent().toString());
            }
        });

        w.connect(new Window.DELETE_EVENT() {
            public boolean onDeleteEvent(Widget source, Event event) {
                System.out.println("I was deleted!");
                Gtk.mainQuit();
                return false;
            }
        });
    }

    public static void main(String[] args) {
        Gtk.init(args);

        new Toggling();

        System.out.println("Main loop starting...");
        Gtk.main();
        System.out.println("Main loop exited");
        // Observe release() being done on the various Proxies created. As
        // ever, though, calling gc() is not imperative.

        System.err.println("Run Java garbage collector (last time)");
        System.gc();

        try {
            Thread.sleep(1000);
        } catch (Exception e) {
            // ignore
        }
        // And, hopefully, we make it out of the main loop without crashing
        System.out.println("Bye now.");
    }
}
