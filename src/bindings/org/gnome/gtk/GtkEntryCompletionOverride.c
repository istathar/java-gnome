/*
 * GtkEntryCompletionOverride.c
 *
 * Copyright (c) 2009 Operational Dynamics Consulting Pty Ltd
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
#include "org_gnome_gtk_GtkEntryCompletionOverride.h"
#include <gtk/gtkmarshal.h>

static guint signalID = 0;

/*
 * Meets the signature requirement of (*GtkEntryCompletionMatchFunc) in
 * order to be the second parameter to the call to 
 * gtk_entry_completion_set_match_func() below.
 */
static gboolean
emit_match
(
	GtkEntryCompletion *source,
	const gchar *key,
	GtkTreeIter *iter,
	gpointer instance
)
{
	gboolean result;
	
	g_signal_emit_by_name(GTK_ENTRY_COMPLETION(instance), "match", source, key, iter, &result);
	printf("Key: %s\n", key);
	
	return result;
}

/**
 * called from
 *   org.gnome.gtk.GtkEntryCompletionOverride.setMatchFunc()
 * called from
 *   org.gnome.gtk.EntryCompletion.setMatchCallback()
 */
JNIEXPORT void JNICALL
Java_org_gnome_gtk_GtkEntryCompletionOverride_gtk_1entry_1completion_1set_1match_1func
(
	JNIEnv* env,
	jclass cls,
	jlong _self
)
{
	GtkEntryCompletion* self;

	// convert parameter self
	self = (GtkEntryCompletion*) _self;

	if (signalID == 0) {
		signalID = g_signal_new("match",
					GTK_TYPE_ENTRY_COMPLETION,
					G_SIGNAL_ACTION,
					0,
					NULL,
					NULL, 
					NULL,
					G_TYPE_BOOLEAN,
					2,
					GTK_TYPE_STRING,
					GTK_TYPE_TREE_ITER);
	}

	// call function
	gtk_entry_completion_set_match_func(self, emit_match, NULL, NULL);
}
