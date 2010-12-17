/*
 * java-gnome, a UI library for writing GTK and GNOME programs from Java!
 *
 * Copyright © 2010 Operational Dynamics Consulting, Pty Ltd
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
#include "org_freedesktop_cairo_CairoSurfaceOverride.h"

JNIEXPORT void JNICALL
Java_org_freedesktop_cairo_CairoSurfaceOverride_cairo_1surface_1set_1mime_1data
(
	JNIEnv* env,
	jclass cls,
	jlong _self,
	jstring _mimeType,
	jbyteArray _data
)
{
	cairo_status_t result;
	cairo_surface_t* self;
	const char* mimeType;
	unsigned char* data;
	long length;

	// convert parameter self
	self = (cairo_surface_t*) _self;

	// convert parameter mimeType
	mimeType = (const char*) bindings_java_getString(env, _mimeType);
	if (mimeType == NULL) {
		return; // Java Exception already thrown
	}

	// convert parameter data
	// set up the data and length parameters.
	length = (*env)->GetArrayLength(env, _data);
	data = (unsigned char*) (*env)->GetByteArrayElements(env, _data, NULL);
	if (data == NULL) {
		return; // Java Exception already thrown
	}

	// call function
	result = cairo_surface_set_mime_data(self, mimeType, data, length, NULL, NULL);

	// cleanup parameter self

	// cleanup parameter mimeType
	bindings_java_releaseString(mimeType);

	// cleanup parameter data
	(*env)->ReleaseByteArrayElements(env, _data, (void*) data, 0);

	/*
	 * Check return value
	 */
	if (result != CAIRO_STATUS_SUCCESS) {
		bindings_java_throwByName(env, "org/freedesktop/cairo/FatalError", "Out of memory");
		return;
	}
}
