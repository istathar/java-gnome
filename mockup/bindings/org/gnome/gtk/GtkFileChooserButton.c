/*
 * GtkFileChooserButton.c
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

#include "org_gnome_gtk_GtkFileChooserButton.h"

JNIEXPORT jlong JNICALL
Java_org_gnome_gtk_GtkFileChooserButton_gtk_1file_1chooser_1button_1new
  (JNIEnv *env, jclass cls, jstring _title, jint _action)
{
	GtkWidget* widget;
	const gchar* title;
	GtkFileChooserAction action;
	
	// translate arg title
	title = (gchar*) (*env)->GetStringUTFChars(env, _title, NULL);
	
	// translate arg action
	action = (GtkFileChooserAction) _action;

	// call constructor
	widget = gtk_file_chooser_button_new(title, action);

	// cleanup arg title
	(*env)->ReleaseStringUTFChars(env, _title, title);
	
	// cleanup arg action

	// return address
	return (jlong) widget;
}
