/*
 * EnumBlock.java
 *
 * Copyright (c) 2007 Operational Dynamics Consulting Pty Ltd, and Others
 * 
 * The code in this file, and the library it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" See the LICENCE file for the terms governing usage and
 * redistribution.
 */
package com.operationaldynamics.defsparser;

import java.util.List;

import com.operationaldynamics.codegen.EnumGenerator;
import com.operationaldynamics.codegen.ConstantThing;
import com.operationaldynamics.codegen.Generator;
import com.operationaldynamics.codegen.Thing;
import com.operationaldynamics.driver.DefsFile;

/**
 * Block object representing the .defs data defining a Enum.
 * 
 * Source .defs data for an emum is of the following form:
 * 
 * <pre>
 * (define-enum ReliefStyle
 *   (in-module &quot;Gtk&quot;)
 *   (c-name &quot;GtkReliefStyle&quot;)
 *   (values
 *     '(&quot;normal&quot; &quot;GTK_RELIEF_NORMAL&quot;)
 *     '(&quot;half&quot; &quot;GTK_RELIEF_HALF&quot;)
 *     '(&quot;none&quot; &quot;GTK_RELIEF_NONE&quot;)
 *   )
 * )
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

    EnumBlock(String blockName, List characteristics, List values) {
        super(blockName, characteristics);

        processValues(values);
    }

    private void processValues(final List values) {
        this.values = (String[][]) values.toArray(new String[values.size()][]);
    }

    public Generator createGenerator(final DefsFile data) {
        return new EnumGenerator(data, values);
    }

    public Thing createThing() {
        return new ConstantThing(cName, moduleToJavaPackage(inModule), cName, blockName);
    }
}
