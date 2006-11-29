/*
 * Experiment.java
 * 
 * Copyright (c) 2006 Operational Dynamics
 * See LICENCE file for usage and redistribution terms
 */

import org.gnome.gtk.Button;
import org.gnome.gtk.Gtk;
import org.gnome.gtk.Object;
import org.gnome.gtk.Widget;
import org.gnome.gtk.Window;

public final class Experiment
{

    public static void main(String[] args) {
        Window w;
        final Button b;

        Gtk.init(args);

        w = new Window();

        b = new Button("Change Me?");
        b.setLabel("Press Me!");

        w.add(b);

        w.setTitle("Exp");
        w.showAll();

        b.connect(new Button.CLICKED() {
            public void onClicked(Button source) {
                System.out.println("I got clicked...");
            }
        });

        w.connect(new Window.DELETE() {
            public boolean onDeleteEvent(Widget source, Object event) {
                Gtk.mainQuit();
                return false;
            }
        });

        Gtk.main();
    }
}
