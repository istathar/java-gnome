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
 * Constants that specify the direction a ProgressBar is to progress. The
 * default you'll be accustomed to seeing is {@link #LEFT_TO_RIGHT
 * LEFT_TO_RIGHT}.
 * 
 * @since 4.0.10
 * @author Miloud Bel
 */
public final class ProgressBarOrientation extends Constant
{
    /**
     * Perform progress from left to right
     */
    public static final ProgressBarOrientation LEFT_TO_RIGHT = new ProgressBarOrientation(
            GtkProgressBarOrientation.LEFT_TO_RIGHT, "LEFT_TO_RIGHT");

    /**
     * Perform progress from right to left
     */
    public static final ProgressBarOrientation RIGHT_TO_LEFT = new ProgressBarOrientation(
            GtkProgressBarOrientation.RIGHT_TO_LEFT, "RIGHT_TO_LEFT");

    /**
     * Perform progress from top to bottom
     */
    public static final ProgressBarOrientation TOP_TO_BOTTOM = new ProgressBarOrientation(
            GtkProgressBarOrientation.TOP_TO_BOTTOM, "TOP_TO_BOTTOM");

    /**
     * Perform progress from bottom to top
     */
    public static final ProgressBarOrientation BOTTOM_TO_TOP = new ProgressBarOrientation(
            GtkProgressBarOrientation.BOTTOM_TO_TOP, "BOTTOM_TO_TOP");

    private ProgressBarOrientation(int ordinal, String nickname) {
        super(ordinal, nickname);
    }
}
