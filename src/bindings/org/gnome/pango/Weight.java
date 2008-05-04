/*
 * Weight.java
 *
 * Copyright (c) 2007-2008 Operational Dynamics Consulting Pty Ltd
 *
 * The code in this file, and the library it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" plus the "Classpath Exception" (you may link to this code as a
 * library into other programs provided you don't make a derivation of it).
 * See the LICENCE file for the terms governing usage and redistribution.
 */
package org.gnome.pango;

import org.freedesktop.bindings.Constant;

/*
 * FIXME this is a placeholder stub for what will become the public API for
 * this type. Replace this comment with appropriate javadoc including author
 * and since tags. Note that the class may need to be made abstract, implement
 * interfaces, or even have its parent changed. No API stability guarantees
 * are made about this class until it has been reviewed by a hacker and this
 * comment has been replaced.
 */
/**
 * ... font weight constants ...
 * 
 * @author Andrew Cowie
 * @since 4.0.8
 */
/*
 * Yes, you can subclass this if you are desperate for font weights other than
 * the established constant values supplied here.
 */
public class Weight extends Constant
{
    private Weight(int ordinal, String nickname) {
        super(ordinal, nickname);
    }

    protected Weight(int value) {
        super(validate(value), Integer.toString(value));
    }

    static final int validate(int value) {
        if ((value < 100) || (value > 900)) {
            throw new IllegalArgumentException("Valid font weights are 100 through 900");
        }
        return value;
    }

    /**
     * The default font weight.
     * 
     * <p>
     * <i>This has a value of <code>400</code>, apparently.</i>
     */
    public static final Weight NORMAL = new Weight(PangoWeight.NORMAL, "NORMAL");

    /**
     * Bold text.
     * 
     * <p>
     * <i>This represents a font weight value of <code>700</code>,
     * apparently.</i>
     */
    public static final Weight BOLD = new Weight(PangoWeight.BOLD, "BOLD");
}
