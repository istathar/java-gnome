/*
 * java-gnome, a UI library for writing GTK and GNOME programs from Java!
 *
 * Copyright © 2008-2010 Operational Dynamics Consulting, Pty Ltd and Others
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
#include "org_gnome_gdk_GdkPixbufOverride.h"

JNIEXPORT jbyteArray JNICALL
Java_org_gnome_gdk_GdkPixbufOverride_gdk_1pixbuf_1get_1pixels
(
	JNIEnv* env,
	jclass cls,
	jlong _self
)
{
	GdkPixbuf* self;
	int rowstride;
	int width;
	int height;
	int n_channels;
	int bits_per_sample;
	guchar* data;
	jbyteArray result;
	int size;
	int j, p;


	// convert parameter self
	self = (GdkPixbuf*) _self;

	/*
	 * We need to know how much image data there is. The rowstride gives
	 * us the width of each row, but the last row is *not* this wide. The
	 * formula is in the GDK docs [and more to the point, in the source
	 * code of gdk_pixbuf_new()] but, as finally implemented, we don't
	 * need it since we're extracting the rows individually, thereby
	 * jettisoning the padding.
	 */

	width = gdk_pixbuf_get_width(self);
	height = gdk_pixbuf_get_height(self);
	rowstride = gdk_pixbuf_get_rowstride(self);
	n_channels = gdk_pixbuf_get_n_channels(self);
	bits_per_sample = gdk_pixbuf_get_bits_per_sample(self);

	// length =  ((height - 1) * rowstride) + (width * ((n_channels * bits_per_sample + 7) / 8));

	if (bits_per_sample != 8) {
		bindings_java_throw(env, "This algorithm only supports 8 bits per channel");
		/*
		 * If this is hit, then the algorithm made below [and
		 * descirbed in our description of Pixbuf's getPixels()] that
		 * each channel is a single byte will need to be reworked.
		 */
		return NULL;
	}

	/*
	 * Now we can extract the image data, and return it.
	 */

	// call function
	data = gdk_pixbuf_get_pixels(self);


	/*
	 * Now copy the bytes of each pixel out row by row. Most of the time
	 * size will match rowstride, but not always. The net effect of this
	 * is to return a cartesian array without padding and without the
	 * dangling last row. We orginally had this in Pixbuf.java, but this
	 * turns out to be 20% faster under heavy load.
	 */

	size = width * n_channels;

	result = (*env)->NewByteArray(env, height * size);

	p = 0;

	for (j = 0; j < height; j++) {
		(*env)->SetByteArrayRegion(env, result, p, size, (jbyte*) data);

		data += rowstride;
		p += size;
	}

	// and finally
	return result;
}

JNIEXPORT jlong JNICALL
Java_org_gnome_gdk_GdkPixbufOverride_gdk_1pixbuf_1new_1from_1stream
(
	JNIEnv* env,
	jclass cls,
	jbyteArray _data,
	jint _width,
	jint _height,
	jboolean _preserveAspectRatio,
	jboolean _scale
)
{
	GInputStream *input_stream;
	gssize len;
	void *data;
	int width;
	int height;
	gboolean preserveAspectRatio;
	GdkPixbuf* result;
	GError* error = NULL;

	// set up the length and input stream parameters.
	len = (*env)->GetArrayLength(env, _data);
	data = (*env)->GetByteArrayElements(env, _data, NULL);
	width = (int) _width;
	height = (int) _height;
	preserveAspectRatio = (gboolean) _preserveAspectRatio;

	/*
	 * Jump through the necessary hoops to feed an array of bytes to the
	 * GdkPixbuf constructor. This code a wrapper around two native
	 * functions; we use the last boolean across the function call stack to
	 * decide which to call.
	 */

	input_stream = g_memory_input_stream_new_from_data(data, len, NULL);
	if (_scale) {
		result = gdk_pixbuf_new_from_stream_at_scale(input_stream, width, height, preserveAspectRatio, NULL, &error);
	} else {
		result = gdk_pixbuf_new_from_stream(input_stream, NULL, &error);
	}

	g_input_stream_close(input_stream, NULL, NULL);
	g_object_unref(input_stream);

	// cleanup parameter data
	(*env)->ReleaseByteArrayElements(env, _data, data, 0);

	// cleanup return value
	if (result != NULL) {
		bindings_java_memory_cleanup((GObject*)result, TRUE);
	}

	// check for a GError
	if (error) {
		bindings_java_throwGlibException(env, error);
		return 0L;
	}

	return (jlong) result;
}

JNIEXPORT jint JNICALL
Java_org_gnome_gdk_GdkPixbufOverride_gdk_1pixbuf_1get_1file_1info_1X
(
	JNIEnv* env,
	jclass cls,
	jstring _filename
)
{
	const GdkPixbufFormat* format;
	const gchar* filename;
	int x;

	// convert parameter filename
	filename = (const gchar*) bindings_java_getString(env, _filename);
	if (filename == NULL) {
		return 0; // Java Exception already thrown
	}

	// call function
	format = gdk_pixbuf_get_file_info(filename, &x, NULL);
	if (format == NULL) {
		x = -1;
	}

	// cleanup parameter filename
	bindings_java_releaseString(filename);

	// translate return value to JNI type
	return (jint) x;
}

JNIEXPORT jint JNICALL
Java_org_gnome_gdk_GdkPixbufOverride_gdk_1pixbuf_1get_1file_1info_1Y
(
	JNIEnv* env,
	jclass cls,
	jstring _filename
)
{
	const GdkPixbufFormat* format;
	const gchar* filename;
	int y;

	// convert parameter filename
	filename = (const gchar*) bindings_java_getString(env, _filename);
	if (filename == NULL) {
		return 0; // Java Exception already thrown
	}

	// call function
	format = gdk_pixbuf_get_file_info(filename, NULL, &y);
	if (format == NULL) {
		y = -1;
	}

	// cleanup parameter filename
	bindings_java_releaseString(filename);

	// translate return value to JNI type
	return (jint) y;
}

