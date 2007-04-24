/*
 * MethodGenerator.java
 *
 * Copyright (c) 2007 Operational Dynamics Consulting Pty Ltd
 * 
 * The code in this file, and the library it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" See the LICENCE file for the terms governing usage and
 * redistribution.
 */
package com.operationaldynamics.codegen;

/**
 * Output the code corresponding to a constructor function on a GObject.
 * 
 * <p>
 * Source .defs data for a constructor is of the following form:
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
 * In calling FunctionGenerator.<init>(), we igore the blockName and
 * returnType; the gFuncitonName is munged per our naming convetion into the
 * method name, and the return type is long, as it will be used as:
 * 
 * <pre>
 * public Button(String label) {
 *     super(GtkButton.createButtonWithLabel(label);
 * }
 * </pre>
 * 
 * @author Andrew Cowie
 */
class ConstructorGenerator extends FunctionGenerator
{

    /**
     * 
     * @param gObjectType
     * @param gFunctionName
     * @param gParameters
     */
    ConstructorGenerator(final String gObjectType, final String gFunctionName,
            final String[][] gParameters) {
        super(gObjectType, "", "glong", gFunctionName, gParameters);

        this.translationMethodName = mungeConstructorName(gObjectType, gFunctionName);
    }

    /**
     * Take a string of the form "gtk_button_new_with_label" and transform it
     * into "create_Button_with_label" [so that a subsequent toCamel() call
     * will transform it to "createButtonWithLabel"]
     */
    static String mungeConstructorName(String gType, String blockName) {
        StringBuffer buf;
        int cut;

        buf = new StringBuffer(blockName);

        cut = buf.indexOf("_new");
        buf.delete(0, cut + 4);
        buf.insert(0, Thing.lookup(gType).javaType);
        buf.insert(0, "create_");

        return toCamel(buf.toString());
    }
}
