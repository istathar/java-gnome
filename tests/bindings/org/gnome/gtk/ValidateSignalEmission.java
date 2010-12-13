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
package org.gnome.gtk;

/**
 * Test methods which cause signal emission.
 * 
 * @author Andrew Cowie
 */
/*
 * Originally this tested Adjustment's emitValueChanged() and emitChanged(),
 * but I have a feeling those predate proper setters on that class.
 */
public class ValidateSignalEmission extends GraphicalTestCase
{
    private boolean result;

    public void setUp() {
        super.setUp();
        result = false;
    }

    public final void testButtonClicked() {
        final Button b;

        assertFalse(result);

        b = new Button("Hello");
        b.connect(new Button.Clicked() {
            public void onClicked(Button source) {
                assertEquals("Hello", source.getLabel());
                result = true;
            }
        });
        b.emitClicked();

        assertTrue("Button.Clicked wasn't handled", result);
    }

    public final void testAdjustmentValueChanged() {
        final Adjustment adj;

        assertFalse(result);

        adj = new Adjustment(5, 0, 100, 2, 5, 10);
        adj.connect(new Adjustment.ValueChanged() {
            public void onValueChanged(Adjustment source) {
                result = true;
            }
        });
        adj.emitValueChanged();

        assertTrue("Adjustment.ValueChanged wasn't handled", result);
    }

    public final void testAdjustmentOtherChanged() {
        final Adjustment adj;

        assertFalse(result);

        adj = new Adjustment(5, 0, 100, 2, 5, 10);
        adj.connect(new Adjustment.Changed() {
            public void onChanged(Adjustment source) {
                result = true;
            }
        });
        adj.emitChanged();

        assertTrue("Adjustment.Changed wasn't handled", result);
    }

    public final void testAdjustmentValueChangedBySetter() {
        final Adjustment adj;

        assertFalse(result);

        adj = new Adjustment(5, 0, 100, 2, 5, 10);
        adj.connect(new Adjustment.ValueChanged() {
            public void onValueChanged(Adjustment source) {
                result = true;
            }
        });
        adj.setValue(99.0);

        assertTrue("Adjustment.ValueChanged wasn't emitted", result);
    }

    public final void testAdjustmentOtherChangedBySetter() {
        final Adjustment adj;

        assertFalse(result);

        adj = new Adjustment(5, 0, 100, 2, 5, 10);
        adj.connect(new Adjustment.Changed() {
            public void onChanged(Adjustment source) {
                result = true;
            }
        });
        adj.setUpper(250.0);

        assertTrue("Adjustment.Changed wasn't emitted", result);
    }
}
