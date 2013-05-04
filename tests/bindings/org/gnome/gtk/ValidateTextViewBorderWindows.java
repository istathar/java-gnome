/*
 * java-gnome, a UI library for writing GTK and GNOME programs from Java!
 *
 * Copyright © 2008-2010 Operational Dynamics Consulting, Pty Ltd
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
package org.gnome.gtk;

import static org.gnome.gtk.TextWindowType.LEFT;
import static org.gnome.gtk.WrapMode.WORD;

/**
 * Evaluate the conversions between buffer co-ordinates and window
 * co-ordinates. Also exercises some of the scrolling code.
 * 
 * @author Andrew Cowie
 */
public class ValidateTextViewBorderWindows extends GraphicalTestCase
{
    public final void testDontAllowAddWithViewport() {
        final TextView view;
        final ScrolledWindow scroll;

        view = new TextView();
        scroll = new ScrolledWindow();

        try {
            scroll.addWithViewport(view);
            fail("Should have been prevented");
        } catch (IllegalArgumentException iae) {
            // good
        }
    }

    /*
     * If you want to see what the heck this is doing, add a showAll() to the
     * Window and a Gtk.main() towards the end. TODO Needs something that
     * tests horizontal (x and X) as well as vertical.
     */
    public final void testCovertCoordinatesRoundTrip() {
        final TextTagTable table;
        final TextView view;
        final TextBuffer buffer;
        final ScrolledWindow scroll;
        final Window window;
        TextIter pointer;
        int y, Y;

        table = new TextTagTable();
        buffer = new TextBuffer(table);
        view = new TextView(buffer);

        view.setBorderWindowSize(LEFT, 50);

        for (int i = 0; i < 10; i++) {
            buffer.insertAtCursor(i + ". This is a test");
            for (int j = 0; j < 8; j++) {
                buffer.insertAtCursor(" of the emergency broadcast system");
            }
            buffer.insertAtCursor("\n");
        }

        view.setWrapMode(WORD);

        scroll = new ScrolledWindow();
        scroll.setPolicy(PolicyType.NEVER, PolicyType.ALWAYS);
        scroll.add(view);
        window = new Window();
        window.add(scroll);
        window.setDefaultSize(300, 200);

        pointer = buffer.getIterStart();
        pointer.setLine(7);
        pointer.setLineOffset(5);

        buffer.placeCursor(pointer);
        view.scrollTo(buffer.getInsert());

        /*
         * Critical to ensure that the necessary [re]calculations have
         * occurred. Much of the TextView processing occurs in idle callbacks.
         */

        cycleMainLoop();

        Y = view.getLineY(pointer);

        y = view.convertBufferToWindowCoordsY(LEFT, Y);

        assertTrue(Y > 0);
        assertTrue(y <= Y);

        assertEquals(Y, view.convertWindowToBufferCoordsY(LEFT, y));

        /*
         * Now test the origin. The window co-ordinate will be somewhere "off"
         * screen to the north/up direction.
         */

        pointer = buffer.getIterStart();
        Y = view.getLineY(pointer);
        y = view.convertBufferToWindowCoordsY(LEFT, Y);

        assertEquals(0, Y);
        assertTrue(y <= 0);
        assertEquals(Y, view.convertWindowToBufferCoordsY(LEFT, y));
    }
}
