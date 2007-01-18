/*
 * Xml.java
 *
 * Copyright (c) 2007 Operational Dynamics Consulting Pty Ltd
 * 
 * The code in this file, and the library it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" plus the "Classpath Exception" (you may link to this code as a
 * library into other programs provided you don't make a derivation of it).
 * See the LICENCE file for the terms governing usage and redistribution.
 */
package org.gnome.glade;

import org.gnome.glib.Object;
import org.gnome.gtk.Widget;

/**
 * This class proxies the internal representation used by
 * <code>libglade</code> to represent the processed <code>.glade</code>
 * XML data. Ideally we wouldn't expose such a thing, but it turns out that
 * the library's power is accessed care of methods on this Object.
 * 
 * <p>
 * <i>There comes a point when the underlying mapping becomes a bit
 * rediculous, but the underlying structure is <code>GladeXML</code>, so
 * according to our mapping algorithm, Xml it is. Parser or ParsedTree or some
 * such would have been better, but oh well.</i>
 * 
 * @author Andrew Cowie
 * @see Glade
 * @since 4.0.2
 */
public class Xml extends Object
{
    protected Xml(long pointer) {
        super(pointer);
    }

    /**
     * Creates a new Glade widget tree. This is internal as we provide the
     * {@link org.gnome.glade.Glade} convenience class to wrap this.
     */
    Xml(String filename, String root) {
        super(GladeXml.createGladeXml(filename, root));
    }

    /**
     * Get the Widget corresponding to the given name.
     * 
     * @since 4.0.2
     */
    public Widget getWidget(String name) {
        return GladeXml.getWidget(this, name);
    }
}
