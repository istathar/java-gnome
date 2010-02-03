/*
 * java-gnome, a UI library for writing GTK and GNOME programs from Java!
 *
 * Copyright © 2007-2010 Operational Dynamics Consulting, Pty Ltd and Others
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

public class StringThing extends Thing
{
    StringThing(String gType) {
        super(gType, null, null, "String", "String", "jstring");
    }

    protected StringThing() {}

    String jniConversionDecode(String name) {
        return "bindings_java_getString(env, _" + name + ")";
    }

    boolean jniConversionCanFail() {
        return true;
    }

    boolean jniConversionHandlesNull() {
        return false;
    }

    String jniConversionCleanup(String name) {
        return "bindings_java_releaseString(" + name + ")";
    }

    String jniReturnEncode(String name) {
        return "bindings_java_newString(env, " + name + ")";
    }

    String jniReturnErrorValue() {
        return "NULL";
    }

    String jniReturnCleanup(String name, char callerOwnsReturn) {
        if (callerOwnsReturn == 't') {
            return "g_free(" + name + ")";
        } else {
            return null;
        }
    }

    String translationToJava(String name, DefsFile data) {
        return name;
    }

    String translationToNative(String name) {
        return name;
    }

    String extraTranslationToJava(String name, DefsFile data) {
        return null;
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
