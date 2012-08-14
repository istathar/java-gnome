/*
 * java-gnome, a UI library for writing GTK and GNOME programs from Java!
 *
 * Copyright © 2006-2010 Operational Dynamics Consulting, Pty Ltd
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
 * The relief to be drawn around a Button. Ordinarily when you think of a
 * Button you think of something that can obviously be pressed, and the relief
 * that GTK theme engines draw around Buttons emphasizes this accordingly. You
 * can, however, change this behaviour with this class.
 * 
 * @author Andrew Cowie
 * @since 4.0.1
 */
public final class ReliefStyle extends Constant
{
    private ReliefStyle(int ordinal, String nickname) {
        super(ordinal, nickname);
    }

    /**
     * Draw normal relief around the Button. In other words, it looks like
     * something you can press. This is the default.
     */
    public static final ReliefStyle NORMAL = new ReliefStyle(GtkReliefStyle.NORMAL, "NORMAL");

    /**
     * Only draw relief around the Button "half" the time.
     */
    /*
     * TODO this means what, exactly? To be honest, I thought that
     * GTK_RELIEF_HALF would exhibit the behaviour described for NONE below.
     */
    public static final ReliefStyle HALF = new ReliefStyle(GtkReliefStyle.HALF, "HALF");

    /**
     * Draw no relief around the Button at all. This is actually a misnomer:
     * no relief is drawn except when the mouse hovers over it, at which point
     * it suddenly decorates up like the Button it really is. This is terrific
     * when you do not want a Button to attract attention, but want to give a
     * hint that it actually <i>is</i> a Button when the user's mouse hovers
     * over it and when the Button is activated.
     */
    public static final ReliefStyle NONE = new ReliefStyle(GtkReliefStyle.NONE, "NONE");
}
