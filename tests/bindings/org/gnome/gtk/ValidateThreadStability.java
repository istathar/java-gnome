/*
 * java-gnome, a UI library for writing GTK and GNOME programs from Java!
 *
 * Copyright © 2007-2011 Operational Dynamics Consulting, Pty Ltd
 * Copyright © 2007      Vreixo Formoso
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
package org.gnome.gtk;

import org.freedesktop.cairo.Context;
import org.gnome.gdk.Event;

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

        w.connect(new Window.DeleteEvent() {
            public boolean onDeleteEvent(Widget source, Event event) {
                System.out.println("I was deleted!");
                Gtk.mainQuit();
                return false;
            }
        });

        x2.connect(new Widget.Draw() {
            public boolean onDraw(Widget source, Context cr) {
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
