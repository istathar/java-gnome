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

import com.operationaldynamics.codegen.Generator;
import com.operationaldynamics.codegen.InterfaceGenerator;
import com.operationaldynamics.codegen.InterfaceThing;
import com.operationaldynamics.codegen.Thing;
import com.operationaldynamics.driver.DefsFile;

/**
 * ObjectBlocks could well be subclasses of TypeBlock, but we need the parent
 * field and that's already implemented in ObjectBlock, so, good enough.
 * 
 * @author Andrew Cowie
 */
public class InterfaceBlock extends ObjectBlock
{
    public InterfaceBlock(final String blockName, final List characteristics) {
        super(blockName, characteristics, null);
    }

    public Thing createThing() {
        return new InterfaceThing(addPointerSymbol(cName), moduleToJavaPackage(inModule), cName,
                blockName);
    }

    public Generator createGenerator(final DefsFile data) {
        return new InterfaceGenerator(data, addPointerSymbol(parent));
    }
}
