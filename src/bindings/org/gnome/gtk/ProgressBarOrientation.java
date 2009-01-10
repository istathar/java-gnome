/*
 * ProgressBarOrientation.java
 *
 * Copyright (c) 2007 Operational Dynamics Consulting Pty Ltd
 *
 * The code in this file, and the library it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" plus the "Classpath Exception" (you may link to this code as a
 * library into other programs provided you don't make a derivation of it).
 * See the LICENCE file for the terms governing usage and redistribution.
 */
package org.gnome.gtk;

import org.freedesktop.bindings.Constant;

/*
 * FIXME this is a placeholder stub for what will become the public API for
 * this type. Replace this comment with appropriate javadoc including author
 * and since tags. Note that the class may need to be made abstract, implement
 * interfaces, or even have its parent changed. No API stability guarantees
 * are made about this class until it has been reviewed by a hacker and this
 * comment has been replaced.
 */
public final class ProgressBarOrientation extends Constant
{
    public static final ProgressBarOrientation LEFT_TO_RIGHT = new ProgressBarOrientation(GtkProgressBarOrientation.LEFT_TO_RIGHT, "LEFT_TO_RIGHT");
    public static final ProgressBarOrientation RIGHT_TO_LEFT = new ProgressBarOrientation(GtkProgressBarOrientation.RIGHT_TO_LEFT, "RIGHT_TO_LEFT");
    public static final ProgressBarOrientation TOP_TO_BOTTOM = new ProgressBarOrientation(GtkProgressBarOrientation.TOP_TO_BOTTOM, "TOP_TO_BOTTOM");
    public static final ProgressBarOrientation BOTTOM_TO_TOP = new ProgressBarOrientation(GtkProgressBarOrientation.BOTTOM_TO_TOP, "BOTTOM_TO_TOP");
    
    private ProgressBarOrientation(int ordinal, String nickname) {
        super(ordinal, nickname);
    }
}
