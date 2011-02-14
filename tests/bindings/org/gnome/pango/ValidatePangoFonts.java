/*
 * java-gnome, a UI library for writing GTK and GNOME programs from Java!
 *
 * Copyright © 2011 Operational Dynamics Consulting, Pty Ltd
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
package org.gnome.pango;

import org.freedesktop.cairo.Format;
import org.freedesktop.cairo.ImageSurface;
import org.gnome.gtk.GraphicalTestCase;

/**
 * Test the Pango Font loading logic. This is a problematic test, since its
 * outcomes depend on the following fonts being installed: DejaVu, Corefonts,
 * Liberation, and Arkandis Gillius. FIXME None of these are "dependencies" of
 * java-gnome so this can't run by UnitTests. Any ideas?
 * 
 * @author Andrew Cowie
 */
public class ValidatePangoFonts extends GraphicalTestCase
{
    private static FontDescription loadFont(final FontDescription request) {
        final FontDescription actual;
        final Layout layout;
        final org.freedesktop.cairo.Surface surface;
        final org.freedesktop.cairo.Context cr;
        final Context context;
        final Font font;

        /*
         * Setup
         */

        surface = new ImageSurface(Format.RGB24, 100, 100);
        cr = new org.freedesktop.cairo.Context(surface);

        layout = new Layout(cr);
        context = layout.getContext();

        /*
         * Now test
         */

        font = context.loadFont(request);
        actual = font.describe();

        return actual;
    }

    /*
     * This depends on Deja Vu Sans being the actual default fallback font.
     * Can't really assume that, can we?
     */
    public final void testLoadKnownFallback() {
        final FontDescription request, actual;
        final String family;
        final Style style;

        request = new FontDescription("Deja Vu Sans");
        actual = loadFont(request);

        assertNotSame(actual, request);

        family = actual.getFamily();
        style = actual.getStyle();

        assertEquals("DejaVu Sans", family);
        assertSame(Style.NORMAL, style);
    }

    public final void testLoadWrongName() {
        final FontDescription request, actual;
        final String family;

        request = new FontDescription("Times New");
        actual = loadFont(request);

        family = actual.getFamily();

        assertEquals("DejaVu Sans", family);
    }

    /*
     * This depends on Microsoft Corefonts being installed. Not going to make
     * that a prerequisite of java-gnome, are we!
     */
    public final void testLoadCorrectName() {
        final FontDescription request, actual;
        final String family;

        request = new FontDescription("Times New Roman,");
        actual = loadFont(request);

        family = actual.getFamily();

        assertEquals("Times New Roman", family);
    }

    /*
     * This depends on Liberation being installed. Not unreasonable, but
     * again, should java-gnome depend on it? No.
     */
    public final void testLoadMinimumName() {
        final FontDescription request, actual;
        final String family;

        request = new FontDescription("Liberation Sans,");
        actual = loadFont(request);

        family = actual.getFamily();

        assertEquals("Liberation Sans", family);
    }

    public final void testLoadMinimumGilus() {
        final FontDescription request, actual;
        final String family;

        request = new FontDescription();
        request.setFamily("Gillius ADF No2 Cd");
        actual = loadFont(request);

        family = actual.getFamily();

        assertEquals("Gillius ADF No2 Cd", family);
    }
}
