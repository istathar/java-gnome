/*
 * bindings_java_memory.c
 *
 * Copyright (c) 2007 Operational Dynamics Consulting Pty Ltd and Others
 * 
 * The code in this file, and the library it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" plus the "Classpath Exception" (you may link to this code as a
 * library into other programs provided you don't make a derivation of it).
 * See the LICENCE file for the terms governing usage and redistribution.
 */

/*
 * Thanks to the guidance from Mathias Hasselmann to protect g_str_equal()
 */

#include <jni.h>
#include <glib.h>
#include "bindings_java.h"

/**
 * Called from
 *   Java_org_gnome_gtk_GtkTreeModelOverride_gtk_1list_1store_1new
 * called from
 *   org.gnome.gtk.GtkTreeModeOverride.gtk_list_store_new(String[])
 *
 * Map from a fully qualified class name string to a GType. So long as this
 * is a short list, do it in an if/else ladder. Switch to a GHashTable if it
 * becomes a performance bottleneck.
 */ 
GType
bindings_java_type_lookup
(
	const gchar* fqcn
)
{
	g_assert(fqcn != NULL);

	if (g_str_equal(fqcn, "java.lang.String")) {
		return G_TYPE_STRING;
	} else if (g_str_equal(fqcn, "java.lang.Integer")) {
		return G_TYPE_INT;
	} else if (g_str_equal(fqcn, "java.lang.Boolean")) {
		return G_TYPE_BOOLEAN;
	} else if (g_str_equal(fqcn, "java.lang.Object")) {
		/*
		 * This is what we use to stash references to Java objects.
		 * They're never displayed in a TreeView, but used to find
		 * your way back to your Java side domain object graph.
		 * TODO confirm type used across boundary.
		 */
		return G_TYPE_POINTER;
	} else {
		return G_TYPE_INVALID;
	} 
 }
