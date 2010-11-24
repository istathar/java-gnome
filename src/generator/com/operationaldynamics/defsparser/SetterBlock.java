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

import com.operationaldynamics.codegen.Generator;
import com.operationaldynamics.codegen.SetterGenerator;
import com.operationaldynamics.driver.DefsFile;

/**
 * Pseudo Block which is to be created if a field in a (define-boxed ...)
 * block is writable. TODO describe what that looks like, exactly.
 * 
 * @author Andrew Cowie
 * @see GetterBlock
 */
public class SetterBlock extends AccessorBlock
{
    SetterBlock(final BoxedBlock parent, final String gType, final String name) {
        // TODO mmm, how can we know if a field can be null?
        super(name, parent, Collections.singletonList(new String[] {
            gType,
            name,
            "no"
        }));

        this.returnType = gType;
    }

    public Generator createGenerator(final DefsFile data) {
        return new SetterGenerator(data, returnType, blockName, parameters);
    }
}
