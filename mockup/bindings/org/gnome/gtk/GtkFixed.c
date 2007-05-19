/*
 * GtkFixed.c
 *
 * Copyright (c) 2006 Srichand Pendyala
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

#include "org_gnome_gtk_GtkFixed.h"

JNIEXPORT jlong JNICALL
Java_org_gnome_gtk_GtkFixed_gtk_1fixed_1new
  (JNIEnv *env, jclass cls)
{
	GtkWidget* fixed;

	// call constructor
	fixed = gtk_fixed_new();

	// return address
	return (jlong) fixed;
}

JNIEXPORT void JNICALL
Java_org_gnome_gtk_GtkFixed_gtk_1fixed_1put
  (JNIEnv *env, jclass cls, jlong _self, jlong _widget, jint _x, jint _y)
{
	GtkFixed* self;
	GtkWidget* widget;
	gint x;
	gint y;

	// translate arg self
	self = (GtkFixed*) _self;
	
	// translate arg widget
	widget = (GtkWidget*) _widget;
	
	// translate arg x
	x = (gint) _x;

	// translate arg x
	y = (gint) _y;

	// call function
	gtk_fixed_put(self, widget, x, y);

	// cleanup arg self
	
	// cleanup arg widget
	
	// cleanup arg x

	// cleanup arg y
}

JNIEXPORT void JNICALL
Java_org_gnome_gtk_GtkFixed_gtk_1fixed_1move
  (JNIEnv *env, jclass cls, jlong _self, jlong _widget, jint _x, jint _y)
{
	GtkFixed* self;
	GtkWidget* widget;
	gint x; 
	gint y;

	// translate arg self
	self = (GtkFixed*) _self;
	
	// translate arg widget
	widget = (GtkWidget*) _widget;

	// translate arg width
	x = (gint) _x;
	
	// translate arg height
	y = (gint) _y;
	
	// call function
	gtk_fixed_move(self, widget, x, y);

	// cleanup arg self
	
	// cleanup arg width
	
	// cleanup arg height
}
