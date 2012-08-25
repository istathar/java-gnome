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
 * Using AttachOptions you control how a Widget placed in a {@link Table} uses
 * eventually existing additional space, e.g. because another cell in the same
 * row is much wider or another column much higher.
 * <p>
 * It may be necessary to encapsulate your attached Widget in an
 * {@link Alignment} container. This could look like this:
 * </p>
 * 
 * <pre>
 * Table table;
 * ...
 * TextEntry number = new TextEntry();
 * number.setWidthChars(4);
 * Alignment alignNumber = new Alignment(Alignment.LEFT, Alignment.CENTER, 0,0, number);
 * table.attach(alignNumber, 1, 2, 1, 2, AttachOptions.FILL, AttachOptions.SHRINK, 0,0);
 * </pre>
 * 
 * @since 4.0.10
 */
public final class AttachOptions extends Constant
{
    private AttachOptions(int ordinal, String nickname) {
        super(ordinal, nickname);
    }

    /**
     * Although there is additional space available, the Widget shall keep its
     * size or even shrink if possible. This usually gets the extra space
     * distributed evenly among the widget, so you have the Widget centered in
     * the extra space.
     * <p>
     * Encapsulating the Widget in an {@link Alignment} container does not
     * help, since the Alignment container only gets the same space granted
     * the shrinked Widget would get.
     * </p>
     */
    public static final AttachOptions SHRINK = new AttachOptions(GtkAttachOptions.SHRINK, "SHRINK");

    /**
     * The widget gets all the available space. Unless you put the widget
     * inside an {@link Alignment} container, the Widget is resized to fill
     * the space. If you do use Alignment containers the Widget itself will
     * not grow, but keep its size while the container controls where it
     * resides.
     */
    public static final AttachOptions FILL = new AttachOptions(GtkAttachOptions.FILL, "FILL");

    /**
     * Like FILL, but also causes the cell to expand and use extra space.
     */
    public static final AttachOptions EXPAND = new AttachOptions(GtkAttachOptions.EXPAND, "EXPAND");

}
