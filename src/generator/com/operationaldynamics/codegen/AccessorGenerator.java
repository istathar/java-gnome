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
