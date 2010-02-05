/*
 * java-gnome, a UI library for writing GTK and GNOME programs from Java!
 *
 * Copyright © 2007-2010 Operational Dynamics Consulting, Pty Ltd
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
 */
package org.gnome.gtk;

/**
 * Validate the intricies of our custom Dialog ResponseType handling. These
 * test cases are somewhat fragile due to their probing the underlying ordinal
 * numbers, but it should be ok for now; NONE is known -1 and the custom
 * response codes are +1 origin.
 * 
 * @author Andrew Cowie
 */
public class ValidateResponseType extends GraphicalTestCase
{
    public final void testPlumbingMethodsExposedInOverride() {
        assertEquals(-1, GtkResponseTypeOverride.numOf(ResponseType.NONE));
        assertSame(ResponseType.NONE, GtkResponseTypeOverride.enumFor(-1));
    }

    public final void testPlumbingMethodsAvailableInProxy() {
        final ResponseType response = ResponseType.NONE;

        assertEquals(-1, response.getResponseId());
        assertSame(ResponseType.NONE, ResponseType.constantFor(-1));
    }

    private static class LocalResponseType extends ResponseType
    {
        protected LocalResponseType(String nickname) {
            super(nickname);
        }

        /**
         * An artificial constant
         */
        static final LocalResponseType MAGIC = new LocalResponseType("MAGIC");

        /**
         * Another artificial constant
         */
        static final LocalResponseType ULTRA = new LocalResponseType("ULTRA");
    }

    public final void testArtificalConstantsInCustomSubclass() {
        ResponseType response;

        assertNotSame(LocalResponseType.MAGIC, LocalResponseType.ULTRA);

        response = LocalResponseType.MAGIC;
        assertEquals(1, response.getResponseId());

        response = LocalResponseType.ULTRA;
        assertEquals(2, response.getResponseId());
    }

    public final void testUnexpectedReponseId() {
        try {
            GtkResponseTypeOverride.enumFor(299);
            fail("Should have thrown Exception");
        } catch (IllegalArgumentException ise) {
            // good
        }
    }
}
