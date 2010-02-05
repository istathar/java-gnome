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
}
