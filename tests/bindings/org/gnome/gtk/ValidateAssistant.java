/*
 * ValidateTreeModel.java
 *
 * Copyright (c) 2008 Operational Dynamics Consulting Pty Ltd, and Others
 * 
 * The code in this file, and the suite it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" See the LICENCE file for the terms governing usage and
 * redistribution.
 */
package org.gnome.gtk;

/**
 * Test setting up an Assistant. Unfortunately you can't test
 * 
 * @author Stefan Prelle
 */
public class ValidateAssistant extends GraphicalTestCase
{
    public final void testAddingPages() {
        final Assistant druid;

        druid = new Assistant();
        Label page1 = new Label("Page1");
        Label page2 = new Label("Page1");
        Label page3 = new Label("Page1");
        druid.appendPage(page1);
        druid.appendPage(page2);
        druid.appendPage(page3);

        assertEquals(3, druid.getNumPages());
        assertEquals(page3, druid.getPage(2));
        assertEquals(page2, druid.getPage(1));
        assertEquals(page1, druid.getPage(0));

        // Append another time
        try {
            druid.appendPage(page1);
            fail("Already in Assistant - should fail");
        } catch (Throwable e) {
            // OK
        }
    }

    public final void testInsertingPages() {
        final Assistant druid;

        druid = new Assistant();
        Label page1 = new Label("Page1");
        Label page2 = new Label("Page1");
        Label page3 = new Label("Page1");
        druid.appendPage(page1);
        druid.appendPage(page2);
        druid.insertPage(page3, 1);

        assertEquals(3, druid.getNumPages());
        assertEquals(page1, druid.getPage(0));
        assertEquals(page3, druid.getPage(1));
        assertEquals(page2, druid.getPage(2));

        // Append another time
        try {
            druid.insertPage(page1, 1);
            fail("Already in Assistant - should fail");
        } catch (Throwable e) {
            // OK
        }
    }

    public final void testPrependingPages() {
        final Assistant druid;

        druid = new Assistant();
        Label page1 = new Label("Page1");
        Label page2 = new Label("Page1");
        Label page3 = new Label("Page1");
        druid.prependPage(page1);
        druid.prependPage(page2);
        druid.prependPage(page3);

        assertEquals(3, druid.getNumPages());
        assertEquals(page3, druid.getPage(0));
        assertEquals(page2, druid.getPage(1));
        assertEquals(page1, druid.getPage(2));

        // Append another time
        try {
            druid.prependPage(page1);
            fail("Already in Assistant - should fail");
        } catch (Throwable e) {
            // OK
        }
    }

    public void testPrepareForDisplay() {
        final Assistant druid;

        druid = new Assistant();
        Label page1 = new Label("Page1");
        Label page2 = new Label("Page1");
        Label page3 = new Label("Page1");
        druid.appendPage(page1);
        druid.appendPage(page2);
        druid.appendPage(page3);

        try {
            druid.checkReadyForDisplay();
            fail("Should be missing types");
        } catch (Throwable e) {
            // OK
        }

        druid.setPageType(page3, AssistantPageType.CONFIRM);
        druid.checkReadyForDisplay();

        druid.setPageType(page3, AssistantPageType.SUMMARY);
        druid.checkReadyForDisplay();

        druid.setPageType(page3, AssistantPageType.CONTENT);
        try {
            druid.checkReadyForDisplay();
            fail("Should be missing types");
        } catch (Throwable e) {
            // OK
        }
    }
}
