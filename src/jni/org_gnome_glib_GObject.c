/*
 * org_gnome_glib_GObject.c
 *
 * Copyright (c) 2006 Operational Dynamics 
 * See LICENCE file for usage and redistribution terms
 */

#include <glib.h>
#include <glib-object.h>
#include <jni.h>
#include "org_gnome_glib_GObject.h"

/*
 * Implements
 *   org.gnome.glib.GObject.g_object_set_property(long instance, String name, long value)
 * called from
 *   org.gnome.glib.GObject.setProperty(Object instance, String name, Value value)
 */
JNIEXPORT void JNICALL
Java_org_gnome_glib_GObject_g_1object_1set_1property
(
	JNIEnv *env,
	jclass cls,
	jlong _instance,
	jstring _name,
	jlong _value
)
{
	GObject* instance;
	gchar* name;
	GValue* value;
	
	// translate instance
	instance = (GObject*) _instance;
	
	// translate name
	name = (gchar*) (*env)->GetStringUTFChars(env, _name, NULL);
	if (name == NULL) {
		return; /* OutOfMemoryError already thrown */
	}
	
	// translate value
	value = (GValue*) _value;
	
	// call	method
	g_object_set_property(instance, name, value);

	// clean up name
	(*env)->ReleaseStringUTFChars(env, _name, name);
}



/*
 * Implements
 *   org.gnome.glib.GObject.g_signal_connect(long instance, Object handler)
 * called from
 *   org.gnome.glib.Plumbing.connectSignal(Object instance, Signal handler)
 * called from
 *   <generated package scope classes>.connect(Signal handler)
 *
 * This is where the magic to create a GClosure and hook it up to the GSignal
 * handling mechanisms is taken care of. A reference is created to the passed
 * Java object which is used as the callback when the signal is fired.
 */

JNIEXPORT void JNICALL
Java_org_gnome_glib_GObject_g_1signal_1connect
	(JNIEnv *env, jclass cls, jlong _instance, jobject _handler)
{
	return;
}

