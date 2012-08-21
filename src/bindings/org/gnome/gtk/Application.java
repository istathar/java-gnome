/*
 * java-gnome, a UI library for writing GTK and GNOME programs from Java!
 *
 * Copyright © 2012 Operational Dynamics Consulting, Pty Ltd and Others
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
 * "Claspath Exception"), the copyright holders of this library give you
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
package org.gnome.gtk;

import org.gnome.glib.ApplicationCommandLine;
import org.gnome.glib.ApplicationFlags;

/**
 * This class handles the lifecycle of a GTK application. It currently ensures
 * that the application is unique and manages a list of top-level windows
 * whose life-cycle is automatically tied to the life-cycle of the
 * application.
 * 
 * @author Guillaume Mazoyer
 * @author Andrew Cowie
 * @since 4.1.2
 */
public class Application extends org.gnome.glib.Application
{
    public Application(long pointer) {
        super(pointer);
    }

    /**
     * Creates a new Application instance. The <code>id</code> needs to be a
     * valid identifier, see
     * {@link org.gnome.glib.Application#isValidID(String) isValidID()} for
     * details. The <code>flags</code> argument allows you to specify
     * different behaviour, marking this program as a
     * {@link org.gnome.glib.ApplicationFlags#IS_LAUNCHER launcher} or
     * {@link org.gnome.glib.ApplicationFlags#IS_SERVICE service}, for
     * example.
     * 
     * @since 4.1.2
     */
    public Application(String id, ApplicationFlags flags) {
        super(GtkApplication.createApplication(isValidId(id), flags));
    }

    /**
     * Creates a new Application instance. The <code>id</code> must be a valid
     * identifier; see {@link org.gnome.glib.Application#isValidID(String)
     * isValidID()}. This id needs to be common between all instances of the
     * application; it is what enables the uniqueness mechanism.
     * 
     * <p>
     * This constructor implies a normal application with default uniqueness
     * behaviour: first instance will become primary, subsequent instances
     * will communicate activation to the primary then terminate.
     * 
     * @since 4.1.2
     */
    public Application(String id) {
        super(GtkApplication.createApplication(isValidId(id), ApplicationFlags.NONE));
    }

    /**
     * Adds a window from the Application. GTK+ will keep the Application
     * running as long as it has any windows. The connection between the
     * Application and the window will remain until the window is destroyed or
     * {@link #removeWindow(Window) removeWindow()} is called.
     * 
     * @since 4.1.2
     */
    public void addWindow(Window window) {
        GtkApplication.addWindow(this, window);
    }

    /**
     * Removes a window from the Application. The Application will stop
     * running if the last window is removed.
     * 
     * @since 4.1.2
     */
    public void removeWindow(Window window) {
        GtkApplication.removeWindow(this, window);
    }

    /**
     * Returns an array of the {@link Window windows} currently associated
     * with Application.
     * 
     * @since 4.1.2
     */
    public Window[] getWindows() {
        return GtkApplication.getWindows(this);
    }

    /**
     * This signal is emitted on the primary instance when an activation
     * occurs (after startup of any instance, or by calling the Application
     * {@link Application#activate() activate()} method.
     * 
     * @author Guillaume Mazoyer
     * @since 4.1.2
     */
    public interface Activate
    {
        public void onActivate(Application source);
    }

    private class ActivateHandler implements org.gnome.glib.Application.Activate
    {
        private final Activate handler;

        private ActivateHandler(Activate handler) {
            this.handler = handler;
        }

        public void onActivate(org.gnome.glib.Application source) {
            handler.onActivate((org.gnome.gtk.Application) source);
        }
    }

    /**
     * Hook up the <code>Application.Activate</code> handler.
     * 
     * @since 4.1.2
     */
    public void connect(Application.Activate handler) {
        super.connect(new ActivateHandler(handler));
    }

    /**
     * This signal is emitted on the primary instance when any of the
     * conditions starting the program are raised. This is a good place to put
     * your UI intitialization logic.
     * 
     * @author Andrew Cowie
     * @since 4.1.2
     */
    public interface Startup
    {
        public void onStartup(Application source);
    }

    private class StartupHandler implements org.gnome.glib.Application.Startup
    {
        private final Startup handler;

        private StartupHandler(Startup handler) {
            this.handler = handler;
        }

        public void onStartup(org.gnome.glib.Application source) {
            handler.onStartup((org.gnome.gtk.Application) source);
        }
    }

    /**
     * Hook up the <code>Application.Startup</code> handler.
     * 
     * @since 4.1.2
     */
    public void connect(Application.Startup handler) {
        super.connect(new StartupHandler(handler));
    }

    /**
     * This signal is emitted on the primary instance when command line
     * arguments are to be processed. When you handle this signal you need to
     * specify the exit code that the invoking process should in turn return
     * to the shell.
     * 
     * @author Andrew Cowie
     * @since 4.1.2
     */
    public interface CommandLine
    {
        public int onCommandLine(Application source, ApplicationCommandLine cmdline);
    }

    private class CommandLineHandler implements org.gnome.glib.Application.CommandLine
    {
        private final CommandLine handler;

        private CommandLineHandler(CommandLine handler) {
            this.handler = handler;
        }

        public int onCommandLine(org.gnome.glib.Application source, ApplicationCommandLine cmdline) {
            return handler.onCommandLine((org.gnome.gtk.Application) source, cmdline);
        }
    }

    /**
     * Hook up the <code>Application.CommandLine</code> handler.
     * 
     * @since 4.1.2
     */
    public void connect(Application.CommandLine handler) {
        super.connect(new CommandLineHandler(handler));
    }
}
