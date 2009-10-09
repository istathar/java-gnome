/*
 * ValidateLinkButton.java
 *
 * Copyright (c) 2009 Operational Dynamics Consulting Pty Ltd, and Others
 * 
 * The code in this file, and the suite it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" See the LICENCE file for the terms governing usage and
 * redistribution.
 */
package org.gnome.gtk;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * Test LinkButton's visited and uri properties.
 * 
 * @author Serkan Kaba
 * @author Andrew Cowie
 */
public class ValidateLinkButton extends GraphicalTestCase
{
    public final void testVisited() throws URISyntaxException {
        final URI uri;
        final LinkButton link;

        uri = new URI("http://java-gnome.sourceforge.net");
        link = new LinkButton(uri);

        assertFalse(link.getVisited());
        link.setVisited(true);
        assertTrue(link.getVisited());
        link.setVisited(false);
        assertFalse(link.getVisited());
        assertEquals(uri, link.getURI());
    }

    /*
     * TODO Actually calling emitClicked() results in gtk_show_uri() popping
     * up a web browser! That's not quite going to work, is it.
     */
    public final void skipClicked() throws URISyntaxException {
        final URI uri;
        final LinkButton link;

        uri = new URI("http://java-gnome.sourceforge.net");
        link = new LinkButton(uri);

        assertFalse(link.getVisited());
        link.emitClicked();
        assertTrue(link.getVisited());
    }
}
