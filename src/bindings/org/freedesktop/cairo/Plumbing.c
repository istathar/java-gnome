/*
 * java-gnome, a UI library for writing GTK and GNOME programs from Java!
 *
 * Copyright © 2007-2010 Operational Dynamics Consulting, Pty Ltd and Others
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
#include <cairo.h>
#include "bindings_java.h"
#include "org_freedesktop_cairo_Plumbing.h"

/*
 * Implements
 *   org.freedesktop.cairo.Plumbing.createPattern(long pointer)
 */
JNIEXPORT jobject JNICALL
Java_org_freedesktop_cairo_Plumbing_createPattern
(
	JNIEnv* env,
	jclass cls,
	jlong _pointer
)
{
	cairo_pattern_t* pattern;
	jclass found;
	static jclass SolidPattern = NULL;
	static jclass SurfacePattern = NULL;	
	static jclass LinearPattern = NULL;	
	static jclass RadialPattern = NULL;	
	jclass type;
	jmethodID constructor;
	jobject proxy;
	
	// convert pointer
	pattern = (cairo_pattern_t*) _pointer;

	// increment reference count
	cairo_pattern_reference(pattern);

	// now figure out Proxy class and create
	switch (cairo_pattern_get_type(pattern)) {
	case CAIRO_PATTERN_TYPE_SOLID:
		if (SolidPattern == NULL) {
			found = (*env)->FindClass(env, "org/freedesktop/cairo/SolidPattern");
			SolidPattern = (*env)->NewGlobalRef(env, found);
		}
		type = SolidPattern;
		break;

	case CAIRO_PATTERN_TYPE_SURFACE:
		if (SurfacePattern == NULL) {
			found = (*env)->FindClass(env, "org/freedesktop/cairo/SurfacePattern");
			SurfacePattern = (*env)->NewGlobalRef(env, found);
		}
		type = SurfacePattern;
		break;

	case CAIRO_PATTERN_TYPE_LINEAR:
		if (LinearPattern == NULL) {
			found = (*env)->FindClass(env, "org/freedesktop/cairo/LinearPattern");
			LinearPattern = (*env)->NewGlobalRef(env, found);
		}
		type = LinearPattern;
		break;

	case CAIRO_PATTERN_TYPE_RADIAL:		
		if (RadialPattern == NULL) {
			found = (*env)->FindClass(env, "org/freedesktop/cairo/RadialPattern");
			RadialPattern = (*env)->NewGlobalRef(env, found);
		}
		type = RadialPattern;
		break;

	default:
		g_critical("Unimplemented pattern type");
		return NULL;
	}
	if (type == NULL) {
		bindings_java_throw(env, "FindClass() failed");
		return NULL;
	}

	constructor = (*env)->GetMethodID(env, type, "<init>", "(J)V");
	if (constructor == NULL) {
		g_error("Constructor methodID not found");
		return NULL;
	}
	
	proxy = (*env)->NewObject(env, type, constructor, _pointer);
	return proxy;
}


/*
 * Implements
 *   org.freedesktop.cairo.Plumbing.createSurface(long pointer)
 */
JNIEXPORT jobject JNICALL
Java_org_freedesktop_cairo_Plumbing_createSurface
(
	JNIEnv* env,
	jclass cls,
	jlong _pointer
)
{
	cairo_surface_t* surface;
	jclass found;
	static jclass ImageSurface = NULL;
	static jclass XlibSurface = NULL;
	static jclass PdfSurface = NULL;
	static jclass SvgSurface = NULL;
	static jclass RecordingSurface = NULL;
	static jclass UnknownSurface = NULL;
	jclass type;
	jmethodID constructor;
	jobject proxy;

	// convert pointer
	surface = (cairo_surface_t*) _pointer;

	// increment reference count
	cairo_surface_reference(surface);

        // now figure out Proxy class and create
	switch (cairo_surface_get_type(surface)) {
	case CAIRO_SURFACE_TYPE_IMAGE:
		if (ImageSurface == NULL) {
			found = (*env)->FindClass(env, "org/freedesktop/cairo/ImageSurface");
			ImageSurface = (*env)->NewGlobalRef(env, found);
		}
		type = ImageSurface;
		break;
		
	case CAIRO_SURFACE_TYPE_XLIB:
		if (XlibSurface == NULL) {
			found = (*env)->FindClass(env, "org/freedesktop/cairo/XlibSurface");
			XlibSurface = (*env)->NewGlobalRef(env, found);
		}
		type = XlibSurface;
		break;

        case CAIRO_SURFACE_TYPE_PDF:
		if (PdfSurface == NULL) {
			found = (*env)->FindClass(env, "org/freedesktop/cairo/PdfSurface");
			PdfSurface = (*env)->NewGlobalRef(env, found);
		}
		type = PdfSurface;
		break;

        case CAIRO_SURFACE_TYPE_SVG:
		if (SvgSurface == NULL) {
			found = (*env)->FindClass(env, "org/freedesktop/cairo/SvgSurface");
			SvgSurface = (*env)->NewGlobalRef(env, found);
		}
		type = SvgSurface;
		break;

        case CAIRO_SURFACE_TYPE_RECORDING:
		if (RecordingSurface == NULL) {
			found = (*env)->FindClass(env, "org/freedesktop/cairo/RecordingSurface");
			RecordingSurface = (*env)->NewGlobalRef(env, found);
		}
		type = RecordingSurface;
		break;

	default:
		/*
		 * This is an unusual scenario. Normally in java-gnome if we
		 * don't know the type that's a fatal error (and on purpose; if
		 * we haven't got a concrete Proxy subclass for someone, that's
		 * it). In Cairo, however, there are a number of cases where
		 * internal types are exposed (notably MetaSurface, created if
		 * you call createSimilar() on a vector backend) for which
		 * there is no publicly available identification. So
		 * UnknownSurface it is. This, however, obscures the real error
		 * condition of needing to add a block to this switch statement
		 * for a newly covered type.
		 */
		if (UnknownSurface == NULL) {
			found = (*env)->FindClass(env, "org/freedesktop/cairo/UnknownSurface");
			UnknownSurface = (*env)->NewGlobalRef(env, found);
		}
		type = UnknownSurface;
	}
	if (type == NULL) {
		bindings_java_throw(env, "FindClass() failed");
		return NULL;
	}

	constructor = (*env)->GetMethodID(env, type, "<init>", "(J)V");
	if (constructor == NULL) {
		bindings_java_throw(env, "Constructor methodID not found");
		return NULL;
	}

	proxy = (*env)->NewObject(env, type, constructor, _pointer);
	return proxy;
}
