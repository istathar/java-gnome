/*
 * Surface.java
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

/**
 * The thing that Cairo will draw on/to. This is the base class for several
 * concrete back ends.
 * 
 * @author Andrew Cowie
 * @since 4.0.6
 */
public abstract class Surface extends Proxy
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
     * your own custom Widget in its <code>EXPOSE_EVENT</code> handler.
     * 
     * <p>
     * See also {@link #finish() finish()} if you're just trying to day "I'm
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

    public void writeToPNG(String filename) {
        CairoSurface.writeToPng(this, filename);
    }
}
