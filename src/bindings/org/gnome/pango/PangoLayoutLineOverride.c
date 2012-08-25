/*
 * java-gnome, a UI library for writing GTK and GNOME programs from Java!
 *
 * Copyright © 2009-2010 Operational Dynamics Consulting, Pty Ltd
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

