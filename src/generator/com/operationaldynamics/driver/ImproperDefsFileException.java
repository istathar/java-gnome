/*
 * ImproperDefsFileException.java
 *
 * Copyright (c) 2007 Vreixo Formoso
 * Copyright (c) 2007 Operational Dynamics Consulting Pty Ltd
 * 
 * The code in this file, and the program it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" See the LICENCE file for the terms governing usage and
 * redistribution.
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
