/*
 * java-gnome, a UI library for writing GTK and GNOME programs from Java!
 *
 * Copyright © 2007-2010 Operational Dynamics Consulting, Pty Ltd and Others
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

import com.operationaldynamics.codegen.EntityGenerator;
import com.operationaldynamics.codegen.EntityThing;
import com.operationaldynamics.codegen.Generator;
import com.operationaldynamics.codegen.Thing;
import com.operationaldynamics.driver.DefsFile;

/**
 * Block object representing the .defs data defining the entities in
 * (currently) the Cairo library. The key feature is forcing the bindings
 * class name to be wildly different than the wacko C side name.
 * 
 * @author Andrew Cowie
 */
public class EntityBlock extends TypeBlock
{
    public EntityBlock(final String blockName, final List<String[]> characteristics) {
        super(blockName, characteristics);
    }

    public Thing createThing() {
        final String bindingsPackage;
        final EntityThing t;

        bindingsPackage = moduleToJavaPackage(inModule);

        if (bindingsPackage.equals("org.freedesktop.enchant")) {
            t = new EntityThing(addPointerSymbol(cName), bindingsPackage, cName, blockName);
        } else {
            /*
             * Note that we're not using cName here; it's cairo_t and not
             * suitable as a generated bindings layer class name.
             */
            t = new EntityThing(addPointerSymbol(cName), bindingsPackage, inModule + blockName,
                    blockName);
        }

        t.setImportHeader(importHeader);
        return t;
    }

    public Generator createGenerator(final DefsFile data) {
        return new EntityGenerator(data);
    }
}
