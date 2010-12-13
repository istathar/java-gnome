/*
 * RsvgDimensionDataOverride.c
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
#include <librsvg/rsvg.h>
#include "bindings_java.h"
#include "org_gnome_rsvg_RsvgDimensionDataOverride.h"

/*
 * Allocates a RsvgDimensionData. See GtkTreeIterOverride.c for discussion and
 * possible remedies.
 */
JNIEXPORT jlong JNICALL
Java_org_gnome_rsvg_RsvgDimensionDataOverride_rsvg_1dimension_1data_1new
(
	JNIEnv* env,
	jclass cls
)
{
	RsvgDimensionData* result;
	
	// allocate
	result = g_slice_new(RsvgDimensionData);

	// and finally
	return (jlong) result;
}

JNIEXPORT void JNICALL
Java_org_gnome_rsvg_RsvgDimensionDataOverride_rsvg_1dimension_1data_1free
(
	JNIEnv* env,
	jclass cls,
	jlong _self
)
{
	RsvgDimensionData* self;

	// convert self
	self = (RsvgDimensionData*) _self;
	
	// free
	g_slice_free(RsvgDimensionData, self);
}
