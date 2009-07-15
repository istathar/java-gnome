/*
 * Response.java
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

import org.freedesktop.bindings.Constant;

/**
 * Constants enumerating the reponse that a Application.MessageReceived
 * handler can make.
 * 
 * <p>
 * The descriptions below correspond to the interpretation that the program
 * calling {@link Application#sendMessage(Command, MessageData) sendMessage()}
 * should apply to the returned Response.
 * 
 * @author Andrew Cowie
 * @since 4.0.12
 */
public class Response extends Constant
{
    private Response(int ordinal, String nickname) {
        super(ordinal, nickname);
    }

    /**
     * The command was completed successfully.
     */
    public static final Response OK = new Response(UniqueResponse.OK, "OK");

    /**
     * For whatever reason, the command was not handled by the instance.
     */
    public static final Response PASSTHROUGH = new Response(UniqueResponse.PASSTHROUGH, "PASSTHROUGH");

    /**
     * There was some kind of inter-process commuincation failure. The command
     * was not handled.
     */
    public static final Response FAIL = new Response(UniqueResponse.FAIL, "FAIL");

    /**
     * The command was cancelled by the user at the other side.
     */
    public static final Response CANCEL = new Response(UniqueResponse.CANCEL, "CANCEL");
}
