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
	guchar* pixel;
	int rowstride;
	int width;
	int height;
	int n_channels;
	int bits_per_sample;
	int length;
	jbyteArray result;
	
	// convert parameter self
	self = (GdkPixbuf*) _self;

	/*
	 * We need to know how much image data there is. The rowstride gives
	 * us the width of each row, but the last row is *not* this wide. The
	 * formula here is from the GDK docs. 
	 */
	
	width = gdk_pixbuf_get_width(self);
	height = gdk_pixbuf_get_height(self);
	rowstride = gdk_pixbuf_get_rowstride(self);
	n_channels = gdk_pixbuf_get_n_channels(self);
	bits_per_sample = gdk_pixbuf_get_bits_per_sample(self);
	
	if (bits_per_sample != 8) {
		bindings_java_throw(env, "This algorithm only supports 8 bits per channel");
		/*
		 * This code below is safe, but if this is hit, then the
		 * assumption made in Pixbuf.java that each channel is a byte
		 * will need to be reworked and the impact of the rounding
		 * implied in the following call to SetByteArrayRegion call
		 * verified.
		 */
		return NULL;
	}

	length =  ((height - 1) * rowstride) + (width * ((n_channels * bits_per_sample + 7) / 8));
	
	/*
	 * Now we can extract the image data, and return it.
	 */

	// call function
	pixel = gdk_pixbuf_get_pixels(self);
	
	// and finally
	result = (*env)->NewByteArray(env, length);
	(*env)->SetByteArrayRegion(env, result, 0, length, (jbyte*) pixel);
	
	return result;
}
