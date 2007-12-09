/*
 * ExampleCairoDrawing.java
 *
 * Copyright (c) 2007 Operational Dynamics Consulting Pty Ltd
 * 
 * The code in this file, and the library it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" See the LICENCE file for the terms governing usage and
 * redistribution.
 */
package cairo;

import org.freedesktop.cairo.Context;
import org.freedesktop.cairo.Format;
import org.freedesktop.cairo.ImageSurface;
import org.gnome.gtk.Gtk;

/**
 * Exercise drawing with the Cairo API.
 * 
 * @author Andrew Cowie
 */
public class ExampleCairoDrawing
{
    public static void main(String[] args) {
        final Context cr;
        final ImageSurface surface;

        Gtk.init(args);

        surface = new ImageSurface(Format.ARGB32, 100, 100);
        cr = new Context(surface);

        cr.setSourceRGBA(0.0, 0.0, 1.0, 0.8);
        cr.moveTo(10, 10);
        cr.lineTo(20, 5);
        cr.stroke();

        surface.writeToPNG("out.png");
    }
}
