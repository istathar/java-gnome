/*
 * ButtonBox.java
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

/**
 * A button box should be used to provide a consistent layout of buttons
 * throughout your application. Use an {@link HButtonBox} to lay buttons out
 * horizontally or an {@link VButtonBox} to lay buttons out vertically.
 * 
 * The layout/spacing can be altered by the programmer, or if desired, by the
 * user to alter the 'feel' of a program to a small degree.
 * 
 * Buttons are packed into a button box the same way widgets are added to any
 * other container, using {@link Container#add(Widget)}. You can also use
 * {@link Box#packStart(Widget)} or {@link Box#packEnd(Widget)}, but for
 * button boxes both these functions work just like
 * {@link Container#add(Widget)}, ie., they pack the button in a way that
 * depends on the current layout style and on whether the button has had
 * {@link ButtonBox#setChildSecondary(Widget, boolean)} called for it.
 * 
 * The arrangement and layout of the buttons can be changed with
 * {@link #setLayout(ButtonBoxStyle)}.
 * 
 * @see ButtonBoxStyle
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
     * Sets whether child should appear in a secondary group of children. A
     * typical use of a secondary child is the help button in a dialog.
     * 
     * This group appears after the other children if the style is
     * {@link ButtonBoxStyle#START}, {@link ButtonBoxStyle#SPREAD} or
     * {@link ButtonBoxStyle#EDGE}, and before the other children if the
     * style is {@link ButtonBoxStyle#END}. For horizontal button boxes, the
     * definition of before/after depends on direction of the widget (see
     * {@link Widget#getDirection()). If the style is {@link ButtonBoxStyle#START}
     * or {@link ButtonBoxStyle#END}, then the secondary children are aligned
     * at the other end of the button box from the main children. For the
     * other styles, they appear immediately next to the main children.
     * 
     * @param child
     *            a widget that is a child of this button box
     * @param isSecondary
     *            if <code>true</code>, the child appears in a secondary
     *            group of the button box
     */
    public void setChildSecondary(Widget child, boolean isSecondary) {
        GtkButtonBox.setChildSecondary(this, child, isSecondary);
    }

    /**
     * Changes the way buttons are arranged in their container.
     * 
     * @param layout
     *            the new layout style.
     */
    public void setLayout(ButtonBoxStyle layout) {
        GtkButtonBox.setLayout(this, layout);
    }

    /**
     * Returns the method being used to arrange the buttons in a button box.
     */
    public ButtonBoxStyle getLayout() {
        return GtkButtonBox.getLayout(this);
    }
}
