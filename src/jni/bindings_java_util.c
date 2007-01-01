/*
 * bindings_java_util.c
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

static JavaVM*	cachedJavaVM;

/**
 * Here we cache the JavaVM pointer so that we can use it to retrieve the
 * JNIEnv interface pointer whenever necessary.
 */
/*
 * A symbol by this name is automatically called when the library containing it
 * is loaded by a System.loadLibrary().
 */
JNIEXPORT jint JNICALL
JNI_OnLoad
(
	JavaVM *jvm,
	void *reserved
)
{
	if (jvm == NULL) {
		g_error("JavaVM pointer was NULL when initializing library");
	}
 	cachedJavaVM = jvm;
 	return JNI_VERSION_1_4;
}


/**
 * Since the JNIEnv pointer is specific to each thread, it is necessary to
 * retrieve it from the VM directly when we are dealing with arbitrary events
 * as opposed to local JNI calls. Returns NULL on failure.
 */
JNIEnv*
bindings_java_getEnv()
{
	JNIEnv* env;
	jint result;

	result = (*cachedJavaVM)->GetEnv(cachedJavaVM, (void **) &env, JNI_VERSION_1_4);
	if (env == NULL) {
		switch (result) {
		case JNI_EDETACHED:
			g_critical("Tried to get JNIEnv but this thread detached");
			// and attach?
			break;
			
		case JNI_EVERSION:
			g_error("Trying to get JNIEnv resulted in version error.");
			break;
		}
		return NULL;
	}
	return env;
}


/*
 * Inspired by code from "The Java Native Interface", section 6.1.2.
 * Modified in java-gnome 2.x by Andrew Cowie to fix a potential crasher.
 * And then improved for use here.
 * 
 * It turns out different Java implementations are finicky about the syntax
 * of the string used to lookup a class. "Ljava/lang/Blah;" makes GCJ
 * unhappy; "java.lang.Blah" makes Sun Java barf. The JNI standard actually
 * specifies "java/lang/Blah" only.
 *
 * If the class can't be found (or the above problem causes a misfire) then
 * ClassNotFoundException is raised by the FindClass() call, and null is
 * returned.
 *
 * We could probably check the syntax ourselves, but the class loader is
 * going to do so for us anyway.
 */
void
bindings_java_throwByName
(
	JNIEnv* env,
	const gchar* name,
	const gchar* msg
)
{
	jclass cls = (*env)->FindClass(env, name);

	if (cls != NULL) {
		(*env)->ThrowNew(env, cls, msg);
		/*
		 * And, since its valid, we need to free the local jclass ref that
		 * FindClass() gave us...
		 */
		(*env)->DeleteLocalRef(env, cls);
	}
}


/**
 * Utility function to just blow a generic RuntimeException in order
 * to propagate a failure back to the caller.
 */
void
bindings_java_throw
(
	JNIEnv* env,
	const char* fmt,
	...
)
{
	va_list args;
	
	const guint WIDTH = 200;
	gchar msg[WIDTH];
	static jclass cls = NULL;
	const gchar* name = "java/lang/RuntimeException";
			// "org/gnome/glib/NativeException" ?

	va_start(args, fmt);
	g_vsnprintf(msg, WIDTH, fmt, args);
	va_end(args);

	if (cls == NULL) {
		if (env == NULL) {
			g_error("Want to throw a %s but JNIEnv is NULL", name);
		}	
		
		cls = (*env)->FindClass(env, name);
		if (cls == NULL) {
			g_critical("Tried to throw a %s but calling FindClass() on that name failed.", name);
			return; 
		}
	}
	
	(*env)->ThrowNew(env, cls, msg);
    	
	(*env)->DeleteLocalRef(env, cls);
}


/**
 * Convert from a GType to a JNI signature
 */
/*
 * This piece of magic is inspired from the old 2.x code, and the technique
 * represents a useful block of knowledge. It turns out the old one was leaky
 * as hell, though. Here we just return constant strings.
 */
const gchar*
bindings_java_typeToSignature
(
	GType type
)
{
	/*
	 * the G_TYPE_FUNDAMENTAL macro returns "the fundamental type which
	 * is the ancestor of the argument ... roots of distinct inheritance
	 * hierarchies."
	 */
	switch(G_TYPE_FUNDAMENTAL(type)) {
	case G_TYPE_NONE:
		return "V";

	case G_TYPE_CHAR:
	case G_TYPE_UCHAR:
		return "C";

	case G_TYPE_BOOLEAN:
		return "Z";

	case G_TYPE_INT:
	case G_TYPE_UINT:
		return "I";

	case G_TYPE_ENUM:
		return "I";

	case G_TYPE_FLAGS:
		return "I";

	case G_TYPE_LONG:
	case G_TYPE_ULONG:
		return "J";

	case G_TYPE_FLOAT:
		return "F";

	case G_TYPE_DOUBLE:
		return "D";

	case G_TYPE_STRING:
		return "Ljava/lang/String;";

	case G_TYPE_BOXED:
		return "J";

	case G_TYPE_OBJECT:
		return "J";

	case G_TYPE_INVALID:
	default:
		g_critical("Don't know how to convert type %s to JNI signature", g_type_name(type));
		return NULL;
	}
}
