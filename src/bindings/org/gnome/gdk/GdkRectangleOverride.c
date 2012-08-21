/*
 * java-gnome, a UI library for writing GTK and GNOME programs from Java!
 *
 * Copyright © 2008-2010 Operational Dynamics Consulting, Pty Ltd
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
#include <gdk/gdk.h>
#include "bindings_java.h"
#include "org_gnome_gdk_GdkRectangleOverride.h"

/*
 * Allocator and release function for GdkRectangle structs. Most of the time
 * we are just allocating a blank, so 0 are passed knowing full well the
 * struct will be populated by the next function call. We've saved ourself
 * some trouble by having one function for both that case and the case where
 * the developer needs to create one themselves.
 */

JNIEXPORT jlong JNICALL
Java_org_gnome_gdk_GdkRectangleOverride_gdk_1rectangle_1new
(
	JNIEnv* env,
	jclass cls,
	jint _x,
	jint _y,
	jint _width,
	jint _height
)
{
	gint x;
	gint y;
	gint width;
	gint height;
	GdkRectangle* result;
	
	// convert parameters

	x = (gint) _x;
	y = (gint) _y;
	width = (gint) _width;
	height = (gint) _height;
	
	/*
	 * This is a dynamic allocation.
	 */	
	result = g_slice_new0(GdkRectangle);

	result->x = x;
	result->y = y;
	result->width = width;
	result->height = height;
		
	// cleanup parameter self

	// and finally
	return (jlong) result;
}

JNIEXPORT void JNICALL
Java_org_gnome_gdk_GdkRectangleOverride_gdk_1rectangle_1free
(
	JNIEnv* env,
	jclass cls,
	jlong _self
)
{
	GdkRectangle* self;
	
	// convert paramter self
	self = (GdkRectangle*) _self;
	
	// call function
	g_slice_free(GdkRectangle, self);
	
	// cleanup parameter self
}
