/*
 * ObjectGenerator.java
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
 * Output the file header and include statements necessary to begin the
 * translation code for a GObject.
 * 
 * @author Andrew Cowie
 * @author Vreixo Formoso
 */
public class ObjectGenerator extends TypeGenerator
{
    /**
     * 
     * @param gObjectType
     *            the ObjectThing that you are generating code for.
     * @param functions
     *            List of function blocks with the definitions of
     *            constructors, methods and virtuals found in .defs
     */
    public ObjectGenerator(String gObjectType, List functions) {
        super(Thing.lookup(gObjectType), functions);
    }
}
