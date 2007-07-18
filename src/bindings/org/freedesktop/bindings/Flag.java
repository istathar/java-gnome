/*
 * Constant.java
 *
 * Copyright (c) 2007 Operational Dynamics Consulting Pty Ltd
 * Copyright (c) 2007 Vreixo Formoso
 * 
 * The code in this file, and the library it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" plus the "Classpath Exception" (you may link to this code as a
 * library into other programs provided you don't make a derivation of it).
 * See the LICENCE file for the terms governing usage and redistribution.
 */
package org.freedesktop.bindings;

/**
 * Representation of flags. A flag is a Constant that can be OR'ed with
 * another flag of the same type
 * 
 * @author Vreixo Formoso
 * @since 4.0.3
 */
public abstract class Flag extends Constant
{

    protected Flag(int ordinal, String nickname) {
        super(ordinal, nickname);
    }

    /**
     * Helper method to simplify the implementation of or() in subclasses.
     */
    protected final static Flag orTwoFlags(Flag a, Flag b) {

        assert (a.getClass().equals(b.getClass()));

        return Plumbing.flagFor(a.getClass(), a.ordinal | b.ordinal);
    }

    /**
     * Helper method to simplify the implementation of contains() in
     * subclasses.
     * 
     * @return <code>true</code> if the bit-wise AND between two flags is
     *         not 0.
     */
    protected final static boolean andTwoFlags(Flag a, Flag b) {

        assert (a.getClass().equals(b.getClass()));

        return (a.ordinal & b.ordinal) != 0;
    }

}
