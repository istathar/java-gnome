/*
 * ObjectBlock.java
 *
 * Copyright (c) 2007 Operational Dynamics Consulting Pty Ltd
 * 
 * The code in this file, and the library it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" See the LICENCE file for the terms governing usage and
 * redistribution.
 */
package com.operationaldynamics.codegen;

class ObjectBlock extends TypeBlock
{
    protected String parent;

    protected String[][] fields;

    ObjectBlock(final String blockName) {
        super(blockName);
        setOfObject(blockName);
    }

    final void setFields(final String[][] fields) {
        this.fields = fields;
    }

    final void setParent(final String parent) {
        this.parent = parent;
    }

    Generator generator() {
        return new ObjectGenerator(null); // FIXME
    }
}
