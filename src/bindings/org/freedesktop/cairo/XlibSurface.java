/*
 * java-gnome, a UI library for writing GTK and GNOME programs from Java!
 *
 * Copyright © 2008-2011 Operational Dynamics Consulting, Pty Ltd and Others
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

/**
 * A Window as rendered by the X Server. Used internally when drawing to an
 * application window.
 * 
 * <p>
 * Ordinarily you don't think about creating an XlibSurface; one is implicitly
 * created for you when you create a Cairo context in order to draw in an
 * <code>Widget.Draw</code> signal handler:
 * 
 * <pre>
 * gizmo.connect(new Widget.Draw() {
 *     public boolean onDraw(Widget source, Context cr) {
 *         Surface surface;
 *         
 *         // start drawing
 *         
 *         // for interest's sake
 *         surface = cr.getTarget();
 *         assert (surface instanceof XlibSurface);
 *     }
 * }
 * </pre>
 * 
 * <h2>Efficient caching of image data</h2>
 * 
 * <p>
 * There is a fair bit of work involved in translating from an image in bitmap
 * form in application memory to the representation that will be used by the X
 * server in video memory. Thus if you are drawing frequently with the same
 * Pixbuf, you may find you are dramatically better off caching the image in
 * the X server.
 * 
 * <p>
 * The best way to do this is to create an XlibSurface with this image in it.
 * You set that image to be the source pattern with a call to
 * <code>setSource()</code> and then paint that onto the Context where you are
 * drawing. The key method involved is <code>createSimilar()</code>, which
 * allows you to create an X resource as a cache:
 * 
 * <pre>
 * gizmo.connect(new Widget.Draw() {
 *     private Surface cache;
 * 
 *     public boolean onDraw(Widget source, Context cr) {
 *         final Context cr2;
 *         final Surface target;
 *         final Pixbuf pixbuf;
 *         final double x, y;
 *         
 *         // start drawing
 *         ...
 *         
 *         // cache image
 *         if (cache == null) {
 *             pixbuf = new Pixbuf(filename);
 *             
 *             target = cr.getTarget();
 *             cache = target.createSimilar(Content.COLOR_ALPHA, pixbuf.getWidth(), pixbuf.getHeight());
 *             cr2 = new Context(cache);
 *             cr2.setSource(pixbuf, 0.0, 0.0);
 *             cr2.paint();
 *         }
 *         
 *         // now we can draw the image
 *         cr.setSource(cache, x, y);
 *         cr.paint();
 *     }
 * }
 * </pre>
 * 
 * note that this is <i>not</i> an ImageSurface; we've deliberately created
 * another XlibSurface which is the proxy around the X resource which the X
 * server can decide how best to blit with efficiently.
 * 
 * <p>
 * <i>Obviously you are consuming X server memory doing this, and it can be
 * overdone. You'll have to look at your application's performance to decide
 * whether this is necessary and if so, how much caching to do. Nevertheless,
 * modern graphics cards are very good at blitting images together so if you
 * are doing anything image intensive you are best to let the X server do the
 * work. Cairo is designed with this in mind.</i>
 * 
 * @author Andrew Cowie
 * @since 4.0.7
 */
/*
 * Thanks to Carl Worth, Davyd Madeley, and Mathias Hasselman for having
 * explained the code paths involved and the implications of efficiently using
 * X server memory and it in turn using video memory.
 */
public class XlibSurface extends Surface
{
    protected XlibSurface(long pointer) {
        super(pointer);
    }
}
