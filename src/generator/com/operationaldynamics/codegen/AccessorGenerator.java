/*
 * AccessorGenerator.java
 *
 * Copyright (c) 2007 Operational Dynamics Consulting Pty Ltd
 * 
 * The code in this file, and the program it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" See the LICENCE file for the terms governing usage and
 * redistribution.
 */
package com.operationaldynamics.codegen;

import com.operationaldynamics.driver.DefsFile;

/**
 * Abstract characteristics common to getter and setter functions. This is
 * mostly here to finish the translation between the simplistic two-String
 * data that drives the two subclasses and the parent
 * {@link FunctionGenerator} which does all the work.
 * 
 * @author Andrew Cowie
 */
abstract class AccessorGenerator extends FunctionGenerator
{
    AccessorGenerator(DefsFile data, String blockName, String gReturnType, String[][] gParameters) {
        super(data, blockName, gReturnType, toUnderscores(data.getType().bindingsClass) + "_"
                + blockName, gParameters, 'f');
    }

    /**
     * Utility method to allow us to create a suitable pseudo cFunctionName
     * matching the rest of the bindings naming conventions in cases where we
     * have to improvise our own names (like the Boxed getters and setters).
     * 
     * @param pascalCaseName
     *            a string like "GtkTreeIter"
     * @return "gtk_tree_iter"
     */
    /*
     * It would be ok to move this up to Generator should it come to pass that
     * anything else needs this.
     */
    protected static String toUnderscores(String pascalCaseName) {
        StringBuffer buf;
        int i;
        char ch;

        buf = new StringBuffer(pascalCaseName);
        i = 0;

        while (i < buf.length()) {
            ch = buf.charAt(i);

            if (Character.isUpperCase(ch)) {
                buf.setCharAt(i, Character.toLowerCase(ch));
                if (i > 0) {
                    buf.insert(i, '_');
                    i++;
                }
            }
            i++;
        }

        return buf.toString();
    }
}
