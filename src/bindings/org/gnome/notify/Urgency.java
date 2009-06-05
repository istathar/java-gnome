/*
 * Urgency.java
 *
 * Copyright (c) 2009 Operational Dynamics Consulting Pty Ltd, and Others
 * 
 * The code in this file, and the library it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" plus the "Classpath Exception" (you may link to this code as a
 * library into other programs provided you don't make a derivation of it).
 * See the LICENCE file for the terms governing usage and redistribution.
 */
package org.gnome.notify;

import org.freedesktop.bindings.Constant;

/**
 * Used to indicate the urgency level of {@link Notification} shown.
 * 
 * @author Serkan Kaba
 * @since 4.0.12
 */
public class Urgency extends Constant
{
    protected Urgency(int ordinal, String nickname) {
        super(ordinal, nickname);
    }

    /**
     * Represents low urgency used for unimportant notifications.
     */
    public static final Urgency LOW = new Urgency(NotifyUrgency.LOW, "LOW");

    /**
     * Represents normal urgency used for standard notifications.
     */
    public static final Urgency NORMAL = new Urgency(NotifyUrgency.NORMAL, "NORMAL");

    /**
     * Represents critical urgency used for very important notifications.
     */
    public static final Urgency CRITICAL = new Urgency(NotifyUrgency.CRITICAL, "CRITICAL");
}
