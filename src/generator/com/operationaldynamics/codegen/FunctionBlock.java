/*
 * FunctionBlock.java
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
 * A .defs (define-function...) block, and the superclass for other entities
 * that are represented in C with functions. As it stands presently, the only
 * actual function we know what to do with are constructors, for which we have
 * subclass ConstructorBlock.
 * 
 * @author Andrew Cowie
 */
/*
 * Change from abstract if we ever figure out something useful to do with
 * other (define-function...) blocks.
 */
abstract class FunctionBlock extends Block
{
    protected String returnType;

    protected String cName;

    protected String[][] parameters;

    protected String callerOwnsReturn;

    FunctionBlock(final String blockName, final List characteristics, final List parameters) {
        super(blockName, characteristics);

        processParameters(parameters);
    }

    private void processParameters(final List parameters) {
        this.parameters = (String[][]) parameters.toArray(new String[parameters.size()][]);
    }

    protected final void setCallerOwnsReturn(final String callerOwnsReturn) {
        this.callerOwnsReturn = callerOwnsReturn;
    }

    protected final void setCName(final String name) {
        this.cName = name;
    }

    protected final void setReturnType(final String returnType) {
        this.returnType = returnType;
    }

    /**
     * Only the TypeBlock class hierarchy can create and return Things that
     * match a given Block.
     */
    Thing createThing() {
        throw new UnsupportedOperationException();
    }
}
