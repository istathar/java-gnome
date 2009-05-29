/*
 * ColorButton.java
 *
 * Copyright (c) 2007-2009 Operational Dynamics Consulting Pty Ltd
 * Copyright (c)      2009 Vreixo Formoso
 *
 * The code in this file, and the library it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" plus the "Classpath Exception" (you may link to this code as a
 * library into other programs provided you don't make a derivation of it).
 * See the LICENCE file for the terms governing usage and redistribution.
 */
package org.gnome.gtk;

import org.gnome.gdk.Color;

/**
 * A Button used to select a {@link Color Color}.
 * 
 * <p>
 * This is a Button that presents a little rectangle filled with the currently
 * selected Color. When pressed, it opens a ColorSelectionDialog where the
 * user can select a Color.
 * 
 * <p>
 * You will usually want to connect to the {@link ColorSet} signal, that is
 * emitted each time the user changes the Color. You can get the currently
 * selected Color with the {@link #getColor() getColor()} method.
 * 
 * @author Vreixo Formoso
 * @since 4.0.12
 */
public class ColorButton extends Button
{
    protected ColorButton(long pointer) {
        super(pointer);
    }

    /**
     * Create a new ColorButton.
     * 
     * @since 4.0.12
     */
    public ColorButton() {
        super(GtkColorButton.createColorButton());
    }

    /**
     * Create a new ColorButton.
     * 
     * @since 4.0.12
     * 
     * @param color
     *            The Color that will be selected.
     */
    public ColorButton(Color color) {
        super(GtkColorButton.createColorButtonWithColor(color));
    }

    /**
     * Get the Color currently selected.
     * 
     * @since 4.0.12
     */
    public Color getColor() {
        final Color color;

        color = new Color(0, 0, 0);
        GtkColorButton.getColor(this, color);

        return color;
    }
    
    /**
     * Set the selected Color.
     * 
     * @since 4.0.12
     */
    public void setColor(Color color) {
        GtkColorButton.setColor(this, color);
    }

    /**
     * Signal emitted when the user selects a different Color.
     * 
     * <p>
     * You can use the {@link ColorButton#getColor() getColor()} to get the
     * Color selected by the user.
     * 
     * @author Vreixo Formoso
     * @since 4.0.12
     */
    public interface ColorSet extends GtkColorButton.ColorSetSignal
    {
        public void onColorSet(ColorButton source);
    }

    /**
     * Hook up a handler for <code>ColorButton.ColorSet</code> signal.
     * 
     * @since 4.0.12
     */
    public void connect(ColorButton.ColorSet handler) {
        GtkColorButton.connect(this, handler, false);
    }
}
