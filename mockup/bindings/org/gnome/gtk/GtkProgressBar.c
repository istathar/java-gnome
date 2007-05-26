/*
 * GtkProgressBar.c
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

#include "org_gnome_gtk_GtkProgressBar.h"

JNIEXPORT jlong JNICALL
Java_org_gnome_gtk_GtkProgressBar_gtk_1progress_1bar_1new
(
	JNIEnv *env,
	jclass cls
)
{
	GtkProgressBar* result;

	// call constructor
	result = (GtkProgressBar*) gtk_progress_bar_new();

	// return address
	return (jlong) result;
}

JNIEXPORT void JNICALL
Java_org_gnome_gtk_GtkProgressBar_gtk_1progress_1bar_1set_1text
(
	JNIEnv *env,
	jclass cls,
	jlong _self,
	jstring _text
)
{
	GtkProgressBar* self;
	const gchar* text;

	// translate arg self
	self = (GtkProgressBar*) _self;

	// translate arg text
	text = (const gchar*) (*env)->GetStringUTFChars(env, _text, NULL);
	if (text == NULL) {
		return; /* OutOfMemoryError already thrown */
	}

	// call function
	gtk_progress_bar_set_text(self, text);

	// cleanup arg self

	// cleanup arg text
	(*env)->ReleaseStringUTFChars(env, _text, text);
}

JNIEXPORT void JNICALL
Java_org_gnome_gtk_GtkProgressBar_gtk_1progress_1bar_1set_1fraction
(
	JNIEnv *env,
	jclass cls,
	jlong _self,
	jdouble _fraction
)
{
	GtkProgressBar* self;
	gdouble fraction;

	// translate arg self
	self = (GtkProgressBar*) _self;

	// translate arg fraction
	fraction = (gdouble) _fraction;

	// call function
	gtk_progress_bar_set_fraction(self, fraction);

	// cleanup arg self

	// cleanup arg fraction
}
