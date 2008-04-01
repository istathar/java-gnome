/*
 * Internationalization.c
 *
 * Copyright (c) 2008 Operational Dynamics Consulting Pty Ltd
 * Copyright (c) 2008 Vreixo Formoso
 * 
 * The code in this file, and the library it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" plus the "Classpath Exception" (you may link to this code as a
 * library into other programs provided you don't make a derivation of it).
 * See the LICENCE file for the terms governing usage and redistribution.
 */

#include <libintl.h>
#include <locale.h>
#include <jni.h>
#include "org_gnome_glib_Internationalization.h"

/**
 * Implements
 *   org.gnome.glib.Internationalization.gettext(String msg)
 * called from
 *   org.gnome.glib.Internationalization._(String msg, java.lang.Object ...parameters)
 */
JNIEXPORT jstring JNICALL
Java_org_gnome_glib_Internationalization_gettext
(
	JNIEnv *env,
	jclass cls,
	jstring _msg
)
{	
	const char* msg;
	char* result;

	// convert parameter msg
	msg = (const char*) (*env)->GetStringUTFChars(env, _msg, NULL);
	if (msg == NULL) {
		return NULL; // expeption already thrown
	}

	// call function 
	result = gettext(msg);
        
	// cleanup parameter msg
	(*env)->ReleaseStringUTFChars(env, _msg, msg);
    
	// convert string and return
	return (*env)->NewStringUTF(env, result);
}

JNIEXPORT void JNICALL 
Java_org_gnome_glib_Internationalization_bindtextdomain
(
	JNIEnv *env,
	jclass cls,
	jstring _packageName,
	jstring _localeDir
)
{
	const char* packageName;
	const char* localeDir;

	// convert parameter packageName
	packageName = (const char*) (*env)->GetStringUTFChars(env, _packageName, NULL);
	if (packageName == NULL) {
		return; // expeption already throw
	}

	// convert parameter localeDir
	localeDir = (const char*) (*env)->GetStringUTFChars(env, _localeDir, NULL);
	if (localeDir == NULL) {
		return; // expeption already throw
	}

	/*
	 * Initialize internationalization and localization libraries
	 */

	setlocale(LC_ALL, "");
	bindtextdomain(packageName, localeDir);
	bind_textdomain_codeset(packageName, "UTF-8");
	textdomain(packageName);

	// cleanup parameter packageName
	(*env)->ReleaseStringUTFChars(env, _packageName, packageName);

	// cleanup parameter localeDir
	(*env)->ReleaseStringUTFChars(env, _localeDir, localeDir);
}
