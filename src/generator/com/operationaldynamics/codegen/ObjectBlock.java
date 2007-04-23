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

import java.util.List;

/**
 * Block object representing the .defs data defining a GObject.
 * 
 * @author Andrew Cowie
 */
class ObjectBlock extends TypeBlock
{
    protected String parent;

    ObjectBlock(final String blockName, final List characteristics, final List fields) {
        super(blockName, characteristics);

        processFields(fields);
    }

    /**
     * The "(fields ...)" subcharacteristic lines present in some GObject
     * definitions are not used by the java-gnome bindings at present.
     */
    /*
     * fields would be String[][]
     */
    protected final void processFields(List fields) {}

    protected final void setParent(final String parent) {
        this.parent = parent;
    }

    Thing createThing() {
        return new ObjectThing(addPointerSymbol(cName), moduleToJavaPackage(inModule), cName, blockName);
    }

    Generator createGenerator() {
        return new ObjectGenerator(addPointerSymbol(cName));
    }
}
