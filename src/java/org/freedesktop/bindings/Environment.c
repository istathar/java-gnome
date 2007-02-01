/*
 * Environment.c
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
#include <glib.h>
#include <stdlib.h>
#include "org_freedesktop_bindings_Environment.h"

/*
 * Implements
 *   org.freedesktop.bindings.Environment.getenv(String variableName)
 */
JNIEXPORT jobject JNICALL
Java_org_freedesktop_bindings_Environment_getenv
(
	JNIEnv *env,
	jclass cls,
	jstring _name
)
{
	gchar* name;
	gchar* value;

	// translate
	name = (gchar*) (*env)->GetStringUTFChars(env, _name, NULL);
	if (name == NULL) {
		return NULL; /* OutOfMemoryError already thrown */
	}

	// call function
	value = (gchar*) getenv(name); 

	// clean up variableName
	(*env)->ReleaseStringUTFChars(env, _name, name);

	// and return	
	return (*env)->NewStringUTF(env, value);
}
