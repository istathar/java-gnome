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
 * "Claspath Exception"), the copyright holders of this library give you
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
	g_signal_emit_by_name(GTK_ABOUT_DIALOG(about), "email-clicked", email);
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
	g_signal_emit_by_name(GTK_ABOUT_DIALOG(about), "url-clicked", link);
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
