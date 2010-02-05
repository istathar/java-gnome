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

import java.util.Collections;
import java.util.List;

import com.operationaldynamics.codegen.Thing;

/**
 * Parent of the pseudo Blocks used to describe the getters and setters for
 * GBoxed fields. These are not described by full (define-...) blocks, but are
 * driven by the lines in (fields ...) subcharacterisitcs.
 * 
 * @author Andrew Cowie
 */
/*
 * Both subclasses use the convention of stashing the gType in returnType and
 * the name in blockName. That's just to give them somewhere to go until the
 * Generator is instantiated.
 */
public abstract class AccessorBlock extends FunctionBlock
{
    @SuppressWarnings("unchecked")
    AccessorBlock(final String blockName, final BoxedBlock parent, final List<String[]> parameters) {
        super(blockName, Collections.EMPTY_LIST, parameters);

        ofObject = parent.cName;
        prependReferenceToSelf();
    }

    /*
     * Each AccessorBlock is for a single field, so only one type to report.
     */
    public List<Thing> usesTypes() {
        Thing type = Thing.lookup(returnType).getTypeToImport();
        if (type != null) {
            return Collections.singletonList(type);
        } else {
            return Collections.emptyList();
        }
    }
}
