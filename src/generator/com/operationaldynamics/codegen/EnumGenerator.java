/*
 * EnumGenerator.java
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

/**
 * Output the file header necessary to declare the class containing the
 * constant objects of our representation of C enums via subclasses of
 * Constant.
 * 
 * @author Andrew Cowie
 */
class EnumGenerator extends TypeGenerator
{
    EnumGenerator(String forObject) {
    // TODO look at ObjectGenerator, probably push its logic up.
    }

    public void writeCBody(PrintWriter out) {
    // TODO Auto-generated method stub
    }

    public void writeJavaBody(PrintWriter out) {
    // TODO Auto-generated method stub
    }

}
