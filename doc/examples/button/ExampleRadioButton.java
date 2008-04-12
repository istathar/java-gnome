/*
 * ExampleRadioButton.java
 *
 * Copyright (c) 2008 Operational Dynamics Consulting Pty Ltd
 * Copyright (c) 2008 Vreixo Formoso
 *
 * The code in this file, and the library it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" See the LICENCE file for the terms governing usage and
 * redistribution.
 */
package button;

import org.gnome.gdk.Event;
import org.gnome.gtk.Button;
import org.gnome.gtk.Frame;
import org.gnome.gtk.Gtk;
import org.gnome.gtk.HBox;
import org.gnome.gtk.RadioButton;
import org.gnome.gtk.ShadowType;
import org.gnome.gtk.VBox;
import org.gnome.gtk.Widget;
import org.gnome.gtk.Window;
import org.gnome.gtk.RadioButton.GROUP_TOGGLED;

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
        final RadioButton opt1, opt2, opt3;

        Gtk.init(args);

        w = new Window();
        x = new HBox(false, 10);

        /*
         * RadioButtons in the same group should be placed together. A
         * Frame is usually a good alternative to place them altogether. You
         * should use a descriptive label about the meaning of the options
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
         * RadioButtons should be placed vertically, as this makes them 
         * easy to scan visually. A VBox is the best Container for this:
         */
        
        vb = new VBox(false, 2);
        frame.add(vb);

        /*
         * Now lets create the RadioButtons.
         * We must create one of then first, and submit it to the constructor
         * of the others. That way all the RadioButtons will be in the same
         * group.
         * Note that is is useful to use a "_" in the label. That will make
         * next character become the mnemonic for the Button, that allows
         * user to enable it directly from the keyboard.
         */
        
        opt1 = new RadioButton("_Exit");
        opt2 = new RadioButton(opt1, "_Move Window");
        opt3 = new RadioButton(opt1, "_Print message");

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
         * The GROUP_TOGGLED signal is an easy way to do so. Alternatively you
         * can use the TOGGLED signal on each RadioButton.
         */
        
        opt1.connect(new GROUP_TOGGLED() {
            public void onGroupToggled(RadioButton source) {
                System.out.println("Chosen: " + source.getLabel());
            }
        });
        
        /*
         * You can also get the active Button at any time.
         */
        b.connect(new Button.CLICKED() {
            public void onClicked(Button source) {
                
                /* this returns the active button on opt1 group */
                RadioButton active = opt1.getActiveButton();
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

        w.connect(new Window.DELETE_EVENT() {
            public boolean onDeleteEvent(Widget source, Event event) {
                Gtk.mainQuit();
                return false;
            }
        });

        Gtk.main();
    }
}
