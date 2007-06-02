/*
 * Gdk.java
 *
 * Copyright (c) 2007 Operational Dynamics Consulting Pty Ltd
 * 
 * The code in this file, and the library it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" plus the "Classpath Exception" (you may link to this code as a
 * library into other programs provided you don't make a derivation of it).
 * See the LICENCE file for the terms governing usage and redistribution.
 */
package org.gnome.gdk;

/**
 * Control multi-threaded access to GDK. The backend to GTK which talks to the
 * actual screen is GDK. There are various classes with the prefix
 * <code>Gdk...</code> which give you access to lower level drawing
 * constructs. This class, on the other hand, simply contains some of the
 * infrastructure necessary to permit multi-threaded access to GDK.
 * 
 * <p>
 * <i>Threading and GNOME is <b>tricky</b>. This class mostly exists for the
 * purpose of holding an object to serve as the global threading monitor and
 * so that when the lock appers in thread dumps it has a useful name.</i>
 * 
 * @author Andrew Cowie
 * @since 4.0.3
 */
/*
 * TODO can we just move this into Plumbing somehow?
 */
public class Gdk
{
    /**
     * The global monitor used to regulate access to GTK functions. If you're
     * not a java-gnome bindings hacker who <i>really</i> knows what they're
     * doing, <b>do not touch this object</b>.
     */
    public static final Object lock;

    static {
        lock = new Lock();
    }

    /**
     * The class of object used for the GDK global lock. An inner class with
     * this name is used for the purely cosmetic purpose of having
     * 
     * <pre>
     * &quot;waiting on ..., a Gdk$Lock&quot;
     * </pre>
     * 
     * appear in thread dumps. Otherwise, it could just as well have been
     * java.lang.Object
     * 
     * @author Andrew Cowie
     */
    static class Lock
    {
    }

    /*
     * Utility class. No instantiation.
     */
    private Gdk() {}
}
