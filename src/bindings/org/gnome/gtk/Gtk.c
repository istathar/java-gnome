/*
 * Gtk.c
 *
 * Copyright (c) 2006-2007 Operational Dynamics Consulting Pty Ltd
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
#include "org_gnome_gtk_Gtk.h"

/*
 * Implements
 *   org.gnome.gtk.Gtk.gtk_init(String[] args)
 * called from
 *   org.gnome.gtk.Gtk.init(String[] args)
 *
 * FIXME we still have to handle passing the args array through.
 */ 
JNIEXPORT void JNICALL
Java_org_gnome_gtk_Gtk_gtk_1init
(
	JNIEnv *env,
	jclass cls,
	jobject _lock,
	jobjectArray _args
)
{
	bindings_java_threads_init(env, _lock);
	
	g_thread_init(NULL);
	gdk_threads_init();
	
	g_set_prgname("java-gnome");
	// call function
	gtk_init(0, NULL);
}


/*
 * Implements
 *   org.gnome.gtk.Gtk.gtk_main()
 * called from
 *   org.gnome.gtk.Gtk.main()
 */
JNIEXPORT void JNICALL
Java_org_gnome_gtk_Gtk_gtk_1main
(
	JNIEnv *env,
	jclass cls
)
{
	// call function
	gtk_main();
}

/*
 * Implements
 *   org.gnome.gtk.Gtk.gtk_main_quit()
 * called from
 *   org.gnome.gtk.Gtk.mainQuit()
 */
JNIEXPORT void JNICALL
Java_org_gnome_gtk_Gtk_gtk_1main_1quit
(
	JNIEnv *env,
	jclass cls
)
{
	// call function
	gtk_main_quit();
}


/*
 * Implements
 *   org.gnome.gtk.Gtk.gtk_events_pending()
 * called from
 *   org.gnome.gtk.Gtk.eventsPending()
 */
JNIEXPORT jboolean JNICALL
Java_org_gnome_gtk_Gtk_gtk_1events_1pending
(
	JNIEnv *env,
	jclass cls
)
{
	gboolean result;
	
	// call function
	result = gtk_events_pending();
	
	// return result
	return (jboolean) result;	
}


/*
 * Implements
 *   org.gnome.gtk.Gtk.gtk_main_iteration_do()
 * called from
 *   org.gnome.gtk.Gtk.mainIterationDo()
 */
JNIEXPORT jboolean JNICALL
Java_org_gnome_gtk_Gtk_gtk_1main_1iteration_1do
(
	JNIEnv *env,
	jclass cls,
	jboolean _blocking
)
{
	gboolean blocking;
	gboolean result;
	
	// translate blocking
	blocking = (gboolean) _blocking;
	
	// call function
	result = gtk_main_iteration_do(blocking);
	
	// clean up blocking
	
	// return result
	return (jboolean) result;	
}
