/*
 * App.java
 *
 * Copyright (c) 2009 Operational Dynamics Consulting Pty Ltd
 * 
 * The code in this file, and the library it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" plus the "Classpath Exception" (you may link to this code as a
 * library into other programs provided you don't make a derivation of it).
 * See the LICENCE file for the terms governing usage and redistribution.
 */
package org.gnome.unique;

import org.gnome.glib.Object;

/**
 * 
 * @author Andrew Cowie
 * @since 4.0.12
 */
public class Application extends Object
{
    protected Application(long pointer) {
        super(pointer);
    }

    /**
     * 
     * @since 4.0.12
     */
    public Application(String name, String id) {
        super(UniqueApp.createApplication(name, id));
    }

    /**
     * Is the application with this name already running?
     * 
     * @since 4.0.12
     */
    public boolean isRunning() {
        return UniqueApp.isRunning(this);
    }
}
