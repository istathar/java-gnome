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

import org.gnome.glib.ApplicationFlags;

/**
 * This class handles some important aspects of a GTK+ application. It
 * currently ensures that the application is unique and manages a list of
 * top-level windows whose life-cycle is automatically tied to the life-cycle
 * of the application.
 * 
 * @author Guillaume Mazoyer
 * @since 4.1.2
 */
public class Application extends org.gnome.glib.Application
{
    public Application(long pointer) {
        super(pointer);
    }

    /**
     * Creates a new Application instance. The <code>id</code> should be
     * valid, this can be checked with
     * {@link org.gnome.glib.Application#isValidID(String) isValidID()}.
     */
    public Application(String id, ApplicationFlags flags) {
        super(GtkApplication.createApplication(id, flags));
    }

    /**
     * Adds a window from the Application. GTK+ will keep the Application
     * running as long as it has any windows. The connection between the
     * Application and the window will remain until the window is destroyed or
     * {@link #removeWindow(Window) removeWindow()} is called.
     */
    public void addWindow(Window window) {
        GtkApplication.addWindow(this, window);
    }

    /**
     * Removes a window from the Application. The Application will stop
     * running if the last window is removed.
     */
    public void removeWindow(Window window) {
        GtkApplication.removeWindow(this, window);
    }

    /**
     * Returns an array of the {@link Window windows} associated with
     * Application. This array should not be modified!
     */
    public Window[] getWindows() {
        return GtkApplication.getWindows(this);
    }
}
