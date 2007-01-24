/*
 * GladeXml.c
 *
 * Copyright (c) 2007 Operational Dynamics Consulting Pty Ltd
 * 
 * The code in this file, and the library it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" plus the "Classpath Exception" (you may link to this code as a
 * library into other programs provided you don't make a derivation of it).
 * See the LICENCE file for the terms governing usage and redistribution.
 *
 *                      THIS FILE WILL BE GENERATED CODE!
 *
 * To modify its contents or behaviour, either update the generation program,
 * change the information in the source defs file, or implement an override
 * for this class.
 */

#include <jni.h>
#include <gtk/gtk.h>
#include <glade/glade.h>

#include "org_gnome_glade_GladeXml.h"

JNIEXPORT jlong JNICALL
Java_org_gnome_glade_GladeXml_glade_1xml_1new
(
	JNIEnv *env,
	jclass cls,
	jstring _filename,
	jstring _root
)
{
	GladeXML* xml;
	const gchar* filename;
	const gchar* root;

	// translate arg filename
	filename = (const gchar*) (*env)->GetStringUTFChars(env, _filename, NULL);
	if (filename == NULL) {
		return 0; /* OutOfMemoryError already thrown */
	}
	
	// translate arg root; null ok.
	if (_root == NULL) {
		root = NULL;
	} else {
		root = (const gchar*) (*env)->GetStringUTFChars(env, _root, NULL);
		if (root == NULL) {
			return 0; /* OutOfMemoryError already thrown */
		}
	}
	
	// call function
	xml = glade_xml_new(filename, root, NULL);

	// cleanup arg filename
	(*env)->ReleaseStringUTFChars(env, _filename, filename);

	// cleanup arg root
	(*env)->ReleaseStringUTFChars(env, _root, root);

	// return pointer
	return (jlong) xml;
}

JNIEXPORT jlong JNICALL
Java_org_gnome_glade_GladeXml_glade_1xml_1get_1widget
(
	JNIEnv *env,
	jclass cls,
	jlong _self,
	jstring _name
)
{
	GladeXML* self;
	const gchar* name;
	GtkWidget* result;

	// translate arg self
	self = (GladeXML*) _self;
	
	// translate arg name
	name = (const gchar*) (*env)->GetStringUTFChars(env, _name, NULL);
	if (name == NULL) {
		return 0; /* OutOfMemoryError already thrown */
	}
	
	// call function
	result = glade_xml_get_widget(self, name);

	// cleanup arg name
	(*env)->ReleaseStringUTFChars(env, _name, name);

	// return pointer
	return (jlong) result;
}
