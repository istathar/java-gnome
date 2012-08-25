/*
 * java-gnome, a UI library for writing GTK and GNOME programs from Java!
 *
 * Copyright © 2007-2012 Operational Dynamics Consulting, Pty Ltd and Others
 *
 * The code in this file, and the program it is a part of, is made available
 * to you by its authors as open source software: you can redistribute it
 * and/or modify it under the terms of the GNU General Public License version
 * 2 ("GPL") as published by the Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GPL for more details.
 *
 * You should have received a copy of the GPL along with this program. If not,
 * see http://www.gnu.org/licenses/. The authors of this program may be
 * contacted through http://java-gnome.sourceforge.net/.
 *
 * Linking this library statically or dynamically with other modules is making
 * a combined work based on this library. Thus, the terms and conditions of
 * the GPL cover the whole combination. As a special exception (the
 * "Classpath Exception"), the copyright holders of this library give you
 * permission to link this library with independent modules to produce an
 * executable, regardless of the license terms of these independent modules,
 * and to copy and distribute the resulting executable under terms of your
 * choice, provided that you also meet, for each linked independent module,
 * the terms and conditions of the license of that module. An independent
 * module is a module which is not derived from or based on this library. If
 * you modify this library, you may extend the Classpath Exception to your
 * version of the library, but you are not obligated to do so. If you do not
 * wish to do so, delete this exception statement from your version.
 */
package org.gnome.unique;

/*
 * Copied from the implementation in ResponseType.java
 */

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
 * @deprecated
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
