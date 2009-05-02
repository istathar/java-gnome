/*
 * NotificationOverride.c
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
JNIEXPORT void JNICALL Java_org_gnome_notify_NotificationOverride_notify_1notification_1set_1hint_1byte
(
	JNIEnv *env,
	jclass cls,
	jlong _self,
	jstring _key,
	jshort _value
)
{
	NotifyNotification* self;
		const gchar* key;
		gshort value;

		// convert parameter self
		self = (NotifyNotification*) _self;

		// convert parameter key
		key = (const gchar*) (*env)->GetStringUTFChars(env, _key, NULL);
		if (key == NULL) {
			return; // Java Exception already thrown
		}

		// convert parameter value
		value = (gshort) _value;

		// call function
		notify_notification_set_hint_byte(self, key, (guchar)value);

		// cleanup parameter self

		// cleanup parameter key
		(*env)->ReleaseStringUTFChars(env, _key, key);

		// cleanup parameter value
}
