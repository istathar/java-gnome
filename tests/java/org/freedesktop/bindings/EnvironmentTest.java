/*
 * EnvironmentTest.java
 *
 * Copyright (c) 2007 Operational Dynamics Consulting Pty Ltd
 * 
 * The code in this file, and the library it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" See the LICENCE file for the terms governing usage and
 * redistribution.
 */
package org.freedesktop.bindings;

import junit.framework.TestCase;

/**
 * Make sure our reimplementaiton of fetching environment variables works.
 * 
 * @author Andrew Cowie
 */
public class EnvironmentTest extends TestCase
{
    public final void testGetEnvironmentVariable() {
        final String home;

        home = Environment.getEnv("HOME");

        assertNotNull(home);

        // TODO: test something whose value will be predictable no matter
        // what. What would that be?
    }
}
