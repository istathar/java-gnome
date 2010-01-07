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
package button;

import java.io.FileNotFoundException;

import org.gnome.gdk.Event;
import org.gnome.gdk.Pixbuf;
import org.gnome.gtk.Button;
import org.gnome.gtk.Gtk;
import org.gnome.gtk.Label;
import org.gnome.gtk.VBox;
import org.gnome.gtk.Widget;
import org.gnome.gtk.Window;

/**
 * A simple Window with a Label and a Button in it. Demonstrates the basics of
 * initializing GTK, packing Widgets into Containers, and hooking up signals.
 * 
 * This example has been around, in one form or another, since the very
 * beginnings of java-gnome. If you're just starting out with GTK and GNOME
 * don't worry; we started here too.
 * 
 * @author Andrew Cowie
 * @since 4.0.0
 */
public class ExamplePressMe
{
    public static void main(String[] args) throws FileNotFoundException {
        final Window w;
        final VBox x;
        final Label l;
        final Button b;
        final Pixbuf logo;

        /*
         * Initialize GTK. You MUST call this to load the library before
         * trying to use any other elements provided by java-gnome.
         */

        Gtk.init(args);

        /*
         * Create a top level Window. It's the Container around which the
         * window manager draws decorations such as a title bar, minimize and
         * close buttons, resize edges, etc.
         */

        w = new Window();

        /*
         * Since a Window is a Bin, it can only have one child Widget. That's
         * probably not very useful in most cases. To take that one slot and
         * turn it into many, we use a VBox, a Container that will stack its
         * children vertically.
         */

        x = new VBox(false, 3);

        /*
         * Create a Label with some text describing the Button that will
         * follow, then add it to the VBox.
         */

        l = new Label("Go ahead:\nMake my day");
        x.add(l);

        /*
         * Create our Button, with a nice explicit bit of text labelling it
         * and what you're to do.
         */

        b = new Button("Press me!");
        x.add(b);

        /*
         * Now the magic part. Just sitting there, the Button won't do
         * anything. You need to hook up a "signal handler" to deal with the
         * 'clicked' signal. This is how we do it in java-gnome.
         * 
         * Button.Clicked is a Java interface. You create an instance of it to
         * do what you want when the callback happens, and then pass it to the
         * Button's connect() method.
         * 
         * The onClicked() method is what is required by the Button.Clicked
         * interface. Most IDEs will prompt you asking if you want to "Add
         * unimplemented methods?" You bet! And ta-da! You have exactly the
         * signature you need to implement a 'clicked' signal handler.
         * 
         * Since we declared b as final we can use it in the anonymous nested
         * class (yet another reason that final is worth using). If the
         * situation were otherwise, then the source parameter can be used to
         * find out what Button was clicked.
         */

        b.connect(new Button.Clicked() {
            public void onClicked(Button source) {
                System.out.println("I was clicked: " + b.getLabel());
            }
        });

        /*
         * Now we pack the VBox into our Window, set the Window's title, and
         * invoke the command to realize everything onto the screen.
         */

        w.add(x);
        w.setTitle("Hello World");
        w.showAll();

        /*
         * We would be be done except for one last detail. Closing a Window
         * does not terminate the application. If that's what you want (and we
         * do indeed want that here) then you need to hook up a handler to do
         * something when the 'delete-event' signal is emitted.
         * 
         * Again, the method here implements the interface.
         */

        w.connect(new Window.DeleteEvent() {
            public boolean onDeleteEvent(Widget source, Event event) {
                Gtk.mainQuit();
                return false;
            }
        });

        /*
         * Just not much of a program if it doesn't have an icon!
         */

        logo = new Pixbuf("src/bindings/java-gnome_Icon.png");
        w.setIcon(logo);

        /*
         * Now we're ready to run the main loop. The signals we've hooked up
         * handlers for above won't be emitted until the user does something,
         * but no events will be processed until the main loop is running;
         * that's where the signals will come from. This call blocks. You have
         * finished setting things up and now it's up to your signal handlers
         * to carry out the program's logic in response to the user's actions;
         * the essence of event-driven programming.
         */

        Gtk.main();
    }
}
