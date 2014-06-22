/*
 * java-gnome, a UI library for writing GTK and GNOME programs from Java!
 *
 * Copyright © 2006-2014 Operational Dynamics Consulting, Pty Ltd
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
package org.freedesktop.bindings;

/**
 * Version constants for the java-gnome library. The top level
 * <code>.config</code> Makefile fragment depends on this file and the
 * <code>./configure</code> of Equivalence extracts the values for the library
 * API version and (pending) release version from the constants defined
 * herein.
 * 
 * @author Andrew Cowie
 * @since 4.0.2
 */
/*
 * This data was formerly in org.gnome.gtk.Gtk; moved here so that hacking on
 * that file's code does not require re-configuring every time it is saved.
 * Later renamed from org.gnome.gtk.Version
 * 
 * We now go to the trouble of wrapping these in getters because otherwise
 * foreign classes will inline these values, making propegating the changes a
 * nightmare.
 */
public final class Version
{
    private static final String APIVERSION = "4.1";

    private static final String VERSION = "4.1.4-dev";

    /**
     * The full (usually three digit) version of java-gnome. This is used in a
     * number of the examples and screenshots, but far more critically it is
     * used to precisely identify which version of the shared library with the
     * native code is to be loaded.
     */
    public static final String getVersion() {
        return VERSION;
    }

    /**
     * The API version (also known as "SLOT") of the java-gnome library.
     * Notably, this is used to name the <code>.jar</code> file.
     */
    public static final String getAPI() {
        return APIVERSION;
    }

    private Version() {}
}
