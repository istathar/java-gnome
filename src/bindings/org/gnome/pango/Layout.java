/*
 * Layout.java
 *
 * Copyright (c) 2007-2008 Operational Dynamics Consulting Pty Ltd
 * Copyright (c) 2008      Vreixo Formoso
 *
 * The code in this file, and the library it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" plus the "Classpath Exception" (you may link to this code as a
 * library into other programs provided you don't make a derivation of it).
 * See the LICENCE file for the terms governing usage and redistribution.
 */
package org.gnome.pango;

import org.gnome.glib.Object;
import org.gnome.gtk.Widget;

/**
 * A Layout represents a paragraph (or paragraphs) of text, together with its
 * attributes.
 * 
 * <p>
 * Drawing is done in with Cairo; you get a Layout by passing the Cairo
 * drawing Context you're currently working in to the constructor. If you're
 * drawing a Widget, you'll be doing so in a <code>Widget.ExposeEvent</code>
 * handler where you'll typically see:
 * 
 * <pre>
 * w.connect(new Widget.ExposeEvent() {
 *     public boolean onExposeEvent(Widget source, EventExpose event) {
 *         final Context cr;
 *         final Layout layout;
 * 
 *         cr = new Context(source.getWindow());
 *         layout = new Layout(cr);
 * 
 *         // use layout to lay out the text you wish to draw
 * 
 *         cr.showLayout(layout);
 *     }
 * });
 * </pre>
 * 
 * @author Vreixo Formoso
 * @author Andrew Cowie
 * @since 4.0.9
 */
public class Layout extends Object
{
    /**
     * Conversion factor to go from "Cairo Units" to "Pango Units".
     */
    /*
     * The Pango library uses a fixed point system, scaled up by this
     * constant; Cairo on the other hand uses doubles. Since all the drawing
     * we're doing with Pango will be in the context of a Cairo drawing
     * operation, we expose our API as doubles to match Cairo, and quietly
     * convert internally.
     * 
     * Seeing as how Pango draws on Cairo, it'd be nice if Pango just exposed
     * the doubles and was done with it.
     * 
     * FIXME retreive from native as this is subject to change in the future.
     */
    private static final int PANGO_SCALE = 1024;

    protected Layout(long pointer) {
        super(pointer);
    }

    /**
     * Create a new Layout configured to draw using the given Cairo Context
     * backend.
     * 
     * <p>
     * This Layout can be used to set up the text to draw and its properties.
     * 
     * <p>
     * To actually draw the text, you will need to call
     * {@link org.freedesktop.cairo.Context#showLayout(Layout) showLayout()}
     * method on the Cairo Context.
     * 
     * TODO If you change the transformation or target surface for context,
     * you need to call pango_cairo_update_layout()
     * 
     * @since 4.0.9
     */
    public Layout(org.freedesktop.cairo.Context context) {
        super(PangoLayout.createLayout(context));
    }

    /**
     * Sets the text of the Layout. This is the text that will be draw.
     * 
     * <p>
     * If you wish to pass text enhanced with Pango Markup, use
     * {@link #setMarkup(String) setMarkup()} instead.
     * 
     * @since 4.0.9
     */
    public void setText(String text) {
        /*
         * we cannot use text.length(), as the length is in bytes, so we use
         * -1 to make Pango compute it.
         */
        PangoLayout.setText(this, text, -1);
    }

    /**
     * Set the text of this Layout. Its format is specified using Pango Markup
     * format [TODO we need to document pango markup somewhere].
     * 
     * If you're just passing in normal straight-forward unformatted text, use
     * {@link #setText(String) setText()}.
     * 
     * @since 4.0.9
     */
    public void setMarkup(String markup) {
        /*
         * we cannot use text.length(), as the length is in bytes, so we use
         * -1 to make Pango compute it.
         */
        PangoLayout.setMarkup(this, markup, -1);
    }

    /**
     * Get the width of the Layout. This is the width of the layout text,
     * taking its format into account (for example, the size of the Font will
     * influence the final size!).
     * 
     * <p>
     * Note that this is not necessarily related with the line wrap width you
     * set with {@link #setWidth(double) setWidth()} method.
     * 
     * @since 4.0.9
     */
    public double getSizeWidth() {
        int[] width = new int[1];
        PangoLayout.getSize(this, width, null);
        return ((double) width[0]) / PANGO_SCALE;
    }

    /**
     * Get the height of the Layout. This is the height of the layout text,
     * taking its format into account (for example, the size of the Font will
     * influence the final size!).
     * 
     * @since 4.0.9
     */
    public double getSizeHeight() {
        int[] height = new int[1];
        PangoLayout.getSize(this, null, height);
        return ((double) height[0]) / PANGO_SCALE;
    }

    /**
     * Get the width, in pixels, of the Layout. This is suitable, together
     * with {@link #getPixelHeight() getPixelHeight()}, to pass to a Widget's
     * {@link Widget#setSizeRequest(int, int) setSizeRequest()} in order to
     * ensure enough space is available for the text to actually be shown.
     * 
     * @since 4.0.9
     */
    public int getPixelWidth() {
        int[] width = new int[1];
        PangoLayout.getPixelSize(this, width, null);
        return width[0];
    }

    /**
     * Get the height, in pixels, of the Layout. See the corresponding method
     * {@link #getPixelWidth() getPixelWidth()} for details.
     * 
     * @since 4.0.9
     */
    public int getPixelHeight() {
        int[] height = new int[1];
        PangoLayout.getPixelSize(this, null, height);
        return height[0];
    }

    /**
     * Sets the default FontDescription for the Layout. If no fFontDescription
     * is set, the FontDescription from the Layout's Context is used.
     * 
     * @since 4.0.9
     */
    public void setFontDescription(FontDescription desc) {
        PangoLayout.setFontDescription(this, desc);
    }

    /**
     * Set the width of the Layout to be used for word-wrapping purposes.
     * 
     * <p>
     * This will determine the positioning of the text and how the lines are
     * wrapped. If a text line is greater than the given size, it is split
     * into several lines.
     * 
     * @param width
     *            The width in Cairo terms (typically pixels).
     * @since 4.0.9
     */
    public void setWidth(double width) {
        PangoLayout.setWidth(this, (int) (width * PANGO_SCALE));
    }

    /**
     * Sets whether each complete line should be stretched to fill the entire
     * width of the layout.
     * 
     * <p>
     * This stretching is typically done by adding whitespace, but for some
     * scripts (such as Arabic), the justification may be done in more complex
     * ways, like extending the characters.
     * 
     * @since 4.0.9
     */
    public void setJustify(boolean justify) {
        PangoLayout.setJustify(this, justify);
    }

    /**
     * Gets whether each complete line should be stretched to fill the entire
     * width of the Layout.
     * 
     * @since 4.0.9
     */
    public boolean getJustify() {
        return PangoLayout.getJustify(this);
    }

    /**
     * Sets the <var>alignment</var> for the Layout. This controls how partial
     * lines are positioned within the available horizontal space.
     * 
     * <p>
     * Note that contrary to what is commonly expressed in the user interface
     * of common tools like word processors, justification is not an alignment
     * type. If you wish to have equally wide lines, see
     * {@link #setJustify(boolean) setJustify()}. Alignment remains important
     * as it controls where indentation is relative to and what to do with the
     * last line of each paragraph.
     * 
     * @since 4.0.9
     */
    public void setAlignment(Alignment alignment) {
        PangoLayout.setAlignment(this, alignment);
    }

    /**
     * Gets the Alignment for the Layout.
     * 
     * @since 4.0.9
     */
    public Alignment getAlignment() {
        return PangoLayout.getAlignment(this);
    }

    /**
     * Sets the width by which to indent the first line of each paragraph. A
     * negative value of indent will produce a hanging indentation. That is,
     * the first line will have the full width, and subsequent lines will be
     * indented by the absolute value of indent.
     * 
     * <p>
     * Note that the indent is relative to the Alignment of the text, if the
     * text is aligned to the right, the indent is computed from there.
     * 
     * @since 4.0.9
     */
    public void setIndent(double indent) {
        PangoLayout.setIndent(this, (int) (indent * PANGO_SCALE));
    }

    /**
     * Get the paragraph indent of this Layout. It'll be <code>0</code> unless
     * you called {@link #setIndent(double) setIndent()} to change it.
     * 
     * @since 4.0.9
     */
    public double getIndent() {
        final int units;
        units = PangoLayout.getIndent(this);
        return ((double) units) / PANGO_SCALE;
    }
}
