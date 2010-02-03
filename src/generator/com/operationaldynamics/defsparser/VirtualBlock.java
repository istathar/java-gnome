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
import com.operationaldynamics.codegen.Thing;
import com.operationaldynamics.codegen.VirtualGenerator;
import com.operationaldynamics.driver.DefsFile;

/**
 * The block type for virtual methods. In the java-gnome bindings, these are
 * translated as signal handlers.
 * 
 * @author Andrew Cowie
 */
/*
 * There isn't a whole lot that this gains by being a subclass of
 * FunctionBlock, but it seems as good a place as any to slot it in.
 */
class VirtualBlock extends FunctionBlock
{
    VirtualBlock(String blockName, final List<String[]> characteristics, final List<String[]> parameters) {
        super(blockName, characteristics, parameters);
    }

    public Generator createGenerator(final DefsFile data) {
        return new VirtualGenerator(data, blockName, returnType, parameters);
    }

    /*
     * Insert org.gnome.glib.Signal ("Signal" is a pseudo gType registered in
     * Thing.<clinit>(). Yes, this is a bit kludgy.
     */
    public List<Thing> usesTypes() {
        List<Thing> things;

        things = super.usesTypes();
        things.add(Thing.lookup("Signal"));

        return things;
    }
}
