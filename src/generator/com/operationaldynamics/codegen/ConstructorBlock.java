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
package com.operationaldynamics.codegen;

import java.util.List;

class ConstructorBlock extends FunctionBlock
{
    protected String isConstructorOf;
    
    ConstructorBlock(String blockName, final List characteristics, final List parameters) {
        super(blockName, characteristics, parameters);
    }
    
    protected final void setIsConstructorOf(final String isConstructorOf) {
        this.isConstructorOf = isConstructorOf;
    }
    
    Generator createGenerator() {
        // TODO
        return null;
    }

}
