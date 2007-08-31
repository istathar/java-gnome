/*
 * GlibException.java
 *
 * Copyright (c) 2007 Operational Dynamics Consulting Pty Ltd
 * Copyright (c) 2007 Vreixo Formoso
 * 
 * The code in this file, and the library it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" plus the "Classpath Exception" (you may link to this code as a
 * library into other programs provided you don't make a derivation of it).
 * See the LICENCE file for the terms governing usage and redistribution.
 */
package org.gnome.glib;

/**
 * An exception thrown by the underlying library.
 * 
 * <p>
 * For functions that take a <code>GError</code> argument, we throw this
 * Exception if the function returns an error.
 * 
 * @author Vreixo Formoso
 * @since 4.0.4
 */
public class GlibException extends Exception
{

    private static final long serialVersionUID = 1;

    protected GlibException() {
        super();
    }

    protected GlibException(String msg) {
        super(msg);
    }
}
