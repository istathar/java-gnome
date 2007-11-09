/*
 * GtkWindow.c
 *
 * Copyright (c) 2007 Operational Dynamics Consulting Pty Ltd
 *
 * The code in this file, and the library it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" plus the "Classpath Exception" (you may link to this code as a
 * library into other programs provided you don't make a derivation of it).
 * See the LICENCE file for the terms governing usage and redistribution.
 */

#include <jni.h>
#include <gtk/gtk.h>
#include "bindings_java.h"
#include "org_gnome_gtk_GtkWindowOverride.h"
#include <stdio.h>

/*
 * Signature the prototype of a (*delete-event) callback. On a 'delete-event'
 * signal, hides the window, and blocks further signal emission.
 */
static gboolean
window_delete_handler
(
	GtkWidget* object,
	gpointer user_data
)
{
	if (DEBUG_MEMORY_MANAGEMENT) {
		g_print("mem: delete caught for\t\t%ld\n", (long) object);
	}
	gtk_widget_hide(GTK_WIDGET(object));
	
	return TRUE;
}

static void
window_hide_handler
(
	GtkWidget* widget,
	gpointer user_data
)
{
	if (DEBUG_MEMORY_MANAGEMENT) {
		g_print("mem: hide caught for\t\t%ld\n", (long) widget);
	}
	g_object_unref(widget);
}

static void
window_show_handler
(
	GtkWidget* widget,
	gpointer user_data
)
{
	if (DEBUG_MEMORY_MANAGEMENT) {
		g_print("mem: show caught for\t\t%ld\n", (long) widget);
	}
	g_object_ref(widget);
}


JNIEXPORT void JNICALL
Java_org_gnome_gtk_GtkWindowOverride_gtk_1window_1drop_1user_1ref
(
	JNIEnv* env,
	jclass cls,
	jlong _self
)
{
	GtkWindow* self;

	// convert parameter self
	self = (GtkWindow*) _self;

	/*
	 * Hook up our custom overrides to unref on hide and ref on show, and
	 * to block delete-event into hide only. 
	 */

	g_signal_connect_after(self, "delete-event", G_CALLBACK(window_delete_handler), NULL); 
	g_signal_connect(self, "hide", G_CALLBACK(window_hide_handler), NULL);
	g_signal_connect(self, "show", G_CALLBACK(window_show_handler), NULL);

	/*
	 * Now. force the behaviour change on GtkWindow. We reconfigure its
	 * memory management mode, and then drop the reference to self that
	 * GTK places in GtkWindows.
	 */

	self->has_user_ref_count = FALSE;
	g_object_unref(self);

	// cleanup parameter self
}
