/*
 * Plumbing.c
 *
 * Copyright (c) 2007 Operational Dynamics Consulting Pty Ltd and Others
 * 
 * The code in this file, and the library it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" plus the "Classpath Exception" (you may link to this code as a
 * library into other programs provided you don't make a derivation of it).
 * See the LICENCE file for the terms governing usage and redistribution.
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
	JNIEnv *env,
	jclass cls,
	jlong _pointer
)
{
	cairo_pattern_t* surface;
	static jclass SolidPattern = NULL;
	static jclass SurfacePattern = NULL;	
	jclass type;
	jmethodID constructor;
	jobject proxy;
	
	// convert pointer
	surface = (cairo_pattern_t*) _pointer;
	
	switch (cairo_pattern_get_type(surface)) {
	case CAIRO_PATTERN_TYPE_SOLID:
		if (SolidPattern == NULL) {
			SolidPattern = (*env)->FindClass(env, "org/freedesktop/cairo/SolidPattern");
		}
		type = SolidPattern;
		break;
		
	case CAIRO_PATTERN_TYPE_SURFACE:
		if (SurfacePattern == NULL) {
			SurfacePattern = (*env)->FindClass(env, "org/freedesktop/cairo/SurfacePattern");
		}
		type = SurfacePattern;
		break;

	case CAIRO_PATTERN_TYPE_LINEAR:
	case CAIRO_PATTERN_TYPE_RADIAL:		
	default:
		g_critical("Unimplemented pattern type");
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
