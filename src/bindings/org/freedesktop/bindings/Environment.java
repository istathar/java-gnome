/*
 * Environment.java
 * 
 * Copyright (c) 2005,2007-2008 Operational Dynamics Consulting Pty Ltd
 * 
 * The code in this file, and the library it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" See the LICENCE file for the terms governing usage and
 * redistribution.
 *
 * This class imported from ObjectiveAccounts accounting package where it was
 * originally deployed as GPL code in generic.util.Environment
 */
package org.freedesktop.bindings;

/**
 * Retrieve values from environment.
 * 
 * @author Andrew Cowie
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
     * @return the value of the environment variable, or <code>null</code>
     *         if empty or not found.
     * @since 4.0.2
     */
    public static String getEnv(final String variableName) {
        if ((variableName == null) || (variableName.equals(""))) {
            throw new IllegalArgumentException("Can't get an empty or null environment variable");
        }

        String candidate = getenv(variableName);

        if ((candidate == null) || (candidate.equals(""))) {
            return null;
        } else {
            return candidate;
        }
    }

    private static native final String getenv(String variableName);

    /**
     * Set a value in the environment.
     * 
     * <p>
     * Use a value of <code>null</code> to delete a variable from the
     * environment.
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
