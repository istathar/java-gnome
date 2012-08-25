/*
 * java-gnome, a UI library for writing GTK and GNOME programs from Java!
 *
 * Copyright © 2006      Srichand Pendyala
 * Copyright © 2006-2010 Operational Dynamics Consulting, Pty Ltd
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
 * Fixed is a Container Widget that allows you to position other widgets on it
 * at fixed coordinates. These could include Buttons and Labels as well as
 * more complex composite widgets like Boxes.
 * 
 * <p>
 * You should not use Fixed for any non-trivial purpose. While you might think
 * at first that it would simplify your application, the reality is that Fixed
 * is almost always the wrong Container choice. Because GTK is the widget
 * toolkit used to power the user interfaces of GNOME applications, the
 * appearance of Widgets is very dynamic. Different window managers and theme
 * engines can radically change the size and appearance of Widgets to suit
 * varying accessibility and usability requirements; different fonts available
 * to your users will cause Labels to be sized differently than you'd expect,
 * and of course translation completely throws predictability right out the
 * window. Since the Fixed Container does <i>not</i> adapt to the size
 * requests of the Widgets with in it, the frequent result is truncated text,
 * overlapping Widgets, and other visual bugs.
 * 
 * <p>
 * The Fixed widget can't be properly mirrored in right-to-left languages such
 * as Hebrew and Arabic. A Fixed widget with a right-to-left font will render
 * your application unusable.
 * 
 * <p>
 * Adding or removing any GUI elements from this Fixed requires you to
 * reposition all the other Widgets within it. As you can imagine, this will
 * end up a long-term maintenance headache for your application.
 * 
 * <p>
 * If any of those are a concern for your application, then you should be
 * using a different Container, either combinations of {@link VBox VBox} and
 * {@link HBox HBox}, or perhaps {@link Table Table}. You have been warned!
 * 
 * @see Layout
 * @see DrawingArea
 * 
 * @author Srichand Pendyala
 * @author Andrew Cowie
 * @since 4.0.1
 */
public class Fixed extends Container
{
    protected Fixed(long pointer) {
        super(pointer);
    }

    /**
     * Create a new Fixed Widget.
     * 
     * @since 4.0.1
     */
    public Fixed() {
        super(GtkFixed.createFixed());
    }

    /**
     * Place a Widget into the Fixed Container at a specified location.
     * 
     * <p>
     * It is up to you to ensure that the placing of the new Widget will not
     * overlap any existing Widgets. If this is starting to be a burden, it's
     * a good sign you're using the wrong Container!
     * 
     * <p>
     * The x and y co-ordinates are measured in pixels from the top left
     * corner of the Fixed.
     * 
     * @param widget
     *            the Widget to be placed in the Fixed.
     * @param x
     *            horizontal position for the Widget being added
     * @param y
     *            vertical position for the Widget being added
     * @since 4.0.1
     */
    public void put(Widget widget, int x, int y) {
        GtkFixed.put(this, widget, x, y);
    }

    /**
     * Move a Widget that has already been added to this Fixed to a new
     * location.
     * 
     * <p>
     * Calling <code>move()</code> will cause GTK to inherently redraw the
     * entire Fixed surface. If you have many Widgets in a Fixed, this can
     * lead to flickering. Consider using {@link Layout} or
     * {@link DrawingArea} instead.
     * 
     * <p>
     * The x and y co-ordinates are measured in pixels from the top left
     * corner of the Fixed.
     * 
     * @param widget
     *            the Widget that will be moved.
     * @param x
     *            the horizontal position to move <code>widget</code> to.
     * @param y
     *            the vertical position to move <code>widget</code> to.
     * @since 4.0.1
     */
    public void move(Widget widget, int x, int y) {
        GtkFixed.move(this, widget, x, y);
    }
}
