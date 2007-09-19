/*
 * GtkTreeModelOverride.c
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
#include "org_gnome_gtk_GtkTreeModelOverride.h"

/*
 * Allocates a GValue with GSlice, the same as we do in our GValue binding.
 * The result will be free'd by org.gnome.glib.Value's release() function.
 * This is an encapsulation violation! 
 */
JNIEXPORT jlong JNICALL
Java_org_gnome_gtk_GtkTreeModelOverride_gtk_1tree_1model_1get_1value
(
	JNIEnv* env,
	jclass cls,
	jlong _self,
	jlong _iter,
	jint _column
)
{
	GtkTreeModel* self;
	GtkTreeIter* iter;
	gint column;
	GValue* result;

	// convert parameter self
	self = (GtkTreeModel*) _self;

	// convert parameter self
	iter = (GtkTreeIter*) _iter;

	// convert parameter column
	column = (gint) _column;

	// allocate blank GValue
	result = g_slice_new0(GValue);

	gtk_tree_model_get_value(self, iter, column, result);

	// cleanup parameter self
	
	// cleanup parameter iter

	// cleanup parameter column

	// and finally
	return (jlong) result;
}

JNIEXPORT jlong JNICALL
Java_org_gnome_gtk_GtkTreeModelOverride_gtk_1list_1store_1new
(
	JNIEnv* env,
	jclass cls
)
{
	GtkListStore* result;

	// call constructor
	result = gtk_list_store_new(2, G_TYPE_STRING, G_TYPE_INT);

	// and finally
	return (jlong) result;
}
