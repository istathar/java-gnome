/*
 * java-gnome, a UI library for writing GTK and GNOME programs from Java!
 *
 * Copyright © 2007-2010 Operational Dynamics Consulting, Pty Ltd
 *
 * The code in this file, and the program it is a part of, is made available
 * to you by its authors as open source software: you can redistribute it
 * and/or modify it under the terms of the GNU General Public License version
 * 2 ("GPL") as published by the Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GPL for more details.
 *
 * You should have received a copy of the GPL along with this program. If not,
 * see http://www.gnu.org/licenses/. The authors of this program may be
 * contacted through http://java-gnome.sourceforge.net/.
 *
 * Linking this library statically or dynamically with other modules is making
 * a combined work based on this library. Thus, the terms and conditions of
 * the GPL cover the whole combination. As a special exception (the
 * "Classpath Exception"), the copyright holders of this library give you
 * permission to link this library with independent modules to produce an
 * executable, regardless of the license terms of these independent modules,
 * and to copy and distribute the resulting executable under terms of your
 * choice, provided that you also meet, for each linked independent module,
 * the terms and conditions of the license of that module. An independent
 * module is a module which is not derived from or based on this library. If
 * you modify this library, you may extend the Classpath Exception to your
 * version of the library, but you are not obligated to do so. If you do not
 * wish to do so, delete this exception statement from your version.
 */
package org.gnome.pango;

import org.gnome.glib.Boxed;

/**
 * A (single) specific formatting or font to be applied to a range of Text.
 * 
 * <p>
 * Examples of setting up Attributes include:
 * 
 * <pre>
 * attr = new StyleAttribute(Style.ITALIC);
 * </pre>
 * 
 * although many settings can be compounded by setting a FontDescription:
 * 
 * <pre>
 * desc = new FontDescription(&quot;DejaVu Serif, 9pt&quot;);
 * attr = new FontDescriptionAttribute(desc);
 * </pre>
 * 
 * in general it's one Attribute per characteristic. You then aggregate these
 * together in an AttributeList and then apply them to a Layout. See
 * {@link AttributeList} for an example of using these on discrete parts of
 * text.
 * 
 * <p>
 * <b>WARNING</b>:<br>
 * When coding with Attributes be aware of the following restrictions:
 * <ul>
 * <li>Once you have assigned an Attribute to a specific range of text in a
 * given Layout you must not attempt to reuse it.
 * 
 * <li>Once you have inserted an Attribute into an AttributeList it is
 * "consumed" by that list and you must not attempt to reuse it.
 * </ul>
 * 
 * <p>
 * <i> The different text attribute manipulations you can do are analogous to
 * those found on FontDescription and TextTag. Indeed, Pango's Attributes are
 * the underlying mechanism powering TextView and Label's rendering of rich
 * markup.</i>
 * 
 * 
 * <p>
 * <i>Pango Attributes have an internal ugliness which is that each one needs
 * to be told the offsets of text it applies to. The problem is that these are
 * in terms of UTF-8 bytes, which not something we have access to from Java
 * (nor would we want to expose such in our public API). We take care of
 * setting the start and end points properly when you call</i>
 * {@link Layout#setAttributes(AttributeList) setAttributes()}<i>, but you
 * have to have already set the text into the Layout for us to be able to do
 * so, obviously.</i>
 * 
 * @author Andrew Cowie
 * @since 4.0.10
 */
/*
 * Apparently there is a facility for developers to extend Attributes, but we
 * haven't needed to expose that as yet.
 */
public abstract class Attribute extends Boxed
{
    private int offset = 0;

    /*
     * We convert this to G_MAXUINT in PangoAttributeOverride.
     */
    private int width = Integer.MIN_VALUE;

    private boolean inserted;

    protected Attribute(long pointer) {
        super(pointer);
        inserted = false;
    }

    protected void release() {
        /*
         * There is no function in Pango to increment the reference count of a
         * PangoAttribute. In fact, PangoAttributes do not have reference
         * counts (which is not surprising; they're just structs). However,
         * things get ugly because PangoAttrList assumes responsibility for
         * disposing of a PangoAttribute once you've inserted it into such a
         * list - but there's no way to tell externally that this has
         * happened.
         * 
         * While we could just ignore this, we don't leak™, and so we go to
         * the trouble of tracking whether or not this Attribute has been
         * inserted, and thereby whether we are still responsible for it.
         * 
         * There is every possibility that
         */
        if (!inserted) {
            PangoAttribute.destroy(this);
        }
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
     * @since 4.0.11
     */
    public void setIndices(int offset, int width) {
        this.offset = offset;
        this.width = width;
    }

    final int getOffset() {
        return offset;
    }

    final int getWidth() {
        return width;
    }

    final boolean isInserted() {
        return inserted;
    }

    final void markInserted() {
        inserted = true;
    }
}
