/*
 * java-gnome, a UI library for writing GTK and GNOME programs from Java!
 *
 * Copyright © 2009-2010 Operational Dynamics Consulting, Pty Ltd and Others
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

import java.net.URI;
import java.net.URISyntaxException;

/**
 * Test LinkButton's visited and uri properties.
 * 
 * @author Serkan Kaba
 * @author Andrew Cowie
 */
public class ValidateLinkBehaviour extends GraphicalTestCase
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
