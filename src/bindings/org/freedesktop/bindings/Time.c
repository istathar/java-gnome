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

/*
 * This code imported from the SlashTime project's wrapper around the C time
 * functions at src/java/com/operationaldynamics/slashtime/Time.c
 */

#include <jni.h>
#include <stdlib.h>
#include <time.h>
#include <glib.h>
#include "bindings_java.h"
#include "org_freedesktop_bindings_Time.h"

#define MAXWIDTH 64


JNIEXPORT void JNICALL
Java_org_freedesktop_bindings_Time_tzset
(
	JNIEnv *env,
	jclass cls,
	jstring _zoneinfo
)
{
	/*
	 * Carry out the magic to switch zones by calling tzset(). It doesn't
	 * have parameters - it pulls TZ from the environment.
	 */

	const char *zoneinfo;
	int ok;

	zoneinfo = bindings_java_getString(env, _zoneinfo);
	if (zoneinfo == NULL) {
		return; /* OutOfMemoryError already thrown */
	}

	ok = g_setenv("TZ", zoneinfo, 1);

	bindings_java_releaseString(zoneinfo);
	if (ok != 0) {
		// throw exception
		return;
	}

	tzset();
}

JNIEXPORT jstring JNICALL
Java_org_freedesktop_bindings_Time_strftime
(
	JNIEnv *env,
	jclass klass,
	jstring _format,
	jlong _timestamp
)
{
	/*
	 * Call strftime() to generate the string in the desired format. We
	 * pass in size as the max, and the return value indicates how much was
	 * used.
	 */

	const char *format;
	size_t size;
	char buf[MAXWIDTH];
	struct tm *brokendown;
	time_t timestamp;

	size = MAXWIDTH;

	format = bindings_java_getString(env, _format);
	if (format == NULL) {
		return NULL; /* OutOfMemoryError already thrown */
	}

	timestamp = (time_t) _timestamp;

	brokendown = localtime(&timestamp);

	size = strftime(buf, size, format, brokendown);

	bindings_java_releaseString(format);
	if (size == 0) {
		// throw exception instead!
		return bindings_java_newString(env, "Nothing returned!\0");
	}

	return bindings_java_newString(env, buf);
}

JNIEXPORT jlong JNICALL
Java_org_freedesktop_bindings_Time_mktime
(
	JNIEnv *env,
	jclass cls,
	jint _year,
	jint _month,
	jint _day,
	jint _hour,
	jint _minute,
	jint _second
)
{
	struct tm brokendown = { 0, };
	time_t timestamp;

	brokendown.tm_year = _year - 1900;
	brokendown.tm_mon = _month - 1;
	brokendown.tm_mday = _day;
	brokendown.tm_hour = _hour;
	brokendown.tm_min = _minute;
	brokendown.tm_sec = _second;

	timestamp = mktime(&brokendown);

#ifdef DEBUG
	fprintf(stderr, "JNI: %s\n", g_getenv("TZ"));
	size_t size;
	char buf[MAXWIDTH];
	strftime(buf, size, "%a, %d %b %Y %H:%M:%S %z %Z", localtime(&timestamp));
	fprintf(stderr, "JNI: %d; %s and %d\n", (int) timestamp, buf, brokendown.tm_isdst);
	fflush(stderr);
#endif

	/*
	 * Bizarre bug that mktime adds an hour of DST to the displayed time if in DST.
	 */
	if (brokendown.tm_isdst == 1) {
		timestamp -= 3600;
	}
		
	return (jlong) timestamp;
}
