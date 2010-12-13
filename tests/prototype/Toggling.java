/*
 * java-gnome, a UI library for writing GTK and GNOME programs from Java!
 *
 * Copyright © 2006-2010 Operational Dynamics Consulting, Pty Ltd
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

import java.util.HashSet;

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
 * almost controllable conditions. This is not example code!
 * 
 * @author Andrew Cowie
 */
public final class Toggling
{

    private HashSet<Window> set;

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
        set = new HashSet<Window>();

        x = new VBox(false, 3);

        b = new Button("New window");
        x.packStart(b, false, false, 0);

        res = new Button("Ressurect");
        x.packStart(res, false, false, 0);
        res.connect(new Button.Clicked() {
            public void onClicked(Button source) {
                for (Window w : set) {
                    w.present();
                }
            }
        });

        gc = new Button("Run gc()");
        x.packStart(gc, false, false, 0);
        gc.connect(new Button.Clicked() {
            public void onClicked(Button source) {
                System.err.println("Run Java garbage collector");
                System.gc();
            }
        });

        w.add(x);

        w.setTitle("Toggling");

        b.connect(new Button.Clicked() {
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
        x.packStart(zero, false, false, 0);
        zero.connect(new Button.Clicked() {
            public void onClicked(Button source) {
                System.err.println("Drop refs");
                set = null;
            }
        });

        w.showAll();

        w.connect(new Window.DeleteEvent() {
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
