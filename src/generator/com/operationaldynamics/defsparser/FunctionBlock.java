/*
 * FunctionBlock.java
 *
 * Copyright (c) 2007-2008 Operational Dynamics Consulting Pty Ltd
 * 
 * The code in this file, and the program it is a part of, are made available
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
 * that are represented in C with functions. This also includes functions with
 * the "is-constructor-of" attributes, i.e. constructors.
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

    protected String varargs;

    protected String isConstructorOf;

    FunctionBlock(final String blockName, final List<String[]> characteristics,
            final List<String[]> parameters) {
        super(blockName, characteristics);

        processParameters(parameters);

        if (varargs != null) {
            appendVarargsMark();
        }
    }

    final void setOfObject(final String ofObject) {
        this.ofObject = ofObject;
    }

    private void processParameters(final List<String[]> parameters) {
        this.parameters = parameters.toArray(new String[parameters.size()][]);
    }

    protected final void setCallerOwnsReturn(final String callerOwnsReturn) {
        this.callerOwnsReturn = callerOwnsReturn;
    }

    protected final char getCallerOwnsReturn() {
        if (this.callerOwnsReturn == null) {
            return 'f';
        } else {
            return this.callerOwnsReturn.charAt(1);
        }
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
     * We don't model variable length arguments in java-gnome, but we do need
     * to pass this along so that we can stick a NULL as a last argument to
     * avoid "warning: not enough variable arguments to fit a sentinel" from
     * the C compiler.
     */
    protected final void setVarargs(final String value) {
        this.varargs = value;
    }

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
    public List<Thing> usesTypes() {
        List<Thing> types;
        Thing t;

        types = new ArrayList<Thing>(parameters.length + 1);

        /*
         * For constructors, our translation layer method will return long and
         * not the actual type, so we don't need to import it.
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
        target[0][2] = "no"; /* self can't ever be null */

        parameters = target;
    }

    /**
     * The varrags case is handled, essentially, as an artificial last
     * parameter. This utility method appends a marker to show that the
     * function being described by this Block was declared as taking variable
     * arguments. We do not actually present this with any kind of public API
     * (and may never do so), but we do need to deal with this at the C
     * library call layer because those functions require a NULL in the
     * argument list to signal the end of the list.
     * 
     * The FunctionGenerator constructor will strip it off the end of the
     * parameters list (thus allowing us to avoid needing a SentinalThing).
     */
    protected void appendVarargsMark() {
        String[][] target;

        target = new String[parameters.length + 1][3];
        System.arraycopy(parameters, 0, target, 0, parameters.length);

        target[parameters.length][0] = "...";

        parameters = target;
    }

    public Generator createGenerator(final DefsFile data) {
        if (isConstructorOf != null) {
            return new ConstructorGenerator(data, blockName, returnType, cName, parameters,
                    getCallerOwnsReturn());
        } else {
            return new FunctionGenerator(data, blockName, returnType, cName, parameters,
                    getCallerOwnsReturn());
        }
    }

}
