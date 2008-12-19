/*
 * ImageSurface.java
 *
 * Copyright (c) 2008 Operational Dynamics Consulting Pty Ltd
 * 
 * The code in this file, and the library it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" plus the "Classpath Exception" (you may link to this code as a
 * library into other programs provided you don't make a derivation of it).
 * See the LICENCE file for the terms governing usage and redistribution.
 */
package org.freedesktop.cairo;

/**
 * A Window as rendered by the X Server. Used internally when drawing to an
 * application window. Ordinarily you don't directly create an XlibSurface;
 * one is implicitly created for you when drawing in an Widget.ExposeEvent
 * handler.
 * 
 * <pre>
 * gizmo.connect(new Widget.ExposeEvent() {
 *     public boolean onExposeEvent(Widget source, EventExpose event) {
 *         Context cr;
 *         Surface surface;
 *         
 *         cr = new Context(source.getWindow());
 *         
 *         // start drawing
 *         
 *         // and for interest's sake,
 *         surface = cr.getTarget();
 *         assert (surface instanceof XlibSurface);
 *     }
 * }
 * </pre>
 * 
 * <h2>Efficient caching of image data</h2>
 * 
 * <p>
 * There is a fair bit of work invovled in translating from an image in bitmap
 * form in application memory to the representation that will be used by the X
 * server in video memory. Thus if you are doing this operation frequently
 * with the same Pixbuf, you may find you are dramatically better off caching
 * the image in the X server.
 * 
 * <p>
 * The best way to do this is to create an XlibSurface with this image in it.
 * You can the <code>setSource()</code> with that Surface and then paint with
 * that onto the Context where you are drawing. The key method involved is
 * <code>createSimilar()</code>.
 * 
 * <pre>
 * private Surface cache;
 * 
 * private void cacheImage(Surface target, Pixbuf image) {
 *     Context cr;
 * 
 *     assert(target instanceof XlibSurface);
 * 
 *     cache = target.createSimilar(Content.COLOR_ALPHA, image.getWidth(), image.getHeight());
 *     cr = new Context(cache);
 *     cr.setSource(image);
 *     cr.paint();
 * }
 * </pre>
 * 
 * then you can use the cached X resource as follows:
 * 
 * <pre>
 * gizmo.connect(new Widget.ExposeEvent() {
 *     public boolean onExposeEvent(Widget source, EventExpose event) {
 *         Context cr, dr;
 *         Surface target;
 *         
 *         cr = new Context(source.getWindow());
 *         
 *         // start drawing
 *         
 *         // cache image
 *         if (cache == null) {
 *              cacheImage(cr.getTarget(););
 *         }
 *         
 *         cr.setSource(cache);
 *         cr.paint();
 *     }
 * }
 * </pre>
 * 
 * note that this is <i>not</i> an ImageSurface; we've deliberately created
 * another XlibSurface which is the proxy around the X resource which the X
 * server can decide how best to blit with efficiently.
 * 
 * @author Andrew Cowie
 * @since 4.0.7
 */
public class XlibSurface extends Surface
{
    protected XlibSurface(long pointer) {
        super(pointer);
    }
}
