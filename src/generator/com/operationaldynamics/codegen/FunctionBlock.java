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

    protected boolean callerOwnsReturn;

    FunctionBlock(final String blockName) {
        super(blockName);
    }

    void setCallerOwnsReturn(final String callerOwnsReturn) {
        if (callerOwnsReturn.equals("#t")) {
            this.callerOwnsReturn = true;
        } else {
            this.callerOwnsReturn = false;
        }
    }

    final void setCName(final String name) {
        this.cName = name;
    }

    final void setParameters(final String[][] parameters) {
        this.parameters = parameters;
    }

    final void setReturnType(final String returnType) {
        this.returnType = returnType;
    }
}
