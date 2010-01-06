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

public class InterfaceThing extends ObjectThing
{
    public InterfaceThing(String gType, String bindingsPackage, String bindingsClass, String javaType) {
        super(gType, bindingsPackage, bindingsClass, javaType);
    }

    protected InterfaceThing() {}

    /*
     * GInterfaces are modelled as Java interfaces, but since objectFor()
     * takes Proxy as its type, we need to cast the passed self references
     * (which are concrete Proxies subclasses, of course)
     */
    String translationToNative(String name) {
        return "pointerOf((Object) " + name + ")";
    }
}
