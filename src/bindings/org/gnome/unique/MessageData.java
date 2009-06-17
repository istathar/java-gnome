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
 * Wrapper around a payload you can send from one Application to another.
 * 
 * @author Andrew Cowie
 * @since 4.0.12
 */
public final class MessageData extends Boxed
{
    protected MessageData(long pointer) {
        super(pointer);
    }

    /**
     * Construct a carrier object.
     * 
     * @since 4.0.12
     */
    public MessageData() {
        super(UniqueMessageData.createMessageData());
    }

    protected final void release() {
        UniqueMessageData.free(this);
    }

    /**
     * Specify a String to be this MessageData's payload.
     * 
     * @since 4.0.12
     */
    public void setText(String text) {
        UniqueMessageData.setText(this, text, -1);
    }

    /**
     * If an application receiving a message with text as its payload, extract
     * that String.
     * 
     * @since 4.0.12
     */
    public String getText() {
        return UniqueMessageData.getText(this);
    }

}
