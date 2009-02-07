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

/*
 * FIXME this is a placeholder stub for what will become the public API for
 * this type. Replace this comment with appropriate javadoc including author
 * and since tags. Note that the class may need to be made abstract, implement
 * interfaces, or even have its parent changed. No API stability guarantees
 * are made about this class until it has been reviewed by a hacker and this
 * comment has been replaced.
 */
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
     * @since <span style="color: red;">Unstable</span>
     */
    public int getStartIndex() {
        return PangoAttribute.getStartIndex(this);
    }

    /**
     * @since <span style="color: red;">Unstable</span>
     */
    public int getEndIndex() {
        return PangoAttribute.getEndIndex(this);
    }

    /**
     * Given a String, a starting point, and a width, set the
     * <var>start_index</var> and <var>end_index</var> of this Attribute.
     * 
     * @since <span style="color: red;">Unstable</span>
     */
    public void setIndexes(String text, int offset, int width) {
        throw new UnsupportedOperationException();
    }

    /**
     * Set the <var>start_index</var> and <var>end_index</var> properties of
     * the specified Attribute. Unfortunately you need to tell Attributes what
     * range they cover. So, given the text of a Pango Layout, a starting
     * offset, and a width, set the indexes.
     * 
     * <p>
     * <i>The <code>offset,length</code> pattern follows that found in most of
     * core Java, and tends to be more useful as you are building a
     * Layout.</i>
     * 
     * @since 4.0.10
     */
    public void setIndexes(Layout layout, int offset, int width) {
        PangoAttributeOverride.setIndexes(this, layout, offset, width);
    }

}
