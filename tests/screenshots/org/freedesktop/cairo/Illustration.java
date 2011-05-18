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
 */
package org.freedesktop.cairo;

/**
 * Extend this class to create a illustration to be used in the documentation
 * 
 * @author Kenneth Prugh
 * @since 4.0.16
 */
/*
 * Based heavily off Snapshot. Could probably be handled a bit better
 * especially the use of illustrate().
 */
public abstract class Illustration
{
    /**
     * The Surface containing the illustration you wish to illustrate
     */
    protected Surface surface;

    private String target;

    /**
     * Instantiate a new illustration demo. For (Operator.class, "blah")
     * you'll get doc/api/org/freedesktop/cairo/Operator-blah.png
     */
    protected Illustration(Class<?> underTest, String suffix) {
        this.target = targetFilenameFromClass(underTest, suffix);
    }

    /**
     * Draw the illustration
     */
    abstract public void illustrate();

    static String targetFilenameFromClass(Class<?> underTest, String suffix) {
        final StringBuffer path;
        int i = 0;

        path = new StringBuffer(underTest.getPackage().getName());
        while ((i = path.indexOf(".", i)) != -1) {
            path.setCharAt(i, '/');
        }
        path.insert(0, "doc/api/");

        path.append("/");
        path.append(underTest.getSimpleName());

        if (suffix != null) {
            path.append("-");
            path.append(suffix);
        }

        path.append(".png");

        return path.toString();
    }

    public String getFilename() {
        return target;
    }

    public Surface getSurface() {
        return surface;
    }
}
