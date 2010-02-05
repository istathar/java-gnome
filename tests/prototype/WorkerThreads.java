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

import org.gnome.gdk.Event;
import org.gnome.gtk.Button;
import org.gnome.gtk.Gtk;
import org.gnome.gtk.Label;
import org.gnome.gtk.VBox;
import org.gnome.gtk.Widget;
import org.gnome.gtk.Window;

/**
 * Examine multi-threading issues. Yet another derivation of the original
 * Experiment class. If you run this in a terminal, say with
 * 
 * <pre>
 *  java -client -ea -classpath tmp/tests:tmp/gtk-4.0.jar WorkerThreads
 * </pre>
 * 
 * you can press Ctrl+\ to get the thread dump to study the lock contention
 * when one or more worker threads are running. Or you can just run it in an
 * IDE debugger.
 * 
 * @author Andrew Cowie
 */
public final class WorkerThreads implements Runnable
{
    /**
     * Simple reference to the created class that can be used when this is
     * blocked.
     */
    private static WorkerThreads self;

    private final Label l;

    private final Button b;

    private int j;

    private WorkerThreads() {
        final Window w;
        final VBox x;

        self = this;
        j = 65;

        w = new Window();
        x = new VBox(false, 3);
        l = new Label("Ready");
        x.packStart(l, false, false, 0);

        b = new Button("Start");
        x.packStart(b, false, false, 0);

        w.add(x);

        w.setTitle("Worker Threads");
        w.showAll();

        b.connect(new Button.Clicked() {
            public void onClicked(Button source) {
                String name = "" + (char) j;
                System.out.println(name + " launching.");
                b.setLabel(name + " running");
                new Thread(self, name).start();
                j++;
            }
        });

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

        new WorkerThreads();

        Gtk.main();

        System.out.println("Bye now.");
        /*
         * There's probably all kinds of threads still running if the button
         * was clicked numerous times, but if the person has closed the
         * window, kill off the app already.
         */
        System.exit(0);
    }

    /*
     * This could have been nested in the callback above. Whatever.
     */
    public void run() {
        final String name;

        name = Thread.currentThread().getName();

        for (int i = 1; i <= 50000; i++) {
            l.setLabel(name + "->" + i);
            /*
             * Interestingly, this loop is tight enough, and the contention
             * sufficient if you fire up multiple threads that you actually
             * get the otherwise rare condition of "lock starvation". If you
             * stick a yield here the balance is more like you would expect.
             */
            // Thread.yield();
        }

        b.setLabel("Restart?");
        System.out.println(name + " done.");
    }
}
