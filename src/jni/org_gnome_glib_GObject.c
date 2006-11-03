/*
 * org_gnome_glib_GObject.c
 *
 * Copyright (c) 2006 Operational Dynamics 
 * See LICENCE file for usage and redistribution terms
 */

#include <jni.h>
#include "org_gnome_glib_GObject.h"

/*
 * Implements
 *   org.gnome.glib.GObject.g_signal_connect(long instance, jobject handler)
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

