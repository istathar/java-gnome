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


/**
 * Connecting the 'visiblity-notify-signal' seems to require that the Widget
 * have a GdkWindow already. While this could be just a quick hack until we
 * have event masks exposed, we streamline things by adding the additional
 * step of realizing the Widget first if necessary.
 */
JNIEXPORT void JNICALL
Java_org_gnome_gtk_GtkWidgetOverride_gtk_1widget_1set_1events_1visibility
(
	JNIEnv* env,
	jclass cls,
	jlong _self
)
{
	GtkWidget* self;
	GdkEventMask mask;

	// convert parameter self
	self = (GtkWidget*) _self;
	if (self->window == NULL) {
		gtk_widget_realize(self);
		gtk_widget_hide(self);
	}

	mask = gdk_window_get_events(self->window);
	gdk_window_set_events(self->window, mask | GDK_VISIBILITY_NOTIFY_MASK);
}
