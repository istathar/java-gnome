/*
 * Status.java
 *
 * Copyright (c) 2007-2008 Operational Dynamics Consulting Pty Ltd
 * 
 * The code in this file, and the library it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" plus the "Classpath Exception" (you may link to this code as a
 * library into other programs provided you don't make a derivation of it).
 * See the LICENCE file for the terms governing usage and redistribution.
 */
package org.freedesktop.cairo;

import org.freedesktop.bindings.Constant;

/**
 * Error constants for problems arising during Cairo drawing operations.
 * 
 * @author Andrew Cowie
 * @since 4.0.7
 */
class Status extends Constant
{
    private Status(int ordinal, String nickname) {
        super(ordinal, nickname);
    }

    /**
     * It worked!
     */
    public static final Status SUCCESS = new Status(CairoStatus.SUCCESS, "SUCCESS");

    /**
     * Out of memory. Yikes.
     */
    public static final Status NO_MEMORY = new Status(CairoStatus.NO_MEMORY, "NO_MEMORY");

    /**
     * C side, a <code>NULL</code> was encountered when a valid pointer was
     * expected.
     */
    public static final Status NULL_POINTER = new Status(CairoStatus.NULL_POINTER, "NULL_POINTER");

    /**
     * The Surface has been marked as finished, so you can't draw to it
     * anymore.
     */
    public static final Status SURFACE_FINISHED = new Status(CairoStatus.SURFACE_FINISHED,
            "SURFACE_FINISHED");
}
