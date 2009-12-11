/*
 * Plumbing.c
 *
 * Copyright (c) 2009 Operational Dynamics Consulting Pty Ltd
 * 
 * The code in this file, and the library it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" plus the "Classpath Exception" (you may link to this code as a
 * library into other programs provided you don't make a derivation of it).
 * See the LICENCE file for the terms governing usage and redistribution.
 */

#include <jni.h>
#include <glib.h>
#include <gdk/gdk.h>
#include <gtk/gtk.h>

#include "bindings_java.h"
#include "org_gnome_glib_Plumbing.h"

/*
 * Implements
 *   org.gnome.glib.Plumbing.initializeNative(Object lock)
 * called from
 *   org.gnome.glib.Plumbing.static()
 */ 
JNIEXPORT void JNICALL
Java_org_gnome_glib_Plumbing_initializeNative
(
	JNIEnv *env,
	jclass cls,
	jobject _lock
)
{

	/*
	 * The call to g_threads_init() needs to be be the very first thing
	 * that happens in our use of GLib. We do that in the following call
	 */

	bindings_java_threads_init(env, _lock);
	bindings_java_logging_init();
}
