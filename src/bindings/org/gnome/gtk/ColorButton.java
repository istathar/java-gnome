/*
 * java-gnome, a UI library for writing GTK and GNOME programs from Java!
 *
 * Copyright © 2007-2011 Operational Dynamics Consulting, Pty Ltd
 * Copyright © 2009      Vreixo Formoso
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

import org.gnome.gdk.RGBA;

/**
 * A Button used to select a {@link RGBA RGBA} colour.
 * 
 * <p>
 * This is a Button that presents a little rectangle filled with the currently
 * selected RGBA colour. When pressed, it opens a ColorSelectionDialog where
 * the user can select a colour.
 * 
 * <p>
 * You will usually want to connect to the <code>ColorButton.ColorSet</code>
 * signal, that is emitted each time the user changes the colour. You can get
 * the currently selected colour with the {@link #getRGBA() getRGBA()} method.
 * 
 * @author Vreixo Formoso
 * @author Andrew Cowie
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
     * Create a new ColorButton with the specified Color selected.
     * 
     * @since 4.1.1
     */
    public ColorButton(RGBA color) {
        super(GtkColorButton.createColorButtonWithRgba(color));
    }

    /**
     * Get the colour currently selected.
     * 
     * @since 4.1.1
     */
    public RGBA getRGBA() {
        final RGBA color;

        color = new RGBA(0, 0, 0, 0);
        GtkColorButton.getRgba(this, color);

        return color;
    }

    /**
     * Set the selected colour.
     * 
     * @since 4.1.1
     */
    public void setRGBA(RGBA color) {
        GtkColorButton.setRgba(this, color);
    }

    /**
     * Signal emitted when the user selects a different RGBA colour.
     * 
     * <p>
     * You can use the {@link ColorButton#getRGBA() getRGBA()} to get the
     * colour selected by the user.
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
