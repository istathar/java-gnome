/*
 * AccessorBlock.java
 *
 * Copyright (c) 2007-2008 Operational Dynamics Consulting Pty Ltd
 * 
 * The code in this file, and the program it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" See the LICENCE file for the terms governing usage and
 * redistribution.
 */
package com.operationaldynamics.defsparser;

import java.util.Collections;
import java.util.List;

import com.operationaldynamics.codegen.Thing;

/**
 * Parent of the pseudo Blocks used to describe the getters and setters for
 * GBoxed fields. These are not described by full (define-...) blocks, but are
 * driven by the lines in (fields ...) subcharacterisitcs.
 * 
 * @author Andrew Cowie
 */
/*
 * Both subclasses use the convention of stashing the gType in returnType and
 * the name in blockName. That's just to give them somewhere to go until the
 * Generator is instantiated.
 */
public abstract class AccessorBlock extends FunctionBlock
{
    @SuppressWarnings("unchecked")
    AccessorBlock(final String blockName, final BoxedBlock parent, final List<String[]> parameters) {
        super(blockName, Collections.EMPTY_LIST, parameters);

        ofObject = parent.cName;
        prependReferenceToSelf();
    }

    /*
     * Each AccessorBlock is for a single field, so only one type to report.
     */
    public List<Thing> usesTypes() {
        Thing type = Thing.lookup(returnType).getTypeToImport();
        if (type != null) {
            return Collections.singletonList(type);
        } else {
            return Collections.emptyList();
        }
    }
}
