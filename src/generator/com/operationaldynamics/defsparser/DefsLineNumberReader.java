/*
 * DefsLineNumberReader.java
 *
 * Copyright (c) 2007 Operational Dynamics Consulting Pty Ltd
 * 
 * The code in this file, and the program it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" See the LICENCE file for the terms governing usage and
 * redistribution.
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
