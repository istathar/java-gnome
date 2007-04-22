/*
 * VirtualBlock.java
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
 * The block type for virtual methods. In the java-gnome bindings, these are
 * translated as signal handlers.
 * 
 * @author Andrew Cowie
 */
/*
 * There isn't a whole lot that this gains by being a subclass of
 * FunctionBlock, but it seems as good a place as any to slot it in.
 */
public class VirtualBlock extends FunctionBlock
{
    VirtualBlock(String blockName) {
        super(blockName);
    }

    Generator generator() {
        // TODO Auto-generated method stub
        return null;
    }
}
