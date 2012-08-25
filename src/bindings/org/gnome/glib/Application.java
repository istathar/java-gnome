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
package org.gnome.glib;

/**
 * The foundation of an application. This class is the basis for higher-level
 * functionality appropriate to a GUI framework; it is accessed through the [
 * <code>org.gnome.gtk</code>] {@link org.gnome.gtk.Application Application}
 * class; see there for a full discussion.
 * 
 * @author Guillaume Mazoyer
 * @since 4.1.2
 */
public class Application extends Object
{
    /*
     * Force a type registration in a static block that's able to see
     * Plumbing.
     */
    static {
        GApplicationOverride.init();
    }

    protected Application(long pointer) {
        super(pointer);
    }

    /**
     * Check if the given string is valid to be used as an Application
     * identifier.
     * 
     * @since 4.1.2
     */
    /*
     * This re-implements the native call, but at the benefit of good error
     * messages. We call g_application_is_valid_id() at the end to be sure.
     */
    protected static String isValidId(final String id) {
        if (id.isEmpty()) {
            throw new IllegalArgumentException("identifier cannot be emtpy.");
        }
        if (!(Character.isLetter(id.charAt(0)) || id.matches("^[A-Z][a-z][0-9][_\\-.]*$"))) {
            throw new IllegalArgumentException(
                    "identifier must contain only the ASCII characters \"[A-Z][a-z][0-9]_-.\" and must not begin with a digit.");
        }
        if (id.indexOf('.') == -1) {
            throw new IllegalArgumentException(
                    "identifier must contain at least one '.' (period) character (and thus at least three elements).");
        }
        if (id.indexOf('$') != -1) {
            throw new IllegalArgumentException("identifier cannot contain '$'.");
        }
        if (id.startsWith(".") || id.endsWith(".")) {
            throw new IllegalArgumentException(
                    "identifier must not begin or end with a '.' (period) character.");
        }
        if (id.length() > 255) {
            throw new IllegalArgumentException("identifier must not exceed 255 characters.");
        }

        if (!GApplication.isValidId(id)) {
            /*
             * Note: if you've found yourself here, they've changed the rules
             * for valid application IDs.
             */
            throw new IllegalArgumentException("Invalid Application ID");
        }

        return id;
    }

    /**
     * Creates a new Application instance. The application ID must be valid.
     * See {@link #isValidId(String) isValidId()}.
     * 
     * <p>
     * <i> You should probably not have to call this constructor by yourself;
     * <code>GApplication</code> is infrastructure over which a framework like
     * GTK can build integrated application management appropriate to their
     * environment.</i>
     * 
     * @since 4.1.2
     */
    protected Application(String id, ApplicationFlags flags) {
        super(GApplication.createApplication(isValidId(id), flags));
    }

    /**
     * Gets the unique identifier of the Application.
     * 
     * @since 4.1.2
     */
    public String getApplicationId() {
        return GApplication.getApplicationId(this);
    }

    /**
     * Sets the unique identifier of the Application. The application ID must
     * be valid. See {@link #isValidId(String) isValidId()}.
     * 
     * @since 4.1.2
     */
    public void setApplicationId(String id) {
        isValidId(id);
        GApplication.setApplicationId(this, id);
    }

    /**
     * Returns the current inactivity timeout for the Application. This is the
     * amount of time (in milliseconds) after the last call to
     * {@link #unhold()} before the Application stops running.
     * 
     * @since 4.1.2
     */
    public int getInactivityTimeout() {
        return GApplication.getInactivityTimeout(this);
    }

    /**
     * Sets the current inactivity timeout (in milliseconds) for the
     * Application.
     * 
     * @since 4.1.2
     */
    public void setInactivityTimeout(int timeout) {
        GApplication.setInactivityTimeout(this, timeout);
    }

    /**
     * Returns the {@link GlibApplicationFlags flags} of the Application.
     * 
     * @since 4.1.2
     */
    public ApplicationFlags getFlags() {
        return GApplication.getFlags(this);
    }

    /**
     * Sets the flags of the Application.
     * 
     * @since 4.1.2
     */
    public void setFlags(ApplicationFlags flags) {
        GApplication.setFlags(this, flags);
    }

    /**
     * Checks if the Application is <var>remote</var>. If it is then it means
     * that another instance of the Application exists and is running, the
     * <var>primary</var>; instance.
     * 
     * <p>
     * <i>Not sure what the point of this is; this method is not available
     * until after <code>run()</code> has been called, and the signals raised
     * on this class all happen in the <var>primary</var> instance.</i>
     * 
     * @since 4.1.2
     */
    public boolean isRemote() {
        return GApplication.isRemote(this);
    }

    /**
     * Increases the use count of the Application.
     * 
     * <p>
     * Use this function to indicate that the Application has a reason to
     * continue to run. This method is called by GTK+ when a top-level
     * {@link org.gnome.gtk.Window} is on the screen.
     * 
     * <p>
     * To cancel the hold, call {@link #unhold()}.
     * 
     * @since 4.1.2
     */
    public void hold() {
        GApplication.hold(this);
    }

    /**
     * Decrease the use count of the Application.
     * 
     * <p>
     * When the use count reaches zero, the Application will stop running.
     * 
     * <p>
     * You should never call this function except to cancel the effect of a
     * previous call to {@link #hold()}.
     * 
     * @since 4.1.2
     */
    public void unhold() {
        GApplication.release(this);
    }

    /**
     * Immediately quits the Application.
     * 
     * @since 4.1.2
     */
    public void quit() {
        GApplication.quit(this);
    }

    /**
     * Emits the {@link Application.Activate} signal.
     * 
     * @since 4.1.2
     */
    public void activate() {
        GApplication.activate(this);
    }

    /**
     * Runs the Application.
     * 
     * <p>
     * This is intended to be called from <code>main()</code>. Its return
     * value is should be used as the exit value of the program.
     * 
     * <p>
     * You can pass <code>null</code> if you don't have any command line
     * arguments.
     * 
     * @since 4.1.2
     */
    public int run(final String[] args) {
        final int result;
        final int argc;
        final String[] argv;

        if (args == null) {
            argc = 0;
            argv = null;
        } else {
            /*
             * In C, the first element in the argv is the program name from
             * the command line. Java skips this, so we need to re-introduce a
             * dummy value here.
             */

            argc = args.length + 1;
            argv = new String[argc];
            argv[0] = Glib.getProgramName();
            System.arraycopy(args, 0, argv, 1, args.length);
        }

        result = GApplication.run(this, argc, argv);

        return result;
    }

    /**
     * This signal is emitted on the <var>primary</var> instance when an
     * activation occurs (at startup or by calling the Application
     * {@link Application#activate() activate()} method.
     * 
     * @author Guillaume Mazoyer
     * @since 4.1.2
     */
    public interface Activate extends GApplication.ActivateSignal
    {
        public void onActivate(Application source);
    }

    /**
     * Hook up the <code>Application.Activate</code> handler.
     * 
     * @since 4.1.2
     */
    protected void connect(Application.Activate handler) {
        GApplication.connect(this, handler, false);
    }

    /**
     * This signal is emitted on the <var>primary</var> instance immediately
     * after registration. This is the place to put (or call) your user
     * interface initialization code.
     * 
     * @author Guillaume Mazoyer
     * @since 4.1.2
     */
    public interface Startup extends GApplication.StartupSignal
    {
        public void onStartup(Application source);
    }

    /**
     * Hook up the <code>Application.Startup</code> handler.
     * 
     * @since 4.1.2
     */
    protected void connect(Application.Startup handler) {
        GApplication.connect(this, handler, false);
    }

    /**
     * Connect to this signal to receive command line arguments from a
     * <var>remote</var> instance. Note that you must call
     * ApplciationCommandLine's {@link ApplicationCommandLine#exit() exit()}
     * to release the <var>remote</var> from its call to Application's
     * <code>run()</code>.
     * 
     * 
     * @author Andrew Cowie
     */
    public interface CommandLine extends GApplication.CommandLineSignal
    {
        public int onCommandLine(Application source, ApplicationCommandLine remote);
    }

    /**
     * Hook up the <code>Application.CommandLine</code> handler.
     * 
     * @since 4.1.2
     */
    protected void connect(Application.CommandLine handler) {
        GApplication.connect(this, handler, false);
    }
}
