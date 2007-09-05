/*
 * GErrorThing.java
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
 * A Thing that represent a GError.
 * 
 * <p>
 * A GError will never wrapped in java. Instead, we throw an exception from
 * JNI side.
 * 
 * @author Vreixo Formoso
 */
public class GErrorThing extends Thing
{
    public GErrorThing() {
        super("GError**", null, null, null, null, null);
        this.cType = "GError*";
    }

    String translationToJava(String name, DefsFile data) {
        throw new Error("This method is undefined for GError");
    }

    String translationToNative(String name) {
        throw new Error("This method is undefined for GError");
    }

    String jniReturnErrorValue() {
        throw new Error("This method is undefined for GError");
    }

    boolean needGuardAgainstNull() {
        throw new Error("This method is undefined for GError");
    }

    public Thing getTypeToImport() {
        return Thing.lookup("GlibException");
    }
}
