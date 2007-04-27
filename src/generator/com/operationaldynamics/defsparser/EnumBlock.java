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
package com.operationaldynamics.defsparser;

import java.util.List;

import com.operationaldynamics.codegen.EnumGenerator;
import com.operationaldynamics.codegen.EnumThing;
import com.operationaldynamics.codegen.Generator;
import com.operationaldynamics.codegen.Thing;

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
