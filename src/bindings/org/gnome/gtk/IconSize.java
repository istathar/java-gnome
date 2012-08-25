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
package org.gnome.gtk;

import org.freedesktop.bindings.Constant;

/**
 * Constants referring to the size of an icon. These are typically used when
 * working with Stock icons, for example with the
 * {@link Image#Image(Stock, IconSize) Image} constructor that takes a Stock
 * image identifier and one of these constants to specify the size you want.
 * 
 * <p>
 * We're rather deliberately <i>not</i> indicated the pixel dimensions
 * associated with each IconSize constant, as this is the sort of thing that
 * can evolve over time - and be theme dependant.
 * 
 * <p>
 * In some sense you don't need this; when working with Stock items in
 * conjunction with Buttons or Menus, the images will be arranged to be the
 * appropriate size automatically.
 * 
 * @author Andrew Cowie
 * @since 4.0.6
 */
public final class IconSize extends Constant
{
    private IconSize(int ordinal, String nickname) {
        super(ordinal, nickname);
    }

    /**
     * The standard size used for icons on Buttons. See also
     * {@link Button#setImage(Image)} for setting an image of arbitrary size.
     */
    public static final IconSize BUTTON = new IconSize(GtkIconSize.BUTTON, "BUTTON");

    /**
     * The standard size used for icons appearing on Menus. See
     * {@link ImageMenuItem}.
     */
    public static final IconSize MENU = new IconSize(GtkIconSize.MENU, "MENU");

    /**
     * The size used when Toolbars are being rendered in their smaller
     * incarnation. Some have noted that icons created for this case bear a
     * striking resemblance to the MENU size.
     */
    public static final IconSize SMALL_TOOLBAR = new IconSize(GtkIconSize.SMALL_TOOLBAR, "SMALL_TOOLBAR");

    /**
     * The size used for icons appearing in large (some would call this
     * normal) size Toolbars.
     */
    public static final IconSize LARGE_TOOLBAR = new IconSize(GtkIconSize.LARGE_TOOLBAR, "LARGE_TOOLBAR");

    public static final IconSize DIALOG = new IconSize(GtkIconSize.DIALOG, "DIALOG");

    public static final IconSize DND = new IconSize(GtkIconSize.DND, "DND");
}
