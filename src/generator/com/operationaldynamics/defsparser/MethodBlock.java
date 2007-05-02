/*
 * MethodBlock.java
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
import com.operationaldynamics.codegen.MethodGenerator;
import com.operationaldynamics.driver.DefsFile;

/**
 * A .defs (define-method...) block.
 * 
 * @author Andrew Cowie
 */
class MethodBlock extends FunctionBlock
{
    /*
     * NOTE: ofObject should be here (by the defs file legacy), but its needed
     * at FunctionGenerator level, and VirtualsBlocks have it too, so push it
     * all the way to Block - after all, we can't compose even an object until
     * we know what class it goes into.
     */

    MethodBlock(final String blockName, final List characteristics, final List parameters) {
        super(blockName, characteristics, parameters);
    }

    public Generator createGenerator(final DefsFile data) {
        return new MethodGenerator(data, blockName, returnType, cName, parameters);
    }
}
