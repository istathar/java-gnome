/*
 * DefsParseException.java
 *
 * Copyright (c) 2007 Operational Dynamics Consulting Pty Ltd
 * 
 * The code in this file, and the program it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" See the LICENCE file for the terms governing usage and
 * redistribution.
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
