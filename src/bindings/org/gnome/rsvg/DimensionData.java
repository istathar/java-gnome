/*
 * DimensionData.java
 *
 * Copyright (c) 2009 Operational Dynamics Consulting Pty Ltd
 * 
 * The code in this file, and the library it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" plus the "Classpath Exception" (you may link to this code as a
 * library into other programs provided you don't make a derivation of it).
 * See the LICENCE file for the terms governing usage and redistribution.
 */
package org.gnome.rsvg;

import org.gnome.glib.Boxed;

/**
 * The size of an SVG image.
 * 
 * @author Andrew Cowie
 * @since 4.0.15
 */
/*
 * Stupid name! Just call it Dimensions... or howabout Rectangle, like every
 * other bloody GNOME library?
 */
public class DimensionData extends Boxed
{
    protected DimensionData(long pointer) {
        super(pointer);
    }

    DimensionData() {
        super(RsvgDimensionDataOverride.createDimensionData());
    }

    protected void release() {
        RsvgDimensionDataOverride.free(this);
    }

    /**
     * Get the width of the SVG, in pixels.
     * 
     * @since 4.0.15
     */
    public int getWidth() {
        return RsvgDimensionData.getWidth(this);
    }

    /**
     * Get the height of the SVG, in pixels.
     * 
     * @since 4.0.15
     */
    public int getHeight() {
        return RsvgDimensionData.getHeight(this);
    }
}
