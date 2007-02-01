/*
 * Environment.java
 * 
 * Copyright (c) 2005,2007 Operational Dynamics Consulting Pty Ltd
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
 */
public class Environment
{
    /**
     * Get an environment variable from the inherited (Linux or Unix)
     * environment. This would wrap the deprecated {@link System#getenv()}
     * call, but that throws Error in modern JDKs [so much for ABI stability,
     * the bastards]. So we reimplement the same thing with our own JNI call.
     * 
     * @param variableName
     *            the name of the environment variable you want to look up
     * @return the value of the environment variable, or <code>null</code>
     *         if empty or not found.
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
     
    public static int getWidth() {
        throw new UnsupportedOperationException("Don't know how to implement this, sorry");
    }   
}
