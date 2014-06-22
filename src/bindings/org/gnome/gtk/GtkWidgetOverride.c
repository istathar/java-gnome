/*
 * java-gnome, a UI library for writing GTK and GNOME programs from Java!
 *
 * Copyright © 2007-2014 Operational Dynamics Consulting, Pty Ltd and Others
 *
 * The code in this file, and the program it is a part of, is made available
 * to you by its authors as open source software: you can redistribute it
 * and/or modify it under the terms of the GNU General Public License version
 * 2 ("GPL") as published by the Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GPL for more details.
 *
 * You should have received a copy of the GPL along with this program. If not,
 * see http://www.gnu.org/licenses/. The authors of this program may be
 * contacted through http://java-gnome.sourceforge.net/.
 *
 * Linking this library statically or dynamically with other modules is making
 * a combined work based on this library. Thus, the terms and conditions of
 * the GPL cover the whole combination. As a special exception (the
 * "Classpath Exception"), the copyright holders of this library give you
 * permission to link this library with independent modules to produce an
 * executable, regardless of the license terms of these independent modules,
 * and to copy and distribute the resulting executable under terms of your
 * choice, provided that you also meet, for each linked independent module,
 * the terms and conditions of the license of that module. An independent
 * module is a module which is not derived from or based on this library. If
 * you modify this library, you may extend the Classpath Exception to your
 * version of the library, but you are not obligated to do so. If you do not
 * wish to do so, delete this exception statement from your version.
 */

#include <jni.h>
#include <gtk/gtk.h>
#include "bindings_java.h"
#include "org_gnome_gtk_GtkWidgetOverride.h"

/**
 * Access GtkWidget's allocation field, a GtkAllocation struct.
 */
JNIEXPORT void JNICALL
Java_org_gnome_gtk_GtkWidgetOverride_gtk_1widget_1get_1allocation
(
	JNIEnv* env,
	jclass cls,
	jlong _self,
	jlong _allocation
)
{
	GtkWidget* self;
	GtkAllocation* allocation;

	// convert parameter self
	self = (GtkWidget*) _self;

	// convert parameter allocation
	allocation = (GtkAllocation*) _allocation;
	
	gtk_widget_get_allocation(self, allocation);

	// cleanup parameter self

	// cleanup parameter allocation
}

/**
 * Access GtkWidget's requisition field, a GtkRequisition struct.
 *
 * This implementation also [is forced to] get on with calling
 * gtk_widget_size_request() on the assumption that the Requisition isn't
 * much use without data in it.
 */
JNIEXPORT void JNICALL
Java_org_gnome_gtk_GtkWidgetOverride_gtk_1widget_1get_1requisition
(
	JNIEnv* env,
	jclass cls,
	jlong _self,
	jlong _requisition
)
{
	GtkRequisition temp = { 0, };
	GtkRequisition* requisition;
	GtkWidget* self;

	// convert parameter self
	self = (GtkWidget*) _self;

	// convert parameter requisition
	requisition = (GtkRequisition*) _requisition;

	/*
	 * To avoid the necessity to instantiate the silly GtkRequisition
	 * struct as a complete object Java side (along with attendant
	 * GtkRequisitionOverride code to allocate them), we take a fairly
	 * sharp shortcut here: we will programatically get on with it and
	 * call size_request() here.
	 */

	gtk_widget_size_request(self, &temp);
	
	gtk_widget_get_requisition(self, requisition);

	// cleanup parameter self
	
	// cleanup parameter requisition
}

/**
 * Get the events that the underlying GdkWindow receives.
 */
JNIEXPORT jint JNICALL
Java_org_gnome_gtk_GtkWidgetOverride_gtk_1widget_1get_1events
(
	JNIEnv* env,
	jclass cls,
	jlong _self
)
{
	GtkWidget* self;
	GdkWindow* window;
	GdkEventMask mask;

	// convert parameter self
	self = (GtkWidget*) _self;

	window = gtk_widget_get_window(self);

	mask = gdk_window_get_events(window);
	return (jint) mask;
}

/**
 * Set the events that the underlying GdkWindow will receive.
 */
JNIEXPORT void JNICALL
Java_org_gnome_gtk_GtkWidgetOverride_gtk_1widget_1set_1events
(
	JNIEnv* env,
	jclass cls,
	jlong _self,
	jint _mask
)
{
	GtkWidget* self;
	GdkWindow* window;
	GdkEventMask mask;

	// convert parameter self
	self = (GtkWidget*) _self;

	mask = (GdkEventMask) _mask;

	window = gtk_widget_get_window(self);
	gdk_window_set_events(window, mask);
}

/**
 * Get the primary clipboard for the given widget.
 */
JNIEXPORT jlong JNICALL
Java_org_gnome_gtk_GtkWidgetOverride_gtk_1widget_1get_1primary_1clipboard
(
	JNIEnv* env,
	jclass cls,
	jlong _self
)
{
	GtkWidget* self;
	GtkClipboard* result;

	self = (GtkWidget*) _self;
	result = gtk_widget_get_clipboard(self, GDK_SELECTION_PRIMARY);

	return (jlong) result;
}

