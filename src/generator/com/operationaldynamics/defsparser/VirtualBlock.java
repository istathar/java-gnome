/*
 * VirtualBlock.java
 *
 * Copyright (c) 2007-2008 Operational Dynamics Consulting Pty Ltd
 * 
 * The code in this file, and the program it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" See the LICENCE file for the terms governing usage and
 * redistribution.
 */
package com.operationaldynamics.defsparser;

import java.util.List;

import com.operationaldynamics.codegen.Generator;
import com.operationaldynamics.codegen.Thing;
import com.operationaldynamics.codegen.VirtualGenerator;
import com.operationaldynamics.driver.DefsFile;

/**
 * The block type for virtual methods. In the java-gnome bindings, these are
 * translated as signal handlers.
 * 
 * @author Andrew Cowie
 */
/*
 * There isn't a whole lot that this gains by being a subclass of
 * FunctionBlock, but it seems as good a place as any to slot it in.
 */
class VirtualBlock extends FunctionBlock
{
    VirtualBlock(String blockName, final List<String[]> characteristics, final List<String[]> parameters) {
        super(blockName, characteristics, parameters);
    }

    public Generator createGenerator(final DefsFile data) {
        return new VirtualGenerator(data, blockName, returnType, parameters);
    }

    /*
     * Insert org.gnome.glib.Signal ("Signal" is a pseudo gType registered in
     * Thing.<clinit>(). Yes, this is a bit kludgy.
     */
    public List<Thing> usesTypes() {
        List<Thing> things;

        things = super.usesTypes();
        things.add(Thing.lookup("Signal"));

        return things;
    }
}
