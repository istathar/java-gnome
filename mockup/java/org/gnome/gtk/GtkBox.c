/*
 * GtkBox.c
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

#include "org_gnome_gtk_GtkBox.h"

JNIEXPORT void JNICALL
Java_org_gnome_gtk_GtkBox_gtk_1box_1pack_1start
  (JNIEnv *env, jclass cls, jlong _self, jlong _widget, jboolean _expand, jboolean _fill, jint _padding)
{
	GtkBox* self;
	GtkWidget* widget;
	gboolean expand;
	gboolean fill;
	guint padding;

	// translate arg self
	self = (GtkBox*) _self;
	
	// translate arg widget
	widget = (GtkWidget*) _widget;

	// translate arg expand
	expand = (gboolean) _expand;
	
	// translate arg fill
	fill = (gboolean) _fill;
	
	// translate arg padding
	padding = (guint) _padding;

	// call function
	gtk_box_pack_start(self, widget, expand, fill, padding);

	// cleanup arg self
	
	// cleanup arg widget
	
	// cleanup arg expand

	// cleanup arg fill
	
	// cleanup arg padding
}
