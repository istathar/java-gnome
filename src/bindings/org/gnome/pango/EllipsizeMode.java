/*
 * java-gnome, a UI library for writing GTK and GNOME programs from Java!
 *
 * Copyright © 2007-2010 Operational Dynamics Consulting, Pty Ltd
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
package org.gnome.pango;

import org.freedesktop.bindings.Constant;

/**
 * Those constants specify what sort of ellipsization should be applied to a
 * line of text. In the ellipsization process characters are removed from the
 * text in order to make it fit to a given width and replaced with an ellipsis
 * (...).
 * 
 * @author Guillaume Mazoyer
 * @since 4.0.13
 */
public final class EllipsizeMode extends Constant
{
    private EllipsizeMode(int ordinal, String nickname) {
        super(ordinal, nickname);
    }

    /**
     * No ellipsization.
     */
    public static final EllipsizeMode NONE = new EllipsizeMode(PangoEllipsizeMode.NONE, "NONE");

    /**
     * Omit characters at the start of the text.
     */
    public static final EllipsizeMode START = new EllipsizeMode(PangoEllipsizeMode.START, "START");

    /**
     * Omit characters in the middle of the text.
     */
    public static final EllipsizeMode MIDDLE = new EllipsizeMode(PangoEllipsizeMode.MIDDLE, "MIDDLE");

    /**
     * Omit characters at the end of the text.
     */
    public static final EllipsizeMode END = new EllipsizeMode(PangoEllipsizeMode.END, "END");
}
