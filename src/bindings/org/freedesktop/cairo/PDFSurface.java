/*
 * PDFSurface.java
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

import java.io.IOException;

/**
 * A Surface that will be rendered to a PDF file.
 * 
 * @author Andrew Cowie
 * @since 4.0.8
 */
public class PDFSurface extends Surface
{
    protected PDFSurface(long pointer) {
        super(pointer);
    }

    public PDFSurface(String filename, int width, int height) throws IOException {
        super(CairoSurface.createSurfacePdf(filename, width, height));
        Status status = CairoSurface.status(this);
        if (status == Status.WRITE_ERROR) {
            throw new IOException("Cairo reports it cannot write open " + filename + " for writing");
        }
        checkStatus(status);
    }
}
