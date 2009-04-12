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
#include "org_gnome_notify_NotifyMain.h"

/*
 * Implements
 *   org.gnome.notify.NotifyMain.notify_init()
 * called from
 *   org.gnome.notify.Notify.init()
 */
JNIEXPORT jboolean JNICALL Java_org_gnome_notify_NotifyMain_notify_1init
(
	JNIEnv * env,
	jclass cls,
	jstring app_name
)
{
	const char *c_app_name;
	c_app_name  = (*env)->GetStringUTFChars(env, app_name, NULL);

	gboolean result;
	result=notify_init(c_app_name);
	return (jboolean)result;
}

/*
 * Implements
 *   org.gnome.notify.NotifyMain.notify_uninit()
 * called from
 *   org.gnome.notify.Notify.uninit()
 */
JNIEXPORT void JNICALL Java_org_gnome_notify_NotifyMain_notify_1uninit
(
	JNIEnv * env,
	jclass cls
)
{
	notify_uninit();
}

/*
 * Implements
 *   org.gnome.notify.NotifyMain.notify_is_initted()
 * called from
 *   org.gnome.notify.Notify.isInitted()
 */
JNIEXPORT jboolean JNICALL Java_org_gnome_notify_NotifyMain_notify_1is_1initted
(
	JNIEnv * env,
	jclass cls
)
{
	gboolean result;
	result=notify_is_initted();
	return (jboolean)result;
}
