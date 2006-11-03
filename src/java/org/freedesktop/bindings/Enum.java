/*
 * Enum.java
 * 
 * Copyright (c) 2006 Operational Dynamics
 * See LICENCE file for usage and redistribution terms
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
