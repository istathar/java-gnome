/*
 * Entry.java
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
 * A single line text entry field.
 * 
 * 
 * @author Sebastian Mancke
 * @since 4.0.3
 */
public class Entry extends Widget implements Editable, CellEditable
{
    protected Entry(long pointer) {
        super(pointer);
    }

    /**
     * Constructs a new Entry
     */
    public Entry() {
        this(GtkEntry.createEntry());
    }

    /**
     * Constructs a new Entry initialited with the specified text
     */
    public Entry(String text) {
        this(GtkEntry.createEntry());
        setText(text);
    }

    /**
     * Set the complete text
     */
    public void setText(String text) {
        GtkEntry.setText(this, text);
    }

    /**
     * Get the complete text
     */
    public String getText() {
        return GtkEntry.getText(this);
    }

    /**
     * Sets the maximum count of characters, the user is able to enter.
     */
    public void setMaxLength(int maxLength) {
        GtkEntry.setMaxLength(this, maxLength);
    }

    /**
     * Returns the maximum count of characters, the user is able to enter.
     */
    public int getMaxLength() {
        return GtkEntry.getMaxLength(this);
    }        
    
    /**
     * Set the chars in the entry to be visible or not.
     * If they are not visible, they are shown with a '*', 
     * like in usual password fields.
     *
     * @param visibleChars, true for showing the chars, false for hiding chars
     */
    public void setVisibleChars(boolean visibleChars) {
        GtkEntry.setVisibility(this, visibleChars);
    }

    /**
     * Returns, if the chars in the entry are visible or hidden.
     * If they are not visible, they are shown with a '*', 
     * like in usual password fields.
     *
     * @return true for shown chars, false for hidden chars
     */
    public boolean isVisibleChars() {
        return GtkEntry.getVisibility(this);
    }
    
    /**
     * Set the entry to be editable by the user or not.
     */
    public void setEditable(boolean editable) {
        GtkEntry.setEditable(this, editable);
    }

    /** 
     * The handler interface for an activation.
     * An activation is usual triggered, when the user hits the return key in the entry.
     */
    public interface ACTIVATE extends GtkEntry.ACTIVATE {
        // TODO: should we repeat the interface here?
        // public void onActivate(Entry sourceObject);        
    }
    
    /** 
     * Connects an {@see ACTIVATE} handler to the widget.
     */
    public void connect(ACTIVATE handler) {
        GtkEntry.connect(this, handler);
    }
}
