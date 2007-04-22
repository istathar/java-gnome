/*
 * TypeBlock.java
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
 * Base class for blocks that define types.
 * 
 * @author Andrew Cowie
 */
abstract class TypeBlock extends Block
{
    protected String inModule;

    protected String cName;

    protected TypeBlock(final String blockName) {
        super(blockName);
    }

    final void setCName(final String name) {
        this.cName = name;
    }

    /**
     * We ignore gtype-id completely as it is unnecessary in the java-gnome
     * context, but this stub allows the reflection to work when it hits a
     * characteristic so named.
     */
    final void setGtypeId(final String gtypeId) {}

    final void setInModule(final String inModule) {
        this.inModule = inModule;
    }
}
