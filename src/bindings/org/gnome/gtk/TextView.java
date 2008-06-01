/*
 * TextView.java
 *
 * Copyright (c) 2007-2008 Operational Dynamics Consulting Pty Ltd, and Others
 *
 * The code in this file, and the library it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" plus the "Classpath Exception" (you may link to this code as a
 * library into other programs provided you don't make a derivation of it).
 * See the LICENCE file for the terms governing usage and redistribution.
 */
package org.gnome.gtk;

import org.gnome.gdk.Rectangle;

/**
 * FIXME
 * 
 * @author Stefan Prelle
 * @author Andrew Cowie
 * @since 4.0.8
 */
public class TextView extends Container
{
    protected TextView(long pointer) {
        super(pointer);
    }

    /**
     * Create an empty TextView without (yet) having an associated TextBuffer.
     * Use {@link #setBuffer(TextBuffer) setBuffer()} to indicate later which
     * TextBuffer to use.
     * 
     * @since 4.0.8
     */
    public TextView() {
        super(GtkTextView.createTextView());
    }

    /**
     * Create a TextView and display the contents of the TextBuffer.
     * 
     * @param buf
     *            TextBuffer to associated with this TextView.
     * @since 4.0.8
     */
    public TextView(TextBuffer buf) {
        super(GtkTextView.createTextViewWithBuffer(buf));
    }

    TextBuffer getBuffer() {
        return GtkTextView.getBuffer(this);
    }

    /**
     * Set or replace the TextBuffer that is currently being displayed by this
     * TextView.
     * 
     * @since 4.0.8
     */
    public void setBuffer(TextBuffer buffer) {
        GtkTextView.setBuffer(this, buffer);
    }

    /**
     * Set the line wrapping for the view.
     * 
     * @since 4.0.8
     */
    public void setWrapMode(WrapMode wrapMode) {
        GtkTextView.setWrapMode(this, wrapMode);
    }

    /**
     * Get the line wrapping for the view.
     * 
     * @since 4.0.8
     */
    public WrapMode getWrapMode() {
        return GtkTextView.getWrapMode(this);
    }

    /**
     * Set whether the normal state of this TextView is to allow editing or
     * not. The default for the <var>editability</var> property is
     * <code>true</code>.
     * 
     * <p>
     * Regardless of the default setting here, you can override this for
     * specific regions of text with TextTag's
     * {@link TextTag#setEditable(boolean) setEditable()}. of tags.
     * 
     * @since 4.0.8
     */
    public void setEditable(boolean editable) {
        GtkTextView.setEditable(this, editable);
    }

    /**
     * Get whether the default editability of the TextView. Tags in the buffer
     * may override this setting for some ranges of text.
     * 
     * @since 4.0.8
     */
    public boolean getEditable() {
        return GtkTextView.getEditable(this);
    }

    /**
     * Allows you to activate or deactivate the visible cursor. Usually used
     * to hide the cursor when displaying text that is non-editable. The
     * default is <code>true</code>, indicating the cursor will be shown.
     * 
     * @since 4.0.8
     */
    public void setCursorVisible(boolean visible) {
        GtkTextView.setCursorVisible(this, visible);
    }

    /**
     * Returns whether the cursor is currently visible or not.
     * 
     * @since 4.0.8
     */
    public boolean getCursorVisible() {
        return GtkTextView.getCursorVisible(this);
    }

    /**
     * Load a Widget into the given TextChildAnchor such that it shows in this
     * TextView.
     * 
     * <p>
     * <i>This is somewhat convoluted due to the fact that more than one
     * TextView can be displaying a given TextBuffer, but a Widget can only
     * appear in one parent Container.</i>
     * 
     * @since 4.0.8
     */
    /*
     * WARNING Signature subject to conversion to an overload if another
     * addChild ish method is implemented.
     */
    public void addChildAtAnchor(Widget child, TextChildAnchor anchor) {
        GtkTextView.addChildAtAnchor(this, child, anchor);
    }

    /**
     * Set the size (width for LEFT and RIGHT and height for TOP and BOTTOM)
     * of the specified side panels. You only need this if using the optional
     * side panels, and obscure, advanced, and not wholly functional feature;
     * see {@link TextWindowType}. If you're reading this and wanting to set
     * the padding around the TextView, you probably want
     * {@link #setBorderWidth(int) setBorderWidth()}, a method inherited from
     * Container.
     * 
     * @since 4.0.8
     */
    public void setBorderWindowSize(TextWindowType which, int size) {
        GtkTextView.setBorderWindowSize(this, which, size);
    }

    /**
     * Place a child Widget into one of the optional side panels around a
     * TextView. This is an advanced feature; see {@link TextWindowType} for a
     * full discussion.
     * 
     * <p>
     * The Widget <code>child</code> will be placed at the coordinates
     * <code>x</code>,<code>y</code> in the [org.gnome.gdk] Window
     * specified by which. You can get that Window by calling
     * {@link #getWindow(TextWindowType) getWindow()}.
     * 
     * <p>
     * This cannot be used unless <code>which</code> has been initialized to
     * have a non-zero size with
     * {@link #setBorderWindowSize(TextWindowType, int) setBorderWindowSize()}.
     * 
     * @since 4.0.8
     */
    public void addChildInWindow(Widget child, TextWindowType which, int x, int y) {
        final int width;

        width = GtkTextView.getBorderWindowSize(this, which);

        if (width < 1) {
            throw new IllegalStateException("Optional border panels must have non-zero size.");
        }

        GtkTextView.addChildInWindow(this, child, which, x, y);
    }

    /**
     * Change the co-ordinates of a child Widget in one of the optional side
     * panels. <code>x</code>,<code>y</code> are specified in <i>window
     * co-ordinates</i>.
     * 
     * @since 4.0.8
     */
    public void moveChild(Widget child, int x, int y) {
        GtkTextView.moveChild(this, child, x, y);
    }

    /**
     * Convert <code>X</code> from <var>buffer co-ordinates</var> to
     * <var>window co-ordinates</var>. See
     * {@link #convertBufferToWindowCoordsY(TextWindowType, int) convertBufferToWindowCoordsY()}
     * for a detailed discussion.
     * 
     * @since 4.0.8
     */
    /*
     * In this method and the corresponding Y co-ordinate version we just pass
     * 0 for the unused axis because we're ignoring the result for that axis
     * anyway. This seems sane based on inspection of the code paths in GTK's
     * gtk/gtktextview.c
     */
    public int convertBufferToWindowCoordsX(TextWindowType which, int X) {
        int[] x;

        x = new int[1];

        GtkTextView.bufferToWindowCoords(this, which, X, 0, x, null);

        return x[0];
    }

    /**
     * The canvas that is used to present the text in a TextView has an origin
     * at <code>0</code>,<code>0</code> that is at the top left corner.
     * and extends for as many pixels as would be necessary to present the
     * entire TextBuffer if it were shown on an arbitrarily large screen
     * without scrolling.
     * 
     * <p>
     * In most cases, the text shown will require an area larger than the
     * viewport provided by the primary area of the TextView. Even without
     * scrollbars (which can be added by putting the TextView into a
     * ScrolledWindow), the viewport showing the text will slide when the
     * cursor is moved down from the start position and into the body of text.
     * Thus you can be at a position in <var>buffer co-ordinates</var> that
     * is far "greater" than the size of the [org.gnome.gdk] Window that
     * displays it.
     * 
     * <p>
     * Numerous methods, notably {@link #getLineY(TextIter) getLineY()},
     * return a value in <var>buffer co-ordinates</var>. If you need to
     * determine what position this represents on screen, you need to convert
     * to <var>window co-ordinates</var> which are relative to the top left
     * corner of the [org.gnome.gdk] Window being used to present the text on
     * screen. This method will carry out that conversion for the vertical
     * axis. See
     * {@link #convertBufferToWindowCoordsX(TextWindowType, int) convertBufferToWindowCoordsX()}
     * for the corresponding horizontal conversion.
     * 
     * @since 4.0.8
     */
    public int convertBufferToWindowCoordsY(TextWindowType which, int Y) {
        int[] y;

        y = new int[1];

        GtkTextView.bufferToWindowCoords(this, which, 0, Y, null, y);

        return y[0];
    }

    /**
     * Convert a horizontal position from <var>window co-ordinates</var> (the
     * on screen position) to <var>buffer co-ordinates</var> (the pixel
     * distance into the canvas used to describe the entire text being
     * displayed). See
     * {@link #convertBufferToWindowCoordsY(TextWindowType, int) convertBufferToWindowCoordsY()}
     * for a detailed discussion.
     * 
     * @since 4.0.8
     */
    public int convertWindowToBufferCoordsX(TextWindowType which, int x) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * Convert a vertical position from <var>window co-ordinates</var> to
     * <var>buffer co-ordinates</var>. See
     * {@link #convertBufferToWindowCoordsY(TextWindowType, int) convertBufferToWindowCoordsY()}
     * for a detailed discussion.
     * 
     * @since 4.0.8
     */
    public int convertWindowToBufferCoordsY(TextWindowType which, int y) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * Get the Rectangle describing what portion of the text canvas the
     * viewport is currently showing. This is (only) relevant when scrollbars
     * are employed.
     * 
     * <p>
     * If you consider the text being displayed as a canvas of a fixed size,
     * but have turned on scrolling and only have a limited portion of that
     * canvas displayed due to the Widget being sized smaller than that
     * canvas, then the <code>x</code>,<code>y</code> co-ordinates
     * returned in the Rectangle represent the current <i>offset</i> into
     * that canvas that the viewport is showing.
     * 
     * <p>
     * If, for example, you only have vertical scrolling enabled,
     * 
     * <pre>
     * view.setWrapMode(WORD);
     * scroll = new ScrolledWindow();
     * scroll.setPolicy(NEVER, ALWAYS);
     * scroll.add(view);
     * </pre>
     * 
     * then you can expect <code>getVisibleRectangle()</code> to always
     * return Rectangles with an {@link Rectangle#getX() x} offset value of
     * <code>0</code> - the viewport is never scrolled horizontally into the
     * text canvas.
     * 
     * <p>
     * The <code>width</code> and <code>height</code> will, more or less,
     * correspond to the size of the area of text actually being display in
     * the TextView.
     * 
     * @since 4.0.8
     */
    public Rectangle getVisibleRectangle() {
        final Rectangle visible;

        visible = new Rectangle(0, 0, 0, 0);

        GtkTextView.getVisibleRect(this, visible);

        return visible;
    }

    /**
     * Get the underlying resource corresponding with one of the sub elements
     * of this TextView. See {@link TextWindowType} for a detailed discussion.
     * 
     * @since 4.0.8
     */
    public org.gnome.gdk.Window getWindow(TextWindowType which) {
        return GtkTextView.getWindow(this, which);
    }

    /**
     * Get a TextIter corresponding to a given location in the canvas that is
     * displayed by the TextView. <code>X</code>,<code>Y</code> are in
     * <var>buffer co-ordinates</var>; if you have a position into the
     * [org.gnome.gdk] Window then use
     * {@link #convertWindowToBufferCoordsY(TextWindowType, int, int) convertWindowToBufferCoordsY()}
     * to convert.
     * 
     * @since 4.0.8
     */
    public TextIter getIterAtLocation(int X, int Y) {
        final TextIter result;

        result = new TextIter(getBuffer());

        GtkTextView.getIterAtLocation(this, result, X, Y);

        return result;
    }

    /**
     * Get the y co-ordinate of the line holding the supplied position. The
     * value is in <var>buffer co-ordinates</var>, and refers to the top of
     * the line. If you need to know how high the line is, call
     * {@link #getLineRange(TextIter) getLineRange()}.
     * 
     * @since 4.0.8
     */
    public int getLineY(TextIter position) {
        int[] y;

        y = new int[1];

        GtkTextView.getLineYrange(this, position, y, null);

        return y[0];
    }

    /**
     * This is the compliment of {@link #getLineY(TextIter) getLineY()},
     * giving you the corresponding line height that drops from the top
     * specified by that method.
     * 
     * @since 4.0.8
     */
    public int getLineRange(TextIter position) {
        int[] range;

        range = new int[1];

        GtkTextView.getLineYrange(this, position, null, range);

        return range[0];
    }
}
