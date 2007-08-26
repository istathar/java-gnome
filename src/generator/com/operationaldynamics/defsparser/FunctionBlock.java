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

import com.operationaldynamics.codegen.ConstructorGenerator;
import com.operationaldynamics.codegen.FunctionGenerator;
import com.operationaldynamics.codegen.Generator;
import com.operationaldynamics.codegen.InterfaceThing;
import com.operationaldynamics.codegen.Thing;
import com.operationaldynamics.driver.DefsFile;

/**
 * A .defs (define-function...) block, and the superclass for other entities
 * that are represented in C with functions. This also includes functions
 * with the "is-constructor-of" attributes, i.e. constructors.
 * 
 * @author Andrew Cowie
 * @author Vreixo Formoso
 */
public class FunctionBlock extends Block
{
    /**
     * Strictly speaking, this isn't here, but down in MethodBlock and
     * VirtualBlock. But it needs to be figured out before FunctionGenerator
     * can be used in a (define-function ...), and it's present for all the
     * sub block types.
     */
    protected String ofObject;

    protected String returnType;

    protected String cName;

    protected String[][] parameters;

    protected String callerOwnsReturn;
    
    protected String isConstructorOf;

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

    protected final void setIsConstructorOf(final String isConstructorOf) {
        this.isConstructorOf = isConstructorOf;
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
        Thing t;

        types = new ArrayList(parameters.length + 1);

        /*
         * For constructors, our translation layer method will return long
         * and not the actual type, so we don't need to import it.
         */
        if (isConstructorOf == null) {
            t = Thing.lookup(returnType).getTypeToImport();
            if (t != null) {
                types.add(t);
            }
        }

        for (int i = 0; i < parameters.length; i++) {
            t = Thing.lookup(parameters[i][0]);
            t = t.getTypeToImport();
            if (t == null) {
                continue;
            }
            types.add(t);

            if (t instanceof InterfaceThing) {
                types.add(Thing.lookup("GObject*"));
            }
        }

        return types;
    }

    /**
     * Load the reference-to-self that all "method" functions start with onto
     * the beginning of the parameters List. Used by MethodBlock and
     * AccessorBlock, in case you're wondering what this is doing here.
     */
    protected void prependReferenceToSelf() {
        String[][] target;

        target = new String[parameters.length + 1][3];
        System.arraycopy(parameters, 0, target, 1, parameters.length);

        target[0][0] = addPointerSymbol(ofObject);
        target[0][1] = "self";
        target[0][2] = "no"; /* self can't never be null */

        parameters = target;
    }

    public Generator createGenerator(final DefsFile data) {
        if (isConstructorOf != null) {
            return new ConstructorGenerator(data, returnType, cName, parameters);
        } else {
            return new FunctionGenerator(data, blockName, returnType,
                    cName, parameters);
        }
    }

}
