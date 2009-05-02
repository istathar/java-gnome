/*
 * NotifyMain.c
 *
 * Copyright (c) 2009 Operational Dynamics Consulting Pty Ltd
 *
 * The code in this file, and the library it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" plus the "Classpath Exception" (you may link to this code as a
 * library into other programs provided you don't make a derivation of it).
 * See the LICENCE file for the terms governing usage and redistribution.
 */

#include <libnotify/notify.h>
#include <jni.h>

/*
 * Implements
 *   org.gnome.notify.NotifyMain.notify_get_server_caps()
 * called from
 *   org.gnome.notify.Notify.getServerCapabilities()
 */
JNIEXPORT jobjectArray JNICALL
Java_org_gnome_notify_NotifyMainOverride_notify_1get_1server_1caps
(
	JNIEnv* env,
	jclass cls
)
{
	jobjectArray _array;
	int i, size;
	jclass stringCls;
	GList* caps;
	GList* iter;
	jstring cap;

	caps=notify_get_server_caps();

	if (caps == NULL) {
		size = 0;
	} else {
		size = g_list_length(caps);
	}

	stringCls = (*env)->FindClass(env, "java/lang/String");
	if ((*env)->ExceptionCheck(env)) {
		(*env)->ExceptionDescribe(env);
		g_printerr("No jclass?");
	}

	_array = (*env)->NewObjectArray(env, size, stringCls, NULL);

	if ((*env)->ExceptionCheck(env)) {
		(*env)->ExceptionDescribe(env);
		g_printerr("Unable to create array?");
	}

	iter = caps;

	for (i = 0; i < size; ++i) {
		//Hopefully capability strings are ASCII only.
		cap = (*env)->NewStringUTF(env, iter->data);
		(*env)->SetObjectArrayElement(env, _array, i, cap);
		g_free(iter->data);
		iter = iter->next;
	}

	if (caps != NULL) {
		g_list_free(caps);
	}

	return _array;
}
