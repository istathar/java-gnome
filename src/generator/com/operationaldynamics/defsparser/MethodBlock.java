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
import com.operationaldynamics.codegen.MethodGenerator;
import com.operationaldynamics.driver.DefsFile;

/**
 * A .defs block that gives the information for a method on a GObject.
 * 
 * <p>
 * Source .defs data is of the following form:
 * 
 * <pre>
 *  (define-method set_label
 *    (of-object &quot;GtkButton&quot;)
 *    (c-name &quot;gtk_button_set_label&quot;)
 *    (return-type &quot;none&quot;)
 *    (parameters
 *      '(&quot;const-gchar*&quot; &quot;label&quot;)
 *    )
 *  )
 * </pre>
 * 
 * In a sense, the definition of a method in GNOME terms is a function whose
 * first parameter is a pointer to the object that the function will act on.
 * This is implicit in the .defs data so we prepend a reference to self to the
 * parameter list as we construct the object to represent this block.
 * 
 * @author Andrew Cowie
 */
class MethodBlock extends FunctionBlock
{
    /*
     * NOTE: ofObject should be here (by the defs file legacy), but its needed
     * at FunctionGenerator level, and VirtualBlocks have it too.
     */

    MethodBlock(final String blockName, final List<String[]> characteristics,
            final List<String[]> parameters) {
        super(blockName, characteristics, parameters);

        prependReferenceToSelf();
    }

    public Generator createGenerator(final DefsFile data) {
        return new MethodGenerator(data, blockName, returnType, cName, parameters, getCallerOwnsReturn());
    }
}
