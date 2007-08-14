/*
 * Misc.java
 *
 * Copyright (c) 2006 Operational Dynamics Consulting Pty Ltd
 * 
 * The code in this file, and the library it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" plus the "Classpath Exception" (you may link to this code as a
 * library into other programs provided you don't make a derivation of it).
 * See the LICENCE file for the terms governing usage and redistribution.
 */
package org.gnome.gtk;

/**
 * Base class for Widgets that have notions of alignment and padding.
 * 
 * @author Andrew Cowie
 * @author Nat Pryce
 * @since 4.0.0
 */
public abstract class Misc extends Widget
{
    protected Misc(long pointer) {
        super(pointer);
    }
    
    public void setAlignment(float xalign, float yalign) {
        GtkMisc.setAlignment(this, xalign, yalign);
    }
    
    public void getAlignment(float[] xalign, float[] yalign) {
        GtkMisc.getAlignment(this, xalign, yalign);
    }
    
    public float getAlignmentX() {
        float[] xalign = new float[1];
        float[] yalign = new float[1];
        getAlignment(xalign, yalign);
        return xalign[0];
    }
    
    public float getAlignmentY() {
        float[] xalign = new float[1];
        float[] yalign = new float[1];
        getAlignment(xalign, yalign);
        return yalign[0];
    }
    
    public void setPadding(int xpad, int ypad) {
        GtkMisc.setPadding(this, xpad, ypad);
    }

    public void getPadding(int[] xpad, int[] ypad) {
        GtkMisc.getPadding(this, xpad, ypad);
    }
}
