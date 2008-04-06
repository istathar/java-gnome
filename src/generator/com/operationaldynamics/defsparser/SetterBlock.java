/*
 * SetterBlock.java
 *
 * Copyright (c) 2007 Operational Dynamics Consulting Pty Ltd
 * 
 * The code in this file, and the program it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" See the LICENCE file for the terms governing usage and
 * redistribution.
 */
package com.operationaldynamics.defsparser;

import java.util.Collections;

import com.operationaldynamics.codegen.Generator;
import com.operationaldynamics.codegen.SetterGenerator;
import com.operationaldynamics.driver.DefsFile;

/**
 * Pseudo Block which is to be created if a field in a (define-boxed ...)
 * block is writable. TODO describe what that looks like, exactly.
 * 
 * @author Andrew Cowie
 * @see GetterBlock
 */
public class SetterBlock extends AccessorBlock
{
    SetterBlock(final BoxedBlock parent, final String gType, final String name) {
        // TODO mmm, how can we know if a field can be null?
        super(name, parent, Collections.singletonList(new String[] {
                gType, name, "no"
        }));

        this.returnType = gType;
    }

    public Generator createGenerator(final DefsFile data) {
        return new SetterGenerator(data, returnType, blockName, parameters);
    }
}
