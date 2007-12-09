/*
 * GtkResponseTypeOverride.java
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

/*
 * See also GtkResponseTypeOverride where we had to pull a similar stunt. This
 * makes two, but still ok.
 */
final class GdkKeyvalOverride extends Plumbing
{
    private GdkKeyvalOverride() {}

    /**
     * Somewhat unusually for the enumFor case, if the ordinal (keyval in this
     * case) isn't known, then a new Constant will be created [and thus
     * registered] for it.
     */
    static Keyval enumFor(int keyval) {
        try {
            return (Keyval) Plumbing.enumFor(Keyval.class, keyval);
        } catch (IllegalArgumentException iae) {
            return new Keyval(keyval, GdkKeyval.name(keyval));
        }
    }

    /*
     * This is mostly for unit testing
     */
    static int numOf(Keyval key) {
        return Plumbing.numOf(key);
    }
}
