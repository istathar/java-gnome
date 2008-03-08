/*
 * ExampleTooltip.java
 *
 * Copyright (c) 2008 Operational Dynamics Consulting Pty Ltd
 * Copyright (c) 2008 Vreixo Formoso
 *
 * The code in this file, and the library it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" See the LICENCE file for the terms governing usage and
 * redistribution.
 */
package tooltip;

import org.gnome.gdk.Event;
import org.gnome.gtk.Button;
import org.gnome.gtk.Gtk;
import org.gnome.gtk.VBox;
import org.gnome.gtk.Widget;
import org.gnome.gtk.Window;

/**
 * A simple example showing the usage of Tooltips. Tooltips are little help
 * messages that are displayed when the use moves the mouse pointer over a
 * Widget.
 * 
 * @author Vreixo Formoso
 * @since 4.0.7
 */
public class ExampleTooltip
{
    public static void main(String[] args) {
        final Window w;
        final VBox x;
        final Button b1;
        final Button b2;

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
         * Create our Buttons, that will have a help Tooltip.
         */

        b1 = new Button("Exit");
        b1.setTooltipText("Click this Button to exit");
        x.add(b1);

        b2 = new Button("Nop");
        
        /*
         * You can use Pango markup to format your Tooltips
         */
        b2.setTooltipMarkup("Clicking this Button has <b>no effect</b>");
        x.add(b2);


        b1.connect(new Button.CLICKED() {
            public void onClicked(Button source) {
                Gtk.mainQuit();
            }
        });

        /*
         * Now we pack the VBox into our Window, set the Window's title, and
         * invoke the command to realize everything onto the screen.
         */

        w.add(x);
        w.setTitle("Tooltip example");
        w.showAll();

        w.connect(new Window.DELETE_EVENT() {
            public boolean onDeleteEvent(Widget source, Event event) {
                Gtk.mainQuit();
                return false;
            }
        });

        Gtk.main();
    }
}
