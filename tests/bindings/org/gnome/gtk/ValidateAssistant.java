/*
 * java-gnome, a UI library for writing GTK and GNOME programs from Java!
 *
 * Copyright © 2008-2010 Operational Dynamics Consulting, Pty Ltd and Others
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

/**
 * Test setting up an Assistant. Unfortunately you can't test
 * 
 * @author Stefan Prelle
 */
public class ValidateAssistant extends GraphicalTestCase
{
    public final void testAddingPages() {
        final Assistant druid;
        final Label page1, page2, page3;

        druid = new Assistant();
        page1 = new Label("Page1");
        page2 = new Label("Page1");
        page3 = new Label("Page1");
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
        final Label page1, page2, page3;

        druid = new Assistant();
        page1 = new Label("Page1");
        page2 = new Label("Page1");
        page3 = new Label("Page1");
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
        final Label page1, page2, page3;

        druid = new Assistant();
        page1 = new Label("Page1");
        page2 = new Label("Page1");
        page3 = new Label("Page1");
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
        final Label page1, page2, page3;

        druid = new Assistant();
        page1 = new Label("Page1");
        page2 = new Label("Page1");
        page3 = new Label("Page1");
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

    public void testForwardPageSignal() {
        final Assistant druid;
        final Label page1, page2, page3, page4;
        final int nextPage1, nextPage2;

        druid = new Assistant();
        page1 = new Label("Page1");
        page2 = new Label("Page2");
        page3 = new Label("Page3");
        page4 = new Label("Page4");
        druid.appendPage(page1);
        druid.appendPage(page2);
        druid.appendPage(page3);
        druid.appendPage(page4);

        druid.setPageType(page1, AssistantPageType.INTRO);
        druid.setPageType(page2, AssistantPageType.CONTENT);
        druid.setPageType(page3, AssistantPageType.CONTENT);
        druid.setPageType(page4, AssistantPageType.SUMMARY);

        druid.setForwardPageCallback(new Assistant.ForwardPage() {
            public int onForward(Assistant source, int currentPage) {
                int next;

                switch (currentPage) {
                case 1:
                    next = 3;
                    break;
                case 2:
                    next = 1;
                    break;
                default:
                    next = (currentPage + 1);
                    break;
                }

                return next;
            }
        });

        nextPage1 = druid.emitForwardPage(1);
        assertEquals(3, nextPage1);

        nextPage2 = druid.emitForwardPage(2);
        assertEquals(1, nextPage2);
    }
}
