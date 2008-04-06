/*
 * WorkerThreads.java
 *
 * Copyright (c) 2006-2007 Operational Dynamics Consulting Pty Ltd
 * 
 * The code in this file, and the program it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" See the LICENCE file for the terms governing usage and
 * redistribution.
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
 *  java -classpath tmp/tests:tmp/gtk-4.0.jar -Djava.library.path=tmp/ -ea WorkerThreads
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
        x.packStart(l);

        b = new Button("Start");
        x.packStart(b);

        w.add(x);

        w.setTitle("Worker Threads");
        w.showAll();

        b.connect(new Button.CLICKED() {
            public void onClicked(Button source) {
                String name = "" + (char) j;
                System.out.println(name + " launching.");
                b.setLabel(name + " running");
                new Thread(self, name).start();
                j++;
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
