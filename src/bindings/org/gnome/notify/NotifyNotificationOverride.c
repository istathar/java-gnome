/*
 * NotifyNotificationOverride.c
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
#include "org_gnome_notify_NotifyNotificationOverride.h"

static guint signalID = 0;

static void
emit_notification
(
	NotifyNotification *notification,
	gchar *action
)
{
	g_signal_emit_by_name(NOTIFY_NOTIFICATION(notification), "action", action);
}

/*
 * Implements
 *   org.gnome.notify.NotifyNotificationOverride.notify_notification_set_hint_byte()
 * called from
 *   org.gnome.notify.Notification.setHint(short)
 */
JNIEXPORT void JNICALL Java_org_gnome_notify_NotifyNotificationOverride_notify_1notification_1set_1hint_1byte
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

	// cleanup parameter key
	(*env)->ReleaseStringUTFChars(env, _key, key);
}

/*
 * Implements
 *   org.gnome.notify.NotifyNotificationOverride.notify_notification_set_hint_byte_array()
 * called from
 *   org.gnome.notify.Notification.setHint(byte[])
 */
JNIEXPORT void JNICALL Java_org_gnome_notify_NotifyNotificationOverride_notify_1notification_1set_1hint_1byte_1array
(
	JNIEnv *env,
	jclass cls,
	jlong _self,
	jstring _key,
	jbyteArray _value
)
{
	NotifyNotification* self;
	const gchar* key;
	guchar* value;
	int size;

	// convert parameter self
	self = (NotifyNotification*) _self;

	// convert parameter key
	key = (const gchar*) (*env)->GetStringUTFChars(env, _key, NULL);
	if (key == NULL) {
		return; // Java Exception already thrown
	}

	// convert parameter value
	value = (guchar*)(*env)->GetByteArrayElements(env, _value, NULL);
	if (value == NULL) {
		return; // Java Exception already thrown
	}

	// determine array size
	size = (*env)->GetArrayLength(env, _value);

	// call function
	notify_notification_set_hint_byte_array(self, key, value, size);

	// cleanup parameter key
	(*env)->ReleaseStringUTFChars(env, _key, key);

	// cleanup parameter value
	(*env)->ReleaseByteArrayElements(env, _value, (jbyte*)value, 0);
}

/*
 * Implements
 *   org.gnome.notify.NotifyNotificationOverride.notify_notification_add_action()
 * called from
 *   org.gnome.notify.Notification.addAction(String, String, Notification.Action)
 */
JNIEXPORT void JNICALL Java_org_gnome_notify_NotifyNotificationOverride_notify_1notification_1add_1action
(
	JNIEnv *env,
	jclass cls,
	jlong _self,
	jstring _action,
	jstring _label
)
{
	NotifyNotification* self;
	const gchar* action;
	const gchar* label;

	// convert parameter self
	self = (NotifyNotification*) _self;

	// convert parameter action
	action = (const gchar*) (*env)->GetStringUTFChars(env, _action, NULL);
	if (action == NULL) {
		return; // Java Exception already thrown
	}

	// convert parameter label
	label = (const gchar*) (*env)->GetStringUTFChars(env, _label, NULL);
	if (label == NULL) {
		return; // Java Exception already thrown
	}

	if(signalID == 0) {
		signalID = g_signal_new("action",
					NOTIFY_TYPE_NOTIFICATION,
					G_SIGNAL_ACTION,
					0,
					NULL,
					NULL,
					NULL, // note 1
					G_TYPE_NONE,
					1,    // note 2
					G_TYPE_STRING);
	}
	notify_notification_add_action(self,action,label,NOTIFY_ACTION_CALLBACK(emit_notification),NULL,NULL);

	// cleanup parameter action
	(*env)->ReleaseStringUTFChars(env, _action, action);

	// cleanup parameter label
	(*env)->ReleaseStringUTFChars(env, _label, label);
}

/*
 * Implements
 *   org.gnome.notify.NotifyNotificationOverride.notify_notification_disconnect_all_actions()
 * called from
 *   org.gnome.notify.Notification.clearActions()
 */
JNIEXPORT void JNICALL Java_org_gnome_notify_NotifyNotificationOverride_notify_1notification_1disconnect_1all_1actions
(
	JNIEnv *env,
	jclass cls,
	jlong _self
)
{
	NotifyNotification* self;
	guint handlerID;

	// convert parameter self
	self = (NotifyNotification*) _self;

	while(TRUE) {
		handlerID = g_signal_handler_find(
			NOTIFY_NOTIFICATION(self),
			G_SIGNAL_MATCH_ID,
			signalID,
			0,
			NULL,
			NULL,
			NULL
		);
		if(handlerID > 0) {
			g_signal_handler_disconnect(NOTIFY_NOTIFICATION(self),handlerID);
		}
		else {
			break;
		}
	}
}
