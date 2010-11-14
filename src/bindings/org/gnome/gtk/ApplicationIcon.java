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
 * "Claspath Exception"), the copyright holders of this library give you
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

/**
 * Named icons representing individual applications and application
 * categories.
 * 
 * @author Guillaume Mazoyer
 * @author Andrew Cowie
 * @since 4.0.17
 */
public class ApplicationIcon extends Icon
{
    protected ApplicationIcon(String name) {
        super(name);
    }

    public static final Icon APPLICATIONS_UTILITIES = new ApplicationIcon("applications-utilities");

    public static final Icon APPLICATIONS_GRAPHICS = new ApplicationIcon("applications-graphics");

    public static final Icon APPLICATIONS_SYSTEM = new ApplicationIcon("applications-system");

    public static final Icon APPLICATIONS_GAMES = new ApplicationIcon("applications-games");

    public static final Icon APPLICATIONS_INTERNET = new ApplicationIcon("applications-internet");

    public static final Icon APPLICATIONS_DEVELOPMENT = new ApplicationIcon("applications-development");

    public static final Icon APPLICATIONS_SCIENCE = new Icon("applications-science");

    public static final Icon APPLICATIONS_ENGINEERING = new Icon("applications-engineering");

    public static final Icon APPLICATIONS_OTHER = new Icon("applications-other");

    public static final Icon APPLICATIONS_ACCESSORIES = new Icon("applications-accessories");

    public static final Icon APPLICATIONS_OFFICE = new Icon("applications-office");

}
