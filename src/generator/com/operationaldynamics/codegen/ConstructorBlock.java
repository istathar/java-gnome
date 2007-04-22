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

public class ConstructorBlock extends FunctionBlock
{
    String isConstructorOf;
    
    ConstructorBlock(String blockName) {
        super(blockName);
        this.ofObject = null;
    }
    
    final void setIsConstructorOf(final String isConstructorOf) {
        this.isConstructorOf = isConstructorOf;
    }
    
    Generator generator() {
        // TODO
        return null;
    }

}
