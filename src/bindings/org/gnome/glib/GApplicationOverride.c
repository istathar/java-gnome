/*
 * java-gnome, a UI library for writing GTK and GNOME programs from Java!
 *
 * Copyright © 2011 Operational Dynamics Consulting, Pty Ltd and Others
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
 * "Claspath Exception"), the copyright holders of this library give you
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
#include <gtk/gtk.h>
#include "bindings_java.h"
#include "org_gnome_glib_GApplicationOverride.h"

/*
 * Implements
 *   org.gnome.glib.GApplicationOverride.run(String[] args)
 * called from
 *   org.gnome.glib.GApplication.run(String[] args)
 */
JNIEXPORT jint JNICALL
Java_org_gnome_glib_GApplicationOverride_g_1application_1main_1run
(
	JNIEnv *env,
	jclass cls,
	jlong _self,
	jobjectArray _args
)
{
	int i, argc, result;
	char** argv;
	jstring _arg;
	gchar* arg;
	jint _result;
	GApplication* self;

	self = (GApplication*) _self;

	// convert args
	if (_args == NULL) {
		argc = 0;
	} else {
		argc = (*env)->GetArrayLength(env, _args);
	}
	argv = (char**) g_newa(char**, argc + 1);

	for (i = 0; i < argc; i++) {
		_arg = (jstring) (*env)->GetObjectArrayElement(env, _args, i);
		arg = (char*) bindings_java_getString(env, _arg);
		argv[i + 1] = arg;
	}

	/*
	 * In C, the first element in the argv is the program name from the
	 * command line. Java skips this, so we need to re-introduce a dummy
	 * value here.
	 */
	argv[0] = "";
	argc++;

	// call function
	result = g_application_run(self, argc, argv);

	// translate return value to JNI type
	_result = (jint) result;

	// and finally
	return _result;
}
