/*
 * java-gnome, a UI library for writing GTK and GNOME programs from Java!
 *
 * Copyright © 2008-2010 Operational Dynamics Consulting, Pty Ltd
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
 * A Surface that will be rendered to a file in the Portable Document Format.
 * 
 * <p>
 * You specify the size of a PdfSurface in points, and all subsequent
 * operations on a Context based on this Surface will likewise be in points.
 * If you are used to using Cairo to draw to screen where a device unit equals
 * a pixel, be aware that here your a distance of <code>1.0</code> is in
 * points, not pixels.
 * 
 * <p>
 * <i>Cairo's PDF support is still nascent but is improving steadily! Wherever
 * possible graphics drawn in your Context will be rendered in vector form in
 * the PDF; when that is not available the PDF backend will fallback to
 * rendering bitmaps of the desired content.</i>
 * 
 * @author Andrew Cowie
 * @since 4.0.10
 */
public class PdfSurface extends Surface
{
    protected PdfSurface(long pointer) {
        super(pointer);
    }

    /**
     * Create a new PdfSurface, supplying the file you want to write to and
     * the size of the page you are creating. The <code>width</code> and
     * <code>height</code> parameters are specified in <i>points</i>, where 1
     * point equals 1/72<sup>nd</sup> of an inch.
     * 
     * <p>
     * A4 paper is 210mm x 297mm, which works out as about:
     * 
     * <pre>
     * surface = new PdfSurface(&quot;output.pdf&quot;, 595.275, 841.889);
     * </pre>
     * 
     * more generally, you can use get paper size information via GTK's
     * printing support using [<code>org.gnome.gtk</code>] PaperSize's
     * {@link org.gnome.gtk.PaperSize#getWidth(org.gnome.gtk.Unit) getWidth()}
     * and {@link org.gnome.gtk.PaperSize#getHeight(org.gnome.gtk.Unit)
     * getHeight()} methods, for example:
     * 
     * <pre>
     * paper = PaperSize.getDefault();
     * width = paper.getWidth(Unit.POINTS);
     * height = paper.getHeight(Unit.POINTS);
     * 
     * surface = new PdfSurface(&quot;output.pdf&quot;, width, height);
     * </pre>
     * 
     * saving you having to worry about just how big such paper really is.
     * 
     * @throws IOException
     *             If you do not have write permissions on the given file.
     * @since 4.0.10
     */
    public PdfSurface(String filename, double width, double height) throws IOException {
        super(CairoSurface.createSurfacePdf(filename, width, height));
        final Status status;

        status = CairoSurface.status(this);
        if (status == Status.WRITE_ERROR) {
            throw new IOException("Cairo reports it cannot open " + filename + " for writing");
        }
        checkStatus(status);
    }

    public void showPage() {
        CairoSurface.showPage(this);
        checkStatus();
    }

    public void copyPage() {
        CairoSurface.copyPage(this);
        checkStatus();
    }
}
