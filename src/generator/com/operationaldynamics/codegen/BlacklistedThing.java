/*
 * BlacklistedThing.java
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
 * Types that are blacklisted. This could be hard coded for either code
 * generator or java-gnome architectural reasons, but in all likelihood it is
 * because we haven't the faintest idea what this type is yet due to lacking
 * defs data or lacking Fundamental declarations. Note that this will be
 * mapped as {@link org.freedesktop.bindings.FIXME} which is about as clear an
 * indication as you could ask for that it's foobarred.
 * 
 * <p>
 * <i>The example that led to the creation of this Thing category were
 * function pointers, which we don't have a representation for yet.</i>
 * 
 * @author Andrew Cowie
 */
public class BlacklistedThing extends Thing
{
    public BlacklistedThing(String gType) {
        super(gType, "org.freedesktop.bindings", "", "FIXME", "java.lang.Object", "");
        this.blacklisted = true;
    }

    protected BlacklistedThing() {}

    String translationToJava(String name, DefsFile data) {
        return "(FIXME) " + name;
    }

    String translationToNative(String name) {
        return name;
    }

    String jniReturnErrorValue() {
        return "";
    }

    String extraTranslationToJava(String name, DefsFile data) {
        return null;
    }

    String extraTranslationToNative(String name) {
        return null;
    }

    public Thing getTypeToImport() {
        return Thing.lookup("FIXME");
    }

    boolean needExtraTranslation() {
        return false;
    }
}
