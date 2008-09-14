/*
 * Experiment.java
 *
 * Copyright (c) 2006-2008 Operational Dynamics Consulting Pty Ltd
 *
 * The code in this file, and the program it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" See the LICENCE file for the terms governing usage and
 * redistribution.
 */

import java.io.FileNotFoundException;

import org.gnome.gdk.Event;
import org.gnome.gdk.Pixbuf;
import org.gnome.gdk.Screen;
import org.gnome.gtk.Button;
import org.gnome.gtk.FileChooserAction;
import org.gnome.gtk.FileChooserButton;
import org.gnome.gtk.Gtk;
import org.gnome.gtk.Label;
import org.gnome.gtk.ReliefStyle;
import org.gnome.gtk.VBox;
import org.gnome.gtk.Widget;
import org.gnome.gtk.Window;

/**
 * A simple demonstration program to experiment with, and validate, the
 * end-developer design of the new java-gnome 4.0 bindings architecture.
 * 
 * @author Andrew Cowie
 */
public final class Experiment
{
    /*
     * It is not, strictly speaking, necessary to put the UI building code
     * into a constructor; there's nothing wrong in a tiny program with doing
     * it all in static code in main().
     * 
     * What we gain, however, is that the references to all the objects
     * created become unreachable as soon as it runs, and that allowed us to
     * evaluate that the memory management is working correctly.
     */
    private Experiment() {
        final Window w;
        final VBox x;
        final Label l;
        final Button b;
        final FileChooserButton fcb;
        final Screen s;
        final Pixbuf logo;

        w = new Window();

        x = new VBox(false, 3);

        l = new Label("These Buttons\nare <b>made</b> for\nclicking.");
        l.setUseMarkup(true);
        x.packStart(l, true, true, 0);

        b = new Button("Change Me?");
        b.setLabel("Press Me!");
        b.setRelief(ReliefStyle.NORMAL);
        x.packStart(b);

        fcb = new FileChooserButton("Good to get, a file is", FileChooserAction.OPEN);
        x.packStart(fcb, false, false, 0);

        w.add(x);

        w.setTitle("Experiments");
        w.showAll();

        s = w.getScreen();
        w.move(s.getWidth() - 200, s.getHeight() - 200);

        b.connect(new Button.Clicked() {
            public void onClicked(Button source) {
                System.out.println("I was clicked... " + b.getLabel());
            }
        });

        w.connect(new Window.DeleteEvent() {
            public boolean onDeleteEvent(Widget source, Event event) {
                System.out.println("I was deleted!");
                Gtk.mainQuit();
                return false;
            }
        });

        fcb.connect(new FileChooserButton.FileSet() {
            public void onFileSet(FileChooserButton source) {
                System.out.println("File selected:   " + source.getURI());
                System.gc();
            }
        });

        b.setTooltipText("Pressing this Button will result in some output");

        try {
            logo = new Pixbuf("src/bindings/java-gnome_Icon.png");
            w.setIcon(logo);
        } catch (FileNotFoundException fnfe) {
            System.err.println("Where's the logo?");
        }
    }

    public static void main(String[] args) {
        Gtk.init(args);

        new Experiment();

        Gtk.main();

        // Observe release() being done on the various Proxies created. As
        // ever, though, calling gc() is not imperative.
        System.gc();

        // And, hopefully, we make it out of the main loop without crashing
        System.out.println("Bye now.");
    }
}
