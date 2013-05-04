/*
 * java-gnome, a UI library for writing GTK and GNOME programs from Java!
 *
 * Copyright © 2008      Vreixo Formoso
 * Copyright © 2008-2013 Operational Dynamics Consulting, Pty Ltd and Others
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
import org.gnome.gtk.Grid;
import org.gnome.gtk.Gtk;
import org.gnome.gtk.IconSize;
import org.gnome.gtk.Image;
import org.gnome.gtk.Label;
import org.gnome.gtk.Stock;
import org.gnome.gtk.Tooltip;
import org.gnome.gtk.VBox;
import org.gnome.gtk.Widget;
import org.gnome.gtk.Window;

/**
 * An example showing the usage of Tooltips. Tooltips are little help messages
 * that are displayed when the use moves the mouse pointer over a Widget.
 * 
 * They can also be extended with custom content or have an icon added to the
 * text.
 * 
 * <p>
 * This code assumes you're already seen the other simple examples that we
 * ship with java-gnome, in particular {@link button.ExamplePressMe}.
 * 
 * @author Vreixo Formoso
 * @author Sarah Leibbrand
 * @since 4.1.3
 */
public class ExampleCustomTooltip
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
         * Create our Buttons. Each will have a help Tooltip. You can define
         * the content yourself as shown in b2.
         */

        b1 = new Button("Exit");
        b1.connect(new Widget.QueryTooltip() {
            public boolean onQueryTooltip(Widget source, int x, int y, boolean keyboardMode,
                    Tooltip tooltip) {
                // set the tooltip text and icon
                tooltip.setStockIcon(Stock.ABOUT, IconSize.MENU);
                tooltip.setMarkup("<b>Exit !</b>");

                // show the tooltip
                return true;
            }
        });
        x.add(b1);

        b2 = new Button("No-op");
        b2.connect(new Widget.QueryTooltip() {
            public boolean onQueryTooltip(Widget source, int x, int y, boolean keyboardMode,
                    Tooltip tooltip) {
                // create the content for the tooltip, in this case a grid
                Grid g = new Grid();
                g.attach(new Label("This button will not exit the example"), 0, 0, 2, 1);
                g.attach(new Label("This is a custom tooltip"), 0, 1, 2, 1);
                g.attach(new Label("That you can extend in any kind of way!"), 0, 2, 1, 1);
                g.attach(new Image(Stock.EXECUTE, IconSize.MENU), 1, 2, 1, 1);

                // show all of the contents or the tooltip will appear empty!
                g.showAll();

                // set the content
                tooltip.setCustomWidget(g);

                // show the tooltip.
                return true;
            }
        });
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
        w.setTitle("Custom Tooltip example");
        w.setSizeRequest(200, 200);
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
