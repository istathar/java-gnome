/*
 * Handle.java
 *
 * Copyright (c) 2009 Operational Dynamics Consulting Pty Ltd
 * 
 * The code in this file, and the library it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" plus the "Classpath Exception" (you may link to this code as a
 * library into other programs provided you don't make a derivation of it).
 * See the LICENCE file for the terms governing usage and redistribution.
 * 
 * This is essentially the same treatment as is found in org.gnome.gdk.Pixbuf
 */
package org.gnome.rsvg;

import java.io.File;
import java.io.FileNotFoundException;

import org.gnome.glib.GlibException;

/**
 * Handle to an SVG image in memory.
 * 
 * @author Andrew Cowie
 * @since 4.0.14
 */
public class Handle extends org.gnome.glib.Object
{
    public Handle() {
        super(RsvgHandle.createHandle());
    }

    public Handle(String filename) throws FileNotFoundException {
        super(checkHandleFromFile(filename));
    }

    /*
     * First check the file exists first, allowing us to isolate the GError
     * representing image format problems. The RsvgError is not very
     * impressive.
     */
    private static long checkHandleFromFile(String filename) throws FileNotFoundException {
        final File target;

        target = new File(filename);
        if (!target.exists()) {
            throw new FileNotFoundException(target + " not found");
        }
        try {
            return RsvgHandle.createHandleFromFile(filename);
        } catch (GlibException ge) {
            throw new RuntimeException(ge.getMessage());
        }
    }

}
