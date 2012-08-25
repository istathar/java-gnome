/*
 * java-gnome, a UI library for writing GTK and GNOME programs from Java!
 *
 * Copyright © 2007-2011 Operational Dynamics Consulting, Pty Ltd and Others
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
 *
 * Linking this library statically or dynamically with other modules is making
 * a combined work based on this library. Thus, the terms and conditions of
 * the GPL cover the whole combination. As a special exception (the
 * "Classpath Exception"), the copyright holders of this library give you
 * permission to link this library with independent modules to produce an
 * executable, regardless of the license terms of these independent modules,
 * and to copy and distribute the resulting executable under terms of your
 * choice, provided that you also meet, for each linked independent module,
 * the terms and conditions of the license of that module. An independent
 * module is a module which is not derived from or based on this library. If
 * you modify this library, you may extend the Classpath Exception to your
 * version of the library, but you are not obligated to do so. If you do not
 * wish to do so, delete this exception statement from your version.
 */
package org.gnome.gtk;

/**
 * Base class for Widgets which present an adjustable quantity in some form of
 * slider. The most obvious feature of this class is the ability to manage the
 * "value" being shown by the Widget, but there are also facilities for
 * exercising fine-grained control over the behaviour of the Widget when the
 * user attempts to adjust the slider.
 * 
 * @author Andrew Cowie
 * @since 4.0.6
 */
/*
 * TODO Add coverage of the step and increment controls, obviously. As I
 * recall, the interactions of these with Adjustment are more than complex, so
 * please test carefully and document your experiences well.
 */
public abstract class Range extends Widget implements Orientable
{
    protected Range(long pointer) {
        super(pointer);
    }

    /**
     * Get whether or not the rate scale of this Range is inverted; see
     * {@link #setInverted(boolean) setInverted()}.
     * 
     * @since 4.0.12
     */
    public boolean getInverted() {
        return GtkRange.getInverted(this);
    }

    /**
     * Inverts the way the value changes when moving the slider.
     * 
     * <p>
     * Ranges normally move from lower to higher values as the slider moves
     * from top to bottom or left to right. Inverted ranges have higher values
     * at the top or on the right rather than on the bottom or left.
     * 
     * @since 4.0.12
     */
    public void setInverted(boolean setting) {
        GtkRange.setInverted(this, setting);
    }

    /**
     * Retrieve the value currently indicated by this Range instance.
     * 
     * @since 4.0.6
     */
    public double getValue() {
        return GtkRange.getValue(this);
    }

    /**
     * Change the value showingin the Range. As you would expect, the
     * <code>Range.ValueChanged</code> signal will be emitted if the new value
     * is different from the present setting.
     * 
     * @since 4.0.6
     */
    public void setValue(double value) {
        GtkRange.setValue(this, value);
    }

    /**
     * The value showing in the Range instance has changed.
     * 
     * @author Andrew Cowie
     * @since 4.0.6
     */
    public interface ValueChanged extends GtkRange.ValueChangedSignal
    {
        public void onValueChanged(Range source);
    }

    /**
     * Connect a <code>Range.ValueChanged</code> handler to this Range
     * instance.
     * 
     * @since 4.0.6
     */
    public void connect(Range.ValueChanged handler) {
        GtkRange.connect(this, handler, false);
    }

    public void setOrientation(Orientation orientation) {
        GtkOrientable.setOrientation(this, orientation);
    }

    public Orientation getOrientation() {
        return GtkOrientable.getOrientation(this);
    }
}
