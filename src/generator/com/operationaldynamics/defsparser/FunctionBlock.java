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
package com.operationaldynamics.defsparser;

import java.util.ArrayList;
import java.util.List;

import com.operationaldynamics.codegen.Thing;

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
public abstract class FunctionBlock extends Block
{
    /**
     * Strictly speaking, this isn't here, but down in MethodBlock and
     * VirtualBlock. But it needs to be figured out before FunctionGenerator
     * can be used in a (define-function ...), and it's present for all the
     * sub block types. As well, by having it here, we can enforce the '*'
     * business in setOfObject() so that lookups are consistent.
     */
    protected String ofObject;

    protected String returnType;

    protected String cName;

    protected String[][] parameters;

    protected String callerOwnsReturn;

    FunctionBlock(final String blockName, final List characteristics, final List parameters) {
        super(blockName, characteristics);

        processParameters(parameters);
    }

    final void setOfObject(final String ofObject) {
        this.ofObject = ofObject;
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

    /*
     * Not modelled in java-gnome at this time
     */
    protected final void setVarargs(final String value) {}

    /**
     * Only the TypeBlock class hierarchy can create and return Things that
     * match a given Block.
     */
    public Thing createThing() {
        throw new UnsupportedOperationException();
    }

    /*
     * Special cases will probably be needed for at least VirtualBlock, but
     * this is a good start.
     */
    public List usesTypes() {
        List types;

        types = new ArrayList(parameters.length + 1);

        types.add(Thing.lookup(returnType));

        for (int i = 0; i < parameters.length; i++) {
            types.add(Thing.lookup(parameters[i][0]));
        }

        return types;
    }
}
