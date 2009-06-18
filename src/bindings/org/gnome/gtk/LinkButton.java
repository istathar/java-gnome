/*
 * LinkButton.java
 *
 * Copyright (c) 2009 Operational Dynamics Consulting Pty Ltd and Others
 *
 * The code in this file, and the library it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" plus the "Classpath Exception" (you may link to this code as a
 * library into other programs provided you don't make a derivation of it).
 * See the LICENCE file for the terms governing usage and redistribution.
 */
package org.gnome.gtk;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * LinkButton is a specialized subclass of {@link Button} for linking to
 * {@link URI}'s. The default action is to open the {@link URI} with its
 * default viewer determined by the desktop environment.
 * 
 * @author Serkan Kaba
 */
public class LinkButton extends Button
{
    protected LinkButton(long pointer) {
        super(pointer);
    }

    /**
     * Create a button pointing to given {@link URI}. The <code>uri</code>
     * will also be used as the label.
     */
    public LinkButton(URI uri) {
        super(GtkLinkButton.createLinkButton(uri.toString()));
    }

    /**
     * Create a button pointing to given {@link URI}. and labeled as
     * <code>label</code>.
     */
    public LinkButton(URI uri, String label) {
        super(GtkLinkButton.createLinkButtonWithLabel(uri.toString(), label));
    }

    /**
     * Returns the button's {@link URI}.
     */
    public URI getUri() {
        try {
            return new URI(GtkLinkButton.getUri(this));
        } catch (URISyntaxException e) {
            throw new RuntimeException("We shouldn't be throwing this exception", e);
        }
    }

    /**
     * Alters the button's {@link URI}.
     */
    public void setUri(URI uri) {
        GtkLinkButton.setUri(this, uri.toString());
    }
}
