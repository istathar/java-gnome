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
 * A Thing that represent GError FIXME
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
        throw new RuntimeException("This method is undefined for GError");
    }

    String translationToNative(String name) {
        throw new RuntimeException("This method is undefined for GError");
    }

    String jniReturnErrorValue() {
        throw new RuntimeException("This method is undefined for GError");
    }

    String extraTranslationToJava(String name, DefsFile data) {
        throw new RuntimeException("This method is undefined for GError");
    }
    
    boolean needGuardAgainstNull() {
        throw new RuntimeException("This method is undefined for GError");
    }

    String extraTranslationToNative(String name) {
        throw new RuntimeException("This method is undefined for GError");
    }

    public Thing getTypeToImport() {
        return Thing.lookup("GlibException");
    }

    boolean needExtraTranslation() {
        throw new RuntimeException("This method is undefined for GError");
    }
}
