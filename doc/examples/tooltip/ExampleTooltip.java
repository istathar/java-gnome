/*
 * java-gnome, a UI library for writing GTK and GNOME programs from Java!
 *
 * Copyright © 2008      Vreixo Formoso
 * Copyright © 2008-2010 Operational Dynamics Consulting, Pty Ltd
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
 * <p>
 * This code assumes you're already seen the other simple examples that we
 * ship with java-gnome, in particular {@link button.ExamplePressMe}.
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

        Gtk.init(args);

        w = new Window();
        x = new VBox(false, 3);

        /*
         * Create our Buttons. Each will have a help Tooltip. You can use
         * Pango markup to format your Tooltips, as b2 demonstrates.
         */

        b1 = new Button("Exit");
        b1.setTooltipText("Click this Button to exit");
        x.add(b1);

        b2 = new Button("No-op");
        b2.setTooltipMarkup("Clicking this Button has <b>no effect</b>");
        x.add(b2);

        /*
         * The rest of the file is the same as you've seen in the other basic
         * examples which ship with java-gnome.
         */

        b1.connect(new Button.Clicked() {
            public void onClicked(Button source) {
                Gtk.mainQuit();
            }
        });

        w.add(x);
        w.setTitle("Tooltip example");
        w.showAll();

        w.connect(new Window.DeleteEvent() {
            public boolean onDeleteEvent(Widget source, Event event) {
                Gtk.mainQuit();
                return false;
            }
        });

        Gtk.main();
    }
}
