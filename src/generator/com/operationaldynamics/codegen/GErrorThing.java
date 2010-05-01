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
