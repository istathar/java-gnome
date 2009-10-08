/*
 * GtkAboutDialogOverride.java
 *
 * Copyright (c) 2009 Operational Dynamics Consulting Pty Ltd, and Others
 * 
 * The code in this file, and the library it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" plus the "Classpath Exception" (you may link to this code as a
 * library into other programs provided you don't make a derivation of it).
 * See the LICENCE file for the terms governing usage and redistribution.
 */
package org.gnome.gtk;

/**
 * Manual code allowing us to hookup the AboutDialog callback functions as if
 * it were a signal.
 * 
 * @author Guillaume Mazoyer
 * @since 4.0.13
 */
public class GtkAboutDialogOverride extends Plumbing
{
    static final void setEmailHook(AboutDialog self) {
        gtk_about_dialog_set_email_hook(pointerOf(self));
    }

    private static native final void gtk_about_dialog_set_email_hook(long self);

    static final void setUrlHook(AboutDialog self) {
        gtk_about_dialog_set_url_hook(pointerOf(self));
    }

    private static native final void gtk_about_dialog_set_url_hook(long self);
}
