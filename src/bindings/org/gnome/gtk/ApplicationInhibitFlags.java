/*
 * java-gnome, a UI library for writing GTK and GNOME programs from Java!
 *
 * Copyright © 2012-2013 Operational Dynamics Consulting, Pty Ltd and Others
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
 *
 * Linking this library statically or dynamically with other modules is making
 * a combined work based on this library. Thus, the terms and conditions of
 * the GPL cover the whole combination. As a special exception (the
 * "Classpath Exception"), the copyright holders of this library give you
 * permission to link this library with independent modules to produce an
 * executable, regardless of the license terms of these independent modules,
 * and to copy and distribute the resulting executable under terms of your
 * choice, provided that you also meet, for each linked independent module,
 * the terms and conditions of the license of that module. An independent
 * module is a module which is not derived from or based on this library. If
 * you modify this library, you may extend the Classpath Exception to your
 * version of the library, but you are not obligated to do so. If you do not
 * wish to do so, delete this exception statement from your version.
 */
package org.gnome.gtk;

import org.freedesktop.bindings.Flag;

/**
 * Type of user actions that may be blocked by
 * 
 * @author Guillaume Mazoyer
 * @since 4.1.3
 */
public class ApplicationInhibitFlags extends Flag
{
    private ApplicationInhibitFlags(int ordinal, String nickname) {
        super(ordinal, nickname);
    }

    /**
     * Inhibit ending the user session by logging out or by shutting down the
     * computer.
     */
    public static final ApplicationInhibitFlags LOGOUT = new ApplicationInhibitFlags(
            GtkApplicationInhibitFlags.LOGOUT, "LOGOUT");

    /**
     * Inhibit user switching.
     */
    public static final ApplicationInhibitFlags SWITCH = new ApplicationInhibitFlags(
            GtkApplicationInhibitFlags.SWITCH, "SWITCH");

    /**
     * Inhibit suspending the session or computer.
     */
    public static final ApplicationInhibitFlags SUSPEND = new ApplicationInhibitFlags(
            GtkApplicationInhibitFlags.SUSPEND, "SUSPEND");

    /**
     * Inhibit the session being marked as idle (and possibly locked).
     */
    public static final ApplicationInhibitFlags IDLE = new ApplicationInhibitFlags(
            GtkApplicationInhibitFlags.IDLE, "IDLE");

    /**
     * Combines several flags to inhibit several user actions with one
     * inhibitor.
     * 
     * @since 4.1.3
     */
    public static ApplicationInhibitFlags or(ApplicationInhibitFlags... flags) {
        ApplicationInhibitFlags flag;

        if (flags.length < 2) {
            throw new IllegalArgumentException("You must pass at least two flags to combine them.");
        }

        flag = flags[0];

        for (int i = 1; i < flags.length; i++) {
            flag = (ApplicationInhibitFlags) orTwoFlags(flag, flags[i]);
        }

        return flag;
    }
}
