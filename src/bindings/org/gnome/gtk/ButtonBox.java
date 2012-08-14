/*
 * java-gnome, a UI library for writing GTK and GNOME programs from Java!
 *
 * Copyright © 2007-2010 Operational Dynamics Consulting, Pty Ltd and Others
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
 * Provide a consistent layout of Buttons throughout your application. Use an
 * {@link HButtonBox} to lay buttons out horizontally or a {@link VButtonBox}
 * to lay buttons out vertically.
 * 
 * <p>
 * Buttons are packed into a ButtonBox the same way widgets are added to any
 * other Container, using {@link Container#add(Widget) add()}. You can also
 * use Box's {@link Box#packStart(Widget) packStart()} or
 * {@link Box#packEnd(Widget) packEnd()}, but for ButtonBoxes both these
 * functions work just like <code>add()</code>, ie., they pack the button in a
 * way that depends on the current layout style and on whether the button has
 * had {@link ButtonBox#setChildSecondary(Widget, boolean)
 * setChildSecondary()} called for it.
 * 
 * <p>
 * The layout/spacing can be altered in a programmatic way in order to alter
 * the 'feel' of a program to a small degree, although in general overriding
 * the defaults used by every other application on the desktop will impact the
 * user's experience and so is not recommended. If you do need to make
 * adjustments, the arrangement and layout of the Buttons can be changed with
 * {@link #setLayout(ButtonBoxStyle) setLayout()}.
 * 
 * @author Nat Pryce
 * @author Andrew Cowie
 * @since 4.0.4
 */
/*
 * TODO it seems that the methods to control spacing even more minutely were
 * deprecated. I'm guessing that's because they were moved up to Box or
 * Container? If so, and if we expose them, then we should add a reference to
 * the appropriate place in the last paragraph above.
 */
public abstract class ButtonBox extends Box
{
    protected ButtonBox(long pointer) {
        super(pointer);
    }

    /**
     * Returns whether <var>child</var> should appear in a secondary group of
     * children.
     * 
     * @param child
     *            a widget that is a child of this button box
     * @return whether child should appear in a secondary group of children
     */
    public boolean getChildSecondary(Widget child) {
        return GtkButtonBox.getChildSecondary(this, child);
    }

    /**
     * Sets whether <code>child</code> should appear in the "secondary" group
     * of children. A typical use of the secondary child mechanism is to place
     * the 'Help' Button in a Dialog; placing it on the far left side while
     * the 'Ok' and 'Cancel' Buttons are on the far right.
     * 
     * <p>
     * The secondary group appears after the other children if the
     * ButtonBoxStyle is {@link ButtonBoxStyle#START START},
     * {@link ButtonBoxStyle#SPREAD SPREAD}, or {@link ButtonBoxStyle#EDGE
     * EDGE}, and before the other children if the it is
     * {@link ButtonBoxStyle#END END}. For horizontal ButtonBoxes, the
     * definition of before/after depends on direction in place (see
     * {@link Widget#setDirection(TextDirection) setDirection()} on Widget).
     * If the style is {@link ButtonBoxStyle#START START} or
     * {@link ButtonBoxStyle#END END}, then the secondary children are aligned
     * at the other end of the ButtonBox from the main children. For the other
     * styles, they appear immediately next to the main children.
     * 
     * @param child
     *            a Widget that is a child of this ButtonBox
     * @param isSecondary
     *            if <code>true</code>, the child appears in a secondary group
     *            of the ButtonBox
     */
    /*
     * TODO what happens if child is not already added to this Box? If that is
     * an error condition, then we need to check for it in code here before
     * making the actual setChildSecondary() call.
     */
    public void setChildSecondary(Widget child, boolean isSecondary) {
        GtkButtonBox.setChildSecondary(this, child, isSecondary);
    }

    /**
     * Change the way the child Buttons are arranged in this ButtonBox. See
     * {@link ButtonBoxStyle} for the list of possible constants; and see also
     * {@link #setChildSecondary(Widget, boolean) setChildSecondary()} for
     * discussion of the impact of these different possibilities.
     */
    public void setLayout(ButtonBoxStyle layout) {
        GtkButtonBox.setLayout(this, layout);
    }

    /**
     * Returns the method currently being used to govern arrangement child
     * Buttons in this ButtonBox.
     */
    public ButtonBoxStyle getLayout() {
        return GtkButtonBox.getLayout(this);
    }
}
