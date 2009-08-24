/*
 * GtkLinkButtonOverride.c
 *
 * Copyright (c) 2009 Operational Dynamics Consulting Pty Ltd and Others
 *
 * The code in this file, and the library it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" plus the "Classpath Exception" (you may link to this code as a
 * library into other programs provided you don't make a derivation of it).
 * See the LICENCE file for the terms governing usage and redistribution.
 */

#include <gtk/gtk.h>
#include "org_gnome_gtk_GtkLinkButtonOverride.h"

static guint signalID = 0;

static void
emit_uri_hook
(
	GtkLinkButton *source,
	const gchar *link
)
{
	g_signal_emit_by_name(GTK_LINK_BUTTON(source), "uri-clicked", link);
}

/*
 * Implements
 *   org.gnome.gtk.GtkLinkButtonOverride.gtk_link_button_set_uri_hook()
 * called from
 *   org.gnome.gtk.Notification.setUriHook(UriHook)
 */
JNIEXPORT void JNICALL Java_org_gnome_gtk_GtkLinkButtonOverride_gtk_1link_1button_1set_1uri_1hook
(
	JNIEnv* env,
	jclass cls,
	jlong _self
)
{
	GtkLinkButton* self;

	// convert parameter self
	self = (GtkLinkButton*) _self;

	if (signalID == 0) {
		signalID = g_signal_new("uri-clicked",
			GTK_TYPE_LINK_BUTTON,
			G_SIGNAL_ACTION,
			0,
			NULL,
			NULL,
			NULL,
			G_TYPE_NONE,
			1,
			G_TYPE_STRING
		);
	}

	// call function
	gtk_link_button_set_uri_hook((GtkLinkButtonUriFunc) emit_uri_hook, NULL, NULL);
}
