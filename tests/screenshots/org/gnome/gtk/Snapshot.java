/*
 * java-gnome, a UI library for writing GTK and GNOME programs from Java!
 *
 * Copyright © 2007-2010 Operational Dynamics Consulting, Pty Ltd
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
package org.gnome.gtk;

import java.io.FileNotFoundException;

import org.gnome.gdk.Event;
import org.gnome.gdk.Pixbuf;

/**
 * Extend this class to create a snapshot to be used in the documentation
 * 
 * @author Andrew Cowie
 * @since 4.0.5
 */
/*
 * This is not the greatest API. The fact that you have to populate the window
 * field in a subclass is ok, but the presence of the helper methods here just
 * seems clutter. What really complicates matters is trying to find a scheme
 * where running the Snapshot with a minimal main() method causes the Window
 * to appear, whereas running the Harness also works sensibly. Ideally this
 * would be like JUnit test cases, but that would require a plugin to Eclipse
 * to work. There is, therefore, something to be said for doing this as a
 * JUnit TestSuite.
 */
public abstract class Snapshot
{
    protected static Pixbuf logo = null;

    /**
     * The Window containing the user interface you wish to screenshot. This
     * Window will be presented and hidden by the calling test harness.
     */
    protected Window window;

    private String target;

    /**
     * Instantiate a new screenshot demo. The class you are doing a demo for
     * will be used to derive the target filename, ie Button.class ->
     * doc/api/org/gnome/gtk/Button.png
     */
    protected Snapshot(Class<?> underTest) {
        this.target = targetFilenameFromClass(underTest, null);
    }

    /**
     * Instantiate a new screenshot demo. For (Button.class, "blah") you'll
     * get doc/api/org/gnome/gtk/Button-blah.png
     */
    protected Snapshot(Class<?> underTest, String suffix) {
        this.target = targetFilenameFromClass(underTest, suffix);
    }

    public static void cycleMainLoop() {
        /*
         * Lead off with one iteration no matter what. Pending events aren't
         * the only thing that the main loop does! Then continue by working
         * off whatever has accumulated.
         */
        do {
            try {
                GtkMain.mainIterationDo(false);
                Thread.sleep(10);
            } catch (InterruptedException e) {
                // ignore
            }
        } while (GtkMain.eventsPending());
    }

    /**
     * Run a single Screenshot wrapper in interactive mode so you can preview
     * it and check your work.
     */
    /*
     * As noted elsewhere, I'm not super thrilled about this API. If anyone
     * has an idea for making is sexier, let me know.
     */
    protected static void runExample(final Snapshot example) {
        final Pixbuf logo;
        final Window w;

        w = example.window;

        try {
            logo = new Pixbuf("src/bindings/java-gnome_Icon.png");
            Gtk.setDefaultIcon(logo);
        } catch (FileNotFoundException fnfe) {
            System.err.println("Where's the logo?");
        }

        w.showAll();
        w.present();

        w.connect(new Window.DeleteEvent() {
            public boolean onDeleteEvent(Widget source, Event event) {
                Gtk.mainQuit();
                return false;
            }
        });
    }

    static String targetFilenameFromClass(Class<?> underTest, String suffix) {
        final StringBuffer path;
        int i = 0;

        path = new StringBuffer(underTest.getPackage().getName());
        while ((i = path.indexOf(".", i)) != -1) {
            path.setCharAt(i, '/');
        }
        path.insert(0, "doc/api/");

        path.append("/");
        path.append(underTest.getSimpleName());

        if (suffix != null) {
            path.append("-");
            path.append(suffix);
        }

        path.append(".png");

        return path.toString();
    }

    public Window getWindow() {
        if (window == null) {
            throw new IllegalStateException(
                    "\nThe window of this Snapshot subclass has not been set yet!");
        }
        return window;
    }

    public String getFilename() {
        return target;
    }
}
