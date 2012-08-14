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
package org.freedesktop.cairo;

import org.freedesktop.bindings.Constant;

/**
 * Error constants for problems arising during Cairo drawing operations.
 * 
 * @author Andrew Cowie
 * @since 4.0.7
 */
class Status extends Constant
{
    private Status(int ordinal, String nickname) {
        super(ordinal, nickname);
    }

    /**
     * It worked!
     */
    public static final Status SUCCESS = new Status(CairoStatus.SUCCESS, "SUCCESS");

    /**
     * Out of memory. Yikes.
     */
    public static final Status NO_MEMORY = new Status(CairoStatus.NO_MEMORY, "NO_MEMORY");

    /**
     * C side, a <code>NULL</code> was encountered when a valid pointer was
     * expected.
     */
    public static final Status NULL_POINTER = new Status(CairoStatus.NULL_POINTER, "NULL_POINTER");

    /**
     * The Surface has been marked as finished, so you can't draw to it
     * anymore.
     */
    public static final Status SURFACE_FINISHED = new Status(CairoStatus.SURFACE_FINISHED,
            "SURFACE_FINISHED");

    /**
     * Write error, you don't have write permissions to a file.
     */
    public static final Status WRITE_ERROR = new Status(CairoStatus.WRITE_ERROR, "WRITE_ERROR");

    /**
     * There is no current point; numerous operations (notably moving
     * relative) require you to have established a current point in the
     * Context.
     * 
     * @since 4.0.10
     */
    public static final Status NO_CURRENT_POINT = new Status(CairoStatus.NO_CURRENT_POINT,
            "NO_CURRENT_POINT");

    /**
     * The transformation matrix is invalid. This can occur if the matrix
     * collapses points together (is degenerate) or doesn't have an inverse.
     * 
     * @since 4.0.10
     */
    public static final Status INVALID_MATRIX = new Status(CairoStatus.INVALID_MATRIX, "INVALID_MATRIX");

    /**
     * Context.restore() misses a previous Context.save() call
     * 
     * @since 4.0.12
     */
    public static final Status INVALID_RESTORE = new Status(CairoStatus.INVALID_RESTORE,
            "INVALID_RESTORE");
}
