/*
 * java-gnome, a UI library for writing GTK and GNOME programs from Java!
 *
 * Copyright © 2007-2010 Operational Dynamics Consulting, Pty Ltd
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
 * "Claspath Exception"), the copyright holders of this library give you
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
package org.gnome.glade;

import org.gnome.glib.Object;
import org.gnome.gtk.Widget;

/**
 * This class proxies the internal representation used by
 * <code>libglade</code> to represent the processed <code>.glade</code> XML
 * data. Ideally we wouldn't expose such a thing, but it turns out that the
 * library's power is accessed care of methods on this Object.
 * 
 * <p>
 * <i>There comes a point when the underlying mapping becomes a bit
 * ridiculous, but the underlying structure is <code>GladeXML</code>, so
 * according to our mapping algorithm, XML it is. Parser or ParsedTree or some
 * such would have been better, but oh well.</i>
 * 
 * <p>
 * <i>Note that this class name changed from Xml to XML in 4.0.3 when we
 * generated from the actual upstream .defs data for libglade</i>
 * 
 * @author Andrew Cowie
 * @see Glade
 * @since 4.0.2
 */
public class XML extends Object
{
    protected XML(long pointer) {
        super(pointer);
    }

    /**
     * Creates a new Glade widget tree. This is internal as we provide the
     * {@link org.gnome.glade.Glade#parse(String, String)} convenience class
     * to wrap this.
     */
    XML(String filename, String root) {
        super(createXML(filename, root));
    }

    private static long createXML(String fname, String root) {
        long ptr;

        ptr = GladeXML.createXML(fname, root, null);

        if (ptr == 0L) {
            // TODO replace with proper log handling.
            throw new IllegalStateException("\nParsing the specified Glade XML file,\n" + fname
                    + "\nresulted in a null return. That's a show stopper.");
        }

        return ptr;
    }

    /**
     * Get the Widget corresponding to the given name.
     * 
     * @since 4.0.2
     */
    public Widget getWidget(String name) {
        if (name == null) {
            throw new IllegalArgumentException();
        }
        return GladeXML.getWidget(this, name);
    }
}
