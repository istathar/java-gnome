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
import com.operationaldynamics.driver.DefsFile;

/**
 * Block object representing the .defs data defining a Enum.
 * 
 * @author Andrew Cowie
 * @author Vreixo Formoso
 */
class EnumBlock extends TypeBlock
{

    protected String[][] values;

    EnumBlock(String blockName, List characteristics, List values) {
        super(blockName, characteristics);

        processValues(values);
    }

    private void processValues(final List values) {
        this.values = (String[][]) values.toArray(new String[values.size()][]);
    }

    public Generator createGenerator(final DefsFile data) {
        return new EnumGenerator(data, values);
    }

    public Thing createThing() {
        return new EnumThing(cName, moduleToJavaPackage(inModule), cName, blockName);
    }

}
