/*
 * ValidateReferenceCounting.java
 *
 * Copyright (c) 2007 Operational Dynamics Consulting Pty Ltd
 * 
 * The code in this file, and the suite it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" See the LICENCE file for the terms governing usage and
 * redistribution.
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
