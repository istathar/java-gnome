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
 * Represent arrays of Constants, Enums or Flags, used as ouput parameters.
 * 
 * @author Vreixo Formoso
 */
public class ConstantArrayThing extends ArrayThing
{

    public ConstantArrayThing(String gType, Thing type) {
        super(gType, type);
    }

    protected ConstantArrayThing() {}

    /*
     * FIXME do we need this?
     */
    String translationToJava(String name, DefsFile data) {
        System.out.println("Warning: Not supported return of Constant array.");
        return "null";
    }

    String translationToNative(String name) {
        return "_" + name;
    }

    String jniConversionDecode(String name) {
        return "(*env)->GetIntArrayElements(env, _" + name + ", NULL)";
    }

    String jniConversionCleanup(String name) {
        return "(*env)->ReleaseIntArrayElements(env, _" + name + ", (jint*)" + name + ", 0)";
    }

    /*
     * FIXME do we need this?
     */
    String jniReturnEncode(String name) {
        System.out.println("Warning: Not supported return of Constant array.");
        return "NULL";
    }

    String jniReturnErrorValue() {
        return "NULL";
    }

    String extraTranslationToJava(String name, DefsFile data) {
        if (type instanceof FlagsThing) {
            return "fillFlagArray(" + type.javaTypeInContext(data) + ".class, " + name + ", _" + name
                    + ")";
        } else {
            return "fillEnumArray(" + type.javaTypeInContext(data) + ".class, " + name + ", _" + name
                    + ")";
        }
    }

    String extraTranslationToNative(String name) {
        return "numsOf(" + name + ")";
    }

    boolean needExtraTranslation() {
        return true;
    }
}
