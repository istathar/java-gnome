/*
 * Block.java
 *
 * Copyright (c) 2007 Operational Dynamics Consulting Pty Ltd
 * 
 * The code in this file, and the library it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" See the LICENCE file for the terms governing usage and
 * redistribution.
 */
package com.operationaldynamics.codegen;

/**
 * Base class representing a block s-expression .defs data. The system is
 * predicated around the notion that by the time you are done creating a Block
 * object, you know that Thing (type) the Block refers to, and can look it up
 * and set it accordingly.
 * 
 * <p>
 * All members are to be protected, and setters are to be used (via reflection
 * in the DefsParser) to map the characteristics in each block to fields.
 * 
 * @author Andrew Cowie
 */
abstract class Block
{
    /**
     * Strictly speaking, this isn't here, but down in MethodBlock and
     * VirtualBlock. But it needs to be figured out before FunctionGenerator
     * can be used in a (define-function ...), and it's present for all the
     * sub block types. As well, by having it here, we can enforce the '*'
     * business in setOfObject() so that lookups are consistent.
     */
    protected String ofObject;

    /**
     * The short "python" name for this Object/Function/Method/Constructor/etc
     */
    final String blockName;

    protected Block(final String blockName) {
        this.blockName = blockName;
    }

    /**
     * Add the '*' pointer character because our indexes are built on the
     * convention that the type that can be looked up is "GObject*", not
     * "GObject".
     */
    final void setOfObject(final String ofObject) {
        this.ofObject = ofObject + '*';
    }

    /**
     * Get the code factory appropriate to this Block type.
     */
    abstract Generator generator();
}
