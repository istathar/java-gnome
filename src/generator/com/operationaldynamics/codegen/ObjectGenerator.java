/*
 * ObjectGenerator.java
 *
 * Copyright (c) 2007 Operational Dynamics Consulting Pty Ltd
 * 
 * The code in this file, and the library it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" See the LICENCE file for the terms governing usage and
 * redistribution.
 */
package com.operationaldynamics.codegen;

import com.operationaldynamics.driver.DefsFile;

/**
 * Output the file header and include statements necessary to begin the
 * translation code for a GObject. This Generator renders an ObjectBlock into
 * the compilation unit class declaration, along with necessary file headers
 * and include statements, care of its parent, {@link TypeGenerator}
 * 
 * @author Andrew Cowie
 */
public class ObjectGenerator extends TypeGenerator
{
    public ObjectGenerator(DefsFile data) {
        super(data);
    }
}
