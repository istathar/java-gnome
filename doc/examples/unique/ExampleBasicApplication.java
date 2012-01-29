/*
 * java-gnome, a UI library for writing GTK and GNOME programs from Java!
 *
 * Copyright © 2012 Operational Dynamics Consulting, Pty Ltd and Others
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
package unique;

import org.gnome.gdk.Event;
import org.gnome.glib.ApplicationFlags;
import org.gnome.glib.CommandLine;
import org.gnome.gtk.Application;
import org.gnome.gtk.Button;
import org.gnome.gtk.Gtk;
import org.gnome.gtk.Label;
import org.gnome.gtk.VBox;
import org.gnome.gtk.Widget;
import org.gnome.gtk.Window;

/**
 * A class that demonstrate how to use the Application system of GLib and GTK.
 * 
 * @author Guillaume Mazoyer
 * @since 4.1.2
 */
public final class ExampleBasicApplication
{
    public static void main(String[] args) {
        final Application a;
        final Window w;
        final VBox x;
        final Label l;
        final Button b;
        final int s;

        Gtk.init(args);

        if (!Application.isValidId("org.gnome.TestGtkApp")) {
            throw new IllegalArgumentException();
        }

        a = new Application("org.gnome.TestGtkApp", ApplicationFlags.HANDLES_OPEN);

        a.connect(new Application.Activate() {
            public void onActivate(org.gnome.glib.Application source) {
                System.out.println("Activated");
            }
        });

        a.connect(new Application.OpenFiles() {
            public void onOpenFiles(org.gnome.glib.Application source, String[] files, String hint) {
                for (String file : files) {
                    System.out.println(file);
                }
            }
        });

        a.connect(new Application.CommandLine() {
            public int onCommandLine(org.gnome.glib.Application source, CommandLine command) {
                for (String s : command.getArguments()) {
                    System.out.println(s);
                }

                return 0;
            }
        });

        w = new Window();
        a.addWindow(w);

        x = new VBox(false, 3);

        l = new Label("Go ahead:\nMake my day");
        x.add(l);

        b = new Button("Press me!");
        x.add(b);

        b.connect(new Button.Clicked() {
            public void onClicked(Button source) {
                System.out.println("I was clicked: " + b.getLabel());
            }
        });

        w.add(x);
        w.setTitle("Hello World");
        w.showAll();

        w.connect(new Window.DeleteEvent() {
            public boolean onDeleteEvent(Widget source, Event event) {
                a.removeWindow(w);
                return false;
            }
        });

        s = a.run(args);

        System.out.println("Status = " + s);
        System.exit(s);
    }
}
