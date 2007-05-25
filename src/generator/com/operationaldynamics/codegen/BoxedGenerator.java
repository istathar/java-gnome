/*
 * BoxedGenerator.java
 *
 * Copyright (c) 2007 Operational Dynamics Consulting Pty Ltd, and Others
 * 
 * The code in this file, and the library it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" See the LICENCE file for the terms governing usage and
 * redistribution.
 */
package com.operationaldynamics.codegen;

/*
 * This file originally contained a massive contribution of code to render
 * getters and setters for fields. That has been refactored to GetterGenerator
 * and SetterGenerator, but credit is due to Vreixo for having led the way
 * with the development of this part of the codegen.
 */

import com.operationaldynamics.driver.DefsFile;

/**
 * Output the file header and include statements necessary to begin the
 * translation code for a GBoxed. This Generator renders a BoxedBlock into the
 * compilation unit class declaration, along with necessary file headers and
 * include statements, care of its parent, {@link TypeGenerator}.
 * 
 * @author Vreixo Formoso
 */
public class BoxedGenerator extends TypeGenerator
{
    public BoxedGenerator(DefsFile data) {
        super(data);
    }
}
