/*
 * GValue.c
 *
 * Copyright (c) 2006-2007 Operational Dynamics Consulting Pty Ltd
 * 
 * The code in this file, and the library it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" plus the "Classpath Exception" (you may link to this code as a
 * library into other programs provided you don't make a derivation of it).
 * See the LICENCE file for the terms governing usage and redistribution.
 */

#include <glib.h>
#include <glib-object.h>
#include <jni.h>
#include "org_gnome_glib_GValue.h"
#include "bindings_java.h"

/**
 * Implements
 *   org.gnome.glib.GValue.g_type_name(long value)
 * called from
 *   org.gnome.glib.GValue.typeName(long value)
 * called from
 *   org.gnome.glib.Plumbing.instanceFor(long pointer)
 * and also made available via
 *   org.gnome.glib.GValue.typeName(Value value)
 */
JNIEXPORT jstring JNICALL
Java_org_gnome_glib_GValue_g_1type_1name
(
	JNIEnv *env,
	jclass cls,
	jlong _value
)
{
	GValue* value;
	const gchar* name;

	// translate value
	value = (GValue*) _value;

	// call function & macro
	name = g_type_name(G_VALUE_TYPE(value));
	//g_print("GValue  g_type_name(%ld): %s\n", (long) G_VALUE_TYPE(value), name);

	// return name. Guard against NullPointerException by returning an
	// empty string instead of null
	return (*env)->NewStringUTF(env, (name != NULL ? name : "\0"));
}

/**
 * Implements
 *   org.gnome.glib.GValue.g_value_free(long value)
 * called from
 *   org.gnome.glib.GValue.free(Fundamental reference)
 * called from
 *   org.gnome.glib.Fundamental.release()
 *
 * This is where we free the chunk of memory containing the GValue pointer
 * (that we know we allocated with GSlice).
 */
JNIEXPORT void JNICALL
Java_org_gnome_glib_GValue_g_1value_1free
(
	JNIEnv *env,
	jclass cls,
	jlong _value
)
{
	GValue* value;
	GObject* object;
		
	value =	(GValue*) _value;
	
	/*
	 * Not 100% sure that this is the correct place to do this, but
	 * it seems that when you get a property with a GObject in it,
	 * there is a ref count to that object from the GValue. 
	 */
	if (G_VALUE_HOLDS_OBJECT(value)) {
		object = g_value_get_object(value);
		if (G_IS_OBJECT(object)) {
			g_object_unref(object);
		}
	}	
	
	g_slice_free(GValue, value);
}

/**
 * Implements
 *   org.gnome.glib.GValue.g_value_init(int i)
 * called from
 *   org.gnome.glib.GValue.createValue(int i)
 *
 * Allocate a GValue for a gint32 with GSlice, then initialize it and return
 * the pointer.
 */
JNIEXPORT jlong JNICALL
Java_org_gnome_glib_GValue_g_1value_1init__I
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


/**
 * Implements
 *   org.gnome.glib.GValue.g_value_init(boolean b)
 * called from
 *   org.gnome.glib.GValue.createValue(boolean b)
 *
 * Allocate a GValue for a gboolean with GSlice, then initialize it and return
 * the pointer.
 */
JNIEXPORT jlong JNICALL
Java_org_gnome_glib_GValue_g_1value_1init__Z
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

/**
 * Implements
 *   org.gnome.glib.GValue.g_value_init(float f)
 * called from
 *   org.gnome.glib.GValue.createValue(float f)
 *
 * Allocate a GValue for a gfloat with GSlice, then initialize it and return
 * the pointer.
 */
JNIEXPORT jlong JNICALL
Java_org_gnome_glib_GValue_g_1value_1init__F
(
	JNIEnv *env,
	jclass cls,
	jfloat _f
)
{
	gfloat f;
	GValue* value;
	
	f = (gfloat) _f;
		
	// allocate it and set to zeros, per what g_value_init requires
	value =	g_slice_new0(GValue);
	g_value_init(value, G_TYPE_FLOAT);
	
	// set the value
	g_value_set_boolean(value, f); 

	// return address
	return (jlong) value;
}

/**
 * Implements
 *   org.gnome.glib.GValue.g_value_init(String str)
 * called from
 *   org.gnome.glib.GValue.createValue(String str)
 *
 * Allocate a GValue for a char* with GSlice, then initialize it and return
 * the pointer.
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


/**
 * Implements
 *   org.gnome.glib.GValue.g_value_init(long obj)
 * called from
 *   org.gnome.glib.GValue.createValue(Object obj)
 *
 * Allocate a GValue for a GObject with GSlice, then initialize it and return
 * the pointer.
 */
JNIEXPORT jlong JNICALL
Java_org_gnome_glib_GValue_g_1value_1init__Lorg_gnome_glib_GObject_2
(
	JNIEnv *env,
	jclass cls,
	jlong _obj
)
{
	GObject* obj;
	GValue* value;
	
	// translate obj
	obj = (GObject*) _obj;
	
	// allocate and set to zeros, per what g_value_init requires
	value =	g_slice_new0(GValue);
	g_value_init(value, G_TYPE_OBJECT);

	// set the value	
	g_value_set_object(value, obj);

	// clean up obj

	// return address
	return (jlong) value;
}

/**
 * Implements
 *   org.gnome.glib.GValue.g_value_get_float(long value)
 * called from
 *   org.gnome.glib.GValue.getFloat(Value value)
 * called from
 *   org.gnome.glib.Object.getPropertyFloat(String name)
 *
 * Extract the gfloat value from a GValue of G_TYPE_FLOAT, returning the
 * primitive.
 */
JNIEXPORT jfloat JNICALL
Java_org_gnome_glib_GValue_g_1value_1get_1float
(
	JNIEnv* env,
	jclass cls,
	jlong _value
)
{
	GValue* value;
	gfloat result;

	// translate value
	value =	(GValue*) _value;
	if (!G_VALUE_HOLDS_FLOAT(value)) {
		bindings_java_throw(env, "You've asked for the float value of a GValue, but it's not a G_TYPE_FLOAT!");
		return 0.0f;
	}
	
	// call function
	result = g_value_get_float(value);
	
	// and return
	return (jfloat) result; 
}


/**
 * Implements
 *   org.gnome.glib.GValue.g_value_get_string(long value)
 * called from
 *   org.gnome.glib.GValue.getString(Value value)
 * called from
 *   org.gnome.glib.Object.getPropertyString(String name)
 *
 * Extract the string value from a GValue of G_TYPE_STRING, returning the
 * primitive (well, String) to be used as such.
 */
JNIEXPORT jstring JNICALL
Java_org_gnome_glib_GValue_g_1value_1get_1string
(
	JNIEnv* env,
	jclass cls,
	jlong _value
)
{
	GValue* value;
	const gchar* str; 

	// translate value
	value =	(GValue*) _value;
	if (!G_VALUE_HOLDS_STRING(value)) {
		bindings_java_throw(env, "You've asked for the string value of a GValue, but it's not a G_TYPE_STRING!");
		return NULL;
	}
	
	// call function
	str = g_value_get_string(value); 

	// and return	
	return (*env)->NewStringUTF(env, str);
}

/**
 * Implements
 *   org.gnome.glib.GValue.g_value_get_enum(long value)
 * called from
 *   org.gnome.glib.GValue.getEnum(Value value)
 * called from
 *   org.gnome.glib.Object.getPropertyEnum(String name)
 *
 * Extract the ordinal of an enum stored in a GValue of type G_TYPE_ENUM.
 */
JNIEXPORT jint JNICALL
Java_org_gnome_glib_GValue_g_1value_1get_1enum
(
	JNIEnv* env,
	jclass cls,
	jlong _value
)
{
	GValue* value;
	gint num; 

	// translate value
	value =	(GValue*) _value;
	if (!G_VALUE_HOLDS_ENUM(value)) {
		bindings_java_throw(env, "You've asked for the ordinal value of a GValue, but it's not a G_TYPE_ENUM!");
		return 0;
	}
	
	// call function
	num = g_value_get_enum(value); 

	// and return	
	return (jint) num;
}

/**
 * Implements
 *   org.gnome.glib.GValue.g_value_get_flags(long value)
 * called from
 *   org.gnome.glib.GValue.getFlags(Value value)
 * called from
 *   org.gnome.glib.Object.getPropertyFlags(String name)
 *
 * Extract the ordinal of an flag stored in a GValue of type G_TYPE_FLAGS.
 */
JNIEXPORT jint JNICALL
Java_org_gnome_glib_GValue_g_1value_1get_1flags
(
	JNIEnv* env,
	jclass cls,
	jlong _value
)
{
	GValue* value;
	guint num; 

	// translate value
	value =	(GValue*) _value;
	if (!G_VALUE_HOLDS_FLAGS(value)) {
		bindings_java_throw(env, "You've asked for the flags ordinal value of a GValue, but it's not a G_TYPE_FLAGS!");
		return 0;
	}
	
	// call function
	num = g_value_get_flags(value); 

	// and return	
	return (jint) num;
}

/**
 * Implements
 *   org.gnome.glib.GValue.g_value_get_enum(long value)
 * called from
 *   org.gnome.glib.GValue.getEnum(Value value)
 * called from
 *   org.gnome.glib.Object.getPropertyEnum(String name)
 *
 * Extract the ordinal of an enum stored in a GValue of type G_TYPE_OBJECT
 */
JNIEXPORT jlong JNICALL
Java_org_gnome_glib_GValue_g_1value_1get_1object
(
	JNIEnv* env,
	jclass cls,
	jlong _value
)
{
	GValue* value;
	GObject* object; 

	// translate value
	value =	(GValue*) _value;
	if (!G_VALUE_HOLDS_OBJECT(value)) {
		bindings_java_throw(env, "You've asked for the GObject within a GValue, but it's not a G_TYPE_OBJECT!");
		return 0L;
	}
	
	// call function
	object = g_value_get_object(value); 

	// and return	
	return (jlong) object;
}
