/*
 * java-gnome, a UI library for writing GTK and GNOME programs from Java!
 *
 * Copyright © 2007-2010 Operational Dynamics Consulting, Pty Ltd
 *
 * The code in this file, and the program it is a part of, is made available
 * to you by its authors as open source software: you can redistribute it
 * and/or modify it under the terms of the GNU General Public License version
 * 2 ("GPL") as published by the Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GPL for more details.
 *
 * You should have received a copy of the GPL along with this program. If not,
 * see http://www.gnu.org/licenses/. The authors of this program may be
 * contacted through http://java-gnome.sourceforge.net/.
 */
package com.operationaldynamics.codegen;

import com.operationaldynamics.driver.DefsFile;

/**
 * A Thing that represent a type that is fundamental in both Java and C.
 * 
 * <p>
 * This types are not Objects and can be used in the same way in both Java and
 * C, passing JNI boundary needs no special conversion.
 * 
 * @author Andrew Cowie
 */
public class FundamentalThing extends Thing
{
    public FundamentalThing(String gType, String javaType, String nativeType, String jniType) {
        super(gType, null, null, javaType, nativeType, jniType);
    }

    protected FundamentalThing() {}

    String translationToJava(String name, DefsFile data) {
        return name;
    }

    String translationToNative(String name) {
        return name;
    }

    String jniReturnErrorValue() {
        if ("jboolean".equals(jniType)) {
            return " JNI_FALSE";
        } else if ("jint".equals(jniType)) {
            return " 0";
        } else if ("jlong".equals(jniType) || "jdouble".equals(jniType)) {
            return " 0L";
        } else {
            return " FIXME";
        }
    }

    String extraTranslationToJava(String name, DefsFile data) {
        return null;
    }

    boolean needGuardAgainstNull() {
        return false;
    }

    String extraTranslationToNative(String name) {
        return null;
    }

    public Thing getTypeToImport() {
        return null;
    }

    boolean needExtraTranslation() {
        return false;
    }
}
