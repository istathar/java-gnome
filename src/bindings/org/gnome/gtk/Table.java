/*
 * Table.java
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

/*
 * FIXME this is a placeholder stub for what will become the public API for
 * this type. Replace this comment with appropriate javadoc including author
 * and since tags. Note that the class may need to be made abstract, implement
 * interfaces, or even have its parent changed. No API stability guarantees
 * are made about this class until it has been reviewed by a hacker and this
 * comment has been replaced.
 */
/**
 * Table is <b>not</b> a spreadsheet Widget!
 * 
 * <p>
 * To be honest, this Widget is a pain in the ass to use; in most cases you
 * can achieve the same alignment effects with far greater flexibility by
 * using HBoxes nested in VBoxes and controlling the size allocations via
 * SizeGroups.
 * 
 * @author Andrew Cowie
 * @since 4.0.6
 */
public class Table extends Container
{
    protected Table(long pointer) {
        super(pointer);
    }

    /**
     * Create a new Table. The Container will be configured to lay out
     * <code>n</code> rows of <code>n</code> columns of child Widgets.
     * 
     * @param homogeneous
     *            If <code>true</code>, all cells are sized to that
     *            requested by the largest Widget in the Table.
     * @since 4.0.6
     */
    public Table(int rows, int columns, boolean homogeneous) {
        super(GtkTable.createTable(rows, columns, homogeneous));
    }

    /**
     * Add a child Widget to this Table. This is a convenience method where
     * the more esoteric parameters of the full
     * {@link #attach(Widget, int, int, int, int, AttachOptions, AttachOptions, int, int) attach()}
     * are given appropriate default values.
     * 
     * <p>
     * Each of the parameters refer to the column or row to which the Widget
     * being added will be anchored. To put a Widget at the fourth column from
     * the left, second row down, you would do:
     * 
     * <pre>
     * table.attach(child, 3, 4, 1, 2);
     * </pre>
     * 
     * As alluded to in the class description, this is quite finicky and worse
     * is error prone.
     * 
     * @since 4.0.6
     */
    public void attach(Widget child, int leftAttach, int rightAttach, int topAttach, int bottomAttach) {
        GtkTable.attachDefaults(this, child, leftAttach, rightAttach, topAttach, bottomAttach);
    }

    /*
     * TODO document and make public.
     */
    void attach(Widget child, int leftAttach, int rightAttach, int topAttach, int bottomAttach,
            AttachOptions xoptions, AttachOptions yoptions, int xpadding, int ypadding) {
        GtkTable.attach(this, child, leftAttach, rightAttach, topAttach, bottomAttach, xoptions,
                yoptions, xpadding, ypadding);
    }
}
