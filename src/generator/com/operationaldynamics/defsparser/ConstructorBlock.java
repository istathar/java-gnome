/*
 * ConstructorBlock.java
 *
 * Copyright (c) 2007 Operational Dynamics Consulting Pty Ltd
 * 
 * The code in this file, and the library it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" See the LICENCE file for the terms governing usage and
 * redistribution.
 */
package com.operationaldynamics.defsparser;

import java.util.List;

import com.operationaldynamics.codegen.ConstructorGenerator;
import com.operationaldynamics.codegen.Generator;

/**
 * A (define-function ...) block containing the description of a GObject's
 * constructor function.
 * 
 * @author Andrew Cowie
 */
class ConstructorBlock extends FunctionBlock
{
    protected String isConstructorOf;

    ConstructorBlock(String blockName, final List characteristics, final List parameters) {
        super(blockName, characteristics, parameters);
    }

    protected final void setIsConstructorOf(final String isConstructorOf) {
        this.isConstructorOf = isConstructorOf;        
    }

    public Generator createGenerator() {
        return new ConstructorGenerator(addPointerSymbol(isConstructorOf), cName, parameters);
    }
}
