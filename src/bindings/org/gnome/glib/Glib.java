/*
 * java-gnome, a UI library for writing GTK and GNOME programs from Java!
 *
 * Copyright © 2006-2010 Operational Dynamics Consulting, Pty Ltd
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
 * Static methods to initialize the Java bindings around GLib
 * 
 * @author Andrew Cowie
 * @since 4.0.0
 */
public class Glib
{
    /**
     * No instantiation. Static methods only!
     */
    protected Glib() {}

    /**
     * Change the internal program name used by GLib and GTK for internal
     * error messages. Occasionally (especially as we develop new
     * functionality) you or we will do something wrong, and GLib will
     * complain loudly about it to the console, for example:
     * 
     * <pre>
     * (gnome-panel:5581): Gtk-WARNING **: gtk_widget_size_allocate(): attempt to...
     * </pre>
     * 
     * where "<code>gnome-panel</code>" was the name set by that program with
     * this method call, and <code>5581</code> was the process id originating
     * the message. As you can see, the whole thing is pretty ugly (not to
     * mention having no context), which is why one of the design goals of
     * java-gnome is to fully proxy the entire underlying library and have
     * none of the internals from GLib or GTK be exposed to the Java
     * developer. If we do our job right, your users should never see a
     * message like that; at <i>worst</i> it would be reported as a Java stack
     * trace.
     * 
     * <p>
     * You don't really need to call this, but it's here if you want to make
     * it clearer in the <code>.xsession-errors</code> log what the culprit
     * application is.
     * 
     * <p>
     * <b>Warning</b><br>
     * If you wish to use this, it <b>must</b> be called before anything else.
     * This is the <i>only</i> method in java-gnome that can be called before
     * {@link org.gnome.gtk.Gtk#init(String[]) Gtk.init()}.
     * 
     * @since 4.0.14
     */
    /*
     * Another one to potentially move to a GtkApplication class.
     */
    public static void setProgramName(String name) {
        GlibMisc.setPrgname(name);
    }

    /**
     * Get the XDG user specific configuration directory. In all likelihood
     * this will be <code>~/.config</code>.
     * 
     * @since 4.0.11
     */
    public static String getUserConfigDir() {
        return GlibMisc.getUserConfigDir();
    }
}
