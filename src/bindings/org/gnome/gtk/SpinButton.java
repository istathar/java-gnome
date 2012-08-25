/*
 * java-gnome, a UI library for writing GTK and GNOME programs from Java!
 *
 * Copyright © 2007-2010 Operational Dynamics Consulting, Pty Ltd
 * Copyright © 2008      Vreixo Formoso
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
 * A SpinButton is a little text entry used as input for numeric values in a
 * given range. It incorporates two little arrow Buttons that user can click
 * to increment or decrement the value by a given amount.
 * 
 * <p>
 * It is used to let the user introduce the value of a numeric property. This
 * is specially useful when the range of allowed values is unlimited (well, in
 * practise limited by <code>Double.MAX_VALUE</code>!) or only limited at one
 * end. Otherwise, a control like {@link Scale} may be a better alternative.
 * 
 * <p>
 * It is also a good idea to add near the SpinButton a Label indicating the
 * units of measure of the property it refers to.
 * 
 * <p>
 * The user can modify the SpinButton either introducing a numeric value in
 * the required range in the text entry, or by clicking the arrow Buttons. The
 * keyboard can also be used, with both the <b><code>Up</code></b> and <b>
 * <code>Down</code></b>, or with the <b><code>PageUp</code></b> or <b>
 * <code>PageDown</code></b> keys. These last decrement or increment the value
 * of the entry by a greater amount (usually ten times the value of the arrow
 * Button step).
 * 
 * <p>
 * The programmer can get the value introduced by the user with the
 * {@link #getValue() getValue()} method. While this method returns a
 * <code>double</code>, by default the SpinButton only allows to introduce
 * integer values. You can use the {@link #setDigits(int) setDigits()} method
 * to change this behaviour.
 * 
 * @author Vreixo Formoso
 * @version 4.0.7
 */
public class SpinButton extends Entry
{
    protected SpinButton(long pointer) {
        super(pointer);
    }

    /**
     * Create a new SpinButton.
     * 
     * @param min
     *            The minimum value allowed.
     * @param max
     *            The maximum value allowed.
     * @param step
     *            The amount to increment/decrement when one of the arrow
     *            Buttons are clicked, or when the user press the <b>
     *            <code>Up</code></b> or <b><code>Down</code></b> keys.
     * @since 4.0.7
     */
    public SpinButton(double min, double max, double step) {
        super(GtkSpinButton.createSpinButtonWithRange(min, max, step));
    }

    /**
     * Set the number of decimal digits to allow in the entry. This affects to
     * both the precision of the value displayed and the number of decimal
     * digits the user can introduce in the text entry. You should always give
     * enough digits to represent the <code>step</code> you have chosen,
     * otherwise you will get an undesired behaviour.
     * 
     * @since 4.0.7
     */
    /*
     * According Gtk+ documentation, only up to 20 digits is allowed. However,
     * my tests shown that this limit does not actually exist. At the end, we
     * are limited by the precision of a double!
     */
    public void setDigits(int digits) {
        GtkSpinButton.setDigits(this, digits);
    }

    /**
     * Get the current numeric value of the SpinButton.
     * 
     * @since 4.0.7
     */
    public double getValue() {
        return GtkSpinButton.getValue(this);
    }

    /**
     * Set the value displayed in the text entry. If the given value can't be
     * represented with the selected decimal digits (see
     * {@link #setDigits(int) setDigits()}), the next time user increments or
     * decrements its values the additional digits will be lost, so take care
     * about this.
     * 
     * @since 4.0.7
     */
    public void setValue(double value) {
        GtkSpinButton.setValue(this, value);
    }

    /**
     * Signal emitted when the value of the SpinButton changes, either by
     * clicking the arrow buttons, by pressing the specified keys, by input a
     * new value on the text entry, or by setting it programmatically with
     * {@link SpinButton#setValue(double) setValue()} method.
     * 
     * @author Vreixo Formoso
     * @since 4.0.7
     */
    public interface ValueChanged extends GtkSpinButton.ValueChangedSignal
    {
        public void onValueChanged(SpinButton source);
    }

    /**
     * Hook up a handler for the <code>SpinButton.ValueChanged</code> signal.
     * 
     * @since 4.0.7
     */
    public void connect(SpinButton.ValueChanged handler) {
        GtkSpinButton.connect(this, handler, false);
    }
}
