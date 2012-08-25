/*
 * java-gnome, a UI library for writing GTK and GNOME programs from Java!
 *
 * Copyright © 2012 Operational Dynamics Consulting, Pty Ltd and Others
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
package org.gnome.glib;

import org.freedesktop.bindings.Flag;

/**
 * Constants used to define the behavior of an {@link Application}.
 * 
 * @author Guillaume Mazoyer
 * @author Andrew Cowie
 * @since 4.1.2
 */
public class ApplicationFlags extends Flag
{
    protected ApplicationFlags(int ordinal, String nickname) {
        super(ordinal, nickname);
    }

    /**
     * Default operation mode. You don't need to specify this; you can call
     * Application's {@link org.gnome.gtk.Application#Application(String)
     * singe arg} constructor.
     * 
     * @since 4.1.2
     */
    public static final ApplicationFlags NONE = new ApplicationFlags(GlibApplicationFlags.NONE, "NONE");

    /**
     * The application run as a service. If the service is already running the
     * registration fails. You use this, along with {@link #IS_LAUNCHER
     * IS_LAUNCHER}, in a pair of binaries, one being server only and the
     * other being launcher only. When a single code base is performing the
     * role of becoming <var>primary</var> when necessary and otherwise being
     * <var>remote</var> then you want the [default] {@link #NONE NONE}
     * setting.
     * 
     * @since 4.1.2
     */
    public static final ApplicationFlags IS_SERVICE = new ApplicationFlags(
            GlibApplicationFlags.IS_SERVICE, "IS_SERVICE");

    /**
     * The application will not try to become the primary instance.
     * 
     * @since 4.1.2
     */
    public static final ApplicationFlags IS_LAUNCHER = new ApplicationFlags(
            GlibApplicationFlags.IS_LAUNCHER, "IS_LAUNCHER");

    /**
     * Indicate that this application handles command line arguments from
     * <var>remote</var> instances. These wil be sent from the invoking
     * process to the <var>primary</var>.
     * 
     * <p>
     * Note that if you choose this mode, the
     * <code>Application.CommandLine</code> signal will be raised on the
     * <var>primary</var> rather than <code>Application.Activate</code> so
     * it's a good idea to call <code>activate()</code> manually from your
     * <code>Application.CommandLine</code> handler.
     * 
     * @since 4.1.2
     */
    public static final ApplicationFlags HANDLES_COMMAND_LINE = new ApplicationFlags(
            GlibApplicationFlags.HANDLES_COMMAND_LINE, "HANDLES_COMMAND_LINE");

    public static final ApplicationFlags HANDLES_OPEN = new ApplicationFlags(
            GlibApplicationFlags.HANDLES_OPEN, "HANDLES_OPEN");

    /**
     * The primary instance will receive the environment of the launching
     * process. This constant is useful if the application should behave
     * differently depending on certain environment variables.
     */
    public static final ApplicationFlags SEND_ENVIRONMENT = new ApplicationFlags(
            GlibApplicationFlags.SEND_ENVIRONMENT, "SEND_ENVIRONMENT");

    /**
     * The application does not wish to model unique behaviour and does not
     * check for an already existing single instance.
     */
    public static final ApplicationFlags NON_UNIQUE = new ApplicationFlags(
            GlibApplicationFlags.NON_UNIQUE, "NON_UNIQUE");

    public static ApplicationFlags or(ApplicationFlags one, ApplicationFlags two) {
        return (ApplicationFlags) Flag.orTwoFlags(one, two);
    }
}
