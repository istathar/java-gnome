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
 * "Claspath Exception"), the copyright holders of this library give you
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

/**
 * Basic coverage to handle command line arguments that can be given by using
 * the {@link org.gnome.glib.Application.CommandLine Application.CommandLine}
 * signal of the {@link Application} class.
 * 
 * @author Guillaume Mazoyer
 * @author Andrew Cowie
 * @since 4.1.2
 */
public class ApplicationCommandLine extends Object
{
    protected ApplicationCommandLine(long pointer) {
        super(pointer);
    }

    /**
     * Returns the arguments of the invoking process's command line.
     * 
     * @since 4.1.2
     */
    public String[] getArguments() {
        int[] argc;
        String[] result;

        argc = new int[1];

        result = GApplicationCommandLine.getArguments(this, argc);

        return result;
    }

    /**
     * Call this when you are finished with the ApplicationCommandLine and
     * wish the remote instance to exit.
     * 
     * <p>
     * If subsequent invocations of your process are to act merely as
     * launchers, activating the primary instance and passing information,
     * then you will want the remote to exit as soon as possible. This method
     * will accomplish that; presumably you will call this from within your
     * <code>Application.CommandLine</code> handler once you have finished
     * dealing with the passed command line arguments.
     * 
     * <p>
     * <b>Do not call more than once.</b>
     * 
     * <p>
     * Do not use this ApplicationCommandLine object further after
     * <code>exit()</code> has been called.
     * 
     * <p>
     * <b style="color: red;">WARNING</b><br>
     * <i>This is as yet un-implemented in GApplication. We have modelled the
     * necessary functionality by artificially dropping a reference count on
     * the GApplicationCommandLine object, but ultimately, causing the remote
     * to exit relies on the backing GObject being finalized. Your mileage may
     * vary.</i>
     * 
     * @since 4.1.2
     */
    public void exit() {
        GApplicationCommandLine.broken(this);
    }
}

/*
 * We get a GDBusCommandLine returned when this fires. This is a placeholder,
 * c.f. GdkX11Display
 */
class DBusCommandLine extends ApplicationCommandLine
{
    protected DBusCommandLine(long pointer) {
        super(pointer);
    }
}
