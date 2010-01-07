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

/**
 * FIXME! Types that are legit but that we simply don't know what to do with
 * yet, either in the terms of code generator itself or more likely in the
 * architecture of java-gnome as a whole. <b>These are known cases that WILL
 * be fixed in due course</b>.
 * 
 * @author Andrew Cowie
 */
public class FixmeThing extends BlacklistedThing
{
    public FixmeThing(String gType) {
        super(gType);
    }

    protected FixmeThing() {}
}
