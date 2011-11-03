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

public class Application extends Object
{
    protected Application(long pointer) {
        super(pointer);
    }

    public static boolean isValidID(String id) {
        return GApplication.isValidId(id);
    }

    public Application(String id, ApplicationFlags flags) {
        super(GApplication.createApplication(id, flags));
    }

    public String getID() {
        return GApplication.getApplicationId(this);
    }

    public void setID(String id) {
        GApplication.setApplicationId(this, id);
    }

    public int getInactivityTimeout() {
        return GApplication.getInactivityTimeout(this);
    }

    public void setInactivityTimeout(int timeout) {
        GApplication.setInactivityTimeout(this, timeout);
    }

    public ApplicationFlags getFlags() {
        return GApplication.getFlags(this);
    }

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

        // argv[0] is the app name in C
        argv = new String[args.length + 1];
        argv[0] = this.getID();

        for (int i = 1; i < argv.length; i++) {
            argv[i] = args[i - 1];
        }

        return GApplication.run(this, argv.length, argv);
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
