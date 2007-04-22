/*
 * ValidateBlockUsage.java
 *
 * Copyright (c) 2007 Operational Dynamics Consulting Pty Ltd
 * 
 * The code in this file, and the library it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" See the LICENCE file for the terms governing usage and
 * redistribution.
 */
package com.operationaldynamics.codegen;

import junit.framework.TestCase;

public class ValidateBlockUsage extends TestCase
{

    public final void testBlockOfObjectSetsPointer() {
        Block block = new ObjectBlock("GnomeBlah");
        
        assertEquals("GnomeBlah*", block.ofObject);
    }
}
