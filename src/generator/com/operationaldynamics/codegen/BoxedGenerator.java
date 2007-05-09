/*
 * BoxedGenerator.java
 *
 * Copyright (c) 2007 Operational Dynamics Consulting Pty Ltd
 * 
 * The code in this file, and the library it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" See the LICENCE file for the terms governing usage and
 * redistribution.
 */
package com.operationaldynamics.codegen;

import java.io.PrintWriter;

import com.operationaldynamics.driver.DefsFile;

/**
 * Output the file header and include statements necessary to begin the
 * translation code for a GBoxed. This Generator renders an BoxedBlock into
 * the compilation unit class declaration, along with necessary file headers
 * and include statements, care of its parent, {@link TypeGenerator}.
 * 
 * TODO also write setters and getters for fields 
 * 
 * @author Vreixo Formoso
 */
public class BoxedGenerator extends TypeGenerator
{
    
    /*
     * TODO
     * We also need to write setters and getters for fields. This has two
     * problems to be resolved:
     *  a) Some fields are a GObject itself, not a pointer, so, how to manage
     *     this? is not as easy as pass the address of the object like a
     *     pointer: we need take care that the boxed is not deleted until all
     *     internal objects are no more used from java.
     *  b) Other are defined as "const-XXXX", where XXXX is a GObject, so we
     *     need to drop the const-.
     */
    
    public BoxedGenerator(DefsFile data) {
        super(data);
    }
    
    public void writeTranslationCode(final PrintWriter out) {
        super.writeTranslationCode(out);
        
        //TODO here we need to write code for fields
    }

    public void writeJniCode(final PrintWriter out) {
        super.writeJniCode(out);
        
        //TODO here we need to write code for fields
    }
    
}
