/*
 * ValidateTextViewBorderWindows.java
 *
 * Copyright (c) 2008 Operational Dynamics Consulting Pty Ltd
 * 
 * The code in this file, and the suite it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" See the LICENCE file for the terms governing usage and
 * redistribution.
 */
package org.gnome.gtk;

import static org.gnome.gtk.TextWindowType.LEFT;
import static org.gnome.gtk.WrapMode.WORD;

/**
 * Evaluate the conversions between buffer co-ordinates and window
 * co-ordinates.
 * 
 * @author Andrew Cowie
 */
public class ValidateTextViewBorderWindows extends TestCaseGtk
{
    /*
     * If you want to see what the heck this is doing, add a showAll() to the
     * Window and a Gtk.main() towards the end.
     */
    public final void testCovertCoordinatesRoundTrip() {
        final TextTagTable table;
        final TextView view;
        final TextBuffer buffer;
        final ScrolledWindow scroll;
        final Window window;
        TextIter pointer;
        int x, y, X, Y;

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
        view.scrollMarkOnscreen(buffer.getInsert());

        /*
         * Critical to ensure that the necessary [re]calculations have
         * occurred. Much of the TextView processing occurs in idle callbacks.
         */

        cycleMainLoop();

        Y = view.getLineY(pointer);

        y = view.convertBufferToWindowCoordsY(LEFT, Y);

        assertTrue(Y > 0);
        assertTrue(y < Y);

        assertEquals(Y, view.convertWindowToBufferCoordsY(LEFT, y));

        /*
         * Now test the origin. The window co-ordinate will be somewhere "off"
         * screen to the north/up direction.
         */

        pointer = buffer.getIterStart();
        Y = view.getLineY(pointer);
        y = view.convertBufferToWindowCoordsY(LEFT, Y);

        assertEquals(0, Y);
        assertTrue(y < 0);
        assertEquals(Y, view.convertWindowToBufferCoordsY(LEFT, y));

        if (false) {
            window.showAll();
            runMainLoop(window);
        }
    }
}
