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
package com.operationaldynamics.defsparser;

import java.util.List;

import com.operationaldynamics.codegen.Generator;
import com.operationaldynamics.codegen.InterfaceGenerator;
import com.operationaldynamics.codegen.InterfaceThing;
import com.operationaldynamics.codegen.Thing;
import com.operationaldynamics.driver.DefsFile;

/**
 * ObjectBlocks could well be subclasses of TypeBlock, but we need the parent
 * field and that's already implemented in ObjectBlock, so, good enough.
 * 
 * @author Andrew Cowie
 */
public class InterfaceBlock extends ObjectBlock
{
    public InterfaceBlock(final String blockName, final List<String[]> characteristics) {
        super(blockName, characteristics, null);
    }

    public Thing createThing() {
        return new InterfaceThing(addPointerSymbol(cName), moduleToJavaPackage(inModule), cName,
                blockName);
    }

    public Generator createGenerator(final DefsFile data) {
        return new InterfaceGenerator(data, addPointerSymbol(parent), implementsToArray(interfaces));
    }
}
