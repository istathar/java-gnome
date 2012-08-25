/*
 * java-gnome, a UI library for writing GTK and GNOME programs from Java!
 *
 * Copyright © 2007-2011 Operational Dynamics Consulting, Pty Ltd
 * Copyright © 2007      Srichand Pendyala
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

import org.gnome.glib.Object;

/**
 * Data concerning a value with a pair of associated values that determine its
 * lower and upper limits. Also, step and page increments as well as a page
 * size are available. Adjustment is not a Widget itself; Adjustments are used
 * by certain Widgets such as SpinButton, VScale and HScale, etc. More
 * commonly, though, you encounter Adjustment objects as the mechanism
 * controlling the position of VScrollbars and HScrollbars as seen in
 * TreeView, TextView, and other Containers packed into a
 * {@link ScrolledWindow}.
 * 
 * <p>
 * A single Adjustment object can be shared by more than one Widget. Thus if
 * you need to have multiple Widgets behave similarly with respect to say
 * vertical scrolling, a single Adjustment object will do.
 * 
 * <p>
 * An Adjustment does <i>not</i> update its own values. Associated Widgets
 * that use an Adjustment call the <code>emitValueChanged()</code> method on
 * the widget, causing a <code>Adjustment.ValueChanged</code> signal which in
 * turn is what drives GTK's internal scrolling behaviours. You can react to
 * it too.
 * 
 * @author Srichand Pendyala
 * @author Andrew Cowie
 * @since 4.0.5
 */
public class Adjustment extends Object
{
    protected Adjustment(long pointer) {
        super(pointer);
    }

    /**
     * Create a new Adjustment, with given values for the initial
     * <var>value</var> value, the <var>lower</var> and <var>upper</var>
     * bounds, the single <var>step increment</var>, <var>page increment</var>
     * and the <var>page size</var> properties.
     * 
     * <p>
     * Be aware that <code>pageSize</code> <b>must</b> be set to zero if this
     * Adjustment is representing a single scalar value (ie, if it is going to
     * be the Adjustment backing a SpinButton or Scale).
     * 
     * @since 4.0.5
     */
    public Adjustment(double value, double lower, double upper, double stepIncrement,
            double pageIncrement, double pageSize) {
        super(
                GtkAdjustment.createAdjustment(value, lower, upper, stepIncrement, pageIncrement,
                        pageSize));
    }

    /**
     * Create an Adjustment with all parameters set to initial values of 0.
     * 
     * @since 4.0.10
     */
    public Adjustment() {
        super(GtkAdjustment.createAdjustment(0, 0, 0, 0, 0, 0));
    }

    /**
     * Get the current value of the Adjustment. This value is always
     * guaranteed to lie between upper and lower. To set this value, see
     * {@link #setValue(double) setValue()}.
     * 
     * @since 4.0.5
     */
    public double getValue() {
        return GtkAdjustment.getValue(this);
    }

    /**
     * Set the value of the Adjustment. This value is bounded between the
     * <var>upper</var> and <var>lower</var> properties of the Adjustment. Any
     * attempt to set the value outside of these bounds is ignored.
     * 
     * @since 4.0.5
     */
    public void setValue(double value) {
        GtkAdjustment.setValue(this, value);
    }

    /**
     * Change all the configuration values and the data value.
     * 
     * <p>
     * This is available as as a single method rather than a series of
     * individual setters because each invocation of a setter causes
     * <code>Adjustment.Changed</code> to be emitted, and generally you'd only
     * want one per sequence of property changes.
     * 
     * @since 4.0.17
     */
    public void configure(double value, double lower, double upper, double stepIncrement,
            double pageIncrement, double pageSize) {
        GtkAdjustment.configure(this, value, lower, upper, stepIncrement, pageIncrement, pageSize);
    }

    /**
     * Update the Adjustment's value, to ensure that the bound, defined by
     * <code>lower</code> and <code>upper</code>, is in the current page.
     * Thus, it will lie between <code>value</code> and
     * <code>value + pageSize</code> that you set in the constructor. If the
     * bound is larger than the page size, then only the start of it will be
     * in the current page.
     * 
     * <p>
     * As always, a <code>Adjustment.ValueChanged</code> signal is emitted if
     * the value is changed.
     * 
     * @since 4.0.5
     */
    public void clampPage(double lower, double upper) {
        GtkAdjustment.clampPage(this, lower, upper);
    }

    /**
     * Emits a <code>Adjustment.Changed</code> signal from the Adjustment
     * widget. This method will typically be called by the Widget with which
     * the Adjustment is associated when it changes any of Adjustment's
     * properties, other than <var>value</var>.
     * 
     * <p>
     * If you have changed <var>value</var>, then {@link #emitValueChanged()
     * emitValueChanged()} is the method you want to change instead.
     * 
     * @since 4.0.8
     */
    public void emitChanged() {
        GtkAdjustment.changed(this);
    }

    /**
     * Emits a <code>Adjustment.ValueChanged</code> signal on the Adjustment.
     * This method is probably unnecessary, since calling
     * {@link #setValue(double) setValue()} will result in the signal being
     * raised.
     * 
     * @since 4.0.8
     */
    public void emitValueChanged() {
        GtkAdjustment.valueChanged(this);
    }

    /**
     * This signal is emitted when one or more of Adjustment's fields, other
     * than the <var>value</var> field have been changed. This will be emitted
     * if you call the {@link #emitChanged() emitChanged()} method, but in
     * general it is in response to actions taken by the Widget with which
     * this Adjustment is associated.
     * 
     * @author Srichand Pendyala
     * @since 4.0.5
     */
    public interface Changed extends GtkAdjustment.ChangedSignal
    {
        public void onChanged(Adjustment source);
    }

    /**
     * Hook up an <code>Adjustment.Changed</code> signal handler.
     */
    public void connect(Changed handler) {
        GtkAdjustment.connect(this, handler, false);
    }

    /**
     * This signal is emitted when Adjustment's <var>value</var> field has
     * been changed. This signal will be emitted if you call the
     * {@link #emitValueChanged() emitValueChanged()} method, although more
     * typically it is the result of changes by the Widget with which this
     * Adjustment is associated.
     * 
     * @author Srichand Pendyala
     * @since 4.0.5
     */
    public interface ValueChanged extends GtkAdjustment.ValueChangedSignal
    {
        public void onValueChanged(Adjustment source);
    }

    /**
     * Hook up an <code>Adjustment.ValueChanged</code> signal handler.
     * 
     * @since 4.0.5
     */
    public void connect(ValueChanged handler) {
        GtkAdjustment.connect(this, handler, false);
    }

    /**
     * Get the <var>lower</var> bound of the Adjustment. This is the minimum
     * value that the <var>value</var> property can range to. In a VScrollbar,
     * it is the top end of the scrollbar trough, which you can reasonably
     * expect to be <code>0</code>.
     * 
     * @since 4.0.10
     */
    public double getLower() {
        return GtkAdjustment.getLower(this);
    }

    /**
     * Set the <var>lower</var> bound of this Adjustment. Most of the time
     * people start their ranges at <code>0.0</code> and leave it that way, so
     * you probably won't need this much.
     * 
     * <p>
     * Use {@link #configure(double, double, double, double, double, double)
     * configure()} if changing more than one property at a time.
     * 
     * @since 4.0.17
     */
    public void setLower(double lower) {
        GtkAdjustment.setLower(this, lower);
    }

    /**
     * Get the <var>upper</var> bound of the Adjustment. This is the maximum
     * value that the <var>value</var> property can range to. In a VScrollbar,
     * it is the (maximum) height that is represents the value at the bottom
     * end of the scrollbar trough.
     * 
     * @since 4.0.10
     */
    public double getUpper() {
        return GtkAdjustment.getUpper(this);
    }

    /**
     * Set the <var>upper</var> bound of this Adjustment.
     * 
     * <p>
     * Use {@link #configure(double, double, double, double, double, double)
     * configure()} if changing more than one property at a time.
     * 
     * @since 4.0.17
     */
    public void setUpper(double upper) {
        GtkAdjustment.setUpper(this, upper);
    }

    /**
     * Get the current value of the <var>page-size</var> property of this
     * Adjustment. For a VScrollbar, this is the (vertical) height of the
     * slider control.
     * 
     * @since 4.0.10
     */
    public double getPageSize() {
        return GtkAdjustment.getPageSize(this);
    }
}
