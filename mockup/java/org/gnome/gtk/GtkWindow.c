/*
 * GtkWindow.c
 *
 * Copyright (c) 2006 Operational Dynamics Consulting Pty Ltd and Others
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

#include "org_gnome_gtk_GtkWindow.h"

JNIEXPORT jlong JNICALL
Java_org_gnome_gtk_GtkWindow_gtk_1window_1new
  (JNIEnv *env, jclass cls, jint _type)
{
	GtkWindowType type;
	GtkWidget* window;

	// translate arg type
	type = (GtkWindowType) _type;

	// call constructor
	window = gtk_window_new(type);

	// cleanup arg type

	// return address
	return (jlong) window;
}

JNIEXPORT void JNICALL
Java_org_gnome_gtk_GtkWindow_gtk_1window_1set_1title
  (JNIEnv *env, jclass cls, jlong _self, jstring _title)
{
	GtkWindow* self;
	gchar* title;

	// translate arg self
	self = (GtkWindow*) _self;

	// translate arg title
	title = (gchar*) (*env)->GetStringUTFChars(env, _title, NULL);
	if (title == NULL) {
		return; /* OutOfMemoryError already thrown */
	}

	// call function
	gtk_window_set_title(self, title);

	// cleanup arg self

	// cleanup arg title
	(*env)->ReleaseStringUTFChars(env, _title, title);
}

JNIEXPORT void JNICALL
Java_org_gnome_gtk_GtkWindow_gtk_1window_1set_1default_1size
  (JNIEnv *env, jclass cls, jlong _self, jint _width, jint _height)
{
	GtkWindow* self;
	gint width, height;

	// translate arg self
	self = (GtkWindow*) _self;

	width = (gint) _width;
	height = (gint) _height;
	// call function
	gtk_window_set_default_size(self, width, height);

	// cleanup arg self

}
