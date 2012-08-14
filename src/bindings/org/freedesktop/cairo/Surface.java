/*
 * java-gnome, a UI library for writing GTK and GNOME programs from Java!
 *
 * Copyright © 2007-2011 Operational Dynamics Consulting, Pty Ltd
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

import java.io.IOException;

/**
 * The thing that Cairo will draw on/to. This is the base class for several
 * concrete back ends.
 * 
 * @author Andrew Cowie
 * @since 4.0.7
 */
public abstract class Surface extends Entity
{
    protected Surface(long pointer) {
        super(pointer);
    }

    protected void release() {
        CairoSurface.destroy(this);
    }

    /**
     * Indicate to Cairo that you are finished drawing on this Surface and
     * that it can release the resources that Cairo has used in association
     * with it. After you call this, the Surface and any Contexts associated
     * with it will be in-operative.
     * 
     * <p>
     * While the virtual machine will of course finalize this object when you
     * no longer have strong Java references to it, garbage collection is
     * non-deterministic and often takes place long after drawing is
     * completed. Calling this can allow the underlying library to get on with
     * releasing the resources involved in what is, after all, meant to be a
     * very fast and transient operation.
     * 
     * @since 4.0.7
     */
    public void finish() {
        CairoSurface.finish(this);
    }

    /**
     * Complete any pending drawing operations. If your Surface comes from a
     * subsystem whose resources are shared with other drawing libraries, then
     * you need to call this before letting control return to a point where
     * those libraries might draw on the same backing Surface.
     * 
     * <p>
     * Quoting Carl Worth, the original author of Cairo, <blockquote>This is
     * really only necessary to call if you want to switch from Cairo-based
     * rendering to non-Cairo-based rendering to the same underlying thingy
     * under the Surface (Window, Pixmap, data buffer, etc.).</blockquote>
     * 
     * <p>
     * In other words, if you are the only one drawing on the Surface, then
     * you don't need this. And that is indeed the case if you are drawing on
     * your own custom Widget in its <code>Widget.Draw</code> signal handler.
     * 
     * <p>
     * See also {@link #finish() finish()} if you're just trying to say "I'm
     * done".
     * 
     * <p>
     * <i>Clearly, "thingy" is an advanced graphics term.</i>
     * 
     * @since 4.0.7
     */
    public void flush() {
        CairoSurface.flush(this);
    }

    /**
     * Output the contents of this Surface to the specified file.
     * 
     * @throws IOException
     *             If the file can't be written.
     * 
     * @since 4.0.7
     */
    public void writeToPNG(String filename) throws IOException {
        final Status status;

        status = CairoSurface.writeToPng(this, filename);

        if (status == Status.WRITE_ERROR) {
            throw new IOException("You cannot write to file " + filename);
        }

        checkStatus(status);
    }

    protected void checkStatus() {
        checkStatus(CairoSurface.status(this));
    }

    /**
     * Emit the current page and clear the Surface, allowing you to continue
     * drawing with your Context but to a new blank page.
     * 
     * <p>
     * If you want to render the current page, but keep the page content in
     * your Context, then call {@link #copyPage() copyPage()} instead.
     * 
     * <p>
     * This method only applies to Surfaces which are paginated, which in
     * practise means the PDF backend.
     * 
     * @since 4.0.10
     */
    /*
     * While Cairo treats these as a no-op for backends which are not
     * paginated, throwing UnsupportedOperationException is the more
     * traditional and accepted Java way of dealing with the case of base
     * classes which expose a method for which many subclasses do not take
     * action. Obviously this should be overridden by concrete subclasses
     * representing backends which are actually paginated.
     */
    public void showPage() {
        throw new UnsupportedOperationException();
    }

    /**
     * Emit the current page, but keep the content of the Surface as the
     * starting point for the next page.
     * 
     * @since 4.0.10
     */
    public void copyPage() {
        throw new UnsupportedOperationException();
    }

    /**
     * Create a new Surface based on this one.
     * 
     * <pre>
     * surface.createSimilar(Content.COLOR_ALPHA, 800, 600);
     * </pre>
     * 
     * <p>
     * This has enormous application when drawing with image heavy content to
     * screen.
     * 
     * @since 4.0.10
     */
    public Surface createSimilar(Content type, int width, int height) {
        return CairoSurface.createSimilar(this, type, width, height);
    }

    /**
     * Attach original image data to a surface. When drawing an image into a
     * vector Surface such as PDF or SVG, Cairo will draw the decoded image as
     * a bitmap. Which is <i>hugely</i> inefficient. This method allows you to
     * add the original image to the surface, and when drawing out Cairo will
     * use this if it can.
     * 
     * <p>
     * Keep in mind that you attach the data to the <i>intermediate</i>
     * Surface which is painted onto the target, not the final target Surface
     * itself.
     * 
     * <pre>
     * pixbuf = new Pixbuf(data);
     * 
     * intermediate = new ImageSurface(Format.ARGB32, width, height);
     * 
     * second = new Context(intermediate);
     * second.setSource(pixbuf, 0, 0);
     * second.paint();
     * intermediate.setMimeData(MimeType.JPEG, data);
     * 
     * cr.setSource(intermediate, 0, 0);
     * cr.paint();
     * </pre>
     * 
     * or you can go through the implicitly created Pattern:
     * 
     * <pre>
     * pixbuf = new Pixbuf(data);
     * 
     * cr.setSource(pixbuf, 0, 0);
     * pattern = cr.getSource();
     * implicit = pattern.getSurface();
     * implicit.setMimeData(MimeType.JPEG, data);
     * 
     * cr.paint();
     * </pre>
     * 
     * which seems a bit easier.
     * 
     * <p>
     * <b>NOTE:</b><br/>
     * You must not make any changes to the intermediate surface after setting
     * the image data or you will lose the effect of this call.
     * 
     * <p>
     * <i>It would be nice if gdk_cairo_set_source_pixbuf() just did this for
     * us.</i>
     * 
     * 
     * @since 4.0.18
     */
    public void setMimeData(MimeType type, byte[] data) {
        CairoSurfaceOverride.setMimeData(this, type, data);
    }

    /**
     * Set a filename as the MIME data for this surface.
     * 
     * @since <span style="color:red">Unstable</span>
     */
    /*
     * FIXME How does this work? Are you supposed to use URI, or the actual
     * file type, or?
     */
    public void setMimeData(MimeType type, String filename) {
        final byte[] data;

        data = filename.getBytes();

        CairoSurfaceOverride.setMimeData(this, type, data);
    }
}
