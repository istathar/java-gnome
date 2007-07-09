/*
 * PolicyType.java
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

/** 
 * Constants to determines when a scroll bar will be visible.
 *
 * @author Sebastian Mancke
 * @since 4.0.3
 */
public final class PolicyType extends Constant
{

    /**
     * The scrollbar is always visible.
     */
    public static final PolicyType ALWAYS = new PolicyType(GtkPolicyType.ALWAYS, "ALWAYS");

    /**
     * The scrollbar will appear and disappear as necessary.
     */
    public static final PolicyType AUTOMATIC = new PolicyType(GtkPolicyType.AUTOMATIC, "ALWAYS");

    /**
     * The scrollbar will never appear.
     */
    public static final PolicyType NEVER = new PolicyType(GtkPolicyType.NEVER, "ALWAYS");


    private PolicyType(int ordinal, String nickname) {
        super(ordinal, nickname);
    }
}
