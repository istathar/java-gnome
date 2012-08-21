/*
 * java-gnome, a UI library for writing GTK and GNOME programs from Java!
 *
 * Copyright © 2012 Operational Dynamics Consulting, Pty Ltd and Others
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

import org.gnome.gtk.GraphicalTestCase;

/**
 * Investigate returns from our File class.
 * 
 * @author Guillaume Mazoyer
 */
public class ValidateGFileMethods extends GraphicalTestCase
{
    public final void testGFileInstanciation() {
        final File file;

        file = new File("/etc");
        assertTrue(file != null);
    }

    public final void testGFilePath() {
        final File file;

        file = new File("/etc");
        assertEquals("/etc", file.getPath());
    }
}
