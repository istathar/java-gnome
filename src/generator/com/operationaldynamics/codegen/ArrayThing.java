/*
 * java-gnome, a UI library for writing GTK and GNOME programs from Java!
 *
 * Copyright © 2007-2010 Operational Dynamics Consulting, Pty Ltd
 * Copyright © 2007 Vreixo Formoso
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

/**
 * Base class for collections of Things and output parameters, that are
 * treated in Java as an array.
 * 
 * @author Vreixo Formoso
 */
public abstract class ArrayThing extends Thing
{
    protected Thing type;

    public ArrayThing(String gType, Thing type) {
        super(gType, null, null, type.javaType + "[]", type.nativeType + "[]", type.jniType + "Array");
        this.type = type;
    }

    public ArrayThing(String gType, Thing type, String jniArrayType) {
        super(gType, null, null, type.javaType + "[]", type.nativeType + "[]", jniArrayType);
        this.type = type;
    }

    protected ArrayThing() {}

    String jniReturnErrorValue() {
        return "NULL";
    }

    boolean jniConversionCanFail() {
        return true;
    }

    public Thing getTypeToImport() {
        return type.getTypeToImport();
    }

    boolean jniConversionHandlesNull() {
        return false;
    }
}
