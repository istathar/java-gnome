/*
 * java-gnome, a UI library for writing GTK and GNOME programs from Java!
 *
 * Copyright © 2005-2010 Operational Dynamics Consulting, Pty Ltd and Others
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
package org.freedesktop.bindings;

/**
 * Retrieve values from environment.
 * 
 * @author Andrew Cowie
 * @author Michael Culbertson
 * @since 4.0.2
 */
public class Environment
{
    /**
     * Get an environment variable from the inherited (Linux or Unix)
     * environment. This is here because the {@link System#getenv(String)}
     * call was deprecated for a while and later threw Error in several JDKs
     * [so much for ABI stability, the bastards]. So we reimplement the same
     * thing with our own JNI call.
     * 
     * @param variableName
     *            the name of the environment variable you want to look up
     * @return the value of the environment variable, or <code>null</code> if
     *         empty or not found.
     * @since 4.0.2
     */
    public static String getEnv(final String variableName) {
        String candidate;

        if ((variableName == null) || (variableName.equals(""))) {
            throw new IllegalArgumentException("Can't get an empty or null environment variable");
        }

        try {
            candidate = Environment.getenv(variableName);
        } catch (UnsatisfiedLinkError ule) {
            /*
             * Fallback for the rare but legitimate cases when this is being
             * called before Gtk.init(). Of course, given the original premise
             * of this method being here this too might fail, but as of Sun
             * Java 1.5 it was working again, so give it a shot.
             */
            candidate = System.getenv(variableName);
        }

        if ((candidate == null) || (candidate.equals(""))) {
            return null;
        } else {
            return candidate;
        }
    }

    private static native final String getenv(String variableName);

    /**
     * Set a value in the environment. See {@link #getEnv(String) getEnv()}
     * for further details.
     * 
     * <p>
     * If a value of <code>null</code> is passed as the <code>value</code>,
     * then that variable will be <b>deleted</b> from the environment.
     * 
     * @since 4.0.7
     */
    public static void setEnv(String variableName, String value) {
        if ((variableName == null) || (variableName.equals(""))) {
            throw new IllegalArgumentException("Can't set an empty or null environment variable");
        }

        if (value == null) {
            unsetenv(variableName);
        } else {
            setenv(variableName, value);
        }
    }

    private static native final void setenv(String variableName, String value);

    private static native final void unsetenv(String variableName);
}
