/*
 * EnumBlock.java
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

class EnumBlock extends TypeBlock
{
    EnumBlock(String blockName, List characteristics, List values) {
        super(blockName, characteristics);
        
        // FIXME handle values!
    }

    public Generator createGenerator() {
        return new EnumGenerator(cName);
    }

    public Thing createThing() {
        return new EnumThing(cName, moduleToJavaPackage(inModule), cName, blockName);
    }

}
