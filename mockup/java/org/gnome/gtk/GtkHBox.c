/*
 * GtkHBox.c
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

#include "org_gnome_gtk_GtkHBox.h"

JNIEXPORT jlong JNICALL
Java_org_gnome_gtk_GtkHBox_gtk_1hbox_1new
  (JNIEnv *env, jclass cls, jboolean _homogeneous, jint _spacing)
{
	GtkWidget* hbox;
	gboolean homogeneous;
	gint spacing;
	
	// translate arg homogeneous
	homogeneous = (gboolean) _homogeneous;
	
	// translate arg spacing
	spacing = (gint) _spacing;

	// call constructor
	hbox = gtk_hbox_new(homogeneous, spacing);

	// return address
	return (jlong) hbox;
}
