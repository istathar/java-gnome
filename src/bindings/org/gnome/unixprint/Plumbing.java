/*
 * Plumbing.java
 *
 * Copyright (c) 2007 Operational Dynamics Consulting Pty Ltd
 * 
 * The code in this file, and the library it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" See the LICENCE file for the terms governing usage and
 * redistribution.
 */
package org.gnome.unixprint;

import org.freedesktop.bindings.Proxy;

abstract class Plumbing extends org.gnome.glib.Plumbing
{
    protected Plumbing() {}

    /*
     * FIXME this is a pathetic hack, only here to permit compilation to
     * succeed while we work out a better means of handling the cross-package
     * visibility issue.
     */
    protected static Proxy entityFor(Class type, long pointer) {
        throw new UnsupportedOperationException(
                "Unfortunately, we haven't figured out how to call the method in Cairo's Plumbing yet");
    }
}
