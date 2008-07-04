/*
 * ValidateProperties.java
 *
 * Copyright (c) 2007-2008 Operational Dynamics Consulting Pty Ltd
 * 
 * The code in this file, and the suite it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" See the LICENCE file for the terms governing usage and
 * redistribution.
 */
package org.gnome.gtk;

import java.io.FileNotFoundException;

import org.gnome.gdk.Pixbuf;

/**
 * Test characteristic getters and setters to ensure correct values are
 * retrieved.
 * 
 * @author Andrew Cowie
 */
public class ValidateProperties extends TestCaseGtk
{
    public final void testStringValues() {
        final Value v = new Value("Hello");
        assertEquals("Hello", v.getString());
    }

    public final void testIntegerValues() {
        final Value v = new Value(-42);
        assertEquals(-42, v.getInteger());
    }

    public final void testLongValues() {
        final Value v = new Value(600000000000L);
        assertEquals(600000000000L, v.getLong());
    }

    public final void testBooleanValues() {
        final Value v1, v2;

        v1 = new Value(true);
        assertEquals(true, v1.getBoolean());

        v2 = new Value(false);
        assertEquals(false, v2.getBoolean());
    }

    /*
     * GValue.g_value_init(float) had a cut and paste bug.
     */
    public final void testFloatValues() {
        final Value v = new Value(0.1f);
        assertEquals(0.1f, v.getFloat(), 0.0001);
    }

    /**
     * Test changing the label of a Button and getting the label back.
     */
    public final void testButtonPropertyLabel() {
        final Button b;
        final String originalLabel = "Hello wonderful World";
        final String endingLabel = "Goodbye cruel World";

        b = new Button(originalLabel);
        assertEquals(
                "The text retrieved by getLabel() was not that set by the Button <init>() constructor!",
                b.getLabel(), originalLabel);

        b.setLabel(endingLabel);
        assertEquals(
                "Incorrect text retrieved from Button's getLabel() following a call to setLabel() with that text!",
                b.getLabel(), endingLabel);
    }

    /**
     * Test ReliefStyle, a series of enum constants. Attempt to set a non-zero
     * enum, and make sure we get it back, by identity
     */
    public final void testButtonReliefStyle() {
        final Button b;

        b = new Button();

        // Button's getRelief() is temporarily implemented with
        // getPropertyEnum().
        assertSame("Default ReliefStyle is not what was expected, validity of test case is affected.",
                b.getRelief(), ReliefStyle.NORMAL);

        // NONE is #3 (ie ordinal 2, not zero)
        ReliefStyle rs = ReliefStyle.NONE;
        b.setRelief(rs);

        // verify we get that object back.
        assertSame("Set ReliefStyle constant is not the object that was retrieved when calling getter!",
                b.getRelief(), rs);
    }

    public final void testAlignmentConstructorProperties() {
        final Alignment a;

        a = new Alignment(0.1f, 0.2f, 0.8f, 0.9f);
        assertEquals(0.1f, a.getAlignmentX(), 0.001);
        assertEquals(0.2f, a.getAlignmentY(), 0.001);
        assertEquals(0.8f, a.getScaleX(), 0.001);
        assertEquals(0.9f, a.getScaleY(), 0.001);
    }

    public final void testAlignmentParameterChecks() {
        Alignment a;

        try {
            a = new Alignment(-0.1f, 0.2f, 0.8f, 0.9f);
            fail("Should have thrown exception due to negative paramater");
        } catch (IllegalArgumentException iae) {
            //
        }
        try {
            a = new Alignment(1.1f, 0.2f, 0.8f, 0.9f);
            fail("Should have thrown exception due to paramater out of bounds");
        } catch (IllegalArgumentException iae) {
            //
        }

        a = new Alignment(0.1f, 0.2f, 0.8f, 0.9f);
        a.setAlignment(0.3f, 0.4f, 0.5f, 0.6f);

        assertEquals(0.3f, a.getAlignmentX(), 0.001);
        assertEquals(0.4f, a.getAlignmentY(), 0.001);
        assertEquals(0.5f, a.getScaleX(), 0.001);
        assertEquals(0.6f, a.getScaleY(), 0.001);

        try {
            a.setAlignment(0.3f, 0.4f, 0.5f, -0.6f);
            fail("Should have thrown exception due to paramater out of bounds");
        } catch (IllegalArgumentException iae) {
            //
        }
        assertEquals(0.6f, a.getScaleY(), 0.001);
    }

    /*
     * Just a quick check deciding to expose some constants.
     */
    public final void testAlignmentConstants() {
        assertEquals(0.0f, Alignment.LEFT, 0.0001f);
        assertEquals(0.5f, Alignment.CENTER, 0.0001f);
        assertEquals(1.0f, Alignment.RIGHT, 0.0001f);
        assertEquals(0.0f, Alignment.TOP, 0.0001f);
        assertEquals(1.0f, Alignment.BOTTOM, 0.0001f);
    }

    public final void testButtonImageMethods() {
        final Button b;
        final Image i;

        b = new Button("Hello There!");
        assertNull(b.getImage());

        // doesn't matter that it'll be the "broken image" icon as a result
        i = new Image("/dev/null");

        b.setImage(i);
        assertNotNull(b.getImage());
        assertEquals(i, b.getImage());
    }

    public final void testAllocationLive() {
        final Window w;
        final Button b;
        Allocation size;

        b = new Button("Blah");
        size = b.getAllocation();

        /*
         * These were derived empirically. A bit unexpected, but hey.
         */
        assertEquals(1, size.getWidth());
        assertEquals(1, size.getHeight());
        assertEquals(-1, size.getX());
        assertEquals(-1, size.getY());

        w = new Window();
        w.add(b);
        w.showAll();

        /*
         * Now test that the our live reference to the GtkAllocation actually
         * changed. Note that we did NOT get a new Allocation object.
         */
        assertTrue(size.getWidth() > 1);
        assertTrue(size.getHeight() > 1);
        assertTrue(size.getX() >= 0);
        assertTrue(size.getY() >= 0);

        w.hide();

        /*
         * and is it indeed still the same pointer, ie the same address, ie
         * the same Proxy?
         */
        assertSame(size, b.getAllocation());
    }

    public final void testRequisitionSizeRequest() {
        final Button b;
        Requisition req;
        int width, height;

        b = new Button("Blah");

        /*
         * This isn't quite as isolated a test as it might be; because we've
         * got this calling gtk_widget_size_request() on the C side if a
         * request isn't set, we can't check that it changed due to invoking
         * this.
         */

        req = b.getRequisition();

        width = req.getWidth();
        height = req.getHeight();
        assertTrue(width > 1);
        assertTrue(height > 1);
    }

    /*
     * FUTURE Given the elaborate relationship between resize(),
     * setDefaultSize(), and setSizeRequest(), this could probably be a lot
     * more stringent.
     */
    public final void testResizeRestrictions() {
        final Window w;

        w = new Window();

        try {
            w.resize(0, 1);
            fail("Shouldn't accept sub-minimum sizes.");
        } catch (IllegalArgumentException iae) {
            // good
        }

        try {
            w.resize(1, 0);
            fail("Shouldn't accept sub-minimum sizes.");
        } catch (IllegalArgumentException iae) {
            // good
        }

        w.resize(1, 1);
    }

    /*
     * TODO make this a bit more meaningful; at the moment all it is doing is
     * making sure that no FatalErrors are blown by switching from an Image
     * storing one type of image to another; (yes, one would expect this to
     * Just Work; but that's the point of this test as it stands so far).
     */
    public final void testImageResetting() throws FileNotFoundException {
        final Image i;
        final Pixbuf data;

        i = new Image(Stock.REFRESH, IconSize.LARGE_TOOLBAR);

        data = new Pixbuf("web/public/images/java-gnome_LargeLogo.png");
        i.setImage(data);
        i.clear();
    }

    public final void testEntryStyleProperties() {
        final Entry entry;

        entry = new Entry();

        /*
         * Check default
         */
        assertEquals(true, GtkEntry.getHasFrame(entry));

        /*
         * Check our setter
         */

        entry.setHasFrame(false);
        assertEquals(false, GtkEntry.getHasFrame(entry));
    }

    public final void testButtonFocus() {
        final Button button;

        button = new Button("Hello");

        /*
         * Check default
         */
        assertEquals(true, GtkButton.getFocusOnClick(button));

        /*
         * Check our setter
         */

        button.setFocusOnClick(false);
        assertEquals(false, GtkButton.getFocusOnClick(button));
    }
}
