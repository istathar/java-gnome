/*
 * Clipboard.java
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

import org.gnome.gdk.Pixbuf;
import org.gnome.glib.Object;

/*
 * FIXME: A description of the class is needed here.
 *
 * @author Serkan Kaba
 * @since 4.0.10
 */
public class Clipboard extends Object
{
    protected Clipboard(long pointer) {
        super(pointer);
    }
    
    /**
     * Returns the default clipboard.
     *
     * @since 4.0.10
     */
    public static Clipboard getDefault() {
        return GtkClipboardOverride.get();
    }
    
    /**
     * Copy a text to clipboard.
     * 
     * @param text
     *            Text to be copied.
     * @since 4.0.10
     */
    public void setText(String text) {
        GtkClipboard.setText(this, text, -1);
    }
    
    /**
     * Copy an image to clipboard.
     * 
     * @param pixbuf
     *            Image to be copied.
     * @since 4.0.10
     */
    public void setImage(Pixbuf pixbuf) {
        GtkClipboard.setImage(this, pixbuf);
    }
}
