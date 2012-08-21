/*
 * java-gnome, a UI library for writing GTK and GNOME programs from Java!
 *
 * Copyright © 2008-2010 Operational Dynamics Consulting, Pty Ltd
 * Copyright © 2008      Vreixo Formoso
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

#include <libintl.h>
#include <locale.h>
#include <jni.h>
#include "bindings_java.h"
#include "org_freedesktop_bindings_Internationalization.h"

/**
 * Implements
 *   org.freedesktop.bindings.Internationalization.gettext(String msg)
 * called from
 *   org.freedesktop.bindings.Internationalization._(String msg, java.lang.Object ...parameters)
 */
JNIEXPORT jstring JNICALL
Java_org_freedesktop_bindings_Internationalization_gettext
(
	JNIEnv *env,
	jclass cls,
	jstring _msg
)
{	
	const char* msg;
	char* result;

	// convert parameter msg
	msg = bindings_java_getString(env, _msg);
	if (msg == NULL) {
		return NULL; // expeption already thrown
	}

	// call function
	result = gettext(msg);

	/*
	 * If there was no translation, so just return the input String, This
	 * avoids corrupting the statically allocated char* returned by
	 * gettext().
	 *
	 * If there is a translation, allocate a new String for it, and
	 * return it.
	 */

	// convert and return
	if (result == msg) {
		bindings_java_releaseString(msg);
		return _msg;
	} else {
		bindings_java_releaseString(msg);
		return bindings_java_newString(env, result);
	}
}

/**
 * Implements
 *   org.freedesktop.bindings.Internationalization.dgettext(String domain, String name)
 * called from
 *   org.freedesktop.bindings.Internationalization._country(String name)
 * and
 *   org.freedesktop.bindings.Internationalization._language(String name)
 */
JNIEXPORT jstring JNICALL
Java_org_freedesktop_bindings_Internationalization_dgettext
(
	JNIEnv *env,
	jclass cls,
	jstring _domain,
	jstring _name
)
{	
	const char* domain;
	const char* name;
	char* result;

	// convert parameter name
	domain = bindings_java_getString(env, _domain);
	if (domain == NULL) {
		return NULL; // expeption already thrown
	}

	// convert parameter name
	name = bindings_java_getString(env, _name);
	if (name == NULL) {
		return NULL; // expeption already thrown
	}

	// call function
	result = dgettext(domain, name);

	/*
	 * See comment in gettext() above
	 */

	// convert and return
	if (result == name) {
		bindings_java_releaseString(name);
		return _name;
	} else {
		bindings_java_releaseString(name);
		return bindings_java_newString(env, result);
	}
}

JNIEXPORT void JNICALL
Java_org_freedesktop_bindings_Internationalization_bindtextdomain
(
	JNIEnv *env,
	jclass cls,
	jstring _packageName,
	jstring _localeDir
)
{
	const char* packageName;
	const char* localeDir;

	// convert parameter packageName
	packageName = bindings_java_getString(env, _packageName);
	if (packageName == NULL) {
		return; // expeption already throw
	}

	// convert parameter localeDir
	localeDir = bindings_java_getString(env, _localeDir);
	if (localeDir == NULL) {
		return; // expeption already throw
	}

	/*
	 * Initialize internationalization and localization libraries. The
	 * second argument to setlocale() being "" means to pull settings
	 * from the environment.
	 */

	if (setlocale(LC_ALL, "") == NULL) {
		bindings_java_throw(env, "\nCall to setlocale() to initialize the program's locale failed");
		return;
	}
	if (bindtextdomain(packageName, localeDir) == NULL) {
		bindings_java_throw(env, "\nCall to bindtextdomain() to set the locale base dir failed");
		return;
	}
	if (bind_textdomain_codeset(packageName, "UTF-8") == NULL) {
		bindings_java_throw(env, "\nCall to bind_textdomain_codeset() to set UTF-8 failed");
		return;
	}
	if (textdomain(packageName) == NULL) {
		bindings_java_throw(env, "\nCall to textdomain() to set message source failed");
		return;
	}

	// cleanup parameter packageName
	bindings_java_releaseString(packageName);

	// cleanup parameter localeDir
	bindings_java_releaseString(localeDir);
}
