/*
 * org_gnome_glib_GObject.c
 *
 * Copyright (c) 2006 Operational Dynamics 
 * See LICENCE file for usage and redistribution terms
 */

#include <glib.h>
#include <glib-object.h>
#include <jni.h>
#include "org_gnome_glib_GValue.h"

/*
 * Implements
 *   org.gnome.glib.GValue.g_slice_free(long value)
 * called from
 *   org.gnome.glib.GValue.free(Fundamental reference)
 * called from
 *   org.gnome.glib.Fundamental.release()
 *
 * This is where we free an chunk of memory that we know we allocated with GSlice.
 */

JNIEXPORT void JNICALL
Java_org_gnome_glib_GBoxed_g_1slice_1free
	(JNIEnv *env, jclass cls, jlong _value)
{
	GValue* value;
		
	value =	(GValue*) _value;
	
	g_slice_free(GValue, value);
}

/*
 * Implements
 *   org.gnome.glib.GValue.g_value_init(int i)
 * called from
 *   org.gnome.glib.GValue.createValue(int i)
 * called from
 *   org.gnome.glib.IntegerValue.<init>(int i);
 *
 * Allocate a GValue for a boolean with GSlice, then initialize it and return the pointer.
 */
JNIEXPORT jlong JNICALL
Java_org_gnome_glib_GBoxed_g_1value_1init__I
(
	JNIEnv *env,
	jclass cls,
	jint _i
)
{
	gint32 i;
	GValue* value;
	
	// translate arg
	i = (gint32) _i;
		
	// allocate it and set to zeros, per what g_value_init requires
	value =	g_slice_new0(GValue);
	g_value_init(value, G_TYPE_INT);
	
	// set the value
	g_value_set_int(value, i); 

	// return address
	return (jlong) value;
}


/*
 * Implements
 *   org.gnome.glib.GValue.g_value_init(boolean b)
 * called from
 *   org.gnome.glib.GValue.createValue(boolean b)
 * called from
 *   org.gnome.glib.BooleanValue.<init>(boolean b);
 *
 * Allocate a GValue for a boolean with GSlice, then initialize it and return the pointer.
 */
JNIEXPORT jlong JNICALL
Java_org_gnome_glib_GBoxed_g_1value_1init__Z
(
	JNIEnv *env,
	jclass cls,
	jboolean _b
)
{
	gboolean b;
	GValue* value;
	
	b = (gboolean) _b;
		
	// allocate it and set to zeros, per what g_value_init requires
	value =	g_slice_new0(GValue);
	g_value_init(value, G_TYPE_BOOLEAN);
	
	// set the value
	g_value_set_boolean(value, b); 

	// return address
	return (jlong) value;
}

/*
 * Implements
 *   org.gnome.glib.GValue.g_value_init(String str)
 * called from
 *   org.gnome.glib.GValue.createValue(String str)
 * called from
 *   org.gnome.glib.StringValue.<init>(String str);
 *
 * Allocate a GValue for a char* with GSlice, then initialize it and return the pointer.
 */
JNIEXPORT jlong JNICALL
Java_org_gnome_glib_GValue_g_1value_1init__Ljava_lang_String_2
(
	JNIEnv *env,
	jclass cls,
	jstring _str
)
{
	gchar* str;
	GValue* value;
	
	// translate
	str = (gchar*) (*env)->GetStringUTFChars(env, _str, NULL);
	if (str == NULL) {
		return 0; /* OutOfMemoryError already thrown */
	}
	
	// allocate and set to zeros, per what g_value_init requires
	value =	g_slice_new0(GValue);
	g_value_init(value, G_TYPE_STRING);

	// set the value	
	g_value_set_string(value, str); 

	// clean up
	(*env)->ReleaseStringUTFChars(env, _str, str);

	// return address
	return (jlong) value;
}
