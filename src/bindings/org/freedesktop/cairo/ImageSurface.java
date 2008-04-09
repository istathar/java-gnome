/*
 * ImageSurface.java
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

/**
 * A Surface which is an image in memory and can be written to disk. If
 * drawing to an image you intend to write to a PNG, you would end up doing
 * something like:
 * 
 * <pre>
 * surface = new ImageSurface(Format.ARGB32, 100, 100);
 * cr = new Context(surface);
 * 
 * // do drawing 
 * 
 * surface.writeToPNG(filename);
 * </pre>
 * 
 * @author Andrew Cowie
 * @since 4.0.7
 */
public class ImageSurface extends Surface
{
    protected ImageSurface(long pointer) {
        super(pointer);
    }

    public ImageSurface(Format format, int width, int height) {
        super(CairoImageSurface.createSurface(format, width, height));
        checkStatus();
    }
}
