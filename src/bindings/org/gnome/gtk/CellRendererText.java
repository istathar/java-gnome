/*
 * CellRendererText.java
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
 * Render textual data into a TreeViewColumn. This is the most commonly used
 * CellRenderer, used to present Strings. The fundamental mapping method is
 * {@link #setText(DataColumnString) setText()} which you use to indicate the
 * particular DataColumnString from the underlying TreeModel which will
 * provide the Strings.
 * 
 * <p>
 * CellRendererTexts are also frequently used to present numerical data when
 * it is desired to apply formatting to that data (converting it to a String
 * in the process) before rendering it. If you do that, you'll probably want
 * to use a second (numerically typed) DataColumn to indicate the appropriate
 * sorting order to be applied if that TreeViewColumn is clicked and sorting
 * is turned on for it; see
 * {@link TreeViewColumn#setSortColumn(DataColumn) setSortColumn()}.
 * 
 * @author Andrew Cowie
 * @since 4.0.5
 */
public class CellRendererText extends CellRenderer
{
    /*
     * protected <init>(long) constructor removed deliberately, replaced with
     * this one.
     * TODO mmm, we should test if add an expand parameter!
     */
    protected CellRendererText(long pointer, CellLayout vertical) {
        super(pointer, vertical, true);
    }

    /**
     * Construct a new CellRendererText. Specify the TreeViewColumn it will
     * belong to and from whose parent TreeView has the TreeModel where the
     * columns of data will come from.
     */
    public CellRendererText(CellLayout vertical) {
        super(GtkCellRendererText.createCellRendererText(), vertical, true);
    }

    /**
     * Indicate the DataColumn containing the plain text to render. This will
     * map the DataColumn that will be used to provide the text to be rendered
     * in the TreeViewColumn this CellRenderer is packed into. This is by far
     * and away the most commonly used mapping in the entire TreeView API.
     * 
     * <p>
     * If you want to use Pango markup to format the text being rendered, call
     * {@link #setMarkup(DataColumnString) setMarkup()} instead.
     */
    public void setText(DataColumnString column) {
        GtkCellLayout.addAttribute(vertical, this, "text", column.getOrdinal());
    }

    /**
     * Indicate the DataColumn containing Pango markup to render as text.
     * 
     * <p>
     * The String in the DataColumn being rendered will be interpreted as
     * containing markup in Pango's text markup language. Using this allows
     * you to make very expressive presentations within TreeView cells.
     * 
     * <p>
     * Using this (instead of {@link #setText(DataColumnString) setText()})
     * has the same effect as using a Label with
     * {@link Label#setUseMarkup(boolean) setUseMarkup(true)} in normal
     * layouts.
     */
    public void setMarkup(DataColumnString column) {
        GtkCellLayout.addAttribute(vertical, this, "markup", column.getOrdinal());
    }

    /**
     * Indicate the DataColumn containing the name of the foreground colour to
     * used when rendering text.
     */
    public void setForeground(DataColumnString column) {
        GtkCellLayout.addAttribute(vertical, this, "foreground", column.getOrdinal());
    }
}
