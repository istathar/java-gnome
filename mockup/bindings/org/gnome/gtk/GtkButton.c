/*
 * GtkButton.c
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

#include "org_gnome_gtk_GtkButton.h"

JNIEXPORT jlong JNICALL
Java_org_gnome_gtk_GtkButton_gtk_1button_1new
  (JNIEnv *env, jclass cls)
{
	GtkWidget* button;

	// call constructor
	button = gtk_button_new();

	// return address
	return (jlong) button;
}

JNIEXPORT jlong JNICALL
Java_org_gnome_gtk_GtkButton_gtk_1button_1new_1with_1label
  (JNIEnv *env, jclass cls, jstring _label)
{
	GtkWidget* button;
	gchar* label;
	//
	// translate arg label
	label = (gchar*) (*env)->GetStringUTFChars(env, _label, NULL);
	if (label == NULL) {
		return 0; /* OutOfMemoryError already thrown */
	}

	// call constructor
	button = gtk_button_new_with_label(label);

	// cleanup arg label
	(*env)->ReleaseStringUTFChars(env, _label, label);

	// return address
	return (jlong) button;
}

JNIEXPORT void JNICALL
Java_org_gnome_gtk_GtkButton_gtk_1button_1set_1label
  (JNIEnv *env, jclass cls, jlong _self, jstring _label)
{
	GtkButton* self;
	const gchar* label;

	// translate arg self
	self = (GtkButton*) _self;

	// translate arg label
	label = (gchar*) (*env)->GetStringUTFChars(env, _label, NULL);
	if (label == NULL) {
		return; /* OutOfMemoryError already thrown */
	}

	// call function
	gtk_button_set_label(self, label);

	// cleanup arg self

	// cleanup arg label
	(*env)->ReleaseStringUTFChars(env, _label, label);
}

JNIEXPORT jstring JNICALL
Java_org_gnome_gtk_GtkButton_gtk_1button_1get_1label
  (JNIEnv *env, jclass cls, jlong _self)
{
	GtkButton* self;
	const gchar* label;

	// translate arg self
	self = (GtkButton*) _self;

	// call function
	label = gtk_button_get_label(self);

	// cleanup arg self

	// return string
	return (*env)->NewStringUTF(env, label);
}


JNIEXPORT void JNICALL
Java_org_gnome_gtk_GtkButton_gtk_1button_1set_1relief
  (JNIEnv *env, jclass cls, jlong _self, jint _newstyle)
{
	GtkButton* self;
	GtkReliefStyle newstyle;

	// translate arg self
	self = (GtkButton*) _self;

	// translate arg newstyle
	newstyle = (GtkReliefStyle) _newstyle;

	// call function
	gtk_button_set_relief(self, newstyle);

	// cleanup arg self

	// cleanup arg newstyle
}
