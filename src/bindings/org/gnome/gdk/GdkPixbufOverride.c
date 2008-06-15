/*
 * GdkPixbufOverride.c
 *
 * Copyright (c) 2008 Operational Dynamics Consulting Pty Ltd
 *
 * The code in this file, and the library it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" plus the "Classpath Exception" (you may link to this code as a
 * library into other programs provided you don't make a derivation of it).
 * See the LICENCE file for the terms governing usage and redistribution.
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
