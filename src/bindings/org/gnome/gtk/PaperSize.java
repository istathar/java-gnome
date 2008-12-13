/*
 * PaperSize.java
 *
 * Copyright (c) 2007-2008 Operational Dynamics Consulting Pty Ltd
 *
 * The code in this file, and the library it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" plus the "Classpath Exception" (you may link to this code as a
 * library into other programs provided you don't make a derivation of it).
 * See the LICENCE file for the terms governing usage and redistribution.
 */
package org.gnome.gtk;

import org.gnome.glib.Boxed;

/**
 * Representations of different sizes of paper used in printing operations.
 * 
 * <p>
 * Usage is straight forward:
 * 
 * <pre>
 * paper = new PaperSize(PaperName.A4);
 * </pre>
 * 
 * where the PaperName class contains constants corresponding to the
 * standardized paper types in normal use.
 * 
 * @author Andrew Cowie
 * @since 4.0.10
 */
public final class PaperSize extends Boxed
{
    protected PaperSize(long pointer) {
        super(pointer);
    }

    protected void release() {
        GtkPaperSize.free(this);
    }

    /**
     * Returns a PaperSize corresponding to the default in effect for the
     * current locale.
     */
    public PaperSize() {
        super(GtkPaperSize.createPaperSize(null));
    }

    /**
     * Get a PaperSize corresponding to one of the well known standard paper
     * sizes.
     */
    public PaperSize(PaperName name) {
        super(GtkPaperSize.createPaperSize(name.valueOf()));
    }

    /**
     * Get a PaperSize that corresponds to a PWG 5101.1-2002 paper seize
     * descriptor. Use {@link #PaperSize(PaperName) &lt;init&gt;(PaperName)}
     * for standard paper sizes.
     * 
     * <p>
     * <i>No, we're not going to help you if you use one that doesn't match
     * the spec.</i>
     * 
     * @since 4.0.10
     */
    public PaperSize(String name) {
        super(GtkPaperSize.createPaperSize(name));
    }

    /**
     * Get the width of a piece of paper in the specified units.
     * 
     * <pre>
     * w = paper.getWidth(Unit.MM);
     * </pre>
     * 
     * @since 4.0.10
     */
    public double getWidth(Unit in) {
        return GtkPaperSize.getWidth(this, in);
    }

    /**
     * Get the height of a piece of paper in the specified units.
     * 
     * <pre>
     * h = paper.getHeight(Unit.POINTS);
     * </pre>
     * 
     * @since 4.0.10
     */
    public double getHeight(Unit in) {
        return GtkPaperSize.getHeight(this, in);
    }
}
