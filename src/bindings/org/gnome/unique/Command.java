/*
 * Command.java
 *
 * Copyright (c) 2007-2009 Operational Dynamics Consulting Pty Ltd, and Others
 *
 * The code in this file, and the library it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" plus the "Classpath Exception" (you may link to this code as a
 * library into other programs provided you don't make a derivation of it).
 * See the LICENCE file for the terms governing usage and redistribution.
 * 
 * Copied from the implementation in ResponseType.java
 */
package org.gnome.unique;

import org.freedesktop.bindings.Constant;

/**
 * Predefined commands that can be sent from one Application to another.
 * 
 * <p>
 * If one of these responses constants fits your needs, it is recommended that
 * you make use of it.
 * 
 * <p>
 * If your needs require it, however, you can define your own responses codes
 * by extending this class. For example:
 * 
 * <pre>
 * public class NinjaCommand extends Command
 * {
 *     protected NinjaCommand(String nickname) {
 *         super(nickname);
 *     }
 * 
 *     public static final NinjaCommand HACK = new NinjaCommand(&quot;HACK&quot;);
 * 
 *     public static final NinjaCommand SLASH = new NinjaCommand(&quot;SLASH&quot;);
 * 
 *     public static final NinjaCommand BURN = new NinjaCommand(&quot;BURN&quot;);
 * }
 * </pre>
 * 
 * @author Andrew Cowie
 * @since 4.0.12
 */
/*
 * This implementation is identical to that used in our coverage GTK Dialogs
 * and their ResponseTypes. See that class for lots of detail.
 */
public class Command extends Constant
{
    private static int response;

    static {
        response = 1;
    }

    private Command(int ordinal, String nickname) {
        super(ordinal, nickname);
    }

    /**
     * Constructor to let developer define their own Commands, if necessary.
     * 
     * @param nickname
     *            Used mainly for debugging purposes. The String supplied
     *            should match the name of the constant as declared in your
     *            Command subclass.
     */
    /*
     * An unique ordinal is assigned automatically
     */
    protected Command(String nickname) {
        super(response++, nickname);
    }

    /*
     * It's not entirely necessary to have these here; we could just have
     * Dialog call the static methods in UniqueCommandOverride directly, but
     * the code seems a bit to have this as instance method here...
     */
    int getCommandId() {
        return UniqueCommandOverride.numOf(this);
    }

    /*
     * ... on the other hand, this is just clumsy, but it is at least
     * consistent with getResponseId() and likewise a bit cleaner.
     */
    static Command constantFor(int ordinal) {
        return UniqueCommandOverride.enumFor(ordinal);
    }

    /**
     * Generally interpreted to mean that the receiving instance should
     * {@link org.gnome.gtk.Window#present() present()} itself.
     * 
     * @since 4.0.12
     */
    public static final Command ACTIVATE = new Command(UniqueCommand.ACTIVATE, "ACTIVATE");

    public static final Command NEW = new Command(UniqueCommand.NEW, "NEW");

    public static final Command OPEN = new Command(UniqueCommand.OPEN, "OPEN");

    public static final Command CLOSE = new Command(UniqueCommand.CLOSE, "CLOSE");
}
