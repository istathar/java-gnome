/*
 * GtkMisc.c
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

#include "org_gnome_gtk_GtkMisc.h"

JNIEXPORT void JNICALL
Java_org_gnome_gtk_GtkMisc_gtk_1misc_1set_1alignment
  (JNIEnv *env, jclass cls, jlong _self, jfloat _xalign, jfloat _yalign)
{
	GtkMisc* self;
	gfloat xalign;
	gfloat yalign;

	// translate arg self
	self = (GtkMisc*) _self;

	// translate arg xalign
	xalign = _xalign;
	
	// translate arg yalign
	yalign = _yalign;

	// call function
	gtk_misc_set_alignment(self, xalign, yalign);

	// cleanup arg self
	
	// cleanup arg xalign
	
	// cleanup arg yalign
}

JNIEXPORT void JNICALL
Java_org_gnome_gtk_GtkMisc_gtk_1misc_1set_1padding
  (JNIEnv *env, jclass cls, jlong _self, jint _xpad, jint _ypad)
{
	GtkMisc* self;
	gint xpad;
	gint ypad;

	// translate arg self
	self = (GtkMisc*) _self;
	
	// translate arg xpad
	xpad = _xpad;
	
	// translate arg ypad
	ypad = _ypad;

	// call function
	gtk_misc_set_padding(self, xpad, ypad);

	// cleanup arg self
	
	// cleanup arg xalign
	
	// cleanup arg yalign
}
