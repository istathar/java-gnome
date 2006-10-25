/*
 * Glib.java
 * 
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2006 Operational Dynamics
 */
package org.gnome.glib;

public final class Glib {
    /**
     * A guard against someone calling init() twice
     */
    private static boolean initialized = false;

    public static void init(String[] args) {
        if (initialized) {
            return;
        }
        initialized = true;
    }
}
