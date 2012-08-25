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
#include <jni.h>
#include "bindings_java.h"
#include "org_gnome_notify_NotifyMainOverride.h"

/*
 * Implements
 *   org.gnome.notify.NotifyMainOverride.notify_get_server_caps()
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
	jclass String;
	GList* caps;
	GList* iter;
	jstring cap;

	caps = notify_get_server_caps();

	if (caps == NULL) {
		size = 0;
	} else {
		size = g_list_length(caps);
	}

	String = (*env)->FindClass(env, "java/lang/String");
	if (String == NULL) {
		// exception already thrown
		return NULL;
	}

	_array = (*env)->NewObjectArray(env, size, String, NULL);
	if (_array == NULL) {
		// exception already thrown
		return NULL;
	}

	iter = caps;

	for (i = 0; i < size; ++i) {
		// Hopefully capability strings are ASCII only.
		cap = bindings_java_newString(env, iter->data);
		(*env)->SetObjectArrayElement(env, _array, i, cap);
		g_free(iter->data);
		iter = iter->next;
	}

	if (caps != NULL) {
		g_list_free(caps);
	}

	return _array;
}
