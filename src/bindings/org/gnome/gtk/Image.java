/*
 * Image.java
 *
 * Copyright (c) 2007 Operational Dynamics Consulting Pty Ltd, and Others
 *
 * The code in this file, and the library it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" plus the "Classpath Exception" (you may link to this code as a
 * library into other programs provided you don't make a derivation of it).
 * See the LICENCE file for the terms governing usage and redistribution.
 */
package org.gnome.gtk;

import org.gnome.gdk.Pixbuf;

/**
 * A Widget that displays an image.
 * 
 * @author Sebastian Mancke
 * @author Andrew Cowie
 * @since 4.0.3
 */
public class Image extends Misc
{
    protected Image(long pointer) {
        super(pointer);
    }

    /**
     * Construct a new Image Widget from the image located at the specified
     * path. This always results in a new Image; if the file is not found the
     * Image will be populated with the "broken image" icon.
     */
    /*
     * I'm not entirely convinced about this - I am inclined instead to have
     * this throw FileNotFoundException; FUTURE revisit this question when we
     * bind GdkPixbuf.createPixbufFromFile().
     */
    public Image(String filename) {
        super(GtkImage.createImageFromFile(filename));
    }

    /**
     * Construct a new Image Widget from an image already loaded into a
     * Pixbuf.
     * 
     * @since 4.0.5
     */
    public Image(Pixbuf pixbuf) {
        super(GtkImage.createImageFromPixbuf(pixbuf));
    }
}
