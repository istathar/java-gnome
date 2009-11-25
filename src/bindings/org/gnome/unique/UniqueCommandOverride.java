/*
 * UniqueCommandOverride.java
 *
 * Copyright (c) 2007-2009 Operational Dynamics Consulting Pty Ltd
 *
 * The code in this file, and the library it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" plus the "Classpath Exception" (you may link to this code as a
 * library into other programs provided you don't make a derivation of it).
 * See the LICENCE file for the terms governing usage and redistribution.
 * 
 * Copied from the implementation in GtkResponseTypeOverride.java
 */
package org.gnome.unique;

/**
 * This gives us package visible access to the utility methods which are of of
 * course visible to the translation layer hierarchy but needed to permit
 * subclassing of Command and its use by Application and MessageData.
 * 
 * @author Andrew Cowie
 */
final class UniqueCommandOverride extends Plumbing
{
    private UniqueCommandOverride() {}

    static Command enumFor(int ordinal) {
        return (Command) Plumbing.enumFor(Command.class, ordinal);
    }

    static int numOf(Command reference) {
        return Plumbing.numOf(reference);
    }
}
