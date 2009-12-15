/*
 * PangoLayoutLine.c
 *
 * Copyright (c) 2009 Operational Dynamics Consulting Pty Ltd
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
#include "org_gnome_pango_PangoLayoutLineOverride.h"

JNIEXPORT jint JNICALL
Java_org_gnome_pango_PangoLayoutLineOverride_pango_1layout_1line_1get_1start_1index
(
	JNIEnv* env,
	jclass cls,
	jlong _self
)
{
	PangoLayoutLine* self;
	const gchar* text;
	const gchar* pointer;
	glong offset;
	gint result;
	jint _result;

	// convert parameter self
	self = (PangoLayoutLine*) _self;

	// get field value

	/*
	 * We need to convert to a character offset; to do so we get
	 * the text backing the Layout, and pass that string and the byte
         * start_index to the UTF8 conversion function.
	 */

	text = pango_layout_get_text(self->layout);
	pointer = text + self->start_index;
	offset = g_utf8_pointer_to_offset(text, pointer);

	/*
	 * The start_index and length fields are both integer, so
	 * so down cast the long to int.
	 */

	result = (gint) offset;

	// cleanup parameter self

	// translate return value to JNI type
	_result = (jint) result;

	// and finally
	return _result;
}

/*
 * This ends up being exactly the same logic as the start_index case above 
 * because we need to work in characters and so need the underlying Layout's
 * underlying string to do the character count.
 */
JNIEXPORT jint JNICALL
Java_org_gnome_pango_PangoLayoutLineOverride_pango_1layout_1line_1get_1length
(
	JNIEnv* env,
	jclass cls,
	jlong _self
)
{
	PangoLayoutLine* self;
	const gchar* text;
	const gchar* alpha;
	const gchar* omega;
	glong begin;
	glong end;
	gint result;
	jint _result;

	// convert parameter self
	self = (PangoLayoutLine*) _self;

	// get field value

	/*
	 * We need to convert to a character offset; to do so we get
	 * the text backing the Layout, and pass that string and the byte
         * start_index to the UTF8 conversion function.
	 */

	text = pango_layout_get_text(self->layout);
	alpha = text + self->start_index;
	omega = text + self->start_index + self->length;
	begin = g_utf8_pointer_to_offset(text, alpha);
	end = g_utf8_pointer_to_offset(text, omega);

	/*
	 * The start_index and length fields are both integer, so
	 * so down cast the long to int.
	 */

	result = (gint) end - begin;

	// cleanup parameter self

	// translate return value to JNI type
	_result = (jint) result;

























	// translate return value to JNI type
	_result = (jint) result;

	// and finally
	return _result;
}

