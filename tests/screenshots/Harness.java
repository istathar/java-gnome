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
import java.io.FileNotFoundException;
import java.io.IOException;

import org.freedesktop.bindings.Environment;
import org.freedesktop.cairo.Illustration;
import org.freedesktop.cairo.IllustrationOperatorAdd;
import org.freedesktop.cairo.IllustrationOperatorAtop;
import org.freedesktop.cairo.IllustrationOperatorClear;
import org.freedesktop.cairo.IllustrationOperatorDest;
import org.freedesktop.cairo.IllustrationOperatorDestAtop;
import org.freedesktop.cairo.IllustrationOperatorDestIn;
import org.freedesktop.cairo.IllustrationOperatorDestOut;
import org.freedesktop.cairo.IllustrationOperatorDestOver;
import org.freedesktop.cairo.IllustrationOperatorIn;
import org.freedesktop.cairo.IllustrationOperatorOut;
import org.freedesktop.cairo.IllustrationOperatorOver;
import org.freedesktop.cairo.IllustrationOperatorSaturate;
import org.freedesktop.cairo.IllustrationOperatorSource;
import org.freedesktop.cairo.IllustrationOperatorXOR;
import org.freedesktop.cairo.SnapshotContextArc;
import org.freedesktop.cairo.SnapshotContextArcNegative;
import org.freedesktop.cairo.SnapshotContextLine;
import org.freedesktop.cairo.SnapshotContextRectangle;
import org.freedesktop.cairo.SnapshotMatrixRotate;
import org.freedesktop.cairo.SnapshotMatrixScale;
import org.freedesktop.cairo.SnapshotMatrixTranslate;
import org.freedesktop.cairo.Surface;
import org.gnome.gdk.Pixbuf;
import org.gnome.gdk.PixbufFormat;
import org.gnome.gtk.Gtk;
import org.gnome.gtk.Snapshot;
import org.gnome.gtk.SnapshotAboutDialog;
import org.gnome.gtk.SnapshotArrow;
import org.gnome.gtk.SnapshotButton;
import org.gnome.gtk.SnapshotCalendar;
import org.gnome.gtk.SnapshotComboBox;
import org.gnome.gtk.SnapshotComboBoxText;
import org.gnome.gtk.SnapshotComboBoxTextEntry;
import org.gnome.gtk.SnapshotEntryCompletion;
import org.gnome.gtk.SnapshotEntryIcon;
import org.gnome.gtk.SnapshotFileChooserDialog;
import org.gnome.gtk.SnapshotHScale;
import org.gnome.gtk.SnapshotInfoBar;
import org.gnome.gtk.SnapshotInfoMessageDialog;
import org.gnome.gtk.SnapshotLinkButton;
import org.gnome.gtk.SnapshotNotebook;
import org.gnome.gtk.SnapshotQuestionMessageDialog;
import org.gnome.gtk.SnapshotRadioButton;
import org.gnome.gtk.SnapshotStatusbar;
import org.gnome.gtk.SnapshotSwitch;
import org.gnome.gtk.SnapshotTextView;
import org.gnome.gtk.SnapshotTextViewBorderWindows;
import org.gnome.gtk.SnapshotTextViewSpelling;
import org.gnome.gtk.SnapshotTreeStore;
import org.gnome.gtk.SnapshotTreeView;
import org.gnome.gtk.SnapshotVScale;
import org.gnome.gtk.SnapshotWindow;
import org.gnome.gtk.Window;
import org.gnome.screenshot.Screenshot;

/**
 * Start a virtual X server and a window manager Run the screenshot suite and
 * capture images of each one for use in the API documentation.
 * 
 * @author Andrew Cowie
 */
/*
 * FIXME This whole system is a bit of a hack with much ugliness.
 * Instantiating the subordinate processes is messy, the list of the Snapshots
 * to be run is in a terrible place, being able to cycling the main loop from
 * here has to be fixed, and Pixbuf's save() has a terrible signature, and
 * there's no progress reporting. Yuck. None of this is to be considered fixed
 * API!
 */
public final class Harness
{
    private static final boolean USE_VIRTUAL_DISPLAY = false;

    public static void main(String[] args) throws IOException, InterruptedException {
        final String DISPLAY;
        final Runtime r;
        Process xServerVirtual = null;
        Process windowManager = null;
        Process settingsDaemon = null;
        final Pixbuf logo;
        final Class<?>[] demos;
        final Class<?>[] illustrationdemos;

        try {
            r = Runtime.getRuntime();

            /*
             * Get the X server address. If it's not present, abort, giving a
             * short-cut way to skip generating snapshots.
             */

            DISPLAY = Environment.getEnv("DISPLAY");

            if (DISPLAY == null) {
                return;
            } else if (USE_VIRTUAL_DISPLAY) {
                /*
                 * Xvfb arguments:
                 * 
                 * -ac disable access control (necessary so that other program
                 * can draw there)
                 * 
                 * -wr white background
                 * 
                 * Don't try to force it to 32 bits per pixed in -screen; for
                 * some reason this makes Xvfb unable to start.
                 */
                System.out.println("EXEC\tXvfb");
                xServerVirtual = r.exec("/usr/bin/Xvfb " + DISPLAY
                        + " -ac -dpi 96 -screen 0 800x600x24 -wr");
                Thread.sleep(1000);
                checkAlive(xServerVirtual, "Xvfb");

                System.out.println("EXEC\tmetacity");
                windowManager = r.exec("/usr/bin/metacity --display=" + DISPLAY);
                Thread.sleep(100);
                checkAlive(windowManager, "metacity");

                System.out.println("EXEC\tgnome-settings-daemon");
                settingsDaemon = r.exec("/usr/libexec/gnome-settings-daemon --display=" + DISPLAY
                        + " --disable-crash-dialog");
                Thread.sleep(100);
                checkAlive(settingsDaemon, "gnome-settings-daemon");
            }

            Gtk.init(new String[] {
                "--display=" + DISPLAY
            });

            /*
             * Set an icon so our screenshots look cool
             */

            try {
                logo = new Pixbuf("src/bindings/java-gnome_Icon.png");
                Gtk.setDefaultIcon(logo);
            } catch (FileNotFoundException fnfe) {
                System.err.println("Where's the logo?");
            }

            /*
             * Iterate over the class list. This is a TERRIBLE place to
             * specify content like this. A Runner API like JUnit has would be
             * far preferable.
             */

            demos = new Class[] {
                SnapshotWindow.class,
                SnapshotStatusbar.class,
                SnapshotButton.class,
                SnapshotInfoMessageDialog.class,
                SnapshotQuestionMessageDialog.class,
                SnapshotTreeView.class,
                SnapshotTreeStore.class,
                SnapshotFileChooserDialog.class,
                SnapshotAboutDialog.class,
                SnapshotHScale.class,
                SnapshotVScale.class,
                SnapshotRadioButton.class,
                SnapshotComboBox.class,
                SnapshotArrow.class,
                SnapshotNotebook.class,
                SnapshotCalendar.class,
                SnapshotComboBoxText.class,
                SnapshotComboBoxTextEntry.class,
                SnapshotContextLine.class,
                SnapshotTextView.class,
                SnapshotTextViewBorderWindows.class,
                SnapshotTextViewSpelling.class,
                SnapshotContextArc.class,
                SnapshotContextArcNegative.class,
                SnapshotMatrixRotate.class,
                SnapshotMatrixScale.class,
                SnapshotMatrixTranslate.class,
                SnapshotContextRectangle.class,
                SnapshotEntryCompletion.class,
                SnapshotEntryIcon.class,
                SnapshotLinkButton.class,
                SnapshotInfoBar.class,
                SnapshotSwitch.class
            };

            illustrationdemos = new Class[] {
                IllustrationOperatorIn.class,
                IllustrationOperatorClear.class,
                IllustrationOperatorSource.class,
                IllustrationOperatorOver.class,
                IllustrationOperatorOut.class,
                IllustrationOperatorAtop.class,
                IllustrationOperatorDest.class,
                IllustrationOperatorDestOver.class,
                IllustrationOperatorDestIn.class,
                IllustrationOperatorDestOut.class,
                IllustrationOperatorDestAtop.class,
                IllustrationOperatorXOR.class,
                IllustrationOperatorAdd.class,
                IllustrationOperatorSaturate.class
            };

            /*
             * And now the hard part. Take screenshots! This thread runs
             * asynchronously to the main loop; even though Gtk.main() below
             * blocs, we have a main loop running so that things like Dialogs
             * will work.
             */

            for (int i = 0; i < demos.length; i++) {
                final Snapshot demo;
                final Window w;
                final String f;
                final Pixbuf image;

                /*
                 * Instantiate here (as opposed to above when specifying the
                 * array) so that each one takes its resources in turn. We ran
                 * into problems with all sorts of cruft being on the display
                 * when doing it otherwise.
                 */
                try {
                    demo = (Snapshot) demos[i].newInstance();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                    continue;
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                    continue;
                } catch (UnsupportedOperationException e) {
                    e.printStackTrace();
                    continue;
                }

                w = demo.getWindow();
                f = demo.getFilename();

                System.out.println("SNAP\t" + f);

                w.setHasResizeGrip(false);
                w.showAll();
                w.present();
                Snapshot.cycleMainLoop();

                image = Screenshot.capture();
                image.save(f, PixbufFormat.PNG);

                w.hide();
            }
            /* Now for the Illustrations */
            for (int i = 0; i < illustrationdemos.length; i++) {
                final Illustration demo;
                final String f;
                final Surface s;

                /*
                 * Instantiate here (as opposed to above when specifying the
                 * array) so that each one takes its resources in turn. We ran
                 * into problems with all sorts of cruft being on the display
                 * when doing it otherwise.
                 */
                try {
                    demo = (Illustration) illustrationdemos[i].newInstance();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                    continue;
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                    continue;
                }

                demo.illustrate();

                s = demo.getSurface();
                f = demo.getFilename();

                System.out.println("WRITE\t" + f);

                s.writeToPNG(f);
            }
        } finally {
            /*
             * And now tear down the virtual X server and friends. This is far
             * from bullet proof. As it is incredibly annoying when processes
             * get orphaned, improvements to this would be welcome.
             */

            if (xServerVirtual != null) {
                System.out.println("KILL\tXvfb");
                xServerVirtual.destroy();
                xServerVirtual.waitFor();
            }
            if (windowManager != null) {
                System.out.println("KILL\tmetacity");
                windowManager.destroy();
                windowManager.waitFor();
            }
            if (settingsDaemon != null) {
                System.out.println("KILL\tgnome-settings-daemon");
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
