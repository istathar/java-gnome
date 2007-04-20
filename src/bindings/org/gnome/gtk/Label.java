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
 * @author Srichand Pendyala
 * @since 4.0.1
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
     * @since 4.0.1
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
     * @since 4.0.1
     */
    public void setLabel(String text) {
        GtkLabel.setLabel(this, text);
    }

    /**
     * Get the text showing in the Label, including any characters which
     * indicate Pango markup syntax and embedded mnemonic underline characters
     * that may be present. Contrast with {@link #getText() getText()} which
     * returns the text unadorned.
     * 
     * @since 4.0.1
     */
    public String getLabel() {
        return GtkLabel.getLabel(this);
    }

    /**
     * Get the text showing in the Label, but with any Pango markup stripped
     * away. This is useful if you've applied some fancy formatting but just
     * want to find out the actual words that appear to the user. It also
     * strips away any embedded underlines indicating mnemonics. If you need
     * the raw text including markup, then you want
     * {@link #getLabel() getLabel()}.
     * 
     * @since 4.0.1
     */
    public String getText() {
        return GtkLabel.getText(this);
    }

    /**
     * Set whether the text showing in the Label is to be parsed as containing
     * markup in Pango's text markup language. Using this allows Labels to be
     * created with expressive formatting considerably more advanced than a
     * simple line of text.
     * 
     * @param setting
     *            If setting is true, then any markup included in the text is
     *            interpreted as such. If its set to false, markup is ignored
     *            and included as-is.
     * @since 4.0.1
     */
    public void setUseMarkup(boolean setting) {
        GtkLabel.setUseMarkup(this, setting);
    }

    /**
     * Sets the angle of rotation for the Label. The angle is measured in
     * degrees from the horizontal, going counter-clockwise. An angle of
     * 90&#176; reads from bottom to top, an angle of 270&#176; from top to
     * bottom. The angle setting for the Label will be ignored if the Label is
     * selectable, wrapped, or ellipsized.
     * 
     * @param angle
     *            The angle that the baseline of the Label's text makes with
     *            the horizontal. The valid range (as you'd expect) is from
     *            0&#176; through 360&#176;.
     * 
     * @since 4.0.1
     */
    public void setAngle(double angle) {
        GtkLabel.setAngle(this, angle);
    }

    /**
     * Get the current angle of the Label. An angle of 90&#176; means the text
     * reads in an upwards direction (ie from bottom to top), whereas an angle
     * of 270&#176; means the text reads in a downwards direction (ie from top
     * to bottom).
     * 
     * @since 4.0.1
     */
    public double getAngle() {
        return GtkLabel.getAngle(this);
    }
}
