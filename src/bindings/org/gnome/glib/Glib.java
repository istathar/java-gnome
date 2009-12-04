/*
 * Glib.java
 *
 * Copyright (c) 2006-2009 Operational Dynamics Consulting Pty Ltd
 * 
 * The code in this file, and the library it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" plus the "Classpath Exception" (you may link to this code as a
 * library into other programs provided you don't make a derivation of it).
 * See the LICENCE file for the terms governing usage and redistribution.
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
}
