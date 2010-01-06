/*
 * java-gnome, a UI library for writing GTK and GNOME programs from Java!
 *
 * Copyright © 2009-2010 Operational Dynamics Consulting, Pty Ltd
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
 * This is effectively a <code>typedef</code>, except it's hard wired to work
 * the other way: java-gnome effectively declares a type (and uses it in the
 * .defs data) but at the conversion to C layer it is returned to an int of
 * the cType specified.
 * 
 * @author Andrew Cowie
 */
public class TypedefEnumThing extends EnumThing
{
    public TypedefEnumThing(String gType, String cType, String javaPackage, String javaClass,
            String javaType) {
        super(gType, javaPackage, javaClass, javaType);
        super.cType = cType;
    }
}
