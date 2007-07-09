/*
 * ComboBox.java
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
 * ComboBox is a widget used to choose from a list of items.
 * 
 * <p>The Gtk ComboBox uses a TreeModel to store the items. Although, 
 *  there is a simple variant, which only maged strings as values.</p>
 *
 */
public class ComboBox extends Bin implements CellEditable, CellLayout
{
    protected ComboBox(long pointer) {
        super(pointer);
    }

    /**
     * Constructs a new ComboBox with a default Model     
     */
    public ComboBox() {
        super(GtkComboBox.createComboBox());
    }

    /**
     * Constructs a new ComboBox with a default Model. 
     *
     * <p>If the flag <code>textItemsOnly</code> is set to 
     *    <code>true</code>, a simple model will be used, which 
     *    is suitable for managing strings, only. In this case,
     *    manipulation of the ComboBox should only be done by the
     *    convenience methods: <code>appendText(), inserText(), 
     *    prependText(), getActiveText()</code>
     * </p>
     *
     * @param textItemsOnly indicates, if a simple model for textitem should be created
     */
    public ComboBox(boolean textItemsOnly) {
        super(textItemsOnly ? GtkComboBox.createComboBoxText() : GtkComboBox.createComboBox());
    }

    /**
     * Returns the active index of the list
     */
    public int getActive() {
        return GtkComboBox.getActive(this);
    }

    /**
     * Sets the active index for the list
     *
     * @param active Index to activate
     */
    public void setActive(int active) {
        GtkComboBox.setActive(this, active);
    }

    /**
     * Appends a text item to the list.
     * This method should only be used, if the ComboBox was
     * created with <code>textItemsOnly</code>.
     */
    public void appendText(String text) {
        GtkComboBox.appendText(this, text);
    }

    /**
     * Appends a text item at the supplied position.
     * This method should only be used, if the ComboBox was
     * created with <code>textItemsOnly</code>.
     *
     * @param position The position beginning from 0, where the new item should be placed
     */
    public void insertText(int position, String text) {
        GtkComboBox.insertText(this, position, text);
    }

    /**
     * Prepends a text item to the list.
     * This method should only be used, if the ComboBox was
     * created with <code>textItemsOnly</code>.
     */
    public void prependText(String text) {
        GtkComboBox.prependText(this, text);
    }

    /**
     * Returns the text of the active item.
     * This method should only be used, if the ComboBox was
     * created with <code>textItemsOnly</code>.
     */
    public String getActiveText() {
        return GtkComboBox.getActiveText(this);
    }

    /**
     * Handler interface for a changed event. This event occurs
     * whenever another item gets is selected.
     */
    public interface CHANGED extends GtkComboBox.CHANGED {
        // TODO: should we repeat the interface here?
        // public void onChanged(ComboBox sourceObject);
    }    

    /** 
     * Connects an {@see CHANGED} handler to the widget.
     */
    public void connect(CHANGED handler) {
        GtkComboBox.connect(this, handler);
    }

}
