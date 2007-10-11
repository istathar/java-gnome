/*
 * Context.java
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

import org.freedesktop.bindings.Proxy;

/**
 * A Cairo Context.
 * 
 * @author Andrew Cowie
 * @since 4.0.5
 */
public class Context extends Proxy
{
    protected Context(long pointer) {
        super(pointer);
    }

    protected void release() {
        CairoContext.destroy(this);
    }

    /**
     * Construct a new "Cairo Context". You supply the Surface that you are
     * drawing to.
     */
    public Context(Surface target) {
        super(CairoContext.createContext(target));
    }

    /**
     * Set the source pattern within this Context to an opaque colour. The
     * parameters each take the range <code>0.0</code> to <code>1.0</code>.
     */
    public void setSourceRGB(double red, double green, double blue) {
        CairoContext.setSourceRgb(this, red, green, blue);
    }

    /**
     * Set the source pattern within this Context to a translucent colour. The
     * parameters each take the range <code>0.0</code> to <code>1.0</code>.
     * For the <code>alpha</code> parameter, a value of <code>0.0</code>
     * indicates full transparency, and <code>1.0</code> is full opacity
     * (ie, normal).
     */
    public void setSourceRGBA(double red, double green, double blue, double alpha) {
        CairoContext.setSourceRgba(this, red, green, blue, alpha);
    }

    /**
     * Add a line from the current location to <code>x</code>,<code>y</code>.
     * After the call the current point will be <code>x</code>,<code>y</code>.
     */
    public void lineTo(double x, double y) {
        CairoContext.lineTo(this, x, y);
    }

    /**
     * Move to a new location without drawing, beginning a new sub-path. After
     * the call the current point will be <code>x</code>,<code>y</code>.
     */
    public void moveTo(double x, double y) {
        CairoContext.moveTo(this, x, y);
    }

    /**
     * Draw the current path as a line.
     */
    public void stroke() {
        CairoContext.stroke(this);
    }
}
