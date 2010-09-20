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
 * @author Kenneth Prugh
 */
abstract class IllustrationOperator extends Illustration
{
    Context cr;

    IllustrationOperator(Class<?> cls, String suffix) {
        super(Operator.class, suffix);

        surface = new ImageSurface(Format.ARGB32, 100, 100);
        cr = new Context(surface);
    }

    protected void drawOperator(Operator op) {
        /* Draw the Destination rectangle */
        cr.rectangle(0, 0, 75, 75);
        cr.setSource(0.7, 0, 0, 0.8);
        cr.fill();

        /* Set the Operator */
        cr.setOperator(op);

        /* Draw the Source Rectangle */
        cr.rectangle(40, 40, 75, 75);
        cr.setSource(0, 0, 0.9, 0.4);
        cr.fill();
    }
}
