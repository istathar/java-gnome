/*
 * Notebook.java
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
 * A tabbed notebook container.
 * 
 * @author Sebastian Mancke
 * @since 4.0.3
 */
public class Notebook extends Container
{
    protected Notebook(long pointer) {
        super(pointer);
    }

    /**
     * Constructs a new notebook
     */
    public Notebook() {
        super(GtkNotebook.createNotebook());
    }

    /**
     * Append one page to the notebook
     * 
     * @param child The widget shown on the notebook page
     * @param tabLabel The label widget for the tab
     */
    public void appendPage(Widget child, Widget tabLabel) {
        GtkNotebook.appendPage(this, child, tabLabel);
    }

    /**
     * Prepend one page to the notebook
     * 
     * @param child The widget shown on the notebook page
     * @param tabLabel The label widget for the tab
     */
    public void prependPage(Widget child, Widget tabLabel) {
        GtkNotebook.prependPage(this, child, tabLabel);
    }

    /**
     * Insert one page to the supplied position on the notebook
     * 
     * @param child The widget shown on the notebook page
     * @param tabLabel The label widget for the tab
     * @param pos The possition to insert the page
     */
    public void insertPage(Widget child, Widget tabLabel, int pos) {
        GtkNotebook.insertPage(this, child, tabLabel, pos);
    }


    /**
     * Remove the tab at the supplied position
     * 
     * @param pos Position of the page to remove
     */
    public void removePage(int pos) {
        GtkNotebook.removePage(this, pos);
    }


    /**
     * Activate/show the page at the supplied position
     *
     * @param pos Position of the page to activate
     */
    public void setCurrentPage(int pos) {
        GtkNotebook.setCurrentPage(this, pos);
    }

    /**
     * The handler interface for notification of changes in the current page.
     */
    public interface CHANGE_CURRENT_PAGE extends GtkNotebook.CHANGE_CURRENT_PAGE {
        // TODO: should we repeat the interface here?
        // public void onChangeCurrentPage(Notebook sourceObject, int offset);

    }

    /** 
     * Connects an {@see CHANGE_CURRENT_PAGE} handler to the widget.
     */
    public void connect(CHANGE_CURRENT_PAGE handler) {
        GtkNotebook.connect(this, handler);
    }
}
