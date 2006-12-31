/*
 * GtkWidget.c
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

#include "org_gnome_gtk_GtkWidget.h"

JNIEXPORT void JNICALL
Java_org_gnome_gtk_GtkWidget_gtk_1widget_1show
  (JNIEnv *env, jclass cls, jlong _self)
{
	GtkWidget* self;

	// translate arg self
	self = (GtkWidget*) _self;

	// call function
	gtk_widget_show(self);

	// cleanup arg self
}

JNIEXPORT void JNICALL
Java_org_gnome_gtk_GtkWidget_gtk_1widget_1show_1all
  (JNIEnv *env, jclass cls, jlong _self)
{
	GtkWidget* self;

	// translate arg self
	self = (GtkWidget*) _self;

	// call function
	gtk_widget_show_all(self);

	// cleanup arg self
}




