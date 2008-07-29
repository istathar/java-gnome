/*
 * Adjustment.java
 *
 * Copyright (c) 2007-2008 Operational Dynamics Consulting Pty Ltd
 * Copyright (c) 2007      Srichand Pendyala
 * 
 * The code in this file, and the library it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" plus the "Classpath Exception" (you may link to this code as a
 * library into other programs provided you don't make a derivation of it).
 * See the LICENCE file for the terms governing usage and redistribution.
 */
package org.gnome.gtk;

/**
 * An Adjustment object contains an adjustment value with a pair of associated
 * values that determine its lower and upper bound. Also, step and page
 * increments as well as a page size are available. The Adjustment is not a
 * Widget itself, but rather is used in conjunction with other Widgets such as
 * {@link SpinButton}, {@link Viewport}, {@link Range}, {@link HScrollbar},
 * {@link HScale}, {@link VScale} and {@link TreeView}.
 * 
 * <p>
 * A single Adjustment object can be shared by more than one Widget. Thus if
 * you need to have more than one Widget behave similarly with respect to
 * horizontal and vertical aspects, a single Adjustment object will do.
 * 
 * <p>
 * An Adjustment object does not update its own values. The associated Widgets
 * that use the Adjustment are free to change its values though. Associated
 * Widgets that use an Adjustment call the <code>emitValueChanged()</code>
 * method on the widget, causing a <code>VALUE_CHANGED</code> signal.
 * 
 * @author Srichand Pendyala
 * @since 4.0.5
 */
public class Adjustment extends Object
{
    protected Adjustment(long pointer) {
        super(pointer);
    }

    /**
     * Create a new Adjustment, with given values for the initial value, the
     * lower and upper bounds, the single step increment values, the page
     * increment values and the page size.
     * 
     * <p>
     * An Adjustment created without passing any arguments to the constructor
     * causes all the parameters to be set to 0.
     * 
     * @param value
     *            the initial setting for the <var>value</var> property of
     *            the Adjustment
     * @param lower
     *            the lower bound of the Adjustment
     * @param upper
     *            the upper bound of the Adjustment
     * @param stepIncrement
     *            the single step increment value of the Adjustment
     * @param pageIncrement
     *            the page increment value of the Adjustment
     * @param pageSize
     *            the size of the page of the Adjustment
     * @since 4.0.5
     */
    public Adjustment(double value, double lower, double upper, double stepIncrement,
            double pageIncrement, double pageSize) {
        super(
                GtkAdjustment.createAdjustment(value, lower, upper, stepIncrement, pageIncrement,
                        pageSize));
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
     * upper and lower values of the Adjustment. Any attempt to set the value
     * outside of the bound is ignored.
     * 
     * @since 4.0.5
     */
    public void setValue(double value) {
        GtkAdjustment.setValue(this, value);
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
     * As always, a <code>Adjustment.Changed</code> signal is emitted if the
     * value is changed.
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
     * If you have changed <var>value</var>, then
     * {@link #emitValueChanged() emitValueChanged()} is the method you want
     * to change instead. instead.
     * 
     * @since 4.0.8
     */
    public void emitChanged() {
        GtkAdjustment.changed(this);
    }

    /** @deprecated */
    public void changed() {
        assert false : "use emitChanged() instead";
        GtkAdjustment.changed(this);
    }

    /**
     * Emits a <code>Adjustment.ValueChanged</code> signal on the
     * Adjustment. This method will typically be called by the Widget with
     * which the Adjustment is associated, when it changes the Adjustment's
     * <var>value</var>.
     * 
     * @since 4.0.8
     */
    public void emitValueChanged() {
        GtkAdjustment.valueChanged(this);
    }

    /** @deprecated */
    public void valueChanged() {
        assert false : "use emitValueChanged() instead";
        GtkAdjustment.valueChanged(this);
    }

    /**
     * This signal is emitted when one or more of Adjustment's fields, other
     * than the <var>value</var> field have been changed. This will be
     * emitted if you call the {@link #emitChanged() emitChanged()} method,
     * but in general it is in response to actions taken by the Widget with
     * which this Adjustment is associated.
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

    /** @deprecated */
    public interface CHANGED extends GtkAdjustment.ChangedSignal
    {
        public void onChanged(Adjustment source);
    }

    /**
     * @deprecated
     */
    public void connect(CHANGED handler) {
        assert false : "use Adjustment.Changed instead";
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

    /** @deprecated */
    public interface VALUE_CHANGED extends GtkAdjustment.ValueChangedSignal
    {
        public void onValueChanged(Adjustment source);
    }

    /** @deprecated */
    public void connect(VALUE_CHANGED handler) {
        assert false : "use Adjustment.ValueChanged instead";
        GtkAdjustment.connect(this, handler, false);
    }
}
