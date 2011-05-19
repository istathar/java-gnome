/*
 * java-gnome, a UI library for writing GTK and GNOME programs from Java!
 *
 * Copyright © 2009-2011 Operational Dynamics Consulting, Pty Ltd and Others
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
package notify;

import org.gnome.gtk.Gtk;
import org.gnome.gtk.StatusIcon;
import org.gnome.notify.Notification;
import org.gnome.notify.Notify;
import org.gnome.notify.Urgency;

/**
 * A simple program that sits in tray and displays a low battery warning.
 * 
 * @author Serkan Kaba
 * @since 4.0.12
 */
public class ExampleLowBattery
{
    public static void main(String[] args) {
        final StatusIcon icon;
        final Notification notification;

        /*
         * Initialize GTK.
         */

        Gtk.init(args);

        /*
         * Initialize notification system.
         */

        Notify.init("low-battery-example");

        /*
         * Create a StatusIcon with GNOME Power Manager icon. Note that
         * StatusIcon can not be directly constructed with an icon name.
         */

        icon = new StatusIcon();
        icon.setFromIconName("gnome-power-manager");

        /*
         * Create a notification with a warning icon, attached to StatusIcon.
         */

        notification = new Notification("Low Battery Example", "Your battery is low!",
                "messagebox_warning");

        /*
         * Quit the application after notification disappears.
         */

        notification.connect(new org.gnome.notify.Notification.Closed() {
            public void onClosed(Notification source) {
                Notify.uninit();
                Gtk.mainQuit();
            }
        });

        /*
         * Make it play the warning sound from gnome-sound. Note that this may
         * change in distributions. Normally this hint should be set if the
         * server supports sounds. But unfortunately notification-daemon
         * doesn't report its sound capability although it supports sounds.
         * 
         * See http://trac.galago-project.org/ticket/187
         */

        notification.setHint("sound-file", "/usr/share/sounds/warning.wav");

        /*
         * Make the notification critical.
         */
        notification.setUrgency(Urgency.CRITICAL);

        /*
         * Display the notification.
         */

        notification.show();

        /*
         * Run the main loop.
         */

        Gtk.main();
    }
}
