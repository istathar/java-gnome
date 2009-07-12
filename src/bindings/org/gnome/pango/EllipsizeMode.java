/*
 * EllipsizeMode.java
 *
 * Copyright (c) 2007-2009 Operational Dynamics Consulting Pty Ltd
 *
 * The code in this file, and the library it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" plus the "Classpath Exception" (you may link to this code as a
 * library into other programs provided you don't make a derivation of it).
 * See the LICENCE file for the terms governing usage and redistribution.
 */
package org.gnome.pango;

import org.freedesktop.bindings.Constant;

/**
 * Those constants specify what sort of ellipsization should be applied to a
 * line of text. In the ellipsization process characters are removed from the
 * text in order to make it fit to a given width and replaced with an ellipsis
 * (...).
 * 
 * @author Guillaume Mazoyer
 * @since 4.0.13
 */
public final class EllipsizeMode extends Constant
{
    private EllipsizeMode(int ordinal, String nickname) {
        super(ordinal, nickname);
    }

    /**
     * No ellipsization.
     */
    public static final EllipsizeMode NONE = new EllipsizeMode(PangoEllipsizeMode.NONE, "NONE");

    /**
     * Omit characters at the start of the text.
     */
    public static final EllipsizeMode START = new EllipsizeMode(PangoEllipsizeMode.START, "START");

    /**
     * Omit characters in the middle of the text.
     */
    public static final EllipsizeMode MIDDLE = new EllipsizeMode(PangoEllipsizeMode.MIDDLE, "MIDDLE");

    /**
     * Omit characters at the end of the text.
     */
    public static final EllipsizeMode END = new EllipsizeMode(PangoEllipsizeMode.END, "END");
}
