/*
 * java-gnome, a UI library for writing GTK and GNOME programs from Java!
 *
 * Copyright © 2012 Operational Dynamics Consulting, Pty Ltd and Others
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

import org.freedesktop.bindings.Flag;

/**
 * Constants used to specify the type of license for an application.
 * 
 * @author Guillaume Mazoyer
 * @since 4.1.2
 */
public class License extends Flag
{
    protected License(int ordinal, String nickname) {
        super(ordinal, nickname);
    }

    /**
     * No license specified.
     */
    public static final License UNKNOWN = new License(GtkLicense.UNKNOWN, "UNKNOWN");

    /**
     * A license text is to be specified by the developer.
     */
    public static final License CUSTOM = new License(GtkLicense.CUSTOM, "CUSTOM");

    /**
     * The GNU General Public License, version 2.0.
     */
    public static final License GPL_2_0 = new License(GtkLicense.GPL_2_0, "GPL_2_0");

    /**
     * The GNU General Public License, version 3.0.
     */
    public static final License GPL_3_0 = new License(GtkLicense.GPL_3_0, "GPL_3_0");

    /**
     * The GNU Lesser General Public License, version 2.1.
     */
    public static final License LGPL_2_1 = new License(GtkLicense.LGPL_2_1, "LGPL_2_1");

    /**
     * The GNU Lesser General Public License, version 3.0.
     */
    public static final License LGPL_3_0 = new License(GtkLicense.LGPL_3_0, "LGPL_3_0");

    /**
     * The BSD standard license.
     */
    public static final License BSD = new License(GtkLicense.BSD, "BSD");

    /**
     * The MIT/X11 standard license.
     */
    public static final License MIT_X11 = new License(GtkLicense.MIT_X11, "MIT_X11");

    /**
     * The Artistic License, version 2.0.
     */
    public static final License ARTISTIC = new License(GtkLicense.ARTISTIC, "ARTISTIC");
}
