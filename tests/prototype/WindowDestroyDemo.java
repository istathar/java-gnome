/*
 * WindowDestroyDemo.java
 *
 * Copyright (c) 2007 Operational Dynamics Consulting Pty Ltd
 * Copyright (c) 2007 Vreixo Formoso
 *
 * The code in this file, and the library it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" plus the "Classpath Exception" (you may link to this code as a
 * library into other programs provided you don't make a derivation of it).
 * See the LICENCE file for the terms governing usage and redistribution.
 */

import org.gnome.gdk.Event;
import org.gnome.gtk.Button;
import org.gnome.gtk.Dialog;
import org.gnome.gtk.Gtk;
import org.gnome.gtk.VBox;
import org.gnome.gtk.Widget;
import org.gnome.gtk.Window;
import org.gnome.gtk.Button.CLICKED;
import org.gnome.gtk.Window.DELETE_EVENT;

/**
 * Demo for manually test the behavior of the Window destroy.
 * 
 * <p>
 * The idea is:
 * 
 * <p>
 * You click the "GC Cycle" Button to see if the Window is actually deleted.
 * 
 * <p>
 * You can play with the Show, Hide and [x] buttons to test the dialog
 * behavior, how it is shown and hidden. You can see how the Window is never
 * deleted.
 * 
 * <p>
 * Then you can call the "Loose" Button, to loose the java ref to the Window.
 * After clicking the GC Cycle, we can see how the Window is only deleted if
 * it is hidden.
 * 
 * <p>
 * Finally, note that after "loose" the java ref, you can't show nor hide the
 * Window programatically. You only can hide it, if shown, by clicking the [x]
 * button.
 * 
 * @author Vreixo Formoso
 */
public class WindowDestroyDemo
{
    Window dialog;

    public WindowDestroyDemo() {
        final Window w;
        final VBox x;
        final Button button1;
        final Button button2;
        final Button button3;
        final Button button4;

        w = new Window();

        x = new VBox(false, 3);
        w.add(x);

        button1 = new Button("Show");
        x.add(button1);

        button2 = new Button("Hide");
        x.add(button2);

        button3 = new Button("Loose");
        x.add(button3);

        button4 = new Button("GC Cycle");
        x.add(button4);

        dialog = new Dialog();

        dialog.add(new Button());

        /*
         * You can respond to user clicks in the ToolButton connecting to the
         * CLICKED signal.
         */
        button1.connect(new CLICKED() {
            public void onClicked(Button source) {
                dialog.showAll();
            }
        });
        button2.connect(new CLICKED() {
            public void onClicked(Button source) {
                dialog.hide();
                dialog.setTitle("ola");
            }
        });
        button3.connect(new CLICKED() {
            public void onClicked(Button source) {
                dialog = null;
            }
        });
        button4.connect(new CLICKED() {
            public void onClicked(Button source) {
                System.gc();
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                }
            }
        });

        dialog.connect(new DELETE_EVENT() {
            public boolean onDeleteEvent(Widget source, Event event) {
                System.out.println("Dialog delete event");
                return false;
            }
        });
        w.connect(new DELETE_EVENT() {
            public boolean onDeleteEvent(Widget source, Event event) {
                System.out.println("Main window delete event");
                Gtk.mainQuit();
                return false;
            }
        });
        w.showAll();
    }

    public static void main(String[] args) {
        Gtk.init(args);

        new WindowDestroyDemo();

        Gtk.main();
    }
}
