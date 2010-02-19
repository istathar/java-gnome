/*
 * java-gnome, a UI library for writing GTK and GNOME programs from Java!
 *
 * Copyright © 2008-2010 Operational Dynamics Consulting, Pty Ltd
 * Copyright © 2008      Vreixo Formoso
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

import org.gnome.gdk.Event;
import org.gnome.glib.Object;
import org.gnome.gtk.Button;
import org.gnome.gtk.Frame;
import org.gnome.gtk.Gtk;
import org.gnome.gtk.HBox;
import org.gnome.gtk.RadioButton;
import org.gnome.gtk.RadioGroup;
import org.gnome.gtk.ShadowType;
import org.gnome.gtk.VBox;
import org.gnome.gtk.Widget;
import org.gnome.gtk.Window;

/**
 * A little example that shows the usage of RadioButtons.
 * 
 * @author Vreixo Formoso
 * @since 4.0.7
 */
public class ExampleRadioButton
{
    public static void main(String[] args) {
        final Window w;
        final HBox x;
        final VBox vb;
        final Frame frame;
        final Button b;
        final RadioGroup group;
        final RadioButton opt1, opt2, opt3;

        Gtk.init(args);

        w = new Window();
        x = new HBox(false, 10);

        /*
         * RadioButtons in the same group should be placed together. A Frame
         * is usually a good alternative to place them altogether. You should
         * use a descriptive label about the meaning of the options
         */

        frame = new Frame("Action to execute:");
        x.add(frame);

        /*
         * While the Frame outline can be useful in some cases, it is usually
         * unnecessary, and uses to be visually ugly. A blank space is good
         * enough.
         */
        frame.setBorderWidth(3);
        frame.setShadowType(ShadowType.NONE);

        /*
         * RadioButtons should be placed vertically, as this makes them easy
         * to scan visually. A VBox is the best Container for this:
         */

        vb = new VBox(false, 2);
        frame.add(vb);

        /*
         * Now lets create the RadioButtons. We must create one of then first,
         * and submit it to the constructor of the others. That way all the
         * RadioButtons will be in the same group. Note that is is useful to
         * use a "_" in the label. That will make next character become the
         * mnemonic for the Button, that allows user to enable it directly
         * from the keyboard.
         */

        group = new RadioGroup();
        opt1 = new RadioButton(group, "_Exit");
        opt2 = new RadioButton(group, "_Move Window");
        opt3 = new RadioButton(group, "_Print message");

        /*
         * And we add the RadioButtons to the VBox
         */

        vb.add(opt1);
        vb.add(opt2);
        vb.add(opt3);

        b = new Button("Execute action");
        x.add(b);

        /*
         * Sometimes you want to be notified each time the selection changes.
         * The RadioGroup.GroupToggled signal is an easy way to do so.
         * Alternatively you can use the RadioButton.Toggled signal on each
         * RadioButton.
         */

        group.connect(new RadioGroup.GroupToggled() {
            public void onGroupToggled(Object source) {
                RadioButton button = (RadioButton) source;
                System.out.println("Chosen: " + button.getLabel());
            }
        });

        /*
         * You can also get the active Button at any time.
         */
        b.connect(new Button.Clicked() {
            public void onClicked(Button source) {

                /* this returns the active button in the group */
                RadioButton active = (RadioButton) group.getActive();
                if (active == opt1) {
                    /* exit */
                    Gtk.mainQuit();
                } else if (active == opt2) {
                    w.move(0, 0);
                } else { // active == opt3
                    System.out.println("This is the message you want to print");
                }
            }
        });

        /*
         * Now we pack the VBox into our Window, set the Window's title, and
         * invoke the command to realize everything onto the screen.
         */

        w.add(x);
        w.setTitle("RadioButton example");
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
