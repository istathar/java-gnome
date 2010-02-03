/*
 * java-gnome, a UI library for writing GTK and GNOME programs from Java!
 *
 * Copyright © 2005-2010 Operational Dynamics Consulting, Pty Ltd
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
package com.operationaldynamics.ui;

/*
 * This class imported from ObjectiveAccounts accounting package where it was
 * originally deployed as GPL code in generic.ui.TextOutput
 */

import java.io.PrintStream;
import java.io.PrintWriter;

import org.freedesktop.bindings.Environment;

/**
 * Base class for routines that output text to terminal. It takes care of
 * working out and making available to subclasses an appropriate terminal
 * width. Note that this can be set or overridden on the VM command line by
 * setting the COLUMNS property:
 * 
 * <pre>
 *  java -DCOLUMNS=70 ...
 * </pre>
 * 
 * @author Andrew Cowie
 */
public abstract class TextOutput extends Text
{
    /**
     * Terminal width, in character cells. Pulled from environment variable
     * "COLUMNS" if it set, otherwise a default value is used.
     */
    public static final int COLUMNS;

    private static final int COLUMNS_DEFAULT = 80;

    protected static final int COLUMNS_MIN = 50;

    /**
     * Specify left alignment for whatever you are outputting or padding.
     * Points to Align.LEFT; is here for convenience and brevity in
     * subclasses.
     */
    protected static final Align LEFT = Align.LEFT;

    /**
     * Specify right alignment for whatever you are outputting or padding
     * Actually just points at Align.RIGHT; is here for brevity in subclasses.
     */
    protected static final Align RIGHT = Align.RIGHT;

    static {
        String env = Environment.getEnv("COLUMNS");
        if (env == null) {
            COLUMNS = COLUMNS_DEFAULT;
        } else {
            int val = Integer.valueOf(env).intValue();
            if (val < COLUMNS_MIN) {
                throw new IllegalStateException("Terminal too narrow for TextOutput. Min width "
                        + COLUMNS_MIN + " characters.");
            }
            COLUMNS = val;
        }
    }

    /**
     * Wrapper around toOutput(PrintWriter) to which you can easily pass an
     * old school PrintStream
     * 
     * @param out
     *            a PrintStream like System.out or System.err
     */
    public final void toOutput(PrintStream out) {
        toOutput(new PrintWriter(out, true));

    }

    public abstract void toOutput(PrintWriter out);
}
