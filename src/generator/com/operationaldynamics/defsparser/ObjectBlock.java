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

import java.util.ArrayList;
import java.util.List;

import com.operationaldynamics.codegen.Generator;
import com.operationaldynamics.codegen.ObjectGenerator;
import com.operationaldynamics.codegen.ObjectThing;
import com.operationaldynamics.codegen.Thing;
import com.operationaldynamics.driver.DefsFile;

/**
 * Block object representing the .defs data defining a GObject.
 * 
 * @author Andrew Cowie
 */
public class ObjectBlock extends TypeBlock
{
    protected String parent;

    protected List<String> interfaces;

    public ObjectBlock(final String blockName, final List<String[]> characteristics,
            final List<String[]> fields) {
        super(blockName, characteristics);

        processFields(fields);
    }

    /**
     * The "(fields ...)" subcharacteristic lines present in some GObject
     * definitions are not used by the java-gnome bindings at present.
     */
    /*
     * fields would be String[][]
     */
    protected final void processFields(List<String[]> fields) {}

    protected final void setParent(final String parent) {
        this.parent = parent;
    }

    protected final void setImplements(final String impl) {
        if (interfaces == null) {
            interfaces = new ArrayList<String>(1);
        }
        interfaces.add(impl);
    }

    public Thing createThing() {
        ObjectThing t = new ObjectThing(addPointerSymbol(cName), moduleToJavaPackage(inModule), cName,
                blockName);
        t.setImportHeader(importHeader);
        return t;
    }

    public Generator createGenerator(final DefsFile data) {
        return new ObjectGenerator(data, addPointerSymbol(parent), implementsToArray(interfaces));
    }

    protected static String[] implementsToArray(List<String> interfaces) {
        String[] implemented;
        int i;

        if (interfaces == null) {
            return new String[0];
        }

        implemented = new String[interfaces.size()];
        for (i = 0; i < implemented.length; i++) {
            implemented[i] = addPointerSymbol(interfaces.get(i));
        }

        return implemented;
    }
}
