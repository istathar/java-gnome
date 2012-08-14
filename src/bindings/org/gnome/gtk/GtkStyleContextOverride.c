/*
 * java-gnome, a UI library for writing GTK and GNOME programs from Java!
 *
 * Copyright © 2011 Operational Dynamics Consulting, Pty Ltd and Others
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
#include "org_gnome_gtk_GtkStyleContextOverride.h"

JNIEXPORT jint JNICALL
Java_org_gnome_gtk_GtkStyleContextOverride_gtk_1style_1context_1contains_1region
(
	JNIEnv* env,
	jclass cls,
	jlong _self,
	jstring _region
)
{
	const gchar* region;
	gboolean result;
	GtkStyleContext* self;
	GtkRegionFlags flags;

	// convert string to gchar*
	region = (const gchar*) bindings_java_getString(env, _region);
	if (region == NULL) {
		return -1;
	}

	self = (GtkStyleContext*) _self;

	// call function
	result = gtk_style_context_has_region(self, region, &flags);
	bindings_java_releaseString(region);

	// return nothing equivalent to false
	return result ? (jint) flags : -1;
}

JNIEXPORT jobjectArray JNICALL
Java_org_gnome_gtk_GtkStyleContextOverride_gtk_1style_1context_1get_1classes
(
	JNIEnv* env,
	jclass cls,
	jlong _self
)
{
	GtkStyleContext* self;
	jobjectArray array;
	int i, size;
	jclass String;
	GList* classes;
	GList* iter;
	jstring class;

	self = (GtkStyleContext*) _self;
	classes = gtk_style_context_list_classes(self);

	size = g_list_length(classes);

	String = (*env)->FindClass(env, "java/lang/String");
	if (String == NULL) {
		// exception already thrown
		return NULL;
	}

	array = (*env)->NewObjectArray(env, size, String, NULL);
	if (array == NULL) {
		// exception already thrown
		return NULL;
	}

	iter = classes;
	for (i = 0; i < size; i++) {
		class = bindings_java_newString(env, iter->data);
		(*env)->SetObjectArrayElement(env, array, i, class);
		iter = iter->next;
	}

	g_list_free(classes);

	return array;
}

JNIEXPORT jobjectArray JNICALL
Java_org_gnome_gtk_GtkStyleContextOverride_gtk_1style_1context_1get_1regions
(
	JNIEnv* env,
	jclass cls,
	jlong _self
)
{
	GtkStyleContext* self;
	jobjectArray array;
	int i, size;
	jclass String;
	GList* regions;
	GList* iter;
	jstring region;

	self = (GtkStyleContext*) _self;
	regions = gtk_style_context_list_regions(self);

	size = g_list_length(regions);

	String = (*env)->FindClass(env, "java/lang/String");
	if (String == NULL) {
		// exception already thown
		return NULL;
	}

	array = (*env)->NewObjectArray(env, size, String, NULL);
	if (array == NULL) {
		// exception already thrown
		return NULL;
	}

	iter = regions;
	for (i = 0; i < size; i++) {
		region = bindings_java_newString(env, iter->data);
		(*env)->SetObjectArrayElement(env, array, i, region);
		iter = iter->next;
	}

	g_list_free(regions);

	return array;
}
