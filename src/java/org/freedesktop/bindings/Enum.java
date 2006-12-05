/*
 * Enum.java
 *
 * Copyright (c) 2006 Operational Dynamics Consulting Pty Ltd and Others
 * 
 * The code in this file, and the library it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" plus the "Classpath Exception" (you may link to this code as a
 * library into other programs provided you don't make a derivation of it).
 * See the LICENCE file for the terms governing usage and redistribution.
 */
package org.freedesktop.bindings;

/*
 * Can these be passed as Values? No; they'll need a wrapper for that.
 */
public class Enum {

    /*
     * This is package visible so that Plumbing can see it, and final so that
     * only the constructor here can set it.
     */
    final int num;

    protected Enum(int sequence) {
        num = sequence;
    }
}
