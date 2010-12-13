/*
 * java-gnome, a UI library for writing GTK and GNOME programs from Java!
 *
 * Copyright © 2006-2010 Operational Dynamics Consulting, Pty Ltd
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
 * originally deployed as GPL code in generic.ui.Text with the following
 * headers:
 * 
 * Word wrap algorithm GPL code imported from xseq.ui.OverviewWindow
 * Copyright (c) 2004-2005 Operational Dynamics Consulting Pty Ltd
 * 
 * Comma padding algorithm imported from pulseSession.session.cmdHandler of 
 * SINS (SINS Is Not ShadNet) codebase, redistributable under the GNU GPL v2.
 * Copyright (c) 1997-1998 Andrew Cowie
 */

import java.text.DecimalFormat;

/**
 * This class also has numerous static methods with useful routines for doing
 * basic formatting on Strings.
 * 
 * @author Andrew Cowie
 */
public abstract class Text
{
    /**
     * @param str
     *            the String to pad.
     * @param width
     *            maximum length of the padded result.
     * @param justify
     *            if RIGHT, right justify. If LEFT, normal left justification.
     * @return the padded String.
     */
    public static String pad(String str, int width, Align justify) {
        String trimmed = null;
        /*
         * crop
         */
        int len;
        if (str == null) {
            len = 0;
            trimmed = "";
        } else {
            len = str.length();

            if (len > width) {
                trimmed = str.substring(0, width);
                len = width;
            } else {
                trimmed = str;
            }
        }
        int spaces = width - len;

        /*
         * pad
         */
        StringBuffer buf = new StringBuffer("");
        if (justify == Align.LEFT) {
            buf.append(trimmed);
        }
        for (int i = 0; i < spaces; i++) {
            buf.append(" ");
        }
        if (justify == Align.RIGHT) {
            buf.append(trimmed);
        }
        return buf.toString();
    }

    /**
     * If argument is longer than width, then trim it back and add three dots.
     */
    public static String chomp(String str, int width) {
        if (width < 2) { // 4
            throw new IllegalArgumentException(
                    "Can't chomp to less than a width of 2 because of adding elipses");
        }
        if (str == null) {
            return "";
        }
        /*
         * crop
         */
        int len = str.length();
        if (len > width) {
            StringBuffer buf = new StringBuffer(str.substring(0, width - 1)); // 3
            final int end = buf.length() - 1;
            if (buf.charAt(end) == ' ') {
                buf.deleteCharAt(end);
            }
            // We now use an ellipsis, … instead of "..."
            buf.append("\u2026");
            return buf.toString();
        } else {
            return str;
        }
    }

    /**
     * Carry out manual word wrap on a String. Normalizes all \n and \t
     * characters to spaces, trims leading and training whitespace, and then
     * inserts \n characters to "wrap" at the specified width boundary.
     * <p>
     * This code came about because unfortunately, Pango markup has no syntax
     * for expressing auto word wrap, and worse, GtkCellRendererText has no
     * ability to wrap text. So we (ick) do it by hand.
     * 
     * @return a single String with newline characters inserted at appropriate
     *         points to cause the effect of wrapping at the specified width.
     */
    public static String wrap(String str, int width) {
        StringBuffer buf = new StringBuffer(str);
        int index;
        /*
         * normalize any existing IFS characters to spaces
         */
        while ((index = buf.indexOf("\n")) != -1) {
            buf.setCharAt(index, ' ');
        }
        while ((index = buf.indexOf("\t")) != -1) {
            buf.setCharAt(index, ' ');
        }
        /*
         * trim. Yes, I know about String.trim(), but we've already done half
         * the work it does; no need to be inefficient.
         */
        while (buf.charAt(0) == ' ') {
            buf.deleteCharAt(0);
        }
        while (buf.charAt(buf.length() - 1) == ' ') {
            buf.deleteCharAt(buf.length() - 1);
        }

        while ((index = buf.indexOf("  ")) != -1) {
            buf.deleteCharAt(index);
        }

        /*
         * word wrap.
         */
        int next_space = 0;
        int line_start = 0;

        while (next_space != -1) {
            if ((next_space - line_start) > width) {
                buf.setCharAt(next_space, '\n');
                line_start = next_space;
            }
            next_space = buf.indexOf(" ", next_space + 1); // bounds?
        }

        return buf.toString();
    }

    /**
     * Pad and justify a long. This is useful for displaying the memory usage
     * numbers from Runtime.freeMemory() and Runtime.totalMemory()
     * 
     * @param num
     *            a number to render as a comma padded string.
     * @return a 25 character wide string with the number right justified wnd
     *         with comma characters at thousand marks.
     */
    /*
     * from pulseSession.session.cmdHandler
     */
    public static String padComma(long num) {
        DecimalFormat df = new DecimalFormat("#,###,###,###,###,###,###");

        StringBuffer comma = new StringBuffer(df.format(num));

        int pad = 25 - comma.length();
        for (int i = 0; i < pad; i++) {
            comma.insert(0, ' ');
        }

        return comma.toString();
    }
}
