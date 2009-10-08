/*
 * ExampleDocLink.java
 *
 * Copyright (c) 2009 Operational Dynamics Consulting Pty Ltd, and Others
 *
 * The code in this file, and the program it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" See the LICENCE file for the terms governing usage and
 * redistribution.
 */
package button;

import java.net.URI;
import java.net.URISyntaxException;

import org.gnome.gdk.Event;
import org.gnome.gtk.Gtk;
import org.gnome.gtk.Label;
import org.gnome.gtk.LinkButton;
import org.gnome.gtk.VBox;
import org.gnome.gtk.Widget;
import org.gnome.gtk.Window;

public class ExampleDocLink
{
    public static void main(String[] args) {
        final Window window;
        final VBox vbox;
        final Label label;
        final LinkButton button;
        URI link = null;

        /*
         * Initialize GTK.
         */

        Gtk.init(args);

        /*
         * Create a top level Window.
         */

        window = new Window();

        /*
         * Connect the signal to close the window
         */

        window.connect(new Window.DeleteEvent() {
            public boolean onDeleteEvent(Widget source, Event event) {
                Gtk.mainQuit();
                return false;
            }
        });

        /*
         * Create a container to place a Label and a LinkButton.
         */

        vbox = new VBox(false, 6);

        /*
         * Create a Label to inform the user about what he can do.
         */

        label = new Label(
                "This window has been made with the java-gnome project.\nTo get more information, you should check out the\ndocumentation by clicking on the following button.");
        vbox.add(label);

        /*
         * URI which points on the documentation.
         */

        try {
            link = new URI("http://java-gnome.sourceforge.net/4.0/doc/");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        /*
         * Create the LinkButton. The setLabel(String) method will hide the
         * link and display friendlier word(s).
         * 
         * Click on this button will automatically open the web browser using
         * the previously given link.
         */

        button = new LinkButton(link);
        button.setLabel("Online documentation");
        vbox.add(button);

        /*
         * Set the window's title and show all widgets.
         */

        window.setTitle("Online documentation");
        window.add(vbox);
        window.showAll();

        /*
         * Run the main loop.
         */

        Gtk.main();
    }
}
