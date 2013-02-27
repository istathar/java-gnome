/*
 * java-gnome, a UI library for writing GTK and GNOME programs from Java!
 *
 * Copyright © 2012-2013 Operational Dynamics Consulting, Pty Ltd and Others
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
package org.gnome.gtk;

import org.gnome.glib.ApplicationCommandLine;
import org.gnome.glib.ApplicationFlags;

/**
 * This class handles the lifecycle of a java-gnome application. It ensures
 * that the application is unique and manages a list of top-level windows
 * whose life-cycle is automatically tied to the life-cycle of the
 * application.
 * 
 * <p>
 * Applications are unique in your desktop session; once the program is
 * running subsequent invocations of the binary will result in a signal being
 * sent to the original, known as the "<var>primary</var>" instance.
 * 
 * <p>
 * You can model several different program styles this way. A straight forward
 * and common use is for subsequent invocations merely to "wake" the primary,
 * perhaps bringing it to front. In this example, you start by constructing an
 * Application object:
 * 
 * <pre>
 * app = new Application(&quot;com.example.MasterControlProgram&quot;);
 * </pre>
 * 
 * in this case "registering" the DBus name
 * <code>com.example.MasterControlProgram</code> as the unique identifier for
 * this application. As with a plain GTK program, you then enter the main
 * loop, but instead of <code>Gtk.main()</code> you use Application's
 * {@link #run(String[]) run()} to do so:
 * 
 * <pre>
 * app.run(args);
 * </pre>
 * 
 * <p>
 * the original process blocks in this call (and runs a GTK main loop and your
 * widgets start receiving event signals). Subsequent programs attempting to
 * register that name (ie, this same code being run through a second time but
 * by a different invocation of your program) will implicitly discover that
 * they are <i>not</i> the <var>primary</var> instance; the <var>remote</var>
 * will return from the call to <code>run()</code> although how fast this
 * occurs depends on actions the <var>primary</var> takes.
 * 
 * <h2>Signals</h2>
 * 
 * <p>
 * The <code>Application.Startup</code> will be fired once for an instance
 * when it becomes <var>primary</var>. So, that's the place to put your UI
 * setup code.
 * 
 * <p>
 * <code>Application.Activate</code> will occurs when when Application's
 * {@link #activate() activate()} is called. It is also raised when the
 * primary begins running the first time if no flags were passed to the
 * Application constructor.
 * 
 * <p>
 * If the application has been configured as passing command line arguments
 * from <var>remote</var> instances to the <var>primary</var>, then instead of
 * <code>Application.Activate</code>, the <code>Application.CommandLine</code>
 * signal will be raised on the <var>primary</var>. You can always call
 * <code>activate()</code> from this handler thereby concentrating the code to
 * deal with raising the UI there.
 * 
 * <h2>Life cycle</h2>
 * 
 * <p>
 * For each principle GTK Window you create, call Application's
 * {@link #addWindow(Window) addWindow()} and be sure to put a call to
 * Application's {@link Application#removeWindow(Window) removeWindow()} in
 * that Window's <code>Window.DeleteEvent</code> handler. The runtime system
 * will keep track of these registered Windows; your application will
 * terminate when the last one is destroyed.
 * 
 * <p>
 * You can also call Application's {@link Application#quit() quit()} to exit
 * the program.
 * 
 * <h2>Initialization</h2>
 * 
 * <p>
 * The call to <code>Gtk.init()</code> or creating a
 * <code>new Application()</code> <b>must</b> be the first thing done in your
 * program.
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
     * Inform the session manager that certain types of actions should be
     * inhibited. This is not guaranteed to work on all platforms and for all
     * types of actions.
     * 
     * <p>
     * Applications should invoke this method when they begin an operation
     * that should not be interrupted, such as creating a CD or DVD. The types
     * of actions that may be blocked are specified by the <code>flags</code>
     * parameter. When the application completes the operation it should call
     * {@link #uninhibit(int) uninhibit()} to remove the inhibitor. Note that
     * an application can have multiple inhibitors, and all of them must be
     * individually removed. Inhibitors are also cleared when the application
     * exits.
     * 
     * <p>
     * Applications should not expect that they will always be able to block
     * the action. In most cases, users will be given the option to force the
     * action to take place.
     * 
     * <p>
     * Reasons should be short and to the point. If <code>window</code> is not
     * null, the session manager may point the user to this window to find out
     * more about why the action is inhibited.
     * 
     * @since 4.1.3
     */
    public int inhibit(Window window, ApplicationInhibitFlags flags, String reason) {
        return GtkApplication.inhibit(this, window, flags, reason);
    }

    /**
     * Removes an inhibitor that has been established with
     * {@link #inhibit(Window, ApplicationInhibitFlags, String) inhibit()}.
     * Inhibitors are also cleared when the application exits.
     * 
     * @since 4.1.4
     */
    public void uninhibit(int cookie) {
        GtkApplication.uninhibit(this, cookie);
    }

    /**
     * Determines if any of the actions specified in <code>flags</code> are
     * currently inhibited (possibly by another application).
     * 
     * @since 4.1.3
     */
    public boolean isInhibited(ApplicationInhibitFlags flags) {
        return GtkApplication.isInhibited(this, flags);
    }

    /**
     * This signal is emitted on the <var>primary</var> instance when an
     * activation occurs. This is triggered by calling Application's
     * {@link Application#activate() activate()} method, or, if the
     * Application is in the {@link ApplicationFlags#NONE default} mode, when
     * an instance starts.
     * 
     * <p>
     * In the {@link ApplicationFlags#HANDLES_COMMAND_LINE command line} mode,
     * see the {@link Application.CommandLine Application.CommandLine} signal
     * is rasied instead.
     * 
     * @author Andrew Cowie
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
     * This signal is emitted on the <var>primary</var> instance when any of
     * the conditions starting the program are raised. This is a good place to
     * put your UI intitialization logic.
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
     * This signal is emitted on the <var>primary</var> instance when command
     * line arguments are to be processed. Typical usage would be:
     * 
     * <pre>
     * app.connect(new Application.CommandLine() {
     *     public int onCommandLine(Application source, ApplicationCommandLine remote) {
     *         final String[] args;
     *         
     *         args = remote.getArguments();
     *         
     *         // do stuff here in primary with the passed arguments
     *         
     *         remote.exit();
     *         return 0;
     *     }
     * }
     * </pre>
     * 
     * as ever, the name of a parameter in a method implenting an interface is
     * arbitrary, but naming the ApplicationCommmandLine object
     * <code>remote</code> makes the point that it represents the command line
     * arguments being sent over the wire.
     * 
     * <p>
     * When you handle this signal you need to specify the exit code that the
     * invoking process should in turn return to the shell, ie:
     * 
     * <pre>
     * s = app.run(args);
     * System.exit(s);
     * </pre>
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
     * Hook up the <code>Application.CommandLine</code> handler. This signal
     * will only be raised if the Application was constructed with the
     * {@link ApplicationFlags#HANDLES_COMMAND_LINE HANDLES_COMMAND_LINE}
     * flag.
     * 
     * @since 4.1.2
     */
    public void connect(Application.CommandLine handler) {
        super.connect(new CommandLineHandler(handler));
    }
}
