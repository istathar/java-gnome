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
#include <pango/pango.h>
#include "bindings_java.h"
#include "org_gnome_pango_PangoAttributeOverride.h"

JNIEXPORT void JNICALL
Java_org_gnome_pango_PangoAttributeOverride_pango_1attribute_1set_1indexes
(
	JNIEnv* env,
	jclass cls,
	jlong _self,
	jlong _layout,
	jint _offset,
	jint _width
)
{
	PangoAttribute* self;
	PangoLayout* layout;
	gint offset;
	gint width;
	const char* text;
	char* alpha;
	char* omega;
	guint start;
	guint end;
	
	// convert paramter self
	self = (PangoAttribute*) _self;

	// convert paramter layout
	layout = (PangoLayout*) _layout;

	// convert parameter offset
	offset = (gint) _offset;

	// convert parameter width
	width = (gint) _width;

	/*
	 * We use Integer.MIN_VALUE as our sentinel that we mean unbounded.
	 */

	if (width == 0x80000000) {
		width = G_MAXUINT;
	}

	// convert to bounds
	/*
	 * Get the text out of the layout, and then work out what
	 * the offset and offset+width work out to in byte terms.
	 */

	text = pango_layout_get_text(layout);

	alpha = g_utf8_offset_to_pointer(text, offset);
	omega = g_utf8_offset_to_pointer(text, offset + width);

	start = alpha - text;
	end = omega - text;

	// set fields
	self->start_index = start;
	self->end_index = end;
	
	// cleanup parameter self

	// cleanup parameter layout

	// cleanup parameter offset

	// cleanup parameter width

	// local text should not be modified or freed
}

