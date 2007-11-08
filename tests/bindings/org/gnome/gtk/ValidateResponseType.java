/*
 * ValidateResponseType.java
 *
 * Copyright (c) 2007 Operational Dynamics Consulting Pty Ltd
 * 
 * The code in this file, and the library it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" See the LICENCE file for the terms governing usage and
 * redistribution.
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
public class ValidateResponseType extends TestCaseGtk
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
