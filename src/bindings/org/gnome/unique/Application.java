/*
 * java-gnome, a UI library for writing GTK and GNOME programs from Java!
 *
 * Copyright © 2009-2010 Operational Dynamics Consulting, Pty Ltd
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

import org.gnome.glib.Object;

/**
 * Facilities for ensuring that only one unique instance of an application is
 * running.
 * 
 * <h2>If you're not the first, go away</h2>
 * 
 * <p>
 * The basic algorithm is to create an Application instance with the unique
 * name corresponding to your prgram, and then check to see if there is
 * another already running. If so, then there is no need for this instance and
 * it should terminate.
 * 
 * <pre>
 * app = new Application(&quot;com.example.Program&quot;, null);
 * 
 * if (app.isRunning()) {
 *     System.exit(1);
 * }
 * </pre>
 * 
 * <h2>Sending commands</h2>
 * 
 * <p>
 * There is a message-passing facility built into LibUnique which allows you
 * to send basic commands to the other running instance before you terminate
 * this one.
 * 
 * <pre>
 * app.sendMessage(Command.ACTIVATE, null);
 * </pre>
 * 
 * <p>
 * Keep in mind that this is not really meant as a generic all-powerful
 * <i>n</i> to <i>m</i> interprocess communication mechanism. In fact, the
 * entire LibUnique library is mostly about ensuring you only have one master
 * copy of an application running. But once you've established that, the
 * messaging subsystem can be used to convey simple requests to of that unique
 * instance.
 * 
 * @author Andrew Cowie
 * @since 4.0.12
 * @deprecated
 */
public class Application extends Object
{
    protected Application(long pointer) {
        super(pointer);
    }

    /**
     * Construct an Application object for the specified unique service.
     * 
     * <p>
     * By convention, the name chosen to identify a unique application should
     * follow the application naming conventions used by DBus (these are
     * similar to the domain name -esque conventions used in Java package
     * space). Some examples are:
     * 
     * <ul>
     * <li><code>"org.gnome.Nautilus"</code><br>
     * (used by the Nautilus file manager)
     * <li><code>"org.gnome.Vino.Preferences"</code><br>
     * (used by the Vino VNC server's preferences &amp; configuration utility)
     * <li><code>"com.operationaldynamics.Slashtime"</code><br>
     * (used by the Slashtime timezone viewer program).
     * </ul>
     * 
     * @since 4.0.12
     */
    /*
     * TODO describe the use of the startup notification id string
     */
    public Application(String name, String id) {
        super(UniqueApp.createApplication(basicValidation(name), id));
    }

    private static String basicValidation(final String name) {
        if (name.equals("")) {
            throw new IllegalArgumentException("name cannot be emtpy.");
        }
        if (name.indexOf('.') == -1) {
            throw new IllegalArgumentException("name needs follow DBus application naming conventions.");
        }
        if (name.indexOf('$') != -1) {
            throw new IllegalArgumentException("name cannot contain '$'.");
        }
        return name;
    }

    /**
     * Is some <i>other</i> instance of this program (ie the application with
     * this name) already running?
     * 
     * <p>
     * If so, then really you probably want to be terminating this program. If
     * not, then carry on with your normal application initialization and
     * start-up.
     * 
     * @since 4.0.12
     */
    public boolean isRunning() {
        return UniqueApp.isRunning(this);
    }

    /**
     * Get the application name that was used when this Application was
     * constructed.
     * 
     * @since 4.0.12
     */
    public String getName() {
        return getPropertyString("name");
    }

    /**
     * Send a message to the other instance (assuming there is one).
     * 
     * <p>
     * <b>This method blocks</b>.
     * 
     * <p>
     * If you have specific information to convey to the other instance, you
     * can create a MessageData object and set its payload accordingly. You
     * can also create custom Commands through subclassing, although that's
     * rarely necessary.
     * 
     * <p>
     * You can get a number of reponses back from the other instance. You
     * should reasonably expect {@link Response#OK OK}. Indeed, if it's not ok
     * there isn't much you can do about it.
     * 
     * @since 4.0.12
     */
    public Response sendMessage(Command cmd, MessageData data) {
        return UniqueApp.sendMessage(this, cmd.getCommandId(), data);
    }

    /**
     * The signal emitted when another instance sends a message to the unique
     * instance.
     * 
     * @author Andrew Cowie
     * @since 4.0.12
     */
    public interface MessageReceived
    {
        /**
         * You can compare the Command by reference with the constants in that
         * class.
         * 
         * <p>
         * <code>data</code> will be <code>null</code> if that was what was
         * passed by the calling instance. The <code>time</code> paramter is a
         * timestamp.
         * 
         * <p>
         * In ordinary circumstances you should return {@link Response#OK OK}.
         * 
         * <p>
         * If (for whatever reason) you chose not to handle the message, you
         * can return {@link Response#PASSTHROUGH PASSTHROUGH} which will
         * cause emission of this signal to continue, letting another handler
         * (in this application instance) deal with the event.
         */
        public Response onMessageReceived(Application source, Command cmd, MessageData data, int time);
    }

    /*
     * Wrap the command id into a strong Command constant.
     */
    private static class MessageReceivedHandler implements UniqueApp.MessageReceivedSignal
    {
        private MessageReceived handler;

        private MessageReceivedHandler(MessageReceived handler) {
            this.handler = handler;
        }

        public Response onMessageReceived(Application source, int commandId, MessageData messageData,
                int time) {
            return handler.onMessageReceived(source, Command.constantFor(commandId), messageData, time);
        }
    }

    /**
     * Hookup a <code>Application.MessageReceived</code> handler.
     * 
     * @since 4.0.12
     */
    public void connect(Application.MessageReceived handler) {
        UniqueApp.connect(this, new MessageReceivedHandler(handler), false);
    }
}
