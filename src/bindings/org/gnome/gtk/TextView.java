/*
 * TextView.java
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
 * A multiline text widget, based on a data model.
 *
 * 
 * {@see TextBuffer}
 * 
 * @author Sebastian Mancke
 * @since 4.0.3
 */
public class TextView extends Container
{
    protected TextView(long pointer) {
        super(pointer);
    }

    /**
     * Construct a TextView with a default TextBuffer
     */
    public TextView() {
        super(GtkTextView.createTextView());
    }

    /**
     * Returns the TextBuffer of this widget
     */
    public TextBuffer getBuffer() {
        return GtkTextView.getBuffer(this);
    }

    /**
     * Set the editable state of the TextView
     *
     * @param editable <code>true</code> makes this TextView editable, <code>false</code> makes it read only
     */
    public void setEditable(boolean editable) {
        GtkTextView.setEditable(this, editable);
    }

    /**
     * Returns the editable state of this widget
     * @return <code>true</code> if this TextView is editable, <code>false</code> otherwise
     */
    public boolean isEditable() {
        return GtkTextView.getEditable(this);
    }
}
