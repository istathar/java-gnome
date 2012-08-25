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
 * Constants describing what sort ordering you want to be in effect. This is
 * used by TreeSortables such as {@link TreeModelSort}.
 * 
 * @author Andrew Cowie
 * @since 4.0.7
 */
public final class SortType extends Constant
{
    private SortType(int ordinal, String nickname) {
        super(ordinal, nickname);
    }

    /**
     * Sort in ascending order. This means:
     * <ul>
     * <li><code>0</code>, <code>1</code>, <code>2</code>, ..., <i>n</i> and</li>
     * <li><code>A</code>, <code>B</code>, <code>C</code>, ..., <code>X</code>, <code>Y</code>, <code>Z</code>
     * </ul>
     * where <code>0</code> or <code>A</code> or whatever will be at the top
     * of your list.
     */
    public static SortType ASCENDING = new SortType(GtkSortType.ASCENDING, "ASCENDING");

    /**
     * Sort in descending order. This means:
     * <ul>
     * <li><i>n</i>, <i>n</i><code>-1</code>, <i>n</i><code>-2</code>, ...,
     * <code>2</code>, <code>1</code>, <code>0</code> and</li>
     * <li><code>Z</code>, <code>Y</code>, <code>X</code>, ... <code>C</code>,
     * <code>B</code>, <code>A</code></li>
     * </ul>
     * where <i>n</i> or <code>Z</code> or whatever will be at the top of your
     * list.
     */
    public static SortType DESCENDING = new SortType(GtkSortType.DESCENDING, "DESCENDING");
}
