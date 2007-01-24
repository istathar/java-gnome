/*
 * GtkBin.c
 *
 * Copyright (c) 2007 Operational Dynamics Consulting Pty Ltd
 * 
 * The code in this file, and the library it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" plus the "Classpath Exception" (you may link to this code as a
 * library into other programs provided you don't make a derivation of it).
 * See the LICENCE file for the terms governing usage and redistribution.
 *
 *                      THIS FILE WILL BE GENERATED CODE!
 *
 * To modify its contents or behaviour, either update the generation program,
 * change the information in the source defs file, or implement an override
 * for this class.
 */

#include <jni.h>
#include <gtk/gtk.h>

#include "org_gnome_gtk_GtkBin.h"

JNIEXPORT jlong JNICALL
Java_org_gnome_gtk_GtkBin_gtk_1bin_1get_1child
  (JNIEnv *env, jclass cls, jlong _self)
{
	GtkBin* self;
	GtkWidget* child;


	// translate arg self
	self = (GtkBin*) _self;

	// call function
	child = gtk_bin_get_child(self);

	// cleanup arg self

	// return pointer
	return (jlong) child;
}
