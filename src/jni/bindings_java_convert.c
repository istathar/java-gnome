/*
 * bindings_java_convert.c
 *
 * Copyright (c) 2007-2009 Operational Dynamics Consulting Pty Ltd, and Others
 * 
 * The code in this file, and the library it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" plus the "Classpath Exception" (you may link to this code as a
 * library into other programs provided you don't make a derivation of it).
 * See the LICENCE file for the terms governing usage and redistribution.
 */

/*
 * Utility functions for handling the conversion of GList and friends
 */

#include <jni.h>
#include <glib.h>
#include "bindings_java.h"

/*
 * Allocates and initializes a GList with the contents of a java long array.
 * The returned list must be freed with g_list_free () when no longer needed.
 */
GList* 
bindings_java_convert_jarray_to_glist
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
	
	for (i = 0; i < size; ++i) {
		list = g_list_append(list, (gpointer) array[i]);
	}
	
	(*env)->ReleaseLongArrayElements(env, _array, array, JNI_ABORT);
	
	return list;
}

/*
 * Allocates and initializes a GSList with the contents of a java long array.
 * The returned list must be freed with g_slist_free() when no longer needed.
 */
GSList* 
bindings_java_convert_jarray_to_gslist
(
	JNIEnv* env, 
	jlongArray _array
)
{
	GSList* list;
	jlong* array;
	int i, size;
	
	list = g_slist_alloc();
	
	size = (*env)->GetArrayLength(env, _array);
	if ( size == 0 ) {
		return list;
	}
	
	array = (jlong*) (*env)->GetLongArrayElements(env, _array, NULL);
	if (array == NULL) {
		return NULL; // Java Exception already thrown
	}
	
	for (i = 0; i < size; ++i) {
		list = g_slist_append(list, (gpointer) array[i]);
	}
	
	(*env)->ReleaseLongArrayElements(env, _array, array, JNI_ABORT);
	
	return list;
}

jlongArray 
bindings_java_convert_glist_to_jarray
(
	JNIEnv* env, 
	GList* list
)
{
	jlongArray _array;
	jlong* array;
	int i, size;
	
	if (list == NULL) {
		size = 0;
	} else {
		size = g_list_length(list);
	}
	
	_array = (*env)->NewLongArray(env, size);
	
	if (size == 0) {
		return _array;
	}
	
	array = (jlong*) (*env)->GetLongArrayElements(env, _array, NULL);
	if (array == NULL) {
		return NULL; // Java Exception already thrown
	}
	
	for (i = 0; i < size; ++i) {
		array[i] = (jlong) list->data;
		list = list->next;
	}
	
	(*env)->ReleaseLongArrayElements(env, _array, array, 0);
	
	return _array;
}

jlongArray 
bindings_java_convert_gslist_to_jarray
(
	JNIEnv* env, 
	GSList* list
)
{
	jlongArray _array;
	jlong* array;
	int i, size;
	
	if (list == NULL) {
		size = 0;
	} else {
		size = g_slist_length(list);
	}
	
	_array = (*env)->NewLongArray(env, size);
	
	if (size == 0) {
		return _array;
	}
	
	array = (jlong*) (*env)->GetLongArrayElements(env, _array, NULL);
	if (array == NULL) {
		return NULL; // Java Exception already thrown
	}
	
	for (i = 0; i < size; ++i) {
		array[i] = (jlong) list->data;
		list = list->next;
	}
	
	(*env)->ReleaseLongArrayElements(env, _array, array, 0);
	
	return _array;
}

gpointer* 
bindings_java_convert_jarray_to_gpointer
(
	JNIEnv* env, 
	jlongArray _array
)
{
	gpointer* ptrs;
	jlong* array;
	int i, size;
	
	size = (*env)->GetArrayLength(env, _array);
	if ( size == 0 ) {
		//FIXME mmm, what if we just what an empty array?
		return NULL;
	}
	
	ptrs = g_malloc(size * sizeof(gpointer));
	if (ptrs == NULL) {
		return NULL; // throw MemoryError??
	}
	
	array = (jlong*) (*env)->GetLongArrayElements(env, _array, NULL);
	if (array == NULL) {
		return NULL; // Java Exception already thrown
	}
	
	for (i = 0; i < size; ++i) {
		ptrs[i] = (gpointer) array[i];
	}
	
	(*env)->ReleaseLongArrayElements(env, _array, array, JNI_ABORT);
	
	return ptrs;
}

void 
bindings_java_convert_gpointer_to_jarray
(
	JNIEnv* env, 
	gpointer* ptrs, 
	jlongArray _array
)
{
	jlong* array;
	int i, size;
	
	size = (*env)->GetArrayLength(env, _array);
	if ( size == 0 ) {
		return;
	}
	
	array = (jlong*) (*env)->GetLongArrayElements(env, _array, NULL);
	if (array == NULL) {
		return; // Java Exception already thrown
	}
	
	for (i = 0; i < size; ++i) {
		array[i] = (jlong) ptrs[i];
	}
	
	(*env)->ReleaseLongArrayElements(env, _array, array, 0);
	
	// and finally free ptrs
	g_free(ptrs);
}

jobjectArray 
bindings_java_convert_gchararray_to_jarray
(
	JNIEnv* env, 
	const gchar** array
)
{
	jobjectArray _array;
	jclass strClass;
	int i, size;
	
	size = 0;
	
	if (array == NULL) {
		return NULL;
	}
	
	/*
	 * In Gtk+, all returning gchar* arrays are NULL-terminated
	 */
	while (array[size] != NULL) ++size; 
	if ( size == 0 ) {
		// FIXME, maybe a 0 length array is better
		return NULL;
	}
	
	strClass = (*env)->FindClass(env, "java/lang/String");
	if (strClass == NULL) {
		return NULL; // Java Exception already thrown
	}
	
	_array = (*env)->NewObjectArray(env, size, strClass, NULL);
	if (_array == NULL) {
		return NULL; // Java Exception already thrown
	}
	
	for (i = 0; i < size; ++i) {
		jstring str = bindings_java_newString(env, array[i]);
		(*env)->SetObjectArrayElement(env, _array, i, (jobject) str);
		(*env)->DeleteLocalRef(env, str);
	}
	
	(*env)->DeleteLocalRef(env, strClass);
	return _array;
}

gchar** 
bindings_java_convert_strarray_to_gchararray
(
	JNIEnv* env, 
	jobjectArray _array 
)
{
	gchar** array;
	int i, size;
	
	size = (*env)->GetArrayLength(env, _array);
	if ( size == 0 ) {
		//FIXME mmm, what if we just pass an empty array?
		return NULL;
	}
	
	/*
	 * Most gchar** arrays in Gtk+ are NULL-terminated. But in java it
	 * has no sense force users to NULL-terminate an array. Thus, we always
	 * allocate an extra element that we will set to NULL.
	 */
	array = g_malloc((size+1) * sizeof(gchar*));
	if (array == NULL) {
		return NULL; // throw MemoryError??
	}
	
	for (i = 0; i < size; ++i) {
		jstring str = (jstring) (*env)->GetObjectArrayElement(env, _array, i);
		const gchar* cstr = bindings_java_getString(env, str);
		array[i] = g_strdup(cstr);
		bindings_java_releaseString(cstr);
		(*env)->DeleteLocalRef(env, str);
	}
	array[size] = NULL;
	
	return array;
}

void 
bindings_java_convert_gchararray_to_strarray
(
	JNIEnv* env, 
	gchar** array, 
	jobjectArray _array
)
{
	/* 
	 * FIXME
	 * For now, we don't support string arrays that are filled by Gtk+,
	 * other than return parameters (managed in above function
	 * bindings_java_convert_gchararray_to_jarray). 
	 * Thus, this just free the C array. It's supposed that this is an array
	 * previously allocated in bindings_java_convert_strarray_to_gchararray.
	 * Take care that this can lead to failures in methods like
	 * gtk_image_get_icon_name, where the returning strings are hanled by Gtk+.
	 */
	// array needs to be NULL-terminated
	g_strfreev(array);
}
