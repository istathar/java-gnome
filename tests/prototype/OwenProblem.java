/*
 * OwenProblem.java
 *
 * Copyright (c) 2007 Operational Dynamics Consulting Pty Ltd
 * Copyright (c) 2007 Vreixo Formoso
 * 
 * The code in this file, and the library it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" See the LICENCE file for the terms governing usage and
 * redistribution.
 */

import org.gnome.gtk.Button;
import org.gnome.gtk.Gtk;
import org.gnome.gtk.Object;
import org.gnome.gtk.ReliefStyle;
import org.gnome.gtk.VBox;
import org.gnome.gtk.Widget;
import org.gnome.gtk.Window;

/**
 * A simple example that shows the problem noticed by Owen Taylor,
 * that causes Gtk+ to break when callbacks (signal handlers) run
 * without holding the Gdk.lock.
 * 
 * @author Vreixo Formoso
 */
public final class OwenProblem
{
    VBox x1;
    VBox x2;
    BadThread thread;
    
    private OwenProblem() {
        final Window w;

        w = new Window();

        x1 = new VBox(false, 3);

        w.add(x1);
        
        x2 = new VBox(false, 0);
        x1.add(x2);

        w.setTitle("Exp");
        w.showAll();

        w.connect(new Window.DELETE() {
            public boolean onDeleteEvent(Widget source, Object event) {
                System.out.println("I was deleted!");
                Gtk.mainQuit();
                return false;
            }
        });
        
        x2.connect( new Widget.EXPOSE_EVENT() {
            public boolean onExposeEvent(Widget source, Object event) {
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

    class BadThread extends Thread {
        
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

        new OwenProblem();

        Gtk.main();

        System.gc();

        System.out.println("Bye now.");
    }
}
