/*
 * HButtonBox.java
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
 * The spacing between buttons can be set with {@link Box#setSpacing(int)}.
 * The arrangement and layout of the buttons can be changed with
 * {@link #setLayout(ButtonBoxStyle)}.
 */
public class HButtonBox extends ButtonBox
{
    public HButtonBox() {
        this(GtkHButtonBox.createHButtonBox());
    }

    protected HButtonBox(long pointer) {
        super(pointer);
    }
}
