/*
 * Status.java
 *
 * Copyright (c) 2007 Operational Dynamics Consulting Pty Ltd
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
 * 
 * 
 * @author Andrew Cowie
 * @since 4.0.5
 */
/*
 * Do we even need to expose these at all?
 */
public class Status extends Constant
{
    private Status(int ordinal, String nickname) {
        super(ordinal, nickname);
    }

    /**
     * It worked!
     */
    public static final Status SUCCESS = new Status(CairoStatus.SUCCESS, "SUCCESS");

    /**
     * Out of memory
     */
    public static final Status NO_MEMORY = new Status(CairoStatus.NO_MEMORY, "NO_MEMORY");
}
