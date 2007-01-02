/*
 * VBox.java
 *
 * Copyright (c) 2007 Operational Dynamics Consulting Pty Ltd
 * 
 * The code in this file, and the library it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" plus the "Classpath Exception" (you may link to this code as a
 * library into other programs provided you don't make a derivation of it).
 * See the LICENCE file for the terms governing usage and redistribution.
 */
package org.gnome.gtk;

/**
 * A Container which holds a variable number of Widgets in a single vertical
 * row. All the children of this VBox are allocated the same width - that of
 * the widest Widget packed into the VBox.
 * 
 * @author Andrew Cowie
 * @since 4.0.1
 */
public class VBox extends Box
{
    protected VBox(long pointer) {
        super(pointer);
    }

    public VBox(boolean homogeneous, int spacing) {
        super(GtkVBox.createVBox(homogeneous, spacing));
    }
}
