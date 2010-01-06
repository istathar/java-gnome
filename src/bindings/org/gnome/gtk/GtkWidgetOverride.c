/*
 * java-gnome, a UI library for writing GTK and GNOME programs from Java!
 *
 * Copyright © 2007-2010 Operational Dynamics Consulting, Pty Ltd
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
 * "Claspath Exception"), the copyright holders of this library give you
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
 * Access GtkWidget's allocation field, a GtkAllocation struct. Note that we
 * return a pointer to the live struct, not a copy. This is a bit of a
 * novelty, but it seems safe so long as we ensure the Widget Proxy is not
 * collected before the Allocation Proxy, which we enforce with a back
 * reference Java side.  
 */
JNIEXPORT jlong JNICALL
Java_org_gnome_gtk_GtkWidgetOverride_gtk_1widget_1get_1allocation
(
	JNIEnv* env,
	jclass cls,
	jlong _self
)
{
	GtkAllocation* result;
	GtkWidget* self;

	// convert parameter self
	self = (GtkWidget*) _self;
	
	/*
	 * This is NOT a dynamic allocation, but rather a live reference to
	 * the GtkAllocation struct in the GtkWidget instance struct.
	 */	
	result = &self->allocation;

	// cleanup parameter self

	// and finally
	return (jlong) result;
}

/**
 * Access GtkWidget's requisition field, a GtkRequisition struct. Note that
 * we return a pointer to the live struct, not a copy. This may cause
 * problems if we expose other methods which take a blank Requisition to be
 * passed by reference.
 * 
 * This implementation also [is forced to] get on with calling
 * gtk_widget_size_request() on the assumption that the Requisition isn't
 * much use without data in it. 
 */
JNIEXPORT jlong JNICALL
Java_org_gnome_gtk_GtkWidgetOverride_gtk_1widget_1get_1requisition
(
	JNIEnv* env,
	jclass cls,
	jlong _self
)
{
	GtkRequisition temp = { 0, };
	GtkRequisition* result;
	GtkWidget* self;

	// convert parameter self
	self = (GtkWidget*) _self;
	
	/*
	 * To avoid the necessity to instantiate the silly GtkRequisition
	 * struct as a complete object Java side (along with attendant
	 * GtkRequisitionOverride code to allocate them), we take a fairly
	 * sharp shortcut here: we will programatically get on with it and
	 * call size_request() here.  
	 */
	gtk_widget_size_request(self, &temp);
	
	/*
	 * This is NOT a dynamic allocation, but rather a live reference to
	 * the GtkAllocation struct in the GtkWidget instance struct.
	 */	
	result = &self->requisition;

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
