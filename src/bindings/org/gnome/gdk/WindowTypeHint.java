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
package org.gnome.gdk;

import org.freedesktop.bindings.Constant;

/**
 * Constants indicating hints you can provide to the window manager about what
 * the nature and purpose of a given Window is. While this is in the GDK
 * package, its primary use is for designating the purpose of top level
 * <code>[org.gnome.gtk]</code> Windows via
 * {@link org.gnome.gtk.Window#setTypeHint(WindowTypeHint) setTypeHint()}.
 * 
 * @author Andrew Cowie
 * @since 4.0.8
 */
public final class WindowTypeHint extends Constant
{
    private WindowTypeHint(int ordinal, String nickname) {
        super(ordinal, nickname);
    }

    /**
     * This is the default when constructing a new Window and you don't need
     * to set it.
     * 
     * @since 4.0.8
     */
    public static final WindowTypeHint NORMAL = new WindowTypeHint(GdkWindowTypeHint.NORMAL, "NORMAL");

    /**
     * Mark this Window as a utility window. Under Metacity you should expect
     * a Window so marked to not appear in the pager or taskbar. A utility
     * window will be raised when a normal Window from the same application
     * gains the focus.
     * 
     * <p>
     * This setting is excellent for secondary windows. The key question is
     * "should I be able to <b><code>Alt+Tab</code></b> to this Window?" If
     * it's not the real application, per se, then the answer is "probably
     * not" and the Window should be marked <code>UTILITY</code>.
     * 
     * @since 4.0.8
     */
    public static final WindowTypeHint UTILITY = new WindowTypeHint(GdkWindowTypeHint.UTILITY, "UTILITY");

    /**
     * This Window will be used to present something that is docked,
     * presumably to the panel. An example in GNOME is the calendar that the
     * clock applet displays as a popup. Once raised, it stays on top of all
     * other windows regardless of which workspace you are on or what other
     * applications you focus.
     * 
     * <p>
     * If you're thinking to use this you probably want Window's
     * {@link org.gnome.gtk.Window#setKeepAbove(boolean) setKeepAbove()}
     * instead.
     * 
     * @since 4.0.8
     */
    public static final WindowTypeHint DOCK = new WindowTypeHint(GdkWindowTypeHint.DOCK, "DOCK");

    /**
     * This Window is a dialog. Obviously if you've used Dialog or one of its
     * subclasses, the window manager will be informed properly for you. This
     * is for when you've created a Window that is acting as a dialog but
     * isn't a Dialog.
     * 
     * @since 4.0.8
     */
    public static final WindowTypeHint DIALOG = new WindowTypeHint(GdkWindowTypeHint.DIALOG, "DIALOG");
}
