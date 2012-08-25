/*
 * java-gnome, a UI library for writing GTK and GNOME programs from Java!
 *
 * Copyright © 2006-2010 Operational Dynamics Consulting, Pty Ltd
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

#include <jni.h>
#include <glib.h>
#include <gdk/gdk.h>
#include <gtk/gtk.h>

#include "bindings_java.h"
#include "org_gnome_gtk_GtkMain.h"

/*
 * Implements
 *   org.gnome.gtk.Gtk.gtk_init(Object lock, String[] args)
 * called from
 *   org.gnome.gtk.Gtk.init(String[] args)
 *
 * FIXME we still have to handle returning the trimmed args array.
 */
JNIEXPORT void JNICALL
Java_org_gnome_gtk_GtkMain_gtk_1init
(
	JNIEnv *env,
	jclass cls,
	jobject _lock,
	jobjectArray _args
)
{
	int argc;
	char** argv;
	gint i;
	jstring _arg;
	gchar* arg;

	/*
	 * The call to g_threads_init() needs to be be the very first thing
	 * that happens in our use of GLib; it will have happened care of
	 * the static initializer in [org.gnome.gib] Plumbing.
	 */

	// convert args
	if (_args == NULL) {
		argc = 0;
	} else {
		argc = (*env)->GetArrayLength(env, _args);
	}
	argv = (char**) g_newa(char**, argc+1);

	for (i = 0; i < argc; i++) {
		_arg = (jstring) (*env)->GetObjectArrayElement(env, _args, i);
		arg = (gchar*) bindings_java_getString(env, _arg);
		argv[i+1] = arg;
	}

	/*
	 * In C, the first element in the argv is the program name from the
	 * command line. Java skips this, so we need to re-introduce a dummy
	 * value here. This is also why it was [i+1] above.
	 */
	argv[0] = "";
	argc++;

	// call function
	gtk_init(&argc, &argv);

 	/*
	 * TODO can we release argv elements?
	 */

	/*
	 * Work around for what may be bug #85715. It appears that the root
	 * window is not given an initial Ref by GDK; if you call Window's
	 * getScreen() the resultant org.gnome.gdk.Screen's ToogleRef is the
	 * only ref count, and when we go through a garbage collection cycle
	 * the ref count drops to zero, resulting in an attempt to "destroy
	 * the root window" which, needless to say, GDK objects to somewhat
	 * vehemently.
	 */
	g_object_ref(gdk_screen_get_default());
}


/*
 * Implements
 *   org.gnome.gtk.Gtk.gtk_main()
 * called from
 *   org.gnome.gtk.Gtk.main()
 *
 * Atypically we do the necessary operations to take and release the GDK lock
 * here on the JNI side; everywhere else in the library we use a Java side
 * synchronized block. This works around a strange behaviour in Eclipse and
 * hopefully results in a better debugging experience.
 *
 * Note that the main loop implicitly uses the gdk_threads_enter/leave()
 * mechanism while spinning. This means that although the Gdk$Lock monitor is
 * held upon making this call (which blocks), the lock is released briefly
 * each time the main loop iterates.
 */
JNIEXPORT void JNICALL
Java_org_gnome_gtk_GtkMain_gtk_1main
(
	JNIEnv *env,
	jclass cls
)
{
	// call function
	gdk_threads_enter();
	gtk_main();
	gdk_threads_leave();
}

/*
 * Implements
 *   org.gnome.gtk.Gtk.gtk_main_quit()
 * called from
 *   org.gnome.gtk.Gtk.mainQuit()
 */
JNIEXPORT void JNICALL
Java_org_gnome_gtk_GtkMain_gtk_1main_1quit
(
	JNIEnv *env,
	jclass cls
)
{
	// call function
	gtk_main_quit();
}


/*
 * Implements
 *   org.gnome.gtk.Gtk.gtk_events_pending()
 * called from
 *   org.gnome.gtk.Gtk.eventsPending()
 */
JNIEXPORT jboolean JNICALL
Java_org_gnome_gtk_GtkMain_gtk_1events_1pending
(
	JNIEnv *env,
	jclass cls
)
{
	gboolean result;

	// call function
	result = gtk_events_pending();

	// return result
	return (jboolean) result;
}


/*
 * Implements
 *   org.gnome.gtk.Gtk.gtk_main_iteration_do()
 * called from
 *   org.gnome.gtk.Gtk.mainIterationDo()
 */
JNIEXPORT jboolean JNICALL
Java_org_gnome_gtk_GtkMain_gtk_1main_1iteration_1do
(
	JNIEnv *env,
	jclass cls,
	jboolean _blocking
)
{
	gboolean blocking;
	gboolean result;

	// translate blocking
	blocking = (gboolean) _blocking;

	// call function
	result = gtk_main_iteration_do(blocking);

	// clean up blocking

	// return result
	return (jboolean) result;
}

/*
 * Implements
 *   org.gnome.gtk.Gtk.gtk_show_uri()
 * called from
 *   org.gnome.gtk.Gtk.showURI()
 */
JNIEXPORT jboolean JNICALL
Java_org_gnome_gtk_GtkMain_gtk_1show_1uri
(
	JNIEnv *env,
	jclass cls,
	jstring _uri
)
{
	const gchar* uri;
	gboolean result;
	GError* error = NULL;

	// convert parameter uri
	uri = bindings_java_getString(env, _uri);
	if (uri == NULL) {
		return FALSE; // Java Exception already thrown
	}

	// call function
	result = gtk_show_uri(NULL, uri, GDK_CURRENT_TIME, &error);

	// cleanup parameter uri
	bindings_java_releaseString(uri);

	// check for error
	if (error) {
		bindings_java_throwGlibException(env, error);
		return FALSE;
	}

	// return result
	return (jboolean) result;
}

