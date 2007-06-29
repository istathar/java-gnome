/*
 * bindings_java_glist.c
 *
 * Copyright (c) 2006 Operational Dynamics Consulting Pty Ltd and Others
 * 
 * The code in this file, and the library it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" plus the "Classpath Exception" (you may link to this code as a
 * library into other programs provided you don't make a derivation of it).
 * See the LICENCE file for the terms governing usage and redistribution.
 */

#include <jni.h>
#include <glib.h>
#include "bindings_java.h"

/*
 * Allocates and initializes a GList with the contents of a java
 * long array.
 * The returned list must be freed with g_list_free () when no more needed.
 */
GList* 
bindings_java_glist_from_java_array
(
	JNIEnv* env, 
	jlongArray _array
)
{
	GList* list;
	jlong* array;
	int i, size;
	
	list = g_list_alloc();
	
	size = (*env)->GetArrayLength(env, _array);
	if ( size == 0 ) {
		return list;
	}
	
	array = (jlong*) (*env)->GetLongArrayElements(env, _array, NULL);
	if (array == NULL) {
		return NULL; // Java Exception already thrown
	}
	
	for ( i = 0; i < size; ++i) {
		list = g_list_append( list, (gpointer) array[i] );
	}
	
	(*env)->ReleaseLongArrayElements(env, _array, array, JNI_ABORT);
	
	return list;
}
