/*
 * Pixbuf.java
 *
 * Copyright (c) 2006 Operational Dynamics Consulting Pty Ltd and Others
 * 
 * The code in this file, and the library it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" plus the "Classpath Exception" (you may link to this code as a
 * library into other programs provided you don't make a derivation of it).
 * See the LICENCE file for the terms governing usage and redistribution.
 */
package org.gnome.gdk;

/**
 * An image in memory.
 * 
 * <p>
 * <i>Image data in a Pixbuf is stored in memory in uncompressed, packed
 * format. Rows in the image are stored top to bottom, and in each row pixels
 * are stored from left to right. There may be padding at the end of a row.
 * The "rowstride" value of a pixbuf, as returned by
 * {@link #getRowstride() getRowstride()}, indicates the number of bytes
 * between rows.</i>
 * 
 * @author Andrew Cowie
 * @since 4.0.0
 */
/*
 * TODO This class is a minimal skeleton only.
 */
public class Pixbuf extends org.gnome.glib.Object
{
    protected Pixbuf(long pointer) {
        super(pointer);
    }
}
