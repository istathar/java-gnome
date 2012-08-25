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

import org.freedesktop.bindings.Constant;

/**
 * Control layout of Buttons in a {@link ButtonBox}. In general you shouldn't
 * need to use this too much; in GNOME we leave presentation style decisions
 * up to the the theme engine currently selected by the user.
 * 
 * @author Nat Pryce
 * @see HButtonBox
 * @see VButtonBox
 * @since 4.0.4
 */
public final class ButtonBoxStyle extends Constant
{
    /**
     * Default packing, allowing the theme engine to control the presentation
     * of ButtonBoxes.
     */
    public static final ButtonBoxStyle DEFAULT_STYLE = new ButtonBoxStyle(
            GtkButtonBoxStyle.DEFAULT_STYLE, "DEFAULT_STYLE");

    /**
     * Buttons are to be evenly spread across the Box.
     */
    public static final ButtonBoxStyle SPREAD = new ButtonBoxStyle(GtkButtonBoxStyle.SPREAD, "SPREAD");

    /**
     * Buttons are to be placed at the edges of the Box.
     */
    public static final ButtonBoxStyle EDGE = new ButtonBoxStyle(GtkButtonBoxStyle.EDGE, "EDGE");

    /**
     * Buttons are to be grouped towards the start of the box (on the left for
     * a HButtonBox, or the top for a VButtonBox).
     */
    public static final ButtonBoxStyle START = new ButtonBoxStyle(GtkButtonBoxStyle.START, "START");

    /**
     * Buttons are to be grouped towards the end of the box (on the right for
     * a HButtonBox, or the bottom for a VButtonBox).
     */
    public static final ButtonBoxStyle END = new ButtonBoxStyle(GtkButtonBoxStyle.END, "END");

    /**
     * Buttons are to be grouped in the center of the box.
     * 
     * @since 4.0.16
     */
    public static final ButtonBoxStyle CENTER = new ButtonBoxStyle(GtkButtonBoxStyle.CENTER, "CENTER");

    private ButtonBoxStyle(int ordinal, String nickname) {
        super(ordinal, nickname);
    }
}
