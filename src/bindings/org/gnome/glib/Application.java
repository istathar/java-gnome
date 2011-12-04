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
 * @author Guillaume Mazoyer
 * @since 4.1.2
 */
public class Application extends Object
{
    protected Application(long pointer) {
        super(pointer);
    }

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

    public boolean isRemote() {
        return GApplication.isRemote(this);
    }

    public void hold() {
        GApplication.hold(this);
    }

    public void unhold() {
        GApplication.release(this);
    }

    public void activate() {
        GApplication.activate(this);
    }

    public int run(String[] args) {
        final String[] argv;

        /*
         * FIXME: When passing arguments a crasher will be generated because
         * glib will tell us that the application cannot open files.
         */
        argv = new String[args.length + 1];
        argv[0] = this.getId();

        for (int i = 1; i < argv.length; i++) {
            argv[i] = args[i - 1];
        }

        return GApplication.run(this, argv.length, argv);
        // FIXME: this (sadly) generates a crasher.
        // return GApplicationOverride.run(this, args);
    }

    public interface Activate extends GApplication.ActivateSignal
    {
        public void onActivate(Application source);
    }

    public void connect(Application.Activate handler) {
        GApplication.connect(this, handler, false);
    }

    public interface Startup extends GApplication.StartupSignal
    {
        public void onStartup(Application source);
    }

    public void connect(Application.Startup handler) {
        GApplication.connect(this, handler, false);
    }

    public interface Shutdown extends GApplication.ShutdownSignal
    {
        public void onShutdown(Application source);
    }

    public void connect(Application.Shutdown handler) {
        GApplication.connect(this, handler, false);
    }
}
