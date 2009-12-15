/*
 * FatalError.java
 *
 * Copyright (c) 2008-2009 Operational Dynamics Consulting Pty Ltd
 * 
 * The code in this file, and the library it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" plus the "Classpath Exception" (you may link to this code as a
 * library into other programs provided you don't make a derivation of it).
 * See the LICENCE file for the terms governing usage and redistribution.
 */
package org.gnome.gtk;

/**
 * Misuse of the GTK.
 * 
 * @author Andrew Cowie
 * @since 4.0.14
 * @see org.gnome.glib.FatalError
 */
public class FatalError extends org.freedesktop.bindings.FatalError
{
    private static final long serialVersionUID = 1;

    protected FatalError() {
        super();
    }

    protected FatalError(String msg) {
        super(msg);
    }
}
