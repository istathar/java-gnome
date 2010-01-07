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
 * Types corresponding to objects defined in (define-flags ...) blocks. These
 * map to Flag subclasses in our bindings.
 * 
 * @author Vreixo Formoso
 */
public class FlagsThing extends EnumThing
{
    public FlagsThing(String gType, String javaPackage, String javaClass, String javaType) {
        super(gType, javaPackage, javaClass, javaType);
    }

    protected FlagsThing() {}

    String translationToJava(String name, DefsFile data) {
        return "(" + javaTypeInContext(data) + ") flagFor(" + javaTypeInContext(data) + ".class, "
                + name + ")";
    }
}
