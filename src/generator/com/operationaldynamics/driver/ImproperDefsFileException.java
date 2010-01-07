/*
 * java-gnome, a UI library for writing GTK and GNOME programs from Java!
 *
 * Copyright © 2007 Vreixo Formoso
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
 */

package com.operationaldynamics.driver;

/**
 * Exception related to errors while parsing .defs. Once a .defs file is
 * parsed, we then attempt to fit it into our model of how such files should
 * be laid out. While there is nothing in the (define-...) blocks themselves
 * that implies order, we impose the additional requirement that all the
 * blocks related to one native type be in a single .defs file If the program
 * cannot figure out how to adapt a given file in such terms, this exception
 * is thrown.
 * 
 * @author Vreixo Formoso
 * @author Andrew Cowie
 */
public class ImproperDefsFileException extends Exception
{
    private static final long serialVersionUID = 1L;

    public ImproperDefsFileException(String message, Throwable cause) {
        super(message, cause);
    }

    public ImproperDefsFileException(String message) {
        super(message);
    }

    public ImproperDefsFileException(Throwable cause) {
        super(cause);
    }
}
