/*
 * GtkContainer.c
 *
 * Copyright (c) 2006 Operational Dynamics Consulting Pty Ltd
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

#include "org_gnome_gtk_GtkContainer.h"

JNIEXPORT void JNICALL
Java_org_gnome_gtk_GtkContainer_gtk_1container_1add
  (JNIEnv *env, jclass cls, jlong _self, jlong _child)
{
	GtkContainer* self;
	GtkWidget* child;


	// translate arg self
	self = (GtkContainer*) _self;

	// translate arg child
	child = (GtkWidget*) _child;

	// call function
	gtk_container_add(self, child);

	// cleanup arg child

	// cleanup arg self
}
