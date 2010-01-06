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
 */
package org.gnome.glib;

import org.gnome.gtk.Button;
import org.gnome.gtk.GraphicalTestCase;
import org.gnome.gtk.VBox;

/**
 * @author Andrew Cowie
 */
/*
 * I'm really not that sure how to test this effectively, but in the course of
 * debugging this test I uncovered a MASSIVE bug in the GObject code path, so
 * it was worth something :)
 */
public class ValidateReferenceCounting extends GraphicalTestCase
{
    public final void testManuallyTwistingRefCount() {
        Button b;
        VBox x;

        b = new Button("Reference counting");

        x = new VBox(false, 3);

        // sink the floating reference.
        x.add(b);

        // artificially remove java-gnome's reference to b. GObject should NOT
        // finalize the object, because it's packed into a Container.
        GObject.removeToggleRef(b);

        System.gc();
        // should not result in object being finalized, of course since we
        // have a Java reference to it.

        GObject.addToggleRef(b);
        x.remove(b);
        b = null;

        System.gc();
        // and with any luck, we haven't segfaulted, and the object finalized.
        // TODO can anyone suggest how the hell to test this better?
    }
}
