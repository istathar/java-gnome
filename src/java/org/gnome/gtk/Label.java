/*
 * Label.java
 *
 * Copyright (c) 2006 Operational Dynamics Consulting Pty Ltd and Others
 * 
 * The code in this file, and the library it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" plus the "Classpath Exception" (you may link to this code as a
 * library into other programs provided you don't make a derivation of it).
 * See the LICENCE file for the terms governing usage and redistribution.
 */
package org.gnome.gtk;

/**
 * A Widget that displays a small amount of text.
 * 
 * <p>
 * Labels are the backbone of any Window. They are frequently used to identify
 * other controls with names, as headings in Windows, and are the building
 * blocks that Menus and Buttons are made up of. All the difficult parts about
 * rendering text are taken care of here, such as text direction, fonts. And,
 * of course, you can enable them to allow their text to be copied.
 * <p>
 * Labels can display normal text or text that has been formatted with Pango
 * markup. FIXME with a reference to our Pango guide page.
 * <p>
 * Although you can pack multiple lines into a Label, there does come a point
 * when the amount of text you're trying to show gets out of hand. At that
 * point you might want to investigate the {@link TextView TextView} Widget.
 * 
 * @author Andrew Cowie
 */
public class Label extends Misc
{

    protected Label(long pointer) {
        super(pointer);
    }

    /**
     * Create a Label with the specified text. This Label will be an ordinary
     * one and will interpret the argument as plain unformatted text. Note
     * that if you use this constructor you can later switch the Label to
     * interpreting the text as Pango markup by calling
     * {@link #setUseMarkup(boolean) setUseMarkup(true)}.
     * 
     * @param text
     *            the text you wish on the Label.
     */
    public Label(String text) {
        super(GtkLabel.createLabel(text));
    }

    /**
     * Set the text showing in the Label.
     * 
     * @param text
     *            If the Label has been told to interpret Pango markup with
     *            {@link #setUseMarkup(boolean) setUseMarkup(true)}, then any
     *            markup included in text will be interpreted as such.
     */
    public void setLabel(String text) {
        GtkLabel.setLabel(this, text);
    }
}
