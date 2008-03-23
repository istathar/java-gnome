/*
 * DataColumnStock.java
 *
 * Copyright (c) 2008 Operational Dynamics Consulting Pty Ltd
 * Copyright (c) 2008 Vreixo Formoso
 * 
 * The code in this file, and the library it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" plus the "Classpath Exception" (you may link to this code as a
 * library into other programs provided you don't make a derivation of it).
 * See the LICENCE file for the terms governing usage and redistribution.
 */
package org.gnome.gtk;

/**
 * A column containing a Stock icon in a TreeModel. See {@link DataColumn} for
 * the full discussion of the role of how to employ these. This column type is
 * used to drive display of images in TreeViews using
 * {@link CellRendererPixbuf CellRendererPixbuf}s.
 * 
 * @author Vreixo Formoso
 * @since 4.0.7
 */
public final class DataColumnStock extends DataColumn
{
    public DataColumnStock() {
        super(String.class);
    }
}
