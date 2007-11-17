/*
 * Harness.java
 *
 * Copyright (c) 2007 Operational Dynamics Consulting Pty Ltd
 *
 * The code in this file, and the library it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" See the LICENCE file for the terms governing usage and
 * redistribution.
 */

import java.io.FileNotFoundException;
import java.io.IOException;

import org.gnome.gdk.Pixbuf;
import org.gnome.gtk.Gtk;
import org.gnome.gtk.Snapshot;
import org.gnome.gtk.SnapshotQuestionMessageDialog;
import org.gnome.gtk.Window;
import org.gnome.screenshot.Screenshot;

/**
 * Start a virtual X server and a window manager Run the screenshot suite and
 * capture images of each one for use in the API documentation.
 * 
 * @author Andrew Cowie
 */
public final class Harness
{
    private static final String DISPLAY = ":1";

    public static void main(String[] args) throws IOException, InterruptedException {
        final Runtime r;
        Process xServerVirtual = null;
        Process windowManager = null;
        Process settingsDaemon = null;
        final Pixbuf logo;
        final Snapshot[] demos;

        try {
            r = Runtime.getRuntime();

            /*
             * Xvfb arguments:
             * 
             * -ac disable access control (necessary so that other program can
             * draw there)
             * 
             * -wr white background
             */

            xServerVirtual = r.exec("/usr/bin/Xvfb " + DISPLAY + " -ac -dpi 96 -screen 0 640x480x24 -wr");
            Thread.sleep(1000);
            checkAlive(xServerVirtual, "Xvfb");

            windowManager = r.exec("/usr/bin/metacity --display=" + DISPLAY);
            Thread.sleep(100);
            checkAlive(windowManager, "metacity");

            windowManager = r.exec("/usr/libexec/gnome-settings-daemon --display=" + DISPLAY);
            Thread.sleep(100);
            checkAlive(windowManager, "gnome-settings-daemon");

            Gtk.init(new String[] {
                "--display=" + DISPLAY
            });

            /*
             * Set an icon so our screenshots look cool
             */

            try {
                logo = new Pixbuf("web/public/images/java-gnome_LargeLogo.png");
                Gtk.setDefaultIcon(logo);
            } catch (FileNotFoundException fnfe) {
                System.err.println("Where's the logo?");
            }

            /*
             * Iterate over the class list
             */

            demos = new Snapshot[] {
                new SnapshotQuestionMessageDialog()
            };

            /*
             * And now the hard part. Take screenshots! This thread runs
             * asynchronously to the main loop; even though Gtk.main() below
             * blocs, we have a main loop running so that things like Dialogs
             * will work.
             */

            for (int i = 0; i < demos.length; i++) {
                final Window w;
                final String f;
                final Pixbuf image;

                w = demos[i].getWindow();
                f = demos[i].getFilename();

                w.showAll();
                w.present();
                Snapshot.cycleMainLoop();

                image = Screenshot.capture();

                image.save(f, "png");
            }

            /*
             * And now tear down the virtual X server
             */

        } finally {
            if (xServerVirtual != null) {
                xServerVirtual.destroy();
                xServerVirtual.waitFor();
            }
            if (windowManager != null) {
                windowManager.destroy();
                windowManager.waitFor();
            }
            if (settingsDaemon != null) {
                settingsDaemon.destroy();
                settingsDaemon.waitFor();
            }
        }
    }

    private static void checkAlive(Process p, String name) {
        try {
            p.exitValue();
            throw new RuntimeException("\n" + name + " didn't start");
        } catch (IllegalThreadStateException itse) {
            // good
        }
    }
}
