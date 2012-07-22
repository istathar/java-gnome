/*
 * java-gnome, a UI library for writing GTK and GNOME programs from Java!
 *
 * Copyright © 2011 Operational Dynamics Consulting, Pty Ltd and Others
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
 * This class represents the foundation of an application. This Application
 * class is the base for the high-level {@link org.gnome.gtk.Application GTK
 * Application} class. In general, you should not use this class outside of a
 * higher level framework.
 * 
 * <p>
 * We do not handle (yet?) passing parameters to the {@link #run()} method
 * because we don't have any way to handle them properly. If you have any
 * parameters for the entry point to use take care of them in the mandatory
 * main method.
 * 
 * @author Guillaume Mazoyer
 * @since 4.1.2
 */
public class Application extends Object
{
    protected Application(long pointer) {
        super(pointer);
    }

    /**
     * Check if the given ID is valid to be used as an Application ID.
     * 
     * @since 4.1.2
     */
    public static boolean isValidId(String id) {
        return GApplication.isValidId(id);
    }

    /**
     * Creates a new Application instance. The application ID must be valid.
     * See {@link #isValidId(String) isValidId()}.
     * 
     * <p>
     * You should probably not have to call this constructor by yourself.
     * 
     * @since 4.1.2
     */
    public Application(String id, ApplicationFlags flags) {
        super(GApplication.createApplication(id, flags));
    }

    /**
     * Gets the unique identifier of the Application.
     * 
     * @since 4.1.2
     */
    public String getId() {
        return GApplication.getApplicationId(this);
    }

    /**
     * Sets the unique identifier of the Application. The application ID must
     * be valid. See {@link #isValidId(String) isValidId()}.
     * 
     * @since 4.1.2
     */
    public void setId(String id) {
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
     * Checks if the Application is remote. If it is then it means that
     * another instance of the Application exists, the &quot;primary&quot;
     * instance.
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
     * continue to run.This method is called by GTK+ when a top-level
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
     * This function is intended to be run from <code>main()</code> and its
     * return value is intended to be returned be used as the exit value of
     * the program.
     * 
     * @since 4.1.2
     */
    public int run() {
        return GApplicationOverride.run(this, null);
    }

    /**
     * This signal is emitted on the primary instance when an activation
     * occurs (at startup or by calling the Application
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
    public void connect(Application.Activate handler) {
        GApplication.connect(this, handler, false);
    }

    /**
     * This signal is emitted on the primary instance immediately after
     * registration.
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
    public void connect(Application.Startup handler) {
        GApplication.connect(this, handler, false);
    }
}
