/*
 * Window.java
 *
 * Copyright (c) 2006 Srichand Pendyala, Operational Dynamics Consulting Pty 
 * Ltd and Others
 * 
 * The code in this file, and the library it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" plus the "Classpath Exception" (you may link to this code as a
 * library into other programs provided you don't make a derivation of it).
 * See the LICENCE file for the terms governing usage and redistribution.
 */
package org.gnome.gtk;

/**
 * Fixed is a Container Widget that allows you to position other widgets
 * on it at fixed coordinates. These could include Buttons, Labels and Boxes.
 * 
 * <p>
 * You should not use Fixed for any non trivial purposes. While it simplifies
 * your GTK+ usage, it also breaks applications. The Fixed widget can result in 
 * truncated text and overlapping widgets among other visual bugs.
 * 
 * <p>
 * The Fixed widget can't be properly mirrored in right-to-left languages such
 * as Hebrew and Arabic. A Fixed widget with a right-to-left font will render
 * your application unusable.
 * 
 * <p>
 * Adding or removing any GUI elements from Fixed requires you to reposition all
 * the other widgets. This can cause a long-term maintenance problem for your
 * application.
 * 
 * <p>
 * If any of those are a concern for your application, look at Layout.
 * You have been warned!
 * 
 * @author Srichand Pendyala
 */
public class Fixed extends Container
{

    /**
     * Create a new Fixed widget.
     */
    public Fixed() {
        super(GtkFixed.createFixed());
        
    }
    
    /**
     * Place a Widget into the Fixed Widget at a specified location.
     * 
     * <p>
     * It is upto you to ensure that the placing of the new Widget will not 
     * overlap existing widgets. Do not use the Fixed Widget if you have too
     * many Widgets.
     * 
     * <p>
     * The x and y co ordinates are calculated from the left top corner of the
     * window.
     *  
     * @param widget
     *             This Widget will be placed on the Fixed.
     * @param x
     *             X coordinate for the Widget on the Fixed.
     * @param y
     *             Y coordinate for the Widget on the Fixed.
     */
    public void putWidget(Widget widget, int x, int y){
        GtkFixed.putWidget(this, widget, x, y);
    }
    
    /**
     * Move a Widget that has already been added to this Fixed to a new
     * location specified by (x,y)
     * 
     * <p>
     * Calling moveWidget() will cause GTK+ to inherently redraw the entire
     * Fixed surface. If you have many Widgets in a Fixed, this can lead to
     * flickering. Consider using Layout instead.
     * 
     * <p>
     * The coordinates (x,y) are calculated from the left top corner, where x
     * is the horizontal distance and y is the vertical distance from the same.
     * 
     *
     * @param widget
     *             This is the Widget that will be moved.
     * @param x
     *             The new X coordinate for the Widget.
     * @param y
     *             The new Y coordinate for the Widget. 
     */
    public void moveWidget(Widget widget, int x, int y){
        GtkFixed.moveWidget(this, widget, x, y);
    } 

}
