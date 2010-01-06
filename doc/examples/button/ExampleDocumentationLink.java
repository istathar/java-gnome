/*
 * java-gnome, a UI library for writing GTK and GNOME programs from Java!
 *
 * Copyright © 2009-2010 Operational Dynamics Consulting, Pty Ltd and Others
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

import java.net.URI;
import java.net.URISyntaxException;

import org.gnome.gdk.Event;
import org.gnome.gtk.Gtk;
import org.gnome.gtk.Label;
import org.gnome.gtk.LinkButton;
import org.gnome.gtk.VBox;
import org.gnome.gtk.Widget;
import org.gnome.gtk.Window;

/**
 * Example to show the interest of using a LinkButton widget.
 * 
 * @author Guillaume Mazoyer
 * @since 4.0.14
 */
public class ExampleDocumentationLink
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
                "This window has been made with the <b>java-gnome</b> project.\nTo get more information, you should check out the\ndocumentation by clicking on the following button.");
        label.setUseMarkup(true);
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
         * A click on this button will automatically open the web browser
         * using the previously given link.
         */

        button = new LinkButton(link);
        button.setLabel("Online documentation");
        vbox.add(button);

        /*
         * Set the window's title and show all widgets.
         */

        window.setTitle("Online documentation");
        window.setBorderWidth(6);
        window.add(vbox);
        window.showAll();

        /*
         * Run the main loop.
         */

        Gtk.main();
    }
}
