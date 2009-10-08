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
 * associated application determined by the desktop environment. <img
 * src="LinkButton.png" class="snapshot">
 * 
 * @author Serkan Kaba
 * @since 4.0.13
 */
public class LinkButton extends Button
{
    protected LinkButton(long pointer) {
        super(pointer);
    }

    /**
     * Create a button pointing to given {@link URI}. The <code>uri</code>
     * will also be used as the label.
     * 
     * @since 4.0.13
     */
    public LinkButton(URI uri) {
        super(GtkLinkButton.createLinkButton(uri.toString()));
    }

    /**
     * Create a button pointing to given {@link URI}. and labeled as
     * <code>label</code>.
     * 
     * @since 4.0.13
     */
    public LinkButton(URI uri, String label) {
        super(GtkLinkButton.createLinkButtonWithLabel(uri.toString(), label));
    }

    /**
     * Returns the button's {@link URI}.
     * 
     * @since 4.0.13
     */
    public URI getUri() {
        try {
            return new URI(GtkLinkButton.getUri(this));
        } catch (URISyntaxException e) {
            throw new RuntimeException("We shouldn't be throwing this exception", e);
        }
    }

    /**
     * Modifies the button's {@link URI}.
     * 
     * @since 4.0.13
     */
    public void setUri(URI uri) {
        GtkLinkButton.setUri(this, uri.toString());
    }

    /**
     * Returns the buttons visited state. Note that this might be modified
     * programatically by {@link #setVisited(boolean)}.
     * 
     * @since 4.0.13
     */
    public boolean getVisited() {
        return GtkLinkButton.getVisited(this);
    }

    /**
     * Modifies the button's visited state.
     * 
     * @since 4.0.13
     */
    public void setVisited(boolean visited) {
        GtkLinkButton.setVisited(this, visited);
    }

    /**
     * Callback invoked when the LinkButton is clicked.
     * 
     * @author Serkan Kaba
     * @since 4.0.13
     */
    public interface UriHook
    {
        public void onUriClicked(LinkButton source, URI uri);
    }

    private static class UriClickHandler implements GtkLinkButton.UriClickedSignal
    {

        private final UriHook uriHook;

        public UriClickHandler(UriHook uriHook) {
            this.uriHook = uriHook;
        }

        public void onUriClicked(LinkButton source, String link) {
            try {
                uriHook.onUriClicked(source, new URI(link));
            } catch (URISyntaxException e) {
                throw new RuntimeException("We shouldn't be throwing this exception", e);
            }

        }
    }

    /**
     * Hookup a custom <code>UriHook</code> to override the default behavior
     * of the LinkButton.
     * 
     * @since 4.0.13
     */
    public void setUriHook(UriHook uriHook) {
        GtkLinkButtonOverride.setUriHook(this);
        GtkLinkButton.connect(this, new UriClickHandler(uriHook), false);
    }
}
