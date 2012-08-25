/*
 * java-gnome, a UI library for writing GTK and GNOME programs from Java!
 *
 * Copyright © 2009-2010 Operational Dynamics Consulting, Pty Ltd
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

#include <libnotify/notify.h>
#include "bindings_java.h"
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
 *   org.gnome.notify.Notification.setHint(byte)
 */
JNIEXPORT void JNICALL Java_org_gnome_notify_NotifyNotificationOverride_notify_1notification_1set_1hint_1byte
(
	JNIEnv *env,
	jclass cls,
	jlong _self,
	jstring _key,
	jbyte _value
)
{
	NotifyNotification* self;
	const gchar* key;
	gshort value;

	// convert parameter self
	self = (NotifyNotification*) _self;

	// convert parameter key
	key = bindings_java_getString(env, _key);
	if (key == NULL) {
		return; // Java Exception already thrown
	}

	// convert parameter value
	value = (guchar) _value;

	// call function
	notify_notification_set_hint_byte(self, key, value);

	// cleanup parameter key
	bindings_java_releaseString(key);
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
	key = bindings_java_getString(env, _key);
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
	bindings_java_releaseString(key);

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
	action = bindings_java_getString(env, _action);
	if (action == NULL) {
		return; // Java Exception already thrown
	}

	// convert parameter label
	label = bindings_java_getString(env, _label);
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
	bindings_java_releaseString(action);

	// cleanup parameter label
	bindings_java_releaseString(label);
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
