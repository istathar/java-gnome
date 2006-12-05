/*
 * GObject.c
 *
 * Copyright (c) 2006 Operational Dynamics Consulting Pty Ltd and Others
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
#include "bindings_java.h"
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
 *   org.gnome.glib.GObject.g_signal_connect(long instance, Object handler, String name)
 * called from
 *   org.gnome.glib.Plumbing.connectSignal(Object instance, Signal handler, String name)
 * called from
 *   <generated package scope classes>.connect(Objet instance, Signal handler)
 *
 * This is where the magic to create a GClosure and hook it up to the GSignal
 * handling mechanisms is taken care of. A reference is created to the passed
 * Java object which is used as the callback when the signal is fired.
 */
JNIEXPORT void JNICALL
Java_org_gnome_glib_GObject_g_1signal_1connect
(
	JNIEnv *env,
	jclass cls,
	jlong _instance,
	jobject _handler,
	jobject _receiver,
	jstring _name
)
{
	GObject* instance;
  	gchar* name;

  	guint id;
  	GQuark detail = 0;
  	GClosure* closure;
  	gboolean ok;

	// translate instance  	
  	instance = (GObject*) _instance;

	// translate the signal name
	name = (gchar*) (*env)->GetStringUTFChars(env, _name, NULL);	
	
	/*
	 * Lookup the signal information. We use this rather than g_signal_lookup() because
	 * it allows us to sidestep the issue of detailed signal names.
	 */

	ok = g_signal_parse_name(name, G_OBJECT_TYPE(instance), &id, &detail, TRUE);
	
	if (!ok) {
		bindings_java_throw(env, "Unknown signal name %s for object %s", name, G_OBJECT_TYPE_NAME(instance));
    		return;
  	}
  	
  	closure = bindings_java_closure_new(env, instance, _handler, (jclass) _receiver, name, id);
  	if (closure == NULL) {
  		// and an exception has already been thrown
	    	return;
  	}

	// returns the handler id, but we don't need it.
	g_signal_connect_closure_by_id(instance, id, detail, closure, FALSE);
	
	// cleanup. Not really necessary as will happen automatically in a moment.
	(*env)->ReleaseStringUTFChars(env, _name, name);
}

/*
 * Implements
 *   org.gnome.glib.GObject.g_object_unref(long reference)
 * called from
 *   org.gnome.glib.GObject.unref(Object reference)
 * called when
 *   org.gnome.glib.Object.release()
 * is invoked by a finalizer.
 */
JNIEXPORT void JNICALL 
Java_org_gnome_glib_GObject_g_1object_1unref
(
	JNIEnv* env,
	jclass cls,
	jlong _reference
)
{
	GObject* reference;
	
	// translate reference
	reference = (GObject*) _reference;
	
	// call function
	g_object_unref(reference);
	
	/*
	 * Which should, incidentally, dispose of this GObject if we're the
	 * only one still holding a reference count to it. See discussion at
	 * the JavaDoc for org.gnome.glib.Object's release() method. 
	 */
}
