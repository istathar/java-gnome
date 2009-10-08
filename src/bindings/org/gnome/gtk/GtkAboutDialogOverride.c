/*
 * GtkAboutDialogOverride.c
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
#include "org_gnome_gtk_GtkAboutDialogOverride.h"

static guint emailSignalID = 0;
static guint urlSignalID = 0;

/*
 * Meets the signature requirement of (*GtkAboutDialogActivateLinkFunc).
 */
static void
emit_email_hook
(
	GtkAboutDialog *about,
	const gchar *email
)
{
	gboolean result;

	g_signal_emit_by_name(GTK_ABOUT_DIALOG(about), "email-clicked", email, &result);
}

/**
 * called from
 *   org.gnome.gtk.GtkAboutDialogOverride.setEmailHook()
 * called from
 *   org.gnome.gtk.AboutDialog.setEmailCallback()
 */
JNIEXPORT void JNICALL Java_org_gnome_gtk_GtkAboutDialogOverride_gtk_1about_1dialog_1set_1email_1hook
(
	JNIEnv* env,
	jclass cls,
	jlong _self
)
{
	GtkAboutDialog* self;

	// convert parameter self
	self = (GtkAboutDialog*) _self;

	if (emailSignalID == 0) {
		emailSignalID = g_signal_new("email-clicked",
			GTK_TYPE_ABOUT_DIALOG,
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
	gtk_about_dialog_set_email_hook((GtkAboutDialogActivateLinkFunc) emit_email_hook, NULL, NULL);
}

/*
 * Meets the signature requirement of (*GtkAboutDialogActivateLinkFunc).
 */
static void
emit_url_hook
(
	GtkAboutDialog *about,
	const gchar *link
)
{
	gboolean result;

	g_signal_emit_by_name(GTK_ABOUT_DIALOG(about), "url-clicked", link, &result);
}

/**
 * called from
 *   org.gnome.gtk.GtkAboutDialogOverride.setUrlHook()
 * called from
 *   org.gnome.gtk.AboutDialog.setUrlCallback()
 */
JNIEXPORT void JNICALL Java_org_gnome_gtk_GtkAboutDialogOverride_gtk_1about_1dialog_1set_1url_1hook
(
	JNIEnv* env,
	jclass cls,
	jlong _self
)
{
	GtkAboutDialog* self;

	// convert parameter self
	self = (GtkAboutDialog*) _self;

	if (urlSignalID == 0) {
		urlSignalID = g_signal_new("url-clicked",
			GTK_TYPE_ABOUT_DIALOG,
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
	gtk_about_dialog_set_url_hook((GtkAboutDialogActivateLinkFunc) emit_url_hook, NULL, NULL);
}
