/*
 * Experiment.java
 * 
 * Copyright (c) 2006 Operational Dynamics
 * See LICENCE file for usage and redistribution terms
 */

import org.gnome.gtk.Button;
import org.gnome.gtk.Gtk;
import org.gnome.gtk.Widget;
import org.gnome.gtk.Window;

public final class Experiment {

    public static void main(String[] args) {
        Gtk.init(args);

        Window w = new Window();

        Button b = new Button("Change Me?");
        b.setLabel("Press Me!");

        b.connect(new Button.CLICKED() {
            public void onClicked(Button source) {
                // TODO Auto-generated method stub
            }
        });

        b.connect(new Button.DEPRESSED() {
            public boolean onDepressed(Button source, Widget whoIsDepressed) {
                // TODO Auto-generated method stub
                return false;
            }
        });

        w.add(b);

        w.setTitle("Exp");
        w.showAll();

        new Thread() {
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException ie) {
                    //
                }
                Gtk.mainQuit();
            }
        }.start();
        Gtk.main();
    }
}
