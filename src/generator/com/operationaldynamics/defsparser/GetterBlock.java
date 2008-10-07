/*
 * GetterBlock.java
 *
 * Copyright (c) 2007 Operational Dynamics Consulting Pty Ltd
 * 
 * The code in this file, and the program it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" See the LICENCE file for the terms governing usage and
 * redistribution.
 */
package com.operationaldynamics.defsparser;

import java.util.Collections;

import com.operationaldynamics.codegen.Generator;
import com.operationaldynamics.codegen.GetterGenerator;
import com.operationaldynamics.driver.DefsFile;

/**
 * Pseudo Block which is to be created when a public field is exposed in a
 * (define-boxed ...) with one or more (fields (...)) subcharacteristics.
 * block is writable. Note that this is <b>not</b> a (define-method ...) block
 * in the source data; we just create these so that we can drive creation of a
 * getter method when the generator runs.
 * 
 * @author Andrew Cowie
 */
public class GetterBlock extends AccessorBlock
{
    /**
     * This breaks the convention used by all the other Block subclass
     * constructors, but this and SetterBlock are special "psuedo" cases
     * anyway, so no great harm done. The call to super() in AccessorBlock
     * normalizes the behaviour in any case.
     * 
     * @param gType
     *            the type from the (define-boxed (fields (...))) line.
     * @param name
     *            the name from the (define-boxed (fields (...))) line.
     * @param ofObject
     *            the boxed this getter belongs to
     */
    @SuppressWarnings("unchecked")
    GetterBlock(final BoxedBlock parent, final String gType, final String name) {
        super(name, parent, Collections.EMPTY_LIST);

        this.returnType = gType;
    }

    public Generator createGenerator(final DefsFile data) {
        return new GetterGenerator(data, returnType, blockName, parameters);
    }

}
