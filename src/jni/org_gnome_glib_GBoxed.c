/*
 * org_gnome_glib_GObject.c
 *
 * Copyright (c) 2006 Operational Dynamics 
 * See LICENCE file for usage and redistribution terms
 */

#include <jni.h>
#include "org_gnome_glib_GBoxed.h"

/*
 * Implements
 *   org.gnome.glib.GBoxed.g_boxed_free(long boxed)
 * called from
 *   org.gnome.glib.GBoxed.free(Boxed reference)
 * called from
 *   org.gnome.glib.Boxed.finalize()
 *
 * This is where we free a GBoxed if we're the owner of it.
 */

JNIEXPORT void JNICALL
Java_org_gnome_glib_GBoxed_g_1boxed_1free
	(JNIEnv *env, jclass cls, jlong _boxed, jlong _type)
{
	GType type;
	GBoxed boxed;
		
	boxed =	(GBoxed) _boxed;
	type =	(GType) _boxed;
	g_boxed_free(type, boxed);
}

