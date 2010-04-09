/*
 * java-gnome, a UI library for writing GTK and GNOME programs from Java!
 *
 * Copyright © 2010 Operational Dynamics Consulting, Pty Ltd and Others
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
package message;

import java.io.FileNotFoundException;

import org.gnome.gdk.Event;
import org.gnome.gtk.Gtk;
import org.gnome.gtk.InfoBar;
import org.gnome.gtk.Label;
import org.gnome.gtk.ResponseType;
import org.gnome.gtk.Stock;
import org.gnome.gtk.VBox;
import org.gnome.gtk.Widget;
import org.gnome.gtk.Window;

/**
 * Example to show the interest of using a InfoBar widget.
 * 
 * @author Guillaume Mazoyer
 * @since 4.0.16
 */
/*
 * TODO This is an ok example as far as it goes, but can we do something
 * non-trivial with it to illustrate a more conventional use of InfoBar?
 */
public class ExampleMessageUsingInfoBar
{
    public static void main(String[] args) throws FileNotFoundException {
        final Window w;
        final VBox x;
        final Label l;
        final InfoBar i;

        /*
         * Initialize GTK.
         */

        Gtk.init(args);

        /*
         * Create a top level Window.
         */

        w = new Window();

        /*
         * Create a container to place a Label and a InfoBar.
         */

        x = new VBox(false, 3);
        w.add(x);

        /*
         * Create a Label.
         */

        l = new Label("Does java-gnome provide a good GTK+ coverage?");
        x.add(l);

        /*
         * Create an InfoBar.
         */

        i = new InfoBar();
        i.add(new Label("Sure! java-gnome rocks!"));
        i.addButton(Stock.CLOSE, ResponseType.CLOSE);
        x.add(i);

        /*
         * Set the default action.
         */

        i.setDefaultResponse(ResponseType.CLOSE);

        /*
         * Handle the Escape button.
         */

        i.connect(new InfoBar.Close() {
            public void onClose(InfoBar source) {
                source.hide();
            }
        });

        /*
         * Handle clicks on the action widget.
         */

        i.connect(new InfoBar.Response() {
            public void onResponse(InfoBar source, ResponseType response) {
                source.hide();
            }
        });

        w.setTitle("java-gnome");
        w.setBorderWidth(6);
        w.showAll();

        /*
         * Connect the signal to close the window
         */

        w.connect(new Window.DeleteEvent() {
            public boolean onDeleteEvent(Widget source, Event event) {
                Gtk.mainQuit();
                return false;
            }
        });

        /*
         * Run the main loop.
         */

        Gtk.main();
    }
}
