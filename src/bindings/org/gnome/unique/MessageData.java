/*
 * MessageData.java
 *
 * Copyright (c) 2009 Operational Dynamics Consulting Pty Ltd
 *
 * The code in this file, and the library it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" plus the "Classpath Exception" (you may link to this code as a
 * library into other programs provided you don't make a derivation of it).
 * See the LICENCE file for the terms governing usage and redistribution.
 */
package org.gnome.unique;

import org.gnome.glib.Boxed;

/**
 * 
 * @author Andrew Cowie
 * @since 4.0.12
 */
public final class MessageData extends Boxed
{
    protected MessageData(long pointer) {
        super(pointer);
    }

    protected void release() {
        UniqueMessageData.free(this);
    }
}
