/*
 * MethodBlock.java
 *
 * Copyright (c) 2007 Operational Dynamics Consulting Pty Ltd
 * 
 * The code in this file, and the library it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" See the LICENCE file for the terms governing usage and
 * redistribution.
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
 * (define-method set_label
 *   (of-object &quot;GtkButton&quot;)
 *   (c-name &quot;gtk_button_set_label&quot;)
 *   (return-type &quot;none&quot;)
 *   (parameters
 *     '(&quot;const-gchar*&quot; &quot;label&quot;)
 *   )
 * )
 * </pre>
 * 
 * In a sense, the definition of a method in G terms is a function whose first
 * parameter is a pointer to the object that the function will act on. This is
 * implicit in the .defs data so we prepend a reference to self to the
 * parameter list as we construct the object to represent this block.
 * 
 * @author Andrew Cowie
 */
class MethodBlock extends FunctionBlock
{
    /*
     * NOTE: ofObject should be here (by the defs file legacy), but its needed
     * at FunctionGenerator level, and VirtualsBlocks have it too.
     */

    MethodBlock(final String blockName, final List characteristics, final List parameters) {
        super(blockName, characteristics, parameters);

        prependReferenceToSelf();
    }

    public Generator createGenerator(final DefsFile data) {
        return new MethodGenerator(data, blockName, returnType, cName, parameters);
    }

    /**
     * Load the reference-to-self that all "method" functions start with onto
     * the beginning of the parameters List.
     */
    private void prependReferenceToSelf() {
        String[][] target;

        target = new String[parameters.length + 1][2];
        System.arraycopy(parameters, 0, target, 1, parameters.length);

        target[0][0] = addPointerSymbol(ofObject);
        target[0][1] = "self";

        parameters = target;
    }

}
