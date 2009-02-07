/*
 * Attribute.java
 *
 * Copyright (c) 2007-2009 Operational Dynamics Consulting Pty Ltd
 *
 * The code in this file, and the library it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" plus the "Classpath Exception" (you may link to this code as a
 * library into other programs provided you don't make a derivation of it).
 * See the LICENCE file for the terms governing usage and redistribution.
 */
package org.gnome.pango;

import org.gnome.glib.Boxed;

/**
 * The different text attribute manipulations you can do are analogous to
 * those found on FontDescription and TextTag. Indeed, this is the underlying
 * mechanism which powers TextView's rendering of rich text.
 * 
 * <p>
 * Examples of setting up Attributes include:
 * 
 * <pre>
 * desc = new FontDescription(&quot;DejaVu Serif, 9pt&quot;);
 * attr = new Attribute(desc);
 * </pre>
 * 
 * and
 * 
 * <pre>
 * attr = new Attribute(Style.ITALIC);
 * </pre>
 * 
 * <p>
 * See {@link AttributeList} for an example of using these on discrete parts
 * of the text to be rendered by a Layout.
 * 
 * <p>
 * <b>WARNING</b>:<br>
 * Once you've applied an Attribute to a specific range of text do not attempt
 * to reuse it.
 * 
 * <p>
 * <i>Pango Attributes have an internal ugliness which is that each one needs
 * to be told what offsets of text it applies to. The problem is that these
 * are in terms of UTF-8 bytes, which not something we have access to from
 * Java (nor would we want to expose such in our public API). We take care of
 * setting the offsets properly when you call</i>
 * {@link #setIndices(Layout, int, int) setIndices()}<i>, which is why you
 * have to have already set the text into the Layout.</i>
 * 
 * @author Andrew Cowie
 * @since 4.0.10
 */
public final class Attribute extends Boxed
{
    protected Attribute(long pointer) {
        super(pointer);
    }

    protected void release() {
        PangoAttribute.destroy(this);
    }

    /**
     * Create an Attribute that applies the given FontDescription. This is a
     * baseline; all the other Attributes will supersede settings established
     * here.
     * 
     * @since 4.0.10
     */
    public Attribute(FontDescription desc) {
        super(PangoAttribute.createAttributeFontDescription(desc));
    }

    /**
     * Create an Attribute that modifies Style.
     * 
     * @since 4.0.10
     */
    public Attribute(Style style) {
        super(PangoAttribute.createAttributeStyle(style));
    }

    /**
     * Set the foreground colour for the text. The colour parameters are in
     * the same [0,1] range as other Cairo drawing functions use.
     * 
     * @since 4.0.10
     */
    public Attribute(double red, double green, double blue) {
        super(PangoAttribute.createAttributeForeground((char) (red * 65535), (char) (green * 65535),
                (char) (blue * 65535)));
    }

    /**
     * Given the text already in a Pango Layout, a starting position, and a
     * width, set the indexes of this Attribute accordingly.
     * <code>offset</code> and <code>width</code> are in terms of Java
     * characters. The result of this call is that the <var>start_index</var>
     * and <var>end_index</var> properties of the specified Attribute will be
     * set.
     * 
     * <p>
     * By default an Attribute added to an AttributeList covers all the text
     * in whatever that AttributeList is applied to. If that's not what you
     * want, you need to tell each Attribute what range it covers using this
     * method.
     * 
     * @since 4.0.10
     */
    public void setIndices(Layout layout, int offset, int width) {
        PangoAttributeOverride.setIndexes(this, layout, offset, width);
    }
}
