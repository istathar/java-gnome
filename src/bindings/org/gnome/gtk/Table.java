/*
 * Table.java
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

/**
 * A Container which arranges child Widgets in particular rows and columns.
 * 
 * <p>
 * Table is <b>not</b> a spreadsheet Widget! For that you would need to have
 * (say) a sea of Entry Widgets that were all hooked up to behave the same
 * (notably to pass focus between them appropriately) and which were
 * individually constrained to each be the same size (at least by default),
 * etc. By contrast, Table is for laying out Widgets in a grid but where each
 * child can happily request the size it needs.
 * 
 * <p>
 * To be honest, this Widget is a pain in the ass to use because you have to
 * manually keep track of which <code>row,column</code> edges a Widget is to
 * be constrained by. In most cases you can achieve the same alignment effects
 * with far greater flexibility by using HBoxes nested in VBoxes and
 * controlling the size allocations via {@link SizeGroup}s.
 * 
 * @author Andrew Cowie
 * @since 4.0.6
 */
/*
 * TODO the documentation in this class still needs a lot of work.
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

    /**
     * Change the number of rows and columns in the Table.
     * 
     * @since 4.0.6
     */
    public void resize(int rows, int columns) {
        GtkTable.resize(this, rows, columns);
    }

    /**
     * Set the (extra) spacing to be between <code>column</code> and the one
     * adjacent to it.
     * 
     * @since 4.0.6
     */
    public void setColumnSpacing(int column, int spacing) {
        GtkTable.setColSpacing(this, column, spacing);
    }

    /**
     * Set the (extra) spacing to be between <code>row</code> and the one
     * following it.
     * 
     * @since 4.0.6
     */
    public void setRowSpacing(int row, int spacing) {
        GtkTable.setRowSpacing(this, row, spacing);
    }
}
