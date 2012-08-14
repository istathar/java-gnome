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
 * A line within a Layout. Once a Layout has been given text to lay out,
 * individual lines within it can be accessed via the methods on this class.
 * 
 * <pre>
 * lines = layout.getLinesReadonly();
 * 
 * x = leftMargin;
 * y = topMargin + rect.getAscent();
 * 
 * for (i = 0; i &lt; lines.length; i++) {
 *     rect = lines[i].getExtentsLogical();
 *     y += rect.getHeight();
 * 
 *     cr.moveTo(x, y);
 *     cr.showLayout(lines[i]);
 * }
 * </pre>
 * 
 * which of course is the hard way of doing:
 * 
 * <pre>
 * cr.showLayout(layout);
 * </pre>
 * 
 * but gives you control over the individual lines of the paragraph which is
 * necessary when doing paginated layout and worrying about available vertical
 * space.
 * 
 * @author Andrew Cowie
 * @since 4.0.10
 */
public final class LayoutLine extends Boxed
{
    protected LayoutLine(long pointer) {
        super(pointer);
        /*
         * FYI, we changed our .defs data to have the generated code wrapping
         * pango_layout_line_ref() to return void rather than returning the
         * PangoLayoutLine pointer. Otherwise we get an endless loop of
         * init(ling) -> ref() -> boxedFor() -> createPointer() -> init(long)
         * -> ref() which blows out the stack. The point is to increase the
         * ref count safely; we don't need the return value.
         */
        PangoLayoutLine.ref(this);
    }

    protected void release() {
        PangoLayoutLine.unref(this);
    }

    public Rectangle getExtentsInk() {
        final Rectangle result;

        result = new Rectangle();

        PangoLayoutLine.getExtents(this, result, null);

        return result;
    }

    /**
     * Get a Rectangle describing the logical extents calculated for this line
     * of rendered text.
     * 
     * <p>
     * The <code>x</code>, <code>y</code>, and <code>height</code> parameters
     * of these Rectangles are the same for each LayoutLine in a given Layout;
     * the <var>width</var> will be variable (unless you have evenly justified
     * the text, and even then last line of each paragraph will be less than
     * the <var>width</var> of the others).
     * 
     * <p>
     * <b>Note</b><br>
     * It is a common mistake to assume that the origin of the Pango
     * co-ordinate space is the top-right corner of an extents Rectangle. This
     * is not the case; the vertical origin is at the base line.
     * 
     * <p>
     * The {@link org.freedesktop.cairo.Context#showLayout(LayoutLine)
     * showLayout()} method that takes a LayoutLine starts its drawing by
     * placing its Rectangle's origin at the current Context point. So to
     * render consistently spaced lines of text you will need to move the
     * Context point <b>down</b> by the value of the ascent (which is the
     * negative of the <code>y</code> co-ordinate describing top of the
     * rectangle from the origin):
     * 
     * <pre>
     * rect = line.getExtentsLogical();
     * 
     * h = leftMargin;
     * v = topMargin + rect.getAscent() + lineNumber * rect.getHeight();
     * 
     * cr.moveTo(h, v);
     * cr.showLayout(line);
     * </pre>
     * 
     * @since 4.0.10
     */
    /*
     * TODO are x, y, height actually going to be the same for all LayoutLines
     * in a given Layout? That's seems to make sense, and is indeed what
     * testing observed. Could be wrong, of course.
     */
    public Rectangle getExtentsLogical() {
        final Rectangle result;

        result = new Rectangle();

        PangoLayoutLine.getExtents(this, null, result);

        return result;
    }

    /**
     * Get the character position into the parent Layout that this LayoutLine
     * begins at.
     * 
     * <p>
     * <i>The underlying PangoLayout and PangoLayoutLine structures work in
     * byte widths; we convert to character offsets.</i>
     * 
     * @since 4.0.14
     */
    public int getStartIndex() {
        return PangoLayoutLineOverride.getStartIndex(this);
    }

    /**
     * Get the width of this LayoutLine, in characters.
     * 
     * @since 4.0.14
     */
    public int getLength() {
        return PangoLayoutLineOverride.getLength(this);
    }
}
