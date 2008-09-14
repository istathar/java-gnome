/*
 * Table.java
 *
 * Copyright (c) 2007-2008 Operational Dynamics Consulting Pty Ltd, and Others
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
     *            If <code>true</code>, all cells are sized to that requested
     *            by the largest Widget in the Table.
     * @since 4.0.6
     */
    public Table(int rows, int columns, boolean homogeneous) {
        super(GtkTable.createTable(rows, columns, homogeneous));
    }

    /**
     * Add a child Widget to this Table. This is a convenience method where
     * the more esoteric parameters of the full
     * {@link #attach(Widget, int, int, int, int, AttachOptions, AttachOptions, int, int)
     * attach()} are given appropriate default values.
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

    /**
     * Like {@link #attach(Widget, int, int, int, int)} but with finer 
     * layout control for the added widget. This is mostly done by using
     * the {@link AttachOptions}.
     * <p>
     * To define that a widget shall grow on the x-axis, but keep the size
     * on the y-axis you would do:
     * </p>
     * <pre>
     * table.attach(child, 3, 4, 1, 2, AttachOptions.EXPAND, AttachOptions.SHRINK, 0,0);
     * </pre>
     * <p>
     * Another common problem is that you have widgets of different sizes 
     * (e.g. labels in a column). If a widget is smaller than the required
     * space additional spaces are added to the sides, so finally the 
     * smaller widget is centered compared to the larger widget. To avoid
     * that you a) need to encapsulate the widget in an {@link Alignment} 
     * container and b) attach it using AttachOptions.FILL, so that instead
     * of additional spaces to the sides the <code>Alignment</code> 
     * container may decide how to distribute it.
     * </p>
     * <p>
     * The following example left-aligns the child widget within its cell,
     * while it is vertically centered.
     * </p>
     * <pre>
     * Alignment aligned = new Alignment(Alignment.LEFT, Alignment.CENTER, 1f,1f, child);
     * table.attach(aligned, 3, 4, 1, 2, AttachOptions.FILL, AttachOptions.FILL, 0,0);
     * </pre>
     * 
     * @since 4.0.10
     */
    public void attach(Widget child, int leftAttach, int rightAttach, int topAttach, int bottomAttach,
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

    /**
     * Set the (extra) spacing between all columns. Overwrites previous
     * settings or those made by the two argument form of
     * {@link #setColumnSpacing(int,int) setColumnSpacing()}.
     * 
     * @since 4.0.8
     */
    public void setColumnSpacing(int spacing) {
        GtkTable.setColSpacings(this, spacing);
    }

    /**
     * Set the (extra) spacing between all rows. Overwrites previous settings
     * or those made by {@link #setRowSpacing(int,int)}.
     * 
     * @since 4.0.8
     */
    public void setRowSpacing(int spacing) {
        GtkTable.setRowSpacings(this, spacing);
    }
}
