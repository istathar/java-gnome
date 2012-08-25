/*
 * java-gnome, a UI library for writing GTK and GNOME programs from Java!
 *
 * Copyright © 2012 Operational Dynamics Consulting, Pty Ltd
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

#include <glib.h>
#include <glib-object.h>
#include <jni.h>
#include "bindings_java.h"
#include "org_gnome_glib_GMain.h"

/*
 * Conforms to the signature requirement of (*GSourceFunc) as required by
 * the first parameter of g_idle_add().
 */
static gboolean
dispatch_callback
(
	gpointer user_data
)
{
	JNIEnv* env;
	jclass found;
	static jclass Handler = NULL;	
	static jmethodID method = NULL;
	jobject obj;
	jboolean result;

	env = bindings_java_getEnv();

	obj = (jobject) user_data;

	/*
	 * Lookup the class, cache it, and then add the closure function.
	 */
	
	if (Handler == NULL) {
		found = (*env)->FindClass(env, "org/gnome/glib/Handler");
		if (found == NULL) {
			return FALSE;
		}
		Handler = (*env)->NewGlobalRef(env, found);
	}

	if (method == NULL) {
		method = (*env)->GetMethodID(env, Handler, "run", "()Z");
		if (method == NULL) {
			return FALSE;
		}
	}

	result = (*env)->CallBooleanMethod(env, obj, method);

	return (gboolean) result;
}

/*
 * Meets signature requirement of a (*GDestroyNotify) function as passed 
 * to g_idle_add_full()'s last argument.
 */
static void
dispatch_release
(
	gpointer data
)
{
	JNIEnv* env;
	jobject obj;

	env = bindings_java_getEnv();

	obj = (jobject) data;

	(*env)->DeleteGlobalRef(env, obj);
}

/**
 * Implements
 *   org.gnome.glib.GMain.g_idle_add()
 * called from
 *   org.gnome.glib.GMain.idleAdd()
 */
JNIEXPORT void JNICALL
Java_org_gnome_glib_GMain_g_1idle_1add
(
	JNIEnv* env,
	jclass cls,
	jobject _handler
)
{
	jobject handler;

	handler = (*env)->NewGlobalRef(env, _handler);

	g_idle_add_full(G_PRIORITY_DEFAULT_IDLE, dispatch_callback, (gpointer) handler, dispatch_release);
}

