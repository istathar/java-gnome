/*
 * bindings_java_util.c
 *
 * Copyright (c) 2006-2008 Operational Dynamics Consulting Pty Ltd and Others
 * 
 * The code in this file, and the library it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" plus the "Classpath Exception" (you may link to this code as a
 * library into other programs provided you don't make a derivation of it).
 * See the LICENCE file for the terms governing usage and redistribution.
 */

#include <jni.h>
#include <glib.h>
#include <stdlib.h>
#include <stdio.h>
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
	JNIEnv* env = NULL;
	JavaVMAttachArgs args = { 0, };
	static int i = 0; 
	jint result;

	result = (*cachedJavaVM)->GetEnv(cachedJavaVM, (void **) &env, JNI_VERSION_1_4);
	if (env != NULL) {
		return env;
	}
	
	switch (result) {	
	case JNI_EDETACHED:
		args.version = JNI_VERSION_1_4;
		args.name = g_strdup_printf("NativeThread%d", i++);

		result = (*cachedJavaVM)->AttachCurrentThreadAsDaemon(cachedJavaVM, (void **) &env, &args);
		if ((result == JNI_OK) && (env != NULL)) {
			g_free(args.name);
			return env;
		}
		
		g_printerr("\nTried to get JNIEnv but thread detached and attempt to attach failed.\n");
		break;
	
	case JNI_EVERSION:
		g_printerr("Trying to get JNIEnv resulted in version error.\n");
		break;
	}

	fflush(stderr);
	exit(2);

	return NULL;
}


/**
 * Throw an exception, naming the Exception or Error class to be thrown. If
 * an Exception is already pending this call will be a no-op.
 */
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
	jclass cls = NULL;

	if (env == NULL) {
		g_printerr("Want to throw a %s but JNIEnv is NULL\n", name);
		return;
	}

	/*
	 * Somewhat ugly hack: Sun's JVM segfaults (!) if you try to throw
	 * another Exception without having first handled, propegated or
	 * otherwise cleared an existing one. The sooner we get back to Java,
	 * the better. 
	 */

	if ((*env)->ExceptionOccurred(env)) {
		return;
	}

	/*
	 * Look up the class. We could cache these, but really, Exceptions
	 * out of the native layer are rare and, in most cases, terminal. If
	 * profiling shows a hot spot on this, then cache the lookup and
	 * create a call which takes a jclass.
	 */

	cls = (*env)->FindClass(env, name);
	if (cls == NULL) {
		g_printerr("Tried to throw a %s but calling FindClass() on that name failed.\n", name);
		return;
	}

	(*env)->ThrowNew(env, cls, msg);

	(*env)->DeleteLocalRef(env, cls);
}


/**
 * Utility function to just blow our generic FatalError throwable in order to
 * propagate a failure back to the caller. While you could, notionally, catch
 * such a thing, we define a condition of this nature coming out of our
 * native code to be unrecoverable, and hence the java.lang.Error subclass.
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
	
	gchar* msg;
	const gchar* name = "org/freedesktop/bindings/FatalError";

	va_start(args, fmt);
	msg = g_strdup_vprintf(fmt, args);
	va_end(args);

	bindings_java_throwByName(env, name, msg);
	
	g_free(msg);
}

void 
bindings_java_throw_gerror
(
	JNIEnv* env, 
	GError* error
)
{
	const gchar* name = "org/gnome/glib/GlibException";
	
	bindings_java_throwByName(env, name, error->message);
	
	g_error_free(error);
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
	case G_TYPE_INTERFACE:
		return "J";

	case G_TYPE_INVALID:
	default:
		g_critical("Don't know how to convert type %s to JNI signature", g_type_name(type));
		return NULL;
	}
}

void
bindings_java_debug
(
	JNIEnv* env,
	jobject obj
)
{
	jclass cls;
	jmethodID mid = NULL;
	jobject str;
	gchar* name;

	cls = (*env)->FindClass(env, "java/lang/Object");
	if ((*env)->ExceptionCheck(env)) {
		(*env)->ExceptionDescribe(env);
		g_error("No jclass?");
	}

	mid = (*env)->GetMethodID(env, cls, "toString", "()Ljava/lang/String;");
	if ((*env)->ExceptionCheck(env)) {
		(*env)->ExceptionDescribe(env);
		g_error("No methodID?");
	}

	str = (*env)->CallObjectMethod(env, obj, mid);
	if (str == NULL) {
		(*env)->ExceptionDescribe(env);
		g_error("null?");
	}
	if ((*env)->ExceptionCheck(env)) {
		(*env)->ExceptionDescribe(env);
		g_error("No String");
	}

	name = (gchar*) (*env)->GetStringUTFChars(env, str, NULL);
	if (name == NULL) {
		(*env)->ExceptionDescribe(env);
		g_error("OOM?");
	}
	if ((*env)->ExceptionCheck(env)) {
		(*env)->ExceptionDescribe(env);
		g_error("No conversion");
	}

	g_debug("obj.toString(): %s", name);
}


/*
 * Meets the signature of (*GLogFunc), as used by the first parameter of
 * g_log_set_default_handler().
 */
static void
bindings_java_logging_handler
(
	const gchar *log_domain,
	GLogLevelFlags log_level,
	const gchar *message,
	gpointer user_data
)
{
	JNIEnv* env;
	gchar* level;
	gchar* msg;
	
	env = bindings_java_getEnv();
	
	switch (log_level) {
	case G_LOG_LEVEL_ERROR:
		level = "ERROR";
		break;

	case G_LOG_LEVEL_CRITICAL:
		level = "CRITICAL";
		break;

	case G_LOG_LEVEL_WARNING:
		/*
		 * Whether or not to throw on WARNING here is an open
		 * question. Although the "--g-fatal-warnings" GTK option
		 * allows people to tell GLib to make warnings fatal, their
		 * idea of fatal is to print the WARNING and then to abort!
		 * 
		 * So the alternatives open to us are to leave it to flow on
		 * to the default log handler, or to throw our FatalError.
		 * During development we're going to have this on. Whether we
		 * take it out for releases remains to be decided.
		 */
#if TRUE
		level = "WARNING";
		break;
#endif

	default:
		g_log_default_handler(log_domain, log_level, message, user_data);
		return;
	}
	
	msg = g_strdup_printf("%s-%s\n%s", log_domain, level, message);

	bindings_java_throwByName(env, "org/gnome/glib/FatalError", msg);

	g_free(msg);
}

void
bindings_java_logging_init
(
)
{
	g_log_set_default_handler(bindings_java_logging_handler, NULL);
}
