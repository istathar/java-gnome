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
     * baseline; all the other Attributes will superceed settings established
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
     * Given a String, a starting point, and a width, set the
     * <var>start_index</var> and <var>end_index</var> of this Attribute.
     * 
     * @since <span style="color: red;">Not implemented</span>
     */
    void setIndexes(String text, int offset, int width) {
        throw new UnsupportedOperationException();
    }

    /**
     * Given the text already in a Pango Layout, a starting position, and a
     * width, set the indexes of this Attribute accodringly.
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
     * <p>
     * When programatically building up a piece of text for rendering since
     * you tend not to have the entire text of what you're going to render
     * with this Layout but instead have pieces you're going to build up one
     * at a time and each piece's respective markup. If that's the case for
     * you, use our {@link TextBuilder} wrapper class instead.
     * 
     * <p>
     * <i>In fact, that's the only way we support doing this right now.</i>
     * 
     * @since <span style="color: red;">Not implemented</span>
     */
    /*
     * This works great, but if we're going to expose this we need to expose
     * AttributeList publicly as well.
     */
    void setIndexes(Layout layout, int offset, int width) {
        PangoAttributeOverride.setIndexes(this, layout, offset, width);
    }
}
