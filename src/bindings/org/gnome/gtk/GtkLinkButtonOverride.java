package org.gnome.gtk;

final class GtkLinkButtonOverride extends Plumbing
{
    /**
     * Manually hookup the function that will emit our custom visible signal.
     */
    static final void setUriHook(LinkButton self) {
        gtk_link_button_set_uri_hook(pointerOf(self));
    }

    private static native final void gtk_link_button_set_uri_hook(long self);
}
