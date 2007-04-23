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
package com.operationaldynamics.codegen;

import java.util.List;

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

    Generator createGenerator() {
        return new MethodGenerator(ofObject, blockName, returnType, cName, parameters);
    }

}
