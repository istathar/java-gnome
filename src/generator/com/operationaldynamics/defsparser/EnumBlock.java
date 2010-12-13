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

import com.operationaldynamics.codegen.EnumGenerator;
import com.operationaldynamics.codegen.EnumThing;
import com.operationaldynamics.codegen.Generator;
import com.operationaldynamics.codegen.Thing;
import com.operationaldynamics.driver.DefsFile;

/**
 * Block object representing the .defs data defining a Enum.
 * 
 * Source .defs data for an emum is of the following form:
 * 
 * <pre>
 *  (define-enum ReliefStyle
 *    (in-module &quot;Gtk&quot;)
 *    (c-name &quot;GtkReliefStyle&quot;)
 *    (values
 *      '(&quot;normal&quot; &quot;GTK_RELIEF_NORMAL&quot;)
 *      '(&quot;half&quot; &quot;GTK_RELIEF_HALF&quot;)
 *      '(&quot;none&quot; &quot;GTK_RELIEF_NONE&quot;)
 *    )
 *  )
 * </pre>
 * 
 * Enums are unusual in that all the information needed to define the Java
 * code that will result for this native type is in a single (define...)
 * stanza; most other TypeBlocks are accompanied by a series of FunctionBlock
 * subclasses.
 * 
 * @author Andrew Cowie
 * @author Vreixo Formoso
 */
public class EnumBlock extends TypeBlock
{
    protected String[][] values;

    EnumBlock(String blockName, List<String[]> characteristics, List<String[]> values) {
        super(blockName, characteristics);

        processValues(values);
    }

    private void processValues(final List<String[]> values) {
        this.values = values.toArray(new String[values.size()][]);
    }

    public Generator createGenerator(final DefsFile data) {
        return new EnumGenerator(data, values);
    }

    public Thing createThing() {
        return new EnumThing(cName, moduleToJavaPackage(inModule), inModule + blockName, blockName);
    }
}
