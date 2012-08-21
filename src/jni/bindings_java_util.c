/*
 * java-gnome, a UI library for writing GTK and GNOME programs from Java!
 *
 * Copyright © 2006-2010 Operational Dynamics Consulting, Pty Ltd and Others
 *
 * The code in this file, and the program it is a part of, is made available
 * to you by its authors as open source software: you can redistribute it
 * and/or modify it under the terms of the GNU General Public License version
 * 2 ("GPL") as published by the Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GPL for more details.
 *
 * You should have received a copy of the GPL along with this program. If not,
 * see http://www.gnu.org/licenses/. The authors of this program may be
 * contacted through http://java-gnome.sourceforge.net/.
 *
 * Linking this library statically or dynamically with other modules is making
 * a combined work based on this library. Thus, the terms and conditions of
 * the GPL cover the whole combination. As a special exception (the
 * "Classpath Exception"), the copyright holders of this library give you
 * permission to link this library with independent modules to produce an
 * executable, regardless of the license terms of these independent modules,
 * and to copy and distribute the resulting executable under terms of your
 * choice, provided that you also meet, for each linked independent module,
 * the terms and conditions of the license of that module. An independent
 * module is a module which is not derived from or based on this library. If
 * you modify this library, you may extend the Classpath Exception to your
 * version of the library, but you are not obligated to do so. If you do not
 * wish to do so, delete this exception statement from your version.
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
	void* ptr = NULL;
	JavaVMAttachArgs args = { 0, };
	static int i = 0;
	jint result;

	result = (*cachedJavaVM)->GetEnv(cachedJavaVM, &ptr, JNI_VERSION_1_4);
	env = (JNIEnv*) ptr;
	if (env != NULL) {
		return env;
	}
	
	switch (result) {	
	case JNI_EDETACHED:
		args.version = JNI_VERSION_1_4;
		args.name = g_strdup_printf("NativeThread%d", i++);

		result = (*cachedJavaVM)->AttachCurrentThreadAsDaemon(cachedJavaVM, &ptr, &args);
		env = (JNIEnv*) ptr;
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
bindings_java_throwGlibException
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
	case G_TYPE_PARAM:
		return "J";

	/*
	 * This is a special case; we don't (and indeed cannot) actually
	 * handle a raw "pointer" as a type, but by marshalling it as a
	 * meaningless long we can at least wrap and ignore it on the Java
	 * side.
	 */

	case G_TYPE_POINTER:
		return "J";

	case G_TYPE_INVALID:
	default:
		g_printerr("Don't know how to convert type %s to JNI signature\n", g_type_name(type));
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
	jobject _name;
	const gchar* name;

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

	_name = (*env)->CallObjectMethod(env, obj, mid);
	if (_name == NULL) {
		(*env)->ExceptionDescribe(env);
		g_error("null?");
	}
	if ((*env)->ExceptionCheck(env)) {
		(*env)->ExceptionDescribe(env);
		g_error("No String");
	}

	name = bindings_java_getString(env, _name);
	if (name == NULL) {
		(*env)->ExceptionDescribe(env);
		g_error("OOM?");
	}
	if ((*env)->ExceptionCheck(env)) {
		(*env)->ExceptionDescribe(env);
		g_error("No conversion");
	}

	g_debug("obj.toString(): %s", name);
	bindings_java_releaseString(name);
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

	/*
	 * Whether or not to throw on WARNING here is an open question.
	 * Although the "--g-fatal-warnings" GTK option allows people to tell
	 * GLib to make warnings fatal, their idea of fatal is to print the
	 * WARNING and then to abort!
	 *
	 * So the alternatives open to us are to leave it to flow on to the
	 * default log handler, or to throw our FatalError.
	 *
	 * In every instance we've observed, seeing a WARNING from GTK has
	 * immediately indicated serious programmer error. So an Exception it
	 * is.
	 */
	
	switch (log_level) {
	case G_LOG_LEVEL_ERROR:
		level = "ERROR";
		break;

	case G_LOG_LEVEL_CRITICAL:
		level = "CRITICAL";
		break;

	case G_LOG_LEVEL_WARNING:
		level = "WARNING";
		break;

	default:
		g_log_default_handler(log_domain, log_level, message, user_data);
		return;
	}
	
	msg = g_strdup_printf("%s-%s\n%s", log_domain, level, message);

	bindings_java_throwByName(env, "org/gnome/glib/FatalError", msg);

	g_free(msg);

	/*
	 * We would happily throw our exception and be done with it, except
	 * that the Java VM messes with our good intentions: Exceptions which
	 * occur during finalizing are ignored.
	 *
	 * Since these messages are often the root cause problem, it's
	 * important to hear about them. So we output an alert.
 	 */

	g_printerr("DANGER: %s-%s, %s\n", log_domain, level, message);
	fflush(stderr);
}

void
bindings_java_logging_init
(
)
{
	g_log_set_default_handler(bindings_java_logging_handler, NULL);
}

/*
 * Convert a Java String object into a NULL-terminated UTF-8 string
 *
 * This serves as a replacement for JNI's GetStringUTFChars(). It uses GLib's
 * UTF-16 to UTF-8 conversion function, which theoretically is a compliant
 * implementation, unlike Java's.
 *
 * You must call bindings_java_releaseString() on the returned gchar*!
 */
const gchar*
bindings_java_getString
(
	JNIEnv* env,
	jstring _str
)
{
	const jchar* _pointer;
	const gunichar2* pointer;
	gchar* str;
	jsize _len;
	glong len;
	GError* error = NULL;

	if (_str == NULL) {
		return NULL;
	}

	/*
	 * This is strange; it's tempting to assume that we can wait for the
	 * NULL termination, but testing showed that there was sometimes junk
	 * characters at the end. It seems safest just to ask the VM how long
	 * the char[] is.
	 */

	_len = (*env)->GetStringLength(env, _str);
	len = (glong) _len;

	/*
	 * Get a pointer to the underlying character array. With any luck, the
	 * data will not be copied.
	 */
	// WARNING uses the JVM critical code path
	_pointer = (*env)->GetStringCritical(env, _str, NULL);
	if (G_UNLIKELY(_pointer == NULL)) {
		return NULL; // Java Exception already thrown
	}
	pointer = (const gunichar2*) _pointer;


	// call conversion function
	str = g_utf16_to_utf8(pointer, len, NULL, NULL, &error);

	(*env)->ReleaseStringCritical(env, _str, _pointer);
	// WARNING end critical section

	// cleanup
	if (G_UNLIKELY(error != NULL)) {
		bindings_java_throwGlibException(env, error);
		return NULL;
	}

	return str;
}

/*
 * Convert a NULL-terminated UTF-8 string into a Java String object.
 *
 * This serves as a replacement for JNI's NewStringUTF(), using GLib's
 * theoretically correct UTF-8 to UTF-16 conversion function, rather than
 * Java's internal non-compliant encoding.
 */
jstring
bindings_java_newString
(
	JNIEnv* env,
	const gchar* str
)
{
	gunichar2* pointer;
	const jchar* _pointer;
	jstring _result;
	glong num;
	GError* error = NULL;

	if (str == NULL) {
		return NULL;
	}

	// call conversion function
	pointer = g_utf8_to_utf16(str, -1, NULL, &num, &error);

	if (G_UNLIKELY(error != NULL)) {
		bindings_java_throwGlibException(env, error);
		return NULL;
	}

	// construct object
	_pointer = (const jchar*) pointer;
	_result = (*env)->NewString(env, _pointer, num);

	// cleanup
	g_free(pointer);

	return _result;
}

/*
 * Free a String as created by bindings_java_getString().
 *
 * This is largely just an exercise in getting us around the const qualifier in
 * const-gchar* types, but it has the benefit of keeping our String conversion
 * utilities symmetric with the JNI API.
 */
void
bindings_java_releaseString
(
	const gchar* str
)
{
	g_free((gpointer) str);
}
