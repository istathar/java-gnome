/*
 * java-gnome, a UI library for writing GTK and GNOME programs from Java!
 *
 * Copyright © 2007-2011 Operational Dynamics Consulting, Pty Ltd and Others
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
package org.freedesktop.cairo;

import org.gnome.gdk.Pixbuf;
import org.gnome.gdk.RGBA;
import org.gnome.gdk.Window;
import org.gnome.pango.Layout;
import org.gnome.pango.LayoutLine;
import org.gnome.rsvg.Handle;

/**
 * Carry out drawing operations with the Cairo Graphics library. The current
 * Context contains the state of the rendering engine, including the
 * co-ordinates of as yet undrawn elements.
 * 
 * <h2>Constructing</h2>
 * 
 * Graphics will be rendered to the Surface specified when you construct the
 * Context:
 * <ul>
 * <li>If creating an image to be written to a file, start with an
 * {@link ImageSurface}, do your drawing, and then use Surface's writeToPNG()
 * to output your image.
 * <li>If drawing to the screen in a user interface application, you get a
 * Context in your Widget's {@link org.gnome.gtk.Widget.Draw Widget.Draw}
 * signal, and do your drawing there.
 * </ul>
 * 
 * See the links above for examples of each use case.
 * 
 * <h2>Drawing Operations</h2>
 * 
 * Context has numerous methods allowing you to draw shapes, patterns, and
 * images to the Surface you are drawing on. These operations are all quite
 * low level, but give you very fine grained control over what is drawn and
 * where.
 * 
 * <p>
 * It is somewhat traditional to call your Context <code>cr</code>.
 * 
 * <p>
 * All of the methods on Context take arguments of type <code>double</code> to
 * represent co-ordinates, angles, colours, transparency levels, etc. Colours
 * are represented as values between <code>0.0</code> and <code>0.1</code>,
 * for example:
 * 
 * <pre>
 * cr.setSource(1.0, 0.0, 0.0);
 * </pre>
 * 
 * for solid red. In the case of co-ordinates, you can simply specify the
 * pixel address you wish to move to or draw to: <img src="Context-line.png"
 * class="snapshot">
 * 
 * <pre>
 * cr.moveTo(10, 10);
 * cr.lineTo(90, 50);
 * cr.stroke();
 * </pre>
 * 
 * where stroke draws the current path with the current line thickness.
 * 
 * <p>
 * Various other drawing operations are done by creating a shape and then
 * filling it in: <img src="Context-rectangle.png" class="snapshot">
 * 
 * <pre>
 * cr.rectangle(30, 20, 60, 60);
 * cr.fill();
 * </pre>
 * 
 * and so on.
 * 
 * <p>
 * <i>Obviously this is only the beginning of our documentation for Cairo.</i>
 * 
 * @author Andrew Cowie
 * @author Carl Worth
 * @author Behdad Esfahbod
 * @author Vreixo Formoso
 * @author Zak Fenton
 * @since 4.0.7
 * @see <a href="http://www.cairographics.org/documentation/">Cairo Graphics
 *      documentation</a>
 */
public class Context extends Entity
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
    void checkStatus() {
        checkStatus(CairoContext.status(this));
    }

    /**
     * Construct a new "Cairo Context" related to a Window. This is the magic
     * glue which allows you to link between GTK's Widgets and Cairo's drawing
     * operations.
     * 
     * <p>
     * You may find yourself needing to get at the Surface that is being drawn
     * on. Use {@link #getTarget() getTarget()}.
     * 
     * <p>
     * <i>Strictly speaking, this method is a part of GDK. We expose it here
     * as we are, from the Java bindings' perspective, constructing a Cairo
     * Context. So a constructor it is.</i>
     * 
     * @since 4.1.1
     */
    public Context(Window window) {
        super(GdkCairoSupport.createContext(window));
        checkStatus();
    }

    /**
     * Makes a copy of the current state of the Context and saves it on an
     * internal stack. The saved state is recovered using {@link #restore()}.
     * 
     * <p>
     * The utility of this function is to preserve a configuration that will
     * be temporary modified. For example, if you are drawing something with a
     * given color, line width, etc. and you need to change some of those
     * properties, draw something else, and then go back to the original
     * state. Instead of changing back all properties again, you can just
     * invoke <code>save()</code> before modifying them, and then
     * <code>restore()</code> later, once you want to use the original
     * configuration again.
     * 
     * <p>
     * Multiple calls to <code>save()</code> and <code>restore()</code> can be
     * nested. Each call to <code>restore()</code> restores the state from the
     * matching paired <code>save()</code>.
     * 
     * @since 4.0.10
     */
    public void save() {
        CairoContext.save(this);
        checkStatus();
    }

    /**
     * Restores the Context to the last (nested) saved state.
     * 
     * @throws IllegalStateException
     *             If there is no matching previous call to
     *             <code>save()</code>.
     * 
     * @since 4.0.10
     */
    public void restore() {
        CairoContext.restore(this);
        Status status = CairoContext.status(this);
        if (status == Status.INVALID_RESTORE) {
            throw new IllegalStateException("No matching call to save()");
        }
        checkStatus(status);
    }

    /**
     * Applies a scale transformation. It scales X and Y axis by sx and sy,
     * respectively.
     * 
     * <p>
     * The effect of this is that the points you submit are scaled by sx, sy.
     * For example, the following sequence:
     * 
     * <pre>
     * Context cr;
     * 
     * cr.scale(2.0, 3.0);
     * cr.moveTo(1.0, 1.0);
     * cr.lineTo(2.0, 2.0);
     * cr.stroke();
     * </pre>
     * 
     * Will actually draw a line from (2.0, 3.0) to (4.0, 6.0) in the target
     * Surface.
     * 
     * <p>
     * Note that you can also use negative numbers. Do not scale by 0.
     * 
     * <p>
     * See {@link Matrix} for the full suite of affine transformations
     * available.
     * 
     * @since 4.0.12
     */
    public void scale(double sx, double sy) {
        CairoContext.scale(this, sx, sy);
        checkStatus();
    }

    /**
     * Applies a translation transformation. What this does is move the point
     * of origin so that <code>(0, 0)</code> is now at a new position.
     * 
     * <pre>
     * cr.translate(20,50);
     * cr.moveTo(20,20);    // This is now 40,70
     *  ...
     * </pre>
     * 
     * <p>
     * See {@link Matrix} for the full suite of affine transformations
     * available.
     * 
     * @since 4.0.10
     */
    public void translate(double tx, double ty) {
        CairoContext.translate(this, tx, ty);
    }

    /**
     * Applies a rotate transformation. This rotates the co-ordinates of
     * subsequent drawing operations through a given angle (in radians). The
     * rotation happens around the origin <code>(0, 0)</code>. To rotate
     * around a different point, try the following:
     * 
     * <pre>
     * cr.translate(x, y);
     * cr.rotate(r);
     * cr.translate(-x, -y);
     * </pre>
     * 
     * <p>
     * See {@link Matrix} for the full suite of affine transformations
     * available.
     * 
     * @since 4.0.10
     */
    public void rotate(double r) {
        CairoContext.rotate(this, r);
    }

    /**
     * Set the source pattern within this Context to the given RGBA colour.
     * 
     * @since 4.1.1
     */
    public void setSource(RGBA color) {
        GdkCairoSupport.setSourceRgba(this, color);
        checkStatus();
    }

    /**
     * Set the source pattern within this Context to an opaque colour. The
     * parameters each take the range <code>0.0</code> to <code>1.0</code>.
     * 
     * @since 4.0.10
     */
    public void setSource(double red, double green, double blue) {
        CairoContext.setSourceRgb(this, red, green, blue);
        checkStatus();
    }

    /**
     * Set the source pattern within this Context to a translucent colour. The
     * parameters each take the range <code>0.0</code> to <code>1.0</code>.
     * For the <code>alpha</code> parameter, a value of <code>0.0</code>
     * indicates full transparency, and <code>1.0</code> is full opacity (ie,
     * normal).
     * 
     * @since 4.0.10
     */
    public void setSource(double red, double green, double blue, double alpha) {
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
    * Adds a cubic Bézier spline to the path from the current point to position (x3, y3)
    * in user-space coordinates, using (x1, y1) and (x2, y2) as the control points.
    * After this call the current point will be (x3, y3). If there is no current point
    * before the call to this function, it will behave as if preceded by a call
    * to {@link #moveTo(double, double)}.
    *
    * @param x1 the X coordinate of the first control point
    * @param y1 the Y coordinate of the first control point
    * @param x2 the X coordinate of the second control point
    * @param y2 the Y coordinate of the second control point
    * @param x3 the X coordinate of the end of the curve
    * @param y3 the Y coordinate of the end of the curve
    *
    * @since 4.1.3
    */
    public void curveTo(double x1, double y1, double x2, double y2, double x3, double y3) {
        CairoContext.curveTo(this, x1, y1, x2, y2, x3, y3);
        checkStatus();
    }

    /**
     * Set the line width for this Context. This will have effect in next call
     * to {@link #stroke()}. Default value is <code>2.0</code>.
     * 
     * @since 4.0.7
     */
    public void setLineWidth(double width) {
        CairoContext.setLineWidth(this, width);
        checkStatus();
    }

    /**
     * Get the line width for this Context.
     * 
     * @since 4.0.12
     */
    public double getLineWidth() {
        return CairoContext.getLineWidth(this);
    }

    /**
     * Set the antialiasing mode of the rasterizer used for drawing shapes.
     * This value is a hint, and a particular backend may or may not support a
     * particular value.
     * 
     * @since 4.0.7
     */
    public void setAntialias(Antialias antialias) {
        CairoContext.setAntialias(this, antialias);
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
     * Confines subsequent drawing operations to the inside area of the
     * current path.
     * 
     * @since 4.0.10
     */
    public void clip() {
        CairoContext.clip(this);
        checkStatus();
    }

    /**
     * Confines subsequent drawing operations to the inside area of the
     * current path, leaving the path intact for subsequent reuse.
     * 
     * @since 4.0.10
     */
    public void clipPreserve() {
        CairoContext.clipPreserve(this);
        checkStatus();
    }

    /**
     * Draw the current path as a line, preserving the path such that it can
     * be used used again. If you have drawn a shape and want to
     * <code>fill()</code> it, you are better off calling
     * {@link #fillPreserve() fillPreserve()} and, changing source and then
     * calling {@link #stroke() stroke()}; otherwise your fill will blot out
     * the inside of your stroke.
     * 
     * @since 4.0.10
     */
    public void strokePreserve() {
        CairoContext.strokePreserve(this);
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
     * <code>getSurface()</code>. So many people have a hard time finding the
     * generic method that allows you to get to the Surface that they're
     * considering renaming this to <code>cairo_get_surface</code> in Cairo
     * itself, but until they do, we'll stick with the algorithmic mapping of
     * <code>cairo_get_target</code>.</i>
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
     * <code>x</code>,<code>y</code> define where, in user-space coordinates,
     * that the Pattern should appear.
     * 
     * <p>
     * You can get the Pattern that was created internally by calling this
     * with {@link #getSource() getSource()} and manipulate it further if you
     * need to change the defaults.
     * 
     * @since 4.0.10
     */
    public void setSource(Surface surface, double x, double y) {
        CairoContext.setSourceSurface(this, surface, x, y);
        checkStatus();
    }

    /**
     * Draw a (closed) rectangular sub-path. The rectangle will be at
     * <code>x</code>,<code>y</code> in user-space coordinates of the given
     * <code>width</code> and <code>height</code>.
     * 
     * @since 4.0.7
     */
    public void rectangle(double x, double y, double width, double height) {
        CairoContext.rectangle(this, x, y, width, height);
        checkStatus();
    }

    /**
     * Adds a circular arc of the given radius to the current path. The arc is
     * centered at <code>xc,yc</code>, begins at <code>angle1</code> and
     * proceeds in the direction of increasing angles to end at
     * <code>angle2</code>. If <code>angle2</code> is less than
     * <code>angle1</code> it will be progressively increased by
     * <code>2&pi;</code> until it is greater than <code>angle1</code>.
     * 
     * <p>
     * If there is a current point, an initial line segment will be added to
     * the path to connect the current point to the beginning of the arc.
     * 
     * <p>
     * Angles are measured in radians. An angle of <code>0.0</code> is in the
     * direction of the positive <i>x</i> axis. An angle of
     * <code>&pi;/2</code> radians (90&deg;) is in the direction of the
     * positive <i>y</i> axis. Angles increase in the direction from the
     * positive <i>x</i> axis toward the positive <i>y</i> axis, increasing in
     * a clockwise direction. <img class="snapshot" src="Context-arc.png">
     * 
     * <p>
     * The 60&deg; arc shown is from angle <code>0</code> through
     * <code>+&pi;/3</code> radians, and was achieved with the following call:
     * 
     * <pre>
     * cr.arc(50.0, 50.0, 30.0, 0.0, Math.PI / 3.0);
     * </pre>
     * 
     * The illustration has its axis centred at position <code>50</code>,
     * <code>50</code>. The key point to note is that positive <i>y</i> is
     * towards the <b>bottom</b>, and that increasing angles as drawn by this
     * function go <b>clockwise</b> which is backwards from the Cartesian or
     * Polar co-ordinates you're probably used to using in mathematics.
     * 
     * <p>
     * See {@link #arcNegative(double, double, double, double, double)
     * arcNegative()} to draw arcs that go in the other direction.
     * 
     * @since 4.0.7
     */
    public void arc(double xc, double yc, double radius, double angle1, double angle2) {
        CairoContext.arc(this, xc, yc, radius, angle1, angle2);
        checkStatus();
    }

    /**
     * Adds a circular arc of the given radius to the current path. The arc is
     * centered at <code>xc</code>,<code>yc</code>, will begin at
     * <code>angle1</code> and proceeds in the direction of decreasing angles
     * to end at <code>angle2</code>. If <code>angle2</code> is greater than
     * <code>angle1</code> it will be progressively decreased by
     * <code>2&pi;</code> until it is less than <code>angle1</code>. <img
     * class="snapshot" src="Context-arcNegative.png">
     * 
     * <p>
     * This 135&deg; arc shown in the illustration goes from <code>0</code> to
     * <code>-3/4&pi</code> radians:
     * 
     * <pre>
     * cr.arcNegative(50.0, 50.0, 30.0, 0.0, -Math.PI * 3.0 / 4.0);
     * </pre>
     * 
     * note that in this example the second angle is negative; if
     * <code>3/4&pi</code> had been specified the arc would have continued to
     * a point 45&deg; below the <code>-</code><i>x</i> axis.
     * 
     * <p>
     * See {@link #arc(double, double, double, double, double) arc()} for
     * drawing arcs in the positive, clockwise direction.
     * 
     * @since 4.0.7
     */
    public void arcNegative(double xc, double yc, double radius, double angle1, double angle2) {
        CairoContext.arcNegative(this, xc, yc, radius, angle1, angle2);
        checkStatus();
    }

    /**
     * Fill the current path, implicitly closing sub-paths first. The drawing
     * will be done according to the current FillRule. The path will be
     * cleared after calling <code>fill()</code>; if you want to keep it use
     * {@link #fillPreserve() fillPreserve()} instead.
     * 
     * @since 4.0.7
     */
    public void fill() {
        CairoContext.fill(this);
        checkStatus();
    }

    /**
     * Sets the dash pattern used in lines drawn with {@link #stroke()
     * stroke()}.
     * 
     * <p>
     * The pattern is specified by an array of double values. Each value
     * provides the length of alternate "on" and "off" portions of the stroke.
     * 
     * @since 4.0.12
     */
    /*
     * TODO the offset seems not very useful, so I always use 0. Later we may
     * want to expose setDash(double[], double) to allow offset specification
     */
    public void setDash(double[] dashes) {
        CairoContext.setDash(this, dashes, dashes.length, 0);
        checkStatus();
    }

    /**
     * Fill the current path, preserving the path such that it can be used
     * used again. This is useful if you have drawn a shape and want to
     * {@link #stroke() stroke()} it with a different colour as an outline.
     * 
     * @since 4.0.7
     */
    public void fillPreserve() {
        CairoContext.fillPreserve(this);
        checkStatus();
    }

    /**
     * Draw a paragraph of text. The top-left corner of the Layout's rendered
     * extents will be drawn at the current Context point.
     * 
     * <p>
     * The text to draw and its format is specified in a Pango {@link Layout},
     * previously constructed with this Context.
     * 
     * @since 4.0.10
     */
    public void showLayout(Layout layout) {
        CairoContext.showLayout(this, layout);
        checkStatus();
    }

    /**
     * Draw a single line of text as extracted from a Layout.
     * 
     * <p>
     * Unlike the <code>showLayout()</code> taking a full Layout, this method
     * draws the base line of the extent (its Rectangle's <code>x</code>,
     * <code>y</code> origin) at the current Context point. See LayoutLine's
     * {@link LayoutLine#getExtentsLogical() getExtentsLogical()} method for
     * details.
     * 
     * @since 4.0.10
     */
    public void showLayout(LayoutLine line) {
        CairoContext.showLayoutLine(this, line);
        checkStatus();
    }

    /*
     * This really doesn't belong here, but we don't have anywhere else for it
     * right now. showLayout() above is lovely, and this is entirely parallel
     * and complementary to it. So it'll do for now.
     */
    public void updateLayout(Layout layout) {
        CairoContext.updateLayout(this, layout);
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

    /**
     * Paint the current source using the alpha channel of the given
     * <code>surface</code> as its mask. The Surface will be offset by
     * <code>x</code> and <code>y</code> before drawing.
     * 
     * @since 4.0.10
     */
    public void mask(Surface surface, double x, double y) {
        CairoContext.maskSurface(this, surface, x, y);
    }

    /**
     * Given an image already loaded in a Pixbuf, set the current Source to be
     * that image. For example, to put the image at the bottom right of your
     * drawing area, you might do something like:
     * 
     * <pre>
     * pixbuf = new Pixbuf(filename);
     * cr.setSource(pixbuf, pageWidth - pixbuf.getWidth(), pageHeight - pixbuf.getHeight());
     * cr.paint();
     * </pre>
     * 
     * as <code>paint()</code> paints the current source "everywhere", and so
     * down goes your image.
     * 
     * If you are drawing the same image data to screen frequently, consider
     * caching the image in video memory. See {@link XlibSurface}.
     * 
     * @since 4.0.10
     */
    public void setSource(Pixbuf pixbuf, double x, double y) {
        GdkCairoSupport.setSourcePixbuf(this, pixbuf, x, y);
    }

    /**
     * Move to a location relative to the current point.
     * 
     * <p>
     * If the point is at <code>x</code>,<code>y</code> then this will move
     * the point to <code>x+dx</code>,<code>y+dy</code>.
     * 
     * <p>
     * <i>In the underlying native library this is</i>
     * <code>cairo_rel_move_to()</code>. <i>We have adjusted the name slightly
     * to provide for better discoverability in the completion space.</i>
     * 
     * @since 4.0.10
     */
    public void moveRelative(double dx, double dy) {
        CairoContext.relMoveTo(this, dx, dy);
    }

    /**
     * Move to a location relative to the current point.
     * 
     * <p>
     * If the point is at <code>x</code>,<code>y</code> then this will draw a
     * line from <code>x</code>,<code>y</code> to <code>x+dx</code>,
     * <code>y+dy</code>, leaving the point at the latter location.
     * 
     * <p>
     * <i>In the underlying native library this is</i>
     * <code>cairo_rel_line_to()</code>. <i>We have adjusted the name slightly
     * to provide for better discoverability in the completion space.</i>
     * 
     * @since 4.0.10
     */
    public void lineRelative(double dx, double dy) {
        CairoContext.relLineTo(this, dx, dy);
    }

    /**
     * Get the x co-ordinate of the current point.
     * 
     * <p>
     * You'll need to call this if you want to resume drawing at that point
     * after calling <code>stroke()</code> or <code>fill()</code>, as after
     * doing their work they clear the current path; the current point goes
     * along with it.
     * 
     * <pre>
     * // do some drawing
     * 
     * x = cr.getCurrentPointX();
     * y = cr.getCurrentPointY();
     * 
     * cr.stroke();
     * 
     * cr.moveTo(x, y);
     * 
     * // carry on drawing
     * </pre>
     * 
     * @since 4.0.10
     */
    public double getCurrentPointX() {
        double[] x;
        double[] y;

        x = new double[1];
        y = new double[1];

        CairoContext.getCurrentPoint(this, x, y);

        return x[0];
    }

    /**
     * Get the y co-ordinate of the current point. See
     * {@link #getCurrentPointX() getCurrentPointX()} for discussion of when
     * you'd need this.
     * 
     * @since 4.0.10
     */
    public double getCurrentPointY() {
        double[] x;
        double[] y;

        x = new double[1];
        y = new double[1];

        CairoContext.getCurrentPoint(this, x, y);

        return y[0];
    }

    /**
     * Apply the given Matrix to affine transform this Context. See
     * {@link Matrix} for examples.
     * 
     * <p>
     * Beware that if there is a scaling component, line widths resulting from
     * <code>stroke()</code> calls will scale too!
     * 
     * @since 4.0.10
     */
    public void transform(Matrix matrix) {
        CairoContext.transform(this, matrix);
    }

    /**
     * Render an SVG image to this Cairo surface.
     * 
     * <p>
     * <i>In the underlying native library this is</i>
     * <code>rsvg_handle_render_cairo()</code>. <i>We have placed the call
     * here to align with other Cairo baesd image and text rendering
     * methods.</i>
     * 
     * @since 4.0.18
     */
    public void showHandle(Handle graphic) {
        CairoContext.showHandle(graphic, this);
    }

    /**
     * Close the current path.
     * 
     * <p>
     * This makes the path a closed loop, rather than it being a line with
     * caps at each end. Call this when you're trying to close a shape.
     * 
     * <p>
     * The current path begins at the point given to the last moveTo() call.
     * If there's no current point, then this has no effect.
     * 
     * @since 4.0.17
     */
    public void closePath() {
        CairoContext.closePath(this);
    }

    /**
     * Create a new path within the current one. Although
     * {@link #moveTo(double, double) moveTo()} also creates a new sub-path,
     * this allows you to do so without needing destination co-ordinates.
     * 
     * @since 4.0.17
     */
    public void newSubPath() {
        CairoContext.newSubPath(this);
    }

    /**
     * Change the fill algorithm. The default is {@link FillRule#WINDING
     * WINDING}.
     * 
     * @since 4.0.17
     */
    public void setFillRule(FillRule setting) {
        CairoContext.setFillRule(this, setting);
    }

    /**
     * Is the supplied point in the area that would be filled if
     * {@link #fill() fill()} was called with the current path?
     * 
     * @since 4.0.17
     */
    public boolean inFill(double x, double y) {
        return CairoContext.inFill(this, x, y);
    }

    /**
     * Is the supplied point in the thickness that would be drawn if
     * {@link #stroke() stroke()} was called with the current path?
     * 
     * @since 4.0.17
     */
    public boolean inStroke(double x, double y) {
        return CairoContext.inStroke(this, x, y);
    }
}
