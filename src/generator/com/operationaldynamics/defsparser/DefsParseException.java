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
 */
package com.operationaldynamics.defsparser;

import java.text.ParseException;

/**
 * Failure to parse a line of a .defs file
 * 
 * @author Andrew Cowie
 */
public class DefsParseException extends ParseException
{
    private static final long serialVersionUID = 1L;

    /**
     * The three parameters are aggregated to gether into one message.
     * 
     * @param problem
     * @param defsLine
     *            the raw line from the defs file that the parser choked on.
     * @param lineNumber
     *            the line of the defs file where the problem occured.
     */
    public DefsParseException(String problem, String defsLine, DefsLineNumberReader in) {
        super("In " + in.getFilename() + ", line " + in.getLineNumber() + ":\n" + defsLine + "\n"
                + problem, 0);
    }
}
