/*
 * DefsException.java
 *
 * Copyright (c) 2007 Operational Dynamics Consulting Pty Ltd
 * Copyright (c) 2007 Vreixo Formoso
 * 
 * The code in this file, and the library it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" See the LICENCE file for the terms governing usage and
 * redistribution.
 */

package com.operationaldynamics.defsparser;

/**
 * Exception related to errors while parsing .defs.
 * 
 * TODO it extends RuntimeException, but in my opinion we should extend
 * Exception and add corresponding throws clauses when needed.
 * 
 * @author Vreixo Formoso
 */
public class DefsException extends RuntimeException
{

    DefsException(String message, Throwable cause) {
        super(message, cause);
    }

    DefsException(String message) {
        super(message);
    }

    DefsException(Throwable cause) {
        super(cause);
    }

}
