/*
 * java-gnome, a UI library for writing GTK and GNOME programs from Java!
 *
 * Copyright © 2010 Operational Dynamics Consulting, Pty Ltd and Others
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
package org.freedesktop.icons;

/**
 * Named icons representing application categories.
 * 
 * @author Guillaume Mazoyer
 * @author Andrew Cowie
 * @since 4.0.17
 */
public class CategoryIcon extends Icon
{
    protected CategoryIcon(String name) {
        super(name);
    }

    public static final Icon APPLICATIONS_ACCESSORIES = new CategoryIcon("applications-accessories");

    public static final Icon APPLICATIONS_DEVELOPMENT = new CategoryIcon("applications-development");

    public static final Icon APPLICATIONS_ENGINEERING = new CategoryIcon("applications-engineering");

    public static final Icon APPLICATIONS_GAMES = new CategoryIcon("applications-games");

    public static final Icon APPLICATIONS_GRAPHICS = new CategoryIcon("applications-graphics");

    public static final Icon APPLICATIONS_INTERNET = new CategoryIcon("applications-internet");

    public static final Icon APPLICATIONS_MULTIMEDIA = new CategoryIcon("applications-multimedia");

    public static final Icon APPLICATIONS_OFFICE = new CategoryIcon("applications-office");

    public static final Icon APPLICATIONS_OTHER = new CategoryIcon("applications-other");

    public static final Icon APPLICATIONS_SCIENCE = new CategoryIcon("applications-science");

    public static final Icon APPLICATIONS_SYSTEM = new CategoryIcon("applications-system");

    public static final Icon APPLICATIONS_UTILITIES = new CategoryIcon("applications-utilities");

    public static final Icon PREFERENCES_DESKTOP_PERIPHERALS = new CategoryIcon(
            "preferences-desktop-peripherals");

    public static final Icon PREFERENCES_DESKTOP_PERSONAL = new CategoryIcon(
            "preferences-desktop-personal");

    public static final Icon PREFERENCES_DESKTOP = new CategoryIcon("preferences-desktop");

    public static final Icon PREFERENCES_OTHER = new CategoryIcon("preferences-other");

    public static final Icon PREFERENCES_SYSTEM_NETWORK = new CategoryIcon("preferences-system-network");

    public static final Icon PREFERENCES_SYSTEM = new CategoryIcon("preferences-system");

    public static final Icon SYSTEM_HELP = new CategoryIcon("system-help");
}
