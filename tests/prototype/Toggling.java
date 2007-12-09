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
import java.util.HashSet;
import java.util.Iterator;

/**
 * Fork of Experiment to specifically test memory management under limited and
 * almost controllable conditions. This is not example code!
 * 
 * @author Andrew Cowie
 */
public final class Toggling
{

    private HashSet set;

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
        final Button res;

        if (!Debug.MEMORY_MANAGEMENT) {
            System.err.println("Debug.MEMORY_MANAGEMENT must be enabled to run this class");
            System.exit(1);
        }

        w = new Window();
        set = new HashSet();

        x = new VBox(false, 3);

        b = new Button("New window");
        x.packStart(b);

        res = new Button("Ressurect");
        x.packStart(res);
        res.connect(new Button.CLICKED() {
            public void onClicked(Button source) {
                Iterator iter = set.iterator();
                while (iter.hasNext()) {
                    Window w = (Window) iter.next();
                    w.present();
                }
            }
        });

        gc = new Button("Run gc()");
        x.packStart(gc);
        gc.connect(new Button.CLICKED() {
            public void onClicked(Button source) {
                System.err.println("Run Java garbage collector");
                System.gc();
            }
        });

        w.add(x);

        w.setTitle("Toggling");

        b.connect(new Button.CLICKED() {
            public void onClicked(Button source) {
                final Window z;
                final Label d;
                z = new Window();
                d = new Label("This is some very very\nimportant data that concerns your future!");
                z.add(d);
                z.showAll();
                set.add(z);
            }
        });

        Button zero = new Button("Drop extra refs");
        x.packStart(zero);
        zero.connect(new Button.CLICKED() {
            public void onClicked(Button source) {
                System.err.println("Drop refs");
                set = null;
            }
        });

        w.showAll();

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

        System.err.println("Run Java garbage collector (last-1 time)");
        System.gc();
        try {
            Thread.sleep(500);
        } catch (Exception e) {
            // ignore
        }

        System.err.println("Run Java garbage collector (last time)");
        System.gc();
        try {
            Thread.sleep(500);
        } catch (Exception e) {
            // ignore
        }

        // And, hopefully, we make it out of the main loop without crashing
        System.out.println("Bye now.");
    }
}
