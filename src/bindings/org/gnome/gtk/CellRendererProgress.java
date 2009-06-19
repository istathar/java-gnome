/*
 * CellRendererProgress.java
 *
 * Copyright (c) 2007-2009 Operational Dynamics Consulting Pty Ltd and Others
 *
 * The code in this file, and the library it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" plus the "Classpath Exception" (you may link to this code as a
 * library into other programs provided you don't make a derivation of it).
 * See the LICENCE file for the terms governing usage and redistribution.
 */
package org.gnome.gtk;

/**
 * Renderer a ProgressBar in a TreeViewColumn. TODO
 * 
 * @author Andrew Cowie
 * @author Serkan Kaba
 * @since 4.0.5
 */
public class CellRendererProgress extends CellRenderer
{
    /**
     * Construct a new CellRendererPixbuf.
     * 
     * @since 4.0.5
     */
    public CellRendererProgress(TreeViewColumn vertical) {
        super(GtkCellRendererProgress.createCellRendererProgress(), vertical, true);
    }
    
    /**
     * Indicate the DataColumn containing the plain text to render on the {@link ProgressBar}.
     * See {@link ProgressBar#setText(String)}.
     * 
     * @since 4.0.12
     */
    public void setText(DataColumnString column) {
        GtkCellLayout.addAttribute(vertical, this, "text", column.getOrdinal());
    }
    
    /**
     * Indicate the DataColumn containing the percentage of the {@link ProgressBar}.
     * 
     * @since 4.0.12
     */
    public void setValue(DataColumnInteger column) {
        GtkCellLayout.addAttribute(vertical, this, "value", column.getOrdinal());
    }
}
