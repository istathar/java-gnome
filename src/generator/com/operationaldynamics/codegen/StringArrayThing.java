/*
 * java-gnome, a UI library for writing GTK and GNOME programs from Java!
 *
 * Copyright © 2007-2010 Operational Dynamics Consulting, Pty Ltd
 * Copyright © 2007-2008 Vreixo Formoso
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
 * Arrays of strings.
 * 
 * @author Vreixo Formoso
 */
public class StringArrayThing extends ArrayThing
{
    public StringArrayThing(String gType) {
        super(gType, Thing.lookup("gchar*"), "jobjectArray");
    }

    protected StringArrayThing() {}

    String jniConversionDecode(String name) {
        return "bindings_java_convert_strarray_to_gchararray(env, _" + name + ")";
    }

    String jniConversionCleanup(String name) {
        return "bindings_java_convert_gchararray_to_strarray(env, (gchar**)" + name + ", _" + name + ")";
    }

    String jniReturnEncode(String name) {
        return "bindings_java_convert_gchararray_to_jarray(env, (const gchar**)" + name + ")";
    }

    String jniReturnCleanup(String name, char callerOwnsReturn) {
        if (callerOwnsReturn == 't') {
            return "g_strfreev(" + name + ")";
        } else if (callerOwnsReturn == 'l') {
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

}
