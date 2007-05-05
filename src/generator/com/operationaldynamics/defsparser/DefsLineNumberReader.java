/*
 * DefsLineNumberReader.java
 *
 * Copyright (c) 2007 Operational Dynamics Consulting Pty Ltd
 * 
 * The code in this file, and the library it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" See the LICENCE file for the terms governing usage and
 * redistribution.
 */
package com.operationaldynamics.defsparser;

import java.io.File;
import java.io.FileReader;
import java.io.LineNumberReader;

/**
 * A LineNumberReader that knows the name of the file its reading.
 * 
 * @author Andrew Cowie
 */
public class DefsLineNumberReader extends LineNumberReader
{
    private String filename;

    /*
     * Not the prettiest constructor ever.
     */
    public DefsLineNumberReader(FileReader in, File file) {
        super(in);
        this.filename = file.getName();
    }

    public String getFilename() {
        return filename;
    }
}
