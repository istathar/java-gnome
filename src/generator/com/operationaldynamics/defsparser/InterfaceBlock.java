/*
 * InterfaceBlock.java
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

import com.operationaldynamics.codegen.InterfaceThing;
import com.operationaldynamics.codegen.Thing;

public class InterfaceBlock extends ObjectBlock
{
    public InterfaceBlock(final String blockName, final List characteristics, final List fields) {
        super(blockName, characteristics, fields);
    }
    
    public Thing createThing() {
        return new InterfaceThing(addPointerSymbol(cName), moduleToJavaPackage(inModule), cName, blockName);
    }
}
