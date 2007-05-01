/*
 * TypeBlock.java
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
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import com.operationaldynamics.codegen.FundamentalThing;
import com.operationaldynamics.codegen.Thing;

/**
 * Base class for blocks that define types.
 * 
 * @author Andrew Cowie
 * @author Vreixo Formoso
 */
public abstract class TypeBlock extends Block
{
    protected String inModule;

    protected String cName;
    
    /** a list of FunctionBlock's with all functions related to this type */
    protected List functions;

    protected TypeBlock(final String blockName, final List characteristics) {
        super(blockName, characteristics);
    }

    final void setCName(final String name) {
        this.cName = name;
    }
    
    /**
     * Get the type wrapper Thing appropriate to this Block.
     */
    public abstract Thing createThing();

    /**
     * We ignore gtype-id completely as it is unnecessary in the java-gnome
     * context, but this stub allows the reflection to work when it hits a
     * characteristic so named.
     */
    protected final void setGtypeId(final String gtypeId) {}

    protected final void setInModule(final String inModule) {
        this.inModule = inModule;
    }

    /**
     * Get the blocks for funtions related to this type.
     * 
     * Sure AfC prefers an array here :) !!!
     * 
     * @return
     *      a List of {@link FunctionBlock}
     */
    public List getFunctions() {
        return functions;
    }
    
    void setFunctions(List functions) {
        this.functions = functions;
    }
    
    /**
     * Convert the module name to a package suiting the Java namespace and our
     * mapping into it. At the moment, this simply means "Gtk" ->
     * "org.gnome.gtk". FUTURE when we wrap things outside of GNOME this will
     * have to change somewhat radically.
     */
    protected static String moduleToJavaPackage(String module) {
        StringBuffer buf;
        char ch;

        buf = new StringBuffer(module);

        ch = buf.charAt(0);
        ch = Character.toLowerCase(ch);
        buf.setCharAt(0, ch);

        buf.insert(0, "org.gnome.");

        return buf.toString();
    }

    /*
     * This will have to change if we start using the values
     * subcharacteristics in ObjectBlocks, but for now, none of the TypeBlocks
     * import anything over and above themselves, but only the types used
     * by its functions.
     */
    public final List usesTypes() {
        
        final HashSet types;
        Iterator blockiter, iter;

        types = new HashSet();

        if ( functions == null ) {
            return Collections.EMPTY_LIST;
        }
        
        blockiter = functions.iterator();
        while ( blockiter.hasNext() ) {
            List things;

            things = ( (Block) blockiter.next() ).usesTypes();

            iter = things.iterator();
            while (iter.hasNext()) {
                Thing t = (Thing) iter.next();
                if (t instanceof FundamentalThing) {
                    continue;
                }
                // As a Set it won't do duplicates. Ta-da.
                types.add(t);
            }
        }
        
        return new ArrayList(types);
    }
}
