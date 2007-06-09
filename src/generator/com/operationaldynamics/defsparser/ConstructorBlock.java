/*
 * ConstructorBlock.java
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

import com.operationaldynamics.codegen.ConstructorGenerator;
import com.operationaldynamics.codegen.Generator;
import com.operationaldynamics.driver.DefsFile;

/**
 * A (define-function ...) block containing the description of a GObject's
 * constructor function. Source .defs data for a constructor is of the
 * following form:
 * 
 * <pre>
 * (define-function gtk_button_new_with_label
 *   (is-constructor-of &quot;GtkButton&quot;)
 *   (c-name &quot;gtk_button_new_with_label&quot;)
 *   (caller-owns-return #t)
 *   (return-type &quot;GtkWidget*&quot;)
 *   (parameters
 *     '(&quot;const-gchar*&quot; &quot;label&quot;)
 *   )
 * )
 * </pre>
 * 
 * @author Andrew Cowie
 */
class ConstructorBlock extends FunctionBlock
{
    protected String isConstructorOf;

    ConstructorBlock(String blockName, final List characteristics, final List parameters) {
        super(blockName, characteristics, parameters);
    }

    protected final void setIsConstructorOf(final String isConstructorOf) {
        this.isConstructorOf = isConstructorOf;
    }

    public Generator createGenerator(final DefsFile data) {
        return new ConstructorGenerator(data, returnType, cName, parameters);
    }

    /**
     * Special case: constructs return long, not the actual type. So we remove
     * the first Thing encountered (which was set in FunctionBlock's
     * useTypes() with the return Thing).
     */
    public List usesTypes() {
        List types;

        types = super.usesTypes();
        types.remove(0);

        return types;
    }
}
