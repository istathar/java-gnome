/*
 * Context.java
 *
 * Copyright (c) 2007-2008 Operational Dynamics Consulting Pty Ltd
 * 
 * The code in this file, and the library it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" plus the "Classpath Exception" (you may link to this code as a
 * library into other programs provided you don't make a derivation of it).
 * See the LICENCE file for the terms governing usage and redistribution.
 */
package org.freedesktop.cairo;

import org.freedesktop.bindings.Proxy;
import org.gnome.gdk.Drawable;

/**
 * Carry out drawing operations with the Cairo library.
 * 
 * <p>
 * Graphics will be rendered to the Surface specified when you construct the
 * Context:
 * <ul>
 * <li>If creating an image to be written to a file, start with an
 * {@link ImageSurface}, do your drawing, and then use Surface's writeToPNG()
 * to output your image.
 * <li>If drawing to the screen in a user interface application, you'll
 * construct a Context using the underlying GDK Window in your Widget's
 * {@link org.gnome.gtk.Widget.EXPOSE_EVENT EXPOSE_EVENT}.
 * </ul>
 * 
 * @author Andrew Cowie
 * @since 4.0.7
 */
public class Context extends Proxy
{
    protected Context(long pointer) {
        super(pointer);
    }

    protected void release() {
        CairoContext.destroy(this);
    }

    /**
     * Construct a new "Cairo Context". You supply the Surface that you are
     * drawing to.
     * 
     * @since 4.0.7
     */
    public Context(Surface target) {
        super(CairoContext.createContext(target));
    }

    /**
     * Check the status of the Cairo Context, and fail with an exception if it
     * is other than SUCCESS. This should be called by each method in our
     * Cairo bindings.
     * 
     * <p>
     * <i>The the fact that errors are not checked for after each operation is
     * a C API convenience only.</i>
     */
    /*
     * FUTURE It might be nice to find a way to get the code generator to
     * insert this into the JNI code automatically. That's non trivial, if for
     * no other reason than different parts of Cairo (ie Surface, Font) use
     * different status checking functions. It doesn't hurt to have it here.
     */
    void checkStatus() {
        final Status status;

        status = CairoContext.status(this);

        if (status != Status.SUCCESS) {
            throw new IllegalStateException(status.toString() + "\n"
                    + CairoContext.statusToString(status));
        }
    }

    /**
     * Construct a new "Cairo Context" related to a Drawable. This is the
     * magic glue which allows you to link between GTK's Widgets and Cairo's
     * drawing operations.
     * 
     * <p>
     * You may find yourself needing to get at the Surface that is being drawn
     * on. Use {@link #getTarget() getTarget()}.
     * 
     * <p>
     * <i>Strictly speaking, this method is a part of GDK. We expose it here
     * as we <b>are</b>, from the Java bindings' perspective, constructing a
     * Cairo Context.</i>
     * 
     * @since 4.0.7
     */
    /*
     * The function in GdkDrawable is tempting, but since it is not marked as
     * a constructor, it wants to return an object, not a long. It's also in
     * the wrong package. We'll leave that be.
     */
    public Context(Drawable drawable) {
        super(CairoContextOverride.createContextFromDrawable(drawable));
        checkStatus();
    }

    /**
     * Set the source pattern within this Context to an opaque colour. The
     * parameters each take the range <code>0.0</code> to <code>1.0</code>.
     * 
     * @since 4.0.7
     */
    public void setSourceRGB(double red, double green, double blue) {
        CairoContext.setSourceRgb(this, red, green, blue);
        checkStatus();
    }

    /**
     * Set the source pattern within this Context to a translucent colour. The
     * parameters each take the range <code>0.0</code> to <code>1.0</code>.
     * For the <code>alpha</code> parameter, a value of <code>0.0</code>
     * indicates full transparency, and <code>1.0</code> is full opacity
     * (ie, normal).
     * 
     * @since 4.0.7
     */
    public void setSourceRGBA(double red, double green, double blue, double alpha) {
        CairoContext.setSourceRgba(this, red, green, blue, alpha);
        checkStatus();
    }

    /**
     * Add a line from the current location to <code>x</code>,<code>y</code>.
     * After the call the current point will be <code>x</code>,<code>y</code>.
     * 
     * @since 4.0.7
     */
    public void lineTo(double x, double y) {
        CairoContext.lineTo(this, x, y);
        checkStatus();
    }

    /**
     * Move to a new location without drawing, beginning a new sub-path. After
     * the call the current point will be <code>x</code>,<code>y</code>.
     * 
     * @since 4.0.7
     */
    public void moveTo(double x, double y) {
        CairoContext.moveTo(this, x, y);
        checkStatus();
    }

    /**
     * Draw the current path as a line.
     * 
     * @since 4.0.7
     */
    public void stroke() {
        CairoContext.stroke(this);
        checkStatus();
    }

    /**
     * Get the current source Pattern for this Context.
     * 
     * @since 4.0.7
     */
    public Pattern getSource() {
        final Pattern result;

        result = CairoContext.getSource(this);
        checkStatus();

        return result;
    }

    /**
     * Get the Surface that this Context is drawing on.
     * 
     * <p>
     * <i>Yes, this method has a stupid name. It really should be
     * <code>getSurface()</code>. So many people have a hard time finding
     * the generic method that allows you to get to the Surface that they're
     * considering renaming this to <code>cairo_get_surface</code> in Cairo
     * itself, but until they do, we'll stick with the algorithmic mapping of
     * <code>cairo_get_target</code>.
     * 
     * @since 4.0.7
     */
    public Surface getTarget() {
        final Surface result;

        result = CairoContext.getTarget(this);
        checkStatus();

        return result;
    }

    /**
     * Set the operation that will govern forthcoming compositing actions.
     * 
     * <p>
     * One particularly useful sequence is clearing the Surface to all
     * transparent pixels:
     * 
     * <pre>
     * cr.setOperator(Operator.CLEAR);
     * cr.paint();
     * </pre>
     * 
     * @since 4.0.7
     */
    public void setOperator(Operator op) {
        CairoContext.setOperator(this, op);
        checkStatus();
    }

    /**
     * Paint the current source everywhere within the current clip region.
     * 
     * @since 4.0.7
     */
    public void paint() {
        CairoContext.paint(this);
        checkStatus();
    }

    /**
     * Create a Pattern from a Surface, and then use it in this Context. This
     * is a convenience method.
     * 
     * <p>
     * <code>x</code>,<code>y</code> define where, in user-space
     * coordinates, that the Pattern should appear.
     * 
     * <p>
     * You can get the Pattern that was created internally by calling this
     * with {@link #getSource() getSource()} and manipulate it further if you
     * need to change the defaults.
     * 
     * @since 4.0.7
     */
    public void setSourceSurface(Surface surface, double x, double y) {
        CairoContext.setSourceSurface(this, surface, x, y);
        checkStatus();
    }

    /**
     * Draw a (closed) rectangular sub-path. The rectangle will be at
     * <code>x</code>,<code>y</code> in user-space coordinates of the
     * given <code>width</code> and <code>height</code>.
     * 
     * @since 4.0.7
     */
    public void rectangle(double x, double y, double width, double height) {
        CairoContext.rectangle(this, x, y, width, height);
        checkStatus();
    }

    /**
     * Fill the current path, implicitly closing sub-paths first. The drawing
     * will be done according to the current FillRule. The path will be
     * cleared after calling <code>fill()</code>; if you want to keep it
     * use {@link #fillPreserve() fillPreserve()} instead.
     * 
     * @since 4.0.7
     */
    public void fill() {
        CairoContext.fill(this);
        checkStatus();
    }

    public void fillPreserve() {
        CairoContext.fillPreserve(this);
        checkStatus();
    }

    /**
     * Set a Pattern to be the source of this Context.
     * 
     * @since 4.0.7
     */
    public void setSource(Pattern pattern) {
        CairoContext.setSource(this, pattern);
        checkStatus();
    }

    /**
     * Paint the current source using the alpha channel of
     * <code>pattern</code> as a mask. This means "opaque areas of the mask
     * will be painted with the source, whereas transparent areas will not be
     * painted"
     * 
     * @since 4.0.7
     */
    public void mask(Pattern pattern) {
        CairoContext.mask(this, pattern);
        checkStatus();
    }
}
