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

import java.io.LineNumberReader;
import java.io.Reader;

/**
 * A LineNumberReader that knows the name of the file its reading.
 * 
 * @author Andrew Cowie
 */
public class DefsLineNumberReader extends LineNumberReader
{
    private final String filename;

    /**
     * 
     * @param in
     *            It's pretty much assumed that this will be a FileReader, but
     *            the type is left abstract to facilitate unit tests.
     * @param filename
     *            the name of the file being parsed.
     */
    public DefsLineNumberReader(final Reader in, final String filename) {
        super(in);
        this.filename = filename;
    }

    public String getFilename() {
        return filename;
    }
}
