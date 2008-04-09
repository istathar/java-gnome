/*
 * DeprecatedException.java
 *
 * Copyright (c) 2007 Operational Dynamics Consulting Pty Ltd
 * 
 * The code in this file, and the program it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" See the LICENCE file for the terms governing usage and
 * redistribution.
 */
package com.operationaldynamics.defsparser;

/**
 * Used to cause the parser to skip a block containing a deprecated
 * declaration.
 * 
 * @author Andrew Cowie
 */
class DeprecatedException extends RuntimeException
{
    private static final long serialVersionUID = 1L;

    DeprecatedException(String msg) {
        super(msg);
    }
}
