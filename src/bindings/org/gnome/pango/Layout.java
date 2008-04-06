/*
 * Layout.java
 *
 * Copyright (c) 2007 Operational Dynamics Consulting Pty Ltd
 * Copyright (c) 2008 Vreixo Formoso
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
 * @author Vreixo Formoso
 * @since 4.0.8
 */
public class Layout extends Object
{
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
     */
    public Layout(org.freedesktop.cairo.Context context) {
        super(PangoLayout.createLayout(context));
    }

    /**
     * Sets the text of the Layout. This is the text that will be draw.
     * 
     * @see #setMarkup(String)
     */
    public void setText(String text) {
        /*
         * we cannot use text.length(), as the length is in bytes, so we use
         * -1, to make Pango compute it.
         */
        PangoLayout.setText(this, text, -1);
    }

    /**
     * Set the text of this Layout. Its format is specified using Pango Markup
     * format [TODO we need to document pango markup somewhere]
     */
    public void setMarkup(String markup) {
        /*
         * we cannot use text.length(), as the length is in bytes, so we use
         * -1, to make Pango compute it.
         */
        PangoLayout.setMarkup(this, markup, -1);
    }

    /**
     * Get the width, in Pango units, of the Layout. This is the width of the
     * layout text, taking its format into account (for example, the size of
     * the Font will influence the final size!).
     * 
     * <p>
     * Note that this is not necessarily related with the line wrap width you
     * set with {@link #setWidth(int) setWidth()} method.
     */
    public int getSizeWidth() {
        int[] width = new int[1];
        PangoLayout.getSize(this, width, null);
        return width[0];
    }

    /**
     * Get the height, in Pango units, of the Layout. This is the height of
     * the layout text, taking its format into account (for example, the size
     * of the Font will influence the final size!).
     */
    public int getSizeHeight() {
        int[] height = new int[1];
        PangoLayout.getSize(this, null, height);
        return height[0];
    }

    /**
     * Get the width, in pixels, of the Layout. This is suitable, together
     * with {@link #getPixelSizeHeight() getPixelSizeHeight()}, to use with
     * Widget {@link Widget#setSizeRequest(int, int) setSizeRequest()}, and
     * thus ensure the full text is shown!
     * 
     * @see #getSizeWidth()
     */
    public int getPixelSizeWidth() {
        int[] width = new int[1];
        PangoLayout.getPixelSize(this, width, null);
        return width[0];
    }

    /**
     * Get the height, in pixels, of the Layout.
     * 
     * @see #getSizeHeight()
     * @see #getPixelSizeWidth()
     */
    public int getPixelSizeHeight() {
        int[] height = new int[1];
        PangoLayout.getPixelSize(this, null, height);
        return height[0];
    }

    /**
     * Sets the default FontDescription for the Layout. If no fFontDescription
     * is set, the FontDescription from the Layout's Context is used.
     */
    public void setFontDescription(FontDescription desc) {
        PangoLayout.setFontDescription(this, desc);
    }

    /**
     * Set the width of the Layout.
     * 
     * <p>
     * This will determine the positioning of the text and how the lines are
     * wrapped. If a text line is greater than the given size, it is splitted
     * in several lines.
     * 
     * @param width
     *            The width in Pango units [TODO a pixel is 1024 pango units,
     *            but this may change in a future. Should we add a class for
     *            functions to manage this?], or <code>-1</code> to disable
     *            automatic line wrapping.
     */
    public void setWidth(int width) {
        PangoLayout.setWidth(this, width);
    }

    /**
     * Sets whether each complete line should be stretched to fill the entire
     * width of the layout.
     * 
     * <p>
     * his stretching is typically done by adding whitespace, but for some
     * scripts (such as Arabic), the justification may be done in more complex
     * ways, like extending the characters.
     * 
     * @see #setWidth(int)
     */
    public void setJustify(boolean justify) {
        PangoLayout.setJustify(this, justify);
    }

    /**
     * Gets whether each complete line should be stretched to fill the entire
     * width of the Layout.
     */
    public boolean getJustify() {
        return PangoLayout.getJustify(this);
    }

    /**
     * Sets the alignment for the layout.
     * 
     * This determines how partial lines are positioned within the horizontal
     * space available, i.e., within the <code>width</code> of the Layout.
     * 
     * @see #setWidth(int)
     * @see #setJustify(boolean)
     */
    public void setAlignment(Alignment alignment) {
        PangoLayout.setAlignment(this, alignment);
    }

    /**
     * Gets the Alignment for the Layout.
     */
    public Alignment getAlignment() {
        return PangoLayout.getAlignment(this);
    }

    /**
     * Sets the width in Pango units to indent the first line of each
     * paragraph. A negative value of indent will produce a hanging
     * indentation. That is, the first line will have the full width, and
     * subsequent lines will be indented by the absolute value of indent.
     * 
     * <p>
     * Note that the indent is relative to the Alignment of the text, if the
     * text is aligned to the right, the indent is computed from there.
     */
    public void setIndent(int indent) {
        PangoLayout.setIndent(this, indent);
    }

    /**
     * Get the paragraph indent of this Layout in Pango units.
     * 
     * @see #setIndent(int)
     */
    public int getIndent() {
        return PangoLayout.getIndent(this);
    }
}
