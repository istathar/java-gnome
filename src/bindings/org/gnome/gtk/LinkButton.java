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
 *
 * Linking this library statically or dynamically with other modules is making
 * a combined work based on this library. Thus, the terms and conditions of
 * the GPL cover the whole combination. As a special exception (the
 * "Classpath Exception"), the copyright holders of this library give you
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

import java.net.URI;
import java.net.URISyntaxException;

/**
 * LinkButton is a specialized subclass of Button for linking to URIs. The
 * default action is to open the URI with its associated application
 * determined by the desktop environment. <img src="LinkButton.png"
 * class="snapshot">
 * 
 * @author Serkan Kaba
 * @author Guillaume Mazoyer
 * @since 4.0.14
 */
public class LinkButton extends Button
{
    protected LinkButton(long pointer) {
        super(pointer);
    }

    /**
     * Create a button pointing to given URI. The <code>uri</code> will also
     * be used as the label.
     * 
     * @since 4.0.14
     */
    public LinkButton(URI uri) {
        super(GtkLinkButton.createLinkButton(uri.toString()));
    }

    /**
     * Create a button pointing to given URI and the given <code>label</code>
     * as you would expect from a normal hyperlink.
     * 
     * @since 4.0.14
     */
    public LinkButton(URI uri, String label) {
        super(GtkLinkButton.createLinkButtonWithLabel(uri.toString(), label));
    }

    /**
     * Return the button's URI.
     * 
     * @since 4.0.14
     */
    /*
     * Naming convention same as FileChooser's getURI().
     */
    public URI getURI() {
        try {
            return new URI(GtkLinkButton.getUri(this));
        } catch (URISyntaxException e) {
            throw new RuntimeException("We shouldn't be throwing this exception", e);
        }
    }

    /**
     * Modify the button's {@link URI}.
     * 
     * @since 4.0.14
     */
    public void setURI(URI uri) {
        GtkLinkButton.setUri(this, uri.toString());
    }

    /**
     * Return the button's visited state. Note that this might be modified
     * programatically by {@link #setVisited(boolean)}.
     * 
     * @since 4.0.14
     */
    public boolean getVisited() {
        return GtkLinkButton.getVisited(this);
    }

    /**
     * Modify the button's visited state.
     * 
     * @since 4.0.14
     */
    public void setVisited(boolean visited) {
        GtkLinkButton.setVisited(this, visited);
    }
}
