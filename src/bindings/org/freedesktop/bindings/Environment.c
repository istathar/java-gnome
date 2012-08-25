/*
 * java-gnome, a UI library for writing GTK and GNOME programs from Java!
 *
 * Copyright © 2007-2011 Operational Dynamics Consulting, Pty Ltd and Others
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
#include <stdlib.h>
#include <errno.h>
#include "bindings_java.h"
#include "org_freedesktop_bindings_Environment.h"

/*
 * Implements
 *   org.freedesktop.bindings.Environment.getenv(String variableName)
 */
JNIEXPORT jobject JNICALL
Java_org_freedesktop_bindings_Environment_getenv
(
	JNIEnv *env,
	jclass cls,
	jstring _name
)
{
	const gchar* name;
	gchar* result;

	// convert parameter name
	name = bindings_java_getString(env, _name);
	if (name == NULL) {
		return NULL; /* OutOfMemoryError already thrown */
	}

	// call function
	result = (gchar*) g_getenv(name);

	// clean up name
	bindings_java_releaseString(name);

	// and return	
	return bindings_java_newString(env, result);
}


/*
 * Implements
 *   org.freedesktop.bindings.Environment.setenv(String variableName, String value)
 */
JNIEXPORT void  JNICALL
Java_org_freedesktop_bindings_Environment_setenv
(
	JNIEnv *env,
	jclass cls,
	jstring _name,
	jstring _value
)
{
	const gchar* name;
	const gchar* value;

	// convert parameter name
	name = bindings_java_getString(env, _name);
	if (name == NULL) {
		return; /* OutOfMemoryError already thrown */
	}

	// convert parameter value
	value =  bindings_java_getString(env, _value);
	if (value == NULL) {
		return; /* OutOfMemoryError already thrown */
	}

	// call function
	if (g_setenv(name, value, 1) == -1) {
		bindings_java_throw(env, "\nsetenv() failed: Insufficient space in environment");
	}
	
	// clean up name
	bindings_java_releaseString(name);

	// clean up name
	bindings_java_releaseString(value);
}


/*
 * Implements
 *   org.freedesktop.bindings.Environment.unsetenv(String variableName)
 */
JNIEXPORT void  JNICALL
Java_org_freedesktop_bindings_Environment_unsetenv
(
	JNIEnv *env,
	jclass cls,
	jstring _name
)
{
	const gchar* name;

	// convert parameter name
	name = bindings_java_getString(env, _name);
	if (name == NULL) {
		return; /* OutOfMemoryError already thrown */
	}

	// call function
	g_unsetenv(name);

	// clean up name
	bindings_java_releaseString(name);
}




/*
 * Will implement
 *   org.freedesktop.bindings.Environment.getWidth()
 */
JNIEXPORT jint JNICALL
Java_org_freedesktop_bindings_Environment_getWidth
(
	JNIEnv *env,
	jclass cls
)
{
	// TODO how can we do this?
	return (jint) 0;
}

/*
 * Implements
 *   org.freedesktop.bindings.Environment.getpid()
 */
JNIEXPORT jint JNICALL
Java_org_freedesktop_bindings_Environment_getpid
(
	JNIEnv *env,
	jclass cls
)
{
	return getpid();
}

/*
 * Implements
 *   org.freedesktop.bindings.Environment.isatty(int fd)
 */
JNIEXPORT jint JNICALL
Java_org_freedesktop_bindings_Environment_isatty
(
        JNIEnv *env,
        jclass cls,
        jint _fd
)
{
        return isatty(_fd);
}
