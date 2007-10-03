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

/**
 * Called from
 *   org.gnome.gtk.GtkTreeModeOverride.gtk_list_store_new(String[])
 * called from
 *   org.gnome.gtk.GtkTreeModeOverride.createListStore(Class[])
 * called from
 *   org.gnome.gtk.ListStore.<init>(???)
 *
 * You should already have established Java side that ther array is bigger
 * that 0 elements before calling this.
 */
JNIEXPORT jlong JNICALL
Java_org_gnome_gtk_GtkTreeModelOverride_gtk_1list_1store_1new
(
	JNIEnv* env,
	jclass cls,
	jobjectArray _columns
)
{
	GtkListStore* result;
	gint num_columns;
	GType* columns; // GType[]
	gint i;
	jstring _name;
	const gchar* name;
	
	num_columns = (gint) (*env)->GetArrayLength(env, _columns);
	columns = g_newa(GType, num_columns);
		
	for (i = 0; i < num_columns; i++) {
		_name = (jstring) (*env)->GetObjectArrayElement(env, _columns, i);

		name = (const gchar*) (*env)->GetStringUTFChars(env, _name, NULL);
		if (name == NULL) {
			return 0L; // OutOfMemory already thrown
		}

		columns[i] = bindings_java_type_lookup(name);
		
		if (columns[i] == G_TYPE_INVALID) {
			bindings_java_throw(env, "Don't know how to map %s into a GType", name);
			return 0L;
		}

		(*env)->ReleaseStringUTFChars(env, _name, name);
		(*env)->DeleteLocalRef(env, _name);
	}

	// call constructor
	result = gtk_list_store_newv(num_columns, columns);

	// clean up of columns is automatic

	// and finally
	return (jlong) result;
}

JNIEXPORT void JNICALL
Java_org_gnome_gtk_GtkTreeModelOverride_gtk_1list_1store_1set_1reference
(
	JNIEnv *env,
	jclass cls,
	jlong _self,
	jlong _row,
	jint _column,
	jobject _reference
)
{
	GtkListStore* self;
	GtkTreeIter* row;
	gint column;
	gpointer reference, stored;

	// convert parameter self
	self = (GtkListStore*) _self;

	// convert parameter iter
	row = (GtkTreeIter*) _row;

	// convert parameter column
	column = (gint) _column;

	// convert parameter reference
	
	/* 
	 * increment ref count, and translate, then, if a value was already
	 * stored, drop a global reference to it. This would be inefficient
	 * in the case where you are storing the object that is already
	 * there, but is easier than maintaining a hash table of references.
	 * This all aligns properly because we map java.lang.Object to
	 * G_TYPE_POINTER in bindings_java_type() as called by our override
	 * constructor above.
	 */
	reference = (gpointer) (*env)->NewGlobalRef(env, _reference);
	stored = NULL;
	
	gtk_tree_model_get(GTK_TREE_MODEL(self), row, column, &stored, -1);
	if (stored != NULL) {
		(*env)->DeleteGlobalRef(env, (jobject) stored);
	}

	// call function
	gtk_list_store_set(self, row, column, reference, -1);

	// clean up
	
	// return address
}


JNIEXPORT jobject JNICALL
Java_org_gnome_gtk_GtkTreeModelOverride_gtk_1tree_1model_1get_1reference
(
	JNIEnv *env,
	jclass cls,
	jlong _self,
	jlong _row,
	jint _column
)
{
	GtkTreeModel* self;
	GtkTreeIter* row;
	gint column;
	gpointer result;

	// convert parameter self
	self = (GtkTreeModel*) GTK_TREE_MODEL((GtkListStore*) _self);

	// convert parameter iter
	row = (GtkTreeIter*) _row;

	// convert parameter column
	column = (gint) _column;

	// call function
	result = NULL;
	gtk_tree_model_get(self, row, column, &result, -1);

	// clean up
	
	// return address
	return (jobject) result;
}
