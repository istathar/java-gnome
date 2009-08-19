/*
 * RiseAttribute.java
 *
 * Copyright (c) 2009 Operational Dynamics Consulting Pty Ltd
 *
 * The code in this file, and the library it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" plus the "Classpath Exception" (you may link to this code as a
 * library into other programs provided you don't make a derivation of it).
 * See the LICENCE file for the terms governing usage and redistribution.
 */
package org.gnome.pango;

/**
 * An Attribute that lifts the text being rendered off the baseline.
 * 
 * @author Andrew Cowie
 * @since 4.0.13
 */
/*
 * Thanks to Behdad Esfahbod for guidance.
 */
public final class RiseAttribute extends Attribute
{
    /**
     * Create a RizeAttribute. The <code>rise</code> parameter is in the same
     * 1/1024th units as the rest of our Pango API.
     * 
     * @since 4.0.13
     */
    public RiseAttribute(double rise) {
        super(PangoAttribute.createAttributeRise((int) (rise * Pango.SCALE)));
    }
}
