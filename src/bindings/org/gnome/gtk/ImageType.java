/*
 * java-gnome, a UI library for writing GTK and GNOME programs from Java!
 *
 * Copyright © 2007-2011 Operational Dynamics Consulting, Pty Ltd and Others
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
import org.gnome.gdk.PixbufAnimation;

/**
 * The type of image in a {@link Image} or a {@link StatusIcon}.
 * 
 * @author Nat Pryce
 * @since 4.0.4
 */
public final class ImageType extends Constant
{
    /**
     * There is no image displayed by the Widget.
     */
    public static final ImageType EMPTY = new ImageType(GtkImageType.EMPTY, "EMPTY");

    /**
     * The Widget contains a {@link org.gnome.gdk.Pixbuf}.
     */
    public static final ImageType PIXBUF = new ImageType(GtkImageType.PIXBUF, "PIXBUF");

    /**
     * The Widget contains an icon derived specified by a stock item (see
     * {@link Stock Stock}).
     */
    public static final ImageType STOCK = new ImageType(GtkImageType.STOCK, "STOCK");

    /**
     * The Widget contains a {@link IconSet}.
     */
    public static final ImageType ICON_SET = new ImageType(GtkImageType.ICON_SET, "ICON_SET");

    /**
     * The Widget contains a {@link PixbufAnimation}.
     */
    public static final ImageType ANIMATION = new ImageType(GtkImageType.ANIMATION, "ANIMATION");

    /**
     * The Widget contains a named icon. See
     * {@link org.freedesktop.icons.Icon Icon}.
     * 
     * @since 4.1.1
     */
    public static final ImageType ICON_NAME = new ImageType(GtkImageType.ICON_NAME, "ICON_NAME");

    private ImageType(int ordinal, String nickname) {
        super(ordinal, nickname);
    }
}
