/*
 * MethodGenerator.java
 *
 * Copyright (c) 2007 Operational Dynamics Consulting Pty Ltd
 * 
 * The code in this file, and the program it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" See the LICENCE file for the terms governing usage and
 * redistribution.
 */
package com.operationaldynamics.codegen;

import java.io.PrintWriter;

import com.operationaldynamics.driver.DefsFile;

/**
 * Output the code corresponding to a constructor function on a GObject. See
 * {@link ConstructorBlock} for an example of the source .defs data describing
 * constructors.
 * 
 * <p>
 * In calling FunctionGenerator.<init>(), we igore the blockName and
 * returnType; the gFuncitonName is munged per our naming convetion into the
 * method name, and the return type is long, as it will be used as:
 * 
 * <pre>
 *  public Button(String label) {
 *      super(GtkButton.createButtonWithLabel(label);
 *  }
 * </pre>
 * 
 * @author Andrew Cowie
 */
public class ConstructorGenerator extends FunctionGenerator
{
    private final Thing actualReturnType;

    /**
     * We send glong as a hard coded retrun type to FunctionGenerator because
     * from an outside perspective, our translations of constructor functions
     * return naked pointers (long), not Widgets, because the widget hasn't
     * been instantiated Java side yet!
     * 
     * @param data
     * @param gReturnType
     *            the actual return type of the constructor function; will be
     *            used internally within the JNI code; external return type is
     *            hard wired to long.
     * @param gFunctionName
     * @param gParameters
     */
    public ConstructorGenerator(final DefsFile data, final String blockName, final String gReturnType,
            final String gFunctionName, final String[][] gParameters, char callerOwnsReturn) {
        super(data, "", "glong", gFunctionName, gParameters, callerOwnsReturn);

        this.translationMethodName = mungeConstructorName(data.getType().gType, blockName);
        this.actualReturnType = Thing.lookup(gReturnType);
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

    /**
     * Unlike normal method calls, the return type from G side constructor
     * functions is long - the pointer address - and we pass it back up
     * through the translation layer to the <code>super(long pointer)</code>
     * constructor in the public API subclass of Proxy.
     * 
     * <p>
     * Twiddling with the returnType field used in FunctionGenerator is a
     * somewhat kludgy way of making sure that the code <i>inside</i> the JNI
     * function is correct, but allows us to easily alter the super code's
     * behaviour and gets the job done.
     */
    protected void jniFunctionConversionCode(PrintWriter out) {
        Thing originalReturnType;

        originalReturnType = returnType;
        returnType = actualReturnType;

        super.jniFunctionConversionCode(out);

        returnType = originalReturnType;
    }

    protected void jniFunctionReturnCleanUp(PrintWriter out) {
        Thing originalReturnType;

        originalReturnType = returnType;
        returnType = actualReturnType;
        super.jniFunctionReturnCleanUp(out);

        returnType = originalReturnType;
    }
}
