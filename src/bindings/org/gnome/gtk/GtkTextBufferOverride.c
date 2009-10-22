/*
 * GtkTextBufferOverride.c
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
#include <gtk/gtk.h>
#include "bindings_java.h"
#include "org_gnome_gtk_GtkTextBufferOverride.h"

JNIEXPORT void JNICALL
Java_org_gnome_gtk_GtkTextBufferOverride_g_1signal_1stop_1emission
(
	JNIEnv* env,
	jclass cls,
	jlong _self,
	jstring _name
)
{
	GtkTextBuffer* self;
	const gchar* name;

	// convert self
	self = (GtkTextBuffer*) _self;

	// convert name
	name = bindings_java_getString(env, _name);

	g_signal_stop_emission_by_name(self, name);

	// cleanup self

	// cleanup name
	bindings_java_releaseString(name);
}
