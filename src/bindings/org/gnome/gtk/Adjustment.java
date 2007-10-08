/*
 * Adjustment.java
 *
 * Copyright (c) 2007 Operational Dynamics Consulting Pty Ltd
 *
 * The code in this file, and the library it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" plus the "Classpath Exception" (you may link to this code as a
 * library into other programs provided you don't make a derivation of it).
 * See the LICENCE file for the terms governing usage and redistribution.
 */
package org.gnome.gtk;

/*
 * FIXME this is a placeholder stub for what will become the public API for
 * this type. Replace this comment with appropriate javadoc including author
 * and since tags. Note that the class may need to be made abstract, implement
 * interfaces, or even have its parent changed. No API stability guarantees
 * are made about this class until it has been reviewed by a hacker and this
 * comment has been replaced.
 */ 

/**
 * An Adjustment object contains an adjustment value with a pair of associated
 * values that determine its lower and upper bound. Also, step and page increments
 * as well as a page size are available. The Adjustment widget is used in
 * conjunction with other widgets, so set either their horizontal or vertical aspect.
 * Some of these widgets include {@link SpinButton}, {@link Viewport}, {@link Range},
 * {@link HScrollbar}, {@link HScale}, {@link VScale} and {@link TreeView}.
 * 
 * A single Adjustment object can be shared by more than one widget. Thus if you
 * need to have more than one widget beve similarly with respect to horizontal
 * and vertical aspects, a single Adjustment object will do.
 * 
 * An Adjustment object does not update its own values. The associated widgets
 * that use the Adjustment are free to change its values though. Associated widgets
 * that use an Adjustment, call the valueChanged() method on the widget, causing
 * a VALUE_CHANGED signal to be emitted.
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
     * Create a new Adjustment, with given values for the initial value, the lower
     * and upper bounds, the single step increment values, the page increment values
     * and the page size.
     * 
     * An Adjustment created without passing any arguments to the constructor causes
     * all the parameters to be set to 0.
     * 
     * @param value  refers to the initial value of the Adjustment
     * @param lower  refers to the lower bound of the Adjustment
     * @param upper  refers to the upper bound of the Adjustment
     * @param stepIncrement  refers to the single step increment value of the Adjustment
     * @param pageIncrement  refers to the page increment value of the Adjustment
     * @param pageSize   refers to the size of the page of the Adjustment
     */
    
    public Adjustment(double value, double lower, double upper,
            double stepIncrement, double pageIncrement, double pageSize){
        super(GtkAdjustment.createAdjustment(value, lower, upper,
                stepIncrement, pageIncrement, pageSize));
    }

    /**
     * Get the current value of the Adjustment. This value is always
     * guaranteed to lie between upper and lower. To set this value,
     * see {@link #setValue(double) setValue()}.
     *   
     * @return value    refers to the value of the Adjustment
     */
    
    public double getValue(){
        return GtkAdjustment.getValue(this);
    }
    
    /**
     * Set the value of the Adjustment. This value is bounded between
     * the upper and lower values of the Adjustment. Any attempt to set
     * the value outside of the bound is ignored.
     * 
     * @param value   defines the value of the Adjustment
     */
    
    public void setValue(double value){
        GtkAdjustment.setValue(this, value);
    }
    
}
