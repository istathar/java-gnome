/*
 * ExampleThereCanBeOnlyOne.java
 *
 * Copyright (c) 2009 Operational Dynamics Consulting Pty Ltd
 *
 * The code in this file, and the program it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" See the LICENCE file for the terms governing usage and
 * redistribution.
 */
package unique;

import org.gnome.gtk.Gtk;
import org.gnome.unique.Application;
import org.gnome.unique.Command;
import org.gnome.unique.MessageData;
import org.gnome.unique.Response;

import static java.lang.System.currentTimeMillis;
import static org.freedesktop.bindings.Time.formatTime;

/**
 * Attempt to demonstrate LibUnique in action.
 * 
 * @author Andrew Cowie
 * @since 4.0.12
 */
public class ExampleThereCanBeOnlyOne
{
    public static void main(String[] args) {
        final Application app;
        final MessageData data;
        final long when;
        final String str;

        Gtk.init(args);

        /*
         * Create an instance of probing for our unique application name.
         */

        app = new Application("com.example.ThereCanBeOnlyOne", null);

        /*
         * Find out if there is already one running. If there is, send it a
         * message, then terminate. Sending a payload with the message is
         * optional; we'll send along the time.
         */

        if (app.isRunning()) {
            when = currentTimeMillis() / 1000;
            str = formatTime("%H:%M:%S", when);

            data = new MessageData();
            data.setText("At " + str + ", all is well");

            app.sendMessage(Command.ACTIVATE, data);
            return;
        }

        /*
         * If there isn't one running, then we are the first instance, and
         * need to become the unique application. We can register a signal
         * handler to listen for commands coming from other instances
         * attempting to start.
         */

        app.connect(new Application.MessageReceived() {
            public Response onMessageReceived(Application source, Command cmd, MessageData data, int time) {

                if (cmd == Command.ACTIVATE) {
                    /*
                     * Do something trivial in reaction to the activation
                     * request.
                     */
                    System.out.println(data.getText());

                    return Response.OK;
                } else if (cmd == Command.CLOSE) {
                    /*
                     * If another instance else wants to tell us to close
                     * that's their business. Up to us whether we decide to do
                     * so, but if we did, then this is what we might do:
                     */
                    Gtk.mainQuit();
                    return Response.OK;
                } else {
                    /*
                     * Well, we didn't do anything, but that's ok too
                     */
                    return Response.OK;
                }
            }
        });

        /*
         * Now carry on with normal application setup, finally running the
         * main loop, which blocks.
         */

        Gtk.main();
    }
}
