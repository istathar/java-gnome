/*
 * bindings_java_threads.c
 *
 * Copyright (c) 2007 Operational Dynamics Consulting Pty Ltd and Others
 * 
 * The code in this file, and the library it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" plus the "Classpath Exception" (you may link to this code as a
 * library into other programs provided you don't make a derivation of it).
 * See the LICENCE file for the terms governing usage and redistribution.
 */

#include <jni.h>
#include <gdk/gdk.h>
#include "bindings_java.h"

/*
 * Permit multi threaded use of GTK by synchronizing access via "the GTK
 * main lock". We do this almost entirely on the Java side, except that a
 * mutex is a mutex, ultimately, so by overriding the default
 * implementations in gtk+'s gdk/gdk.c we can at least let other libraries
 * continue to function... (assuming they were written right, and assuming the
 * way we're using this is actually legal!)
 */
 
/* 
 * The technique of manually _releasing_ the GDK lock via gdk_threads_leave()
 * before conducting an invokation on the Java side (which we've used in 
 * bindings_java_signal.c) was encountered in Classpath's AWT peer. See
 * classpath/native/jni/gtk-peer/gnu_java_awt_peer_gtk_GtkToolkit.c; this is
 * a rather radical idea.
 */

static jobject lock;

/**
 * Save a reference to the lock object on the Java side, and then register
 * our custom lock functions. 
 */
void
bindings_java_threads_init
(
	JNIEnv *env,
	jobject obj
)
{
	lock = (*env)->NewGlobalRef(env, obj);

	gdk_threads_set_lock_functions(bindings_java_threads_lock, bindings_java_threads_unlock);
}

/**
 * Enter the monitor for our Java side GDK lock object. This is, strictly,
 * the opening half of a sychronized (obj) { ... } in Java, as expressed in
 * JNI.
 */
/*
 * Signature the prototype of the generic (*GCallback) prototype, meeting the
 * requirements of the first argument to gdk_threads_set_lock_functions()
 */
void
bindings_java_threads_lock
( 
)
{
	JNIEnv* env;
	
	env = bindings_java_getEnv();

	if ((*env)->MonitorEnter(env, lock) != JNI_OK) {
		g_critical("Error trying to get Java side GDK lock!?!");
	}
}

/**
 * Release the monitor (lock) held on our Java side GDK lock object.
 */
/*
 * Signature the prototype of the generic (*GCallback) prototype, meeting the
 * requirements of the second argument to gdk_threads_set_lock_functions()
 */
void
bindings_java_threads_unlock
( 
)
{
	JNIEnv* env;

	env = bindings_java_getEnv();

	if ((*env)->MonitorExit(env, lock) != JNI_OK) {
		g_critical("Error trying to release Java side GDK lock?!?");
	}
}
