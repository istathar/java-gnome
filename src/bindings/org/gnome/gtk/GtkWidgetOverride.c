/*
 * GtkWidget.c
 *
 * Copyright (c) 2007 Operational Dynamics Consulting Pty Ltd
 *
 * The code in this file, and the library it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" plus the "Classpath Exception" (you may link to this code as a
 * library into other programs provided you don't make a derivation of it).
 * See the LICENCE file for the terms governing usage and redistribution.
 */

#include <jni.h>
#include <gtk/gtk.h>
#include "bindings_java.h"
#include "org_gnome_gtk_GtkWidgetOverride.h"

JNIEXPORT jlong JNICALL
Java_org_gnome_gtk_GtkWidgetOverride_gtk_1widget_1get_1window
(
	JNIEnv* env,
	jclass cls,
	jlong _self
)
{
	GdkWindow* result;
	GtkWidget* self;

	// convert parameter self
	self = (GtkWidget*) _self;

	result = self->window;

	// cleanup parameter self

	// and finally
	return (jlong) result;
}
