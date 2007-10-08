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
    
    /**
     * Update the Adjustment's value, to ensure that the bound, defined
     * by lower and upper, is in the current page. Thus, it will lie between
     * value and value + pageSize. If the bound is larger than the page size,
     * then only the start of it will be in the current page.
     * 
     * As always, a <code>CHANGED</code> signal is emitted if the value is changed.
     *  
     * @param lower   lower bound for the range of Adjustment's value
     * @param upper   upper bound for the range of Adjustment's value
     * 
     */
    
    public void clampPage(double lower, double upper){
        GtkAdjustment.clampPage(this, lower, upper);
    }
    
    /**
     * Emits a <code>CHANGED</code> signal from the Adjustment widget. This
     * method will typically be called by the widget with which the Adjustment 
     * is associated, when it changes any of Adjustment's properties, other than
     * <code>value</code>.
     * 
     * When <code>value</code> is changed, the {@link #valueChanged() valueChanged()}
     * method is called instead.
     *
     */
    public void changed(){
        GtkAdjustment.changed(this);
    }
    
    /**
     * Emits a <code>VALUE_CHANGED</code> signal from the Adjustment widget. This
     * method will typically be called by the widget with which the Adjustment 
     * is associated, when it changes the Adjustment's <code>value</code>.
     * 
     * When a property other than code>value</code> is changed, the {@link #changed() changed()}
     * method is called instead.
     */
    
    public void valueChanged(){
        GtkAdjustment.valueChanged(this);
    }
    
    /**
     * This signal is emitted when one or more of Adjustment's fields, other than
     * the <code>value</code> field have been changed. Typically this signal is
     * emitted by calling the {@link #changed() changed()} method. This method
     * is called by the widget, with which the Adjustment is associated.
     * 
     * @author Srichand Pendyala
     *
     */
    
    public interface CHANGED extends GtkAdjustment.CHANGED
    {
        public void onChanged(Adjustment source);
    }
    

    /**
     * Hook up a <code>CHANGED</code> handler.
     */
    
    public void connect(CHANGED handler){
        GtkAdjustment.connect(this, handler);
    }
    
    /**
     * This signal is emitted when Adjustment's <code>value</code> field has been
     * changed. Typically this signal is emitted by calling the {@link #valueChanged() valueChanged()}
     * method. This method is called by the widget, with which the Adjustment is
     * associated.
     * 
     * @author Srichand Pendyala
     *
     */
    
    public interface VALUE_CHANGED extends GtkAdjustment.VALUE_CHANGED
    {
        public void onValueChanged(Adjustment source);
    }
    
    /**
     * Hook up a <code>VALUE_CHANGED</code> handler.
     */
    
    public void connect(VALUE_CHANGED handler){
        GtkAdjustment.connect(this, handler);
    }
    
    
}
